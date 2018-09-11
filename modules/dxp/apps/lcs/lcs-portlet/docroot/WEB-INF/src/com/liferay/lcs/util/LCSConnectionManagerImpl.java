/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.lcs.util;

import com.liferay.lcs.advisor.LCSAlertAdvisor;
import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.advisor.UptimeMonitoringAdvisor;
import com.liferay.lcs.exception.CompressionException;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.scheduler.MessageListenerSchedulerService;
import com.liferay.lcs.runnable.LCSConnectorRunnable;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.task.CommandMessageTask;
import com.liferay.lcs.task.HandshakeTask;
import com.liferay.lcs.task.HeartbeatTask;
import com.liferay.lcs.task.SignOffTask;
import com.liferay.lcs.task.UptimeMonitoringTask;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.license.messaging.LCSPortletState;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.lang.ref.WeakReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class LCSConnectionManagerImpl implements LCSConnectionManager {

	public LCSConnectionManagerImpl(
		LCSAlertAdvisor lcsAlertAdvisor,
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		LCSGatewayService lcsGatewayService, LCSKeyAdvisor lcsKeyAdvisor,
		MessageListenerSchedulerService messageListenerSchedulerService,
		TaskSchedulerService taskSchedulerService, ThreadFactory threadFactory,
		UptimeMonitoringAdvisor uptimeMonitoringAdvisor) {

		_heartbeatInterval = GetterUtil.getLong(
			PortletPropsValues.COMMUNICATION_HEARTBEAT_INTERVAL);

		_lcsConnectionMetadata.put(
			"handshakeWaitTime",
			PortletPropsValues.COMMUNICATION_HANDSHAKE_WAIT_TIME);
		_lcsConnectionMetadata.put(
			"heartbeatInterval",
			PortletPropsValues.COMMUNICATION_HEARTBEAT_INTERVAL);

		_lcsAlertAdvisor = lcsAlertAdvisor;
		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_lcsGatewayService = lcsGatewayService;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_messageListenerSchedulerService = messageListenerSchedulerService;

		_scheduledExecutorService = Executors.newScheduledThreadPool(
			10, threadFactory);

		_taskSchedulerService = taskSchedulerService;
		_threadFactory = threadFactory;
		_uptimeMonitoringAdvisor = uptimeMonitoringAdvisor;
	}

	@Override
	public void deleteMessages(String key) throws JSONWebServiceException {
		try {
			_lcsGatewayService.deleteMessages(key);
		}
		catch (JSONWebServiceException jsonwse) {
			_processJSONWebServiceException(jsonwse);
		}
	}

	public void destroy() {
		if (_log.isTraceEnabled()) {
			_log.trace("Destroying " + this);
		}

		_shutdownRequested = true;

		LCSConnectorRunnable lcsConnectorRunnable =
			_lcsConnectorRunnableWeakReference.get();

		if (lcsConnectorRunnable != null) {
			lcsConnectorRunnable.stop();
		}

		LCSUtil.processLCSPortletState(LCSPortletState.PLUGIN_ABSENT);

		Future<?> future = stop(false, false, true);

		try {
			if (future != null) {
				future.get();
			}
		}
		catch (Exception e) {
			_log.error("Unable to stop communication with LCS gateway", e);
		}

		if (ClusterExecutorUtil.isEnabled()) {
			try {
				while (!_scheduledFutures.isEmpty()) {
					Thread.sleep(1000);
				}

				Thread.sleep(1000);
			}
			catch (InterruptedException ie) {
				if (_log.isTraceEnabled()) {
					_log.trace(
						"Interrupted while waiting for scheduled features to " +
							"complete");
				}
			}
		}

		_scheduledExecutorService.shutdown();

		try {
			if (!_scheduledExecutorService.awaitTermination(
					5, TimeUnit.SECONDS)) {

				_scheduledExecutorService.shutdownNow();
			}
		}
		catch (InterruptedException ie) {
			_scheduledExecutorService.shutdownNow();
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Destroyed " + this);
		}
	}

	@Override
	public Map<String, String> getLCSConnectionMetadata() {
		return _lcsConnectionMetadata;
	}

	@Override
	public List<Message> getMessages(String key)
		throws JSONWebServiceException {

		try {
			return _lcsGatewayService.getMessages(key);
		}
		catch (JSONWebServiceException jsonwse) {
			_processJSONWebServiceException(jsonwse);
		}

		return Collections.emptyList();
	}

	@Override
	public boolean isLCSGatewayAvailable() {
		return _lcsGatewayAvailable;
	}

	@Override
	public synchronized boolean isReady() {
		return _ready;
	}

	@Override
	public boolean isShutdownRequested() {
		return _shutdownRequested;
	}

	@Override
	public void onHandshakeSuccess() {
		if (_log.isTraceEnabled()) {
			_log.trace("Handshake success");
		}

		setLCSGatewayAvailable(true);
		setReady(true);

		LCSUtil.processLCSPortletState(LCSPortletState.NO_SUBSCRIPTION);

		_lcsConnectionMetadata.put(
			"handshakeTime", String.valueOf(System.currentTimeMillis()));
		_lcsConnectionMetadata.put(
			"jvmMetricsTaskInterval", String.valueOf(60000));
		_lcsConnectionMetadata.put(
			"messageTaskInterval", String.valueOf(10000));

		if (_log.isTraceEnabled()) {
			_log.trace("Scheduling command message task");
		}

		_scheduledFutures.add(
			_scheduledExecutorService.scheduleAtFixedRate(
				new CommandMessageTask(_lcsKeyAdvisor.getKey(), this),
				LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD,
				LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD,
				TimeUnit.SECONDS));

		if (_log.isTraceEnabled()) {
			_log.trace("Scheduling heartbeat task");
		}

		_scheduledFutures.add(
			_scheduledExecutorService.scheduleAtFixedRate(
				new HeartbeatTask(_lcsKeyAdvisor.getKey(), this), 10000L,
				_heartbeatInterval, TimeUnit.MILLISECONDS));

		_lcsAlertAdvisor.clear();

		_lcsAlertAdvisor.add(LCSAlert.SUCCESS_CONNECTION_TO_LCS_VALID);
	}

	@Override
	public void onPortletDeployed() {
		try {
			_uptimeMonitoringAdvisor.init();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_scheduleUptimeMonitoringTask();

		_executeLCSConnectorRunnable(false);
	}

	@Override
	public void putLCSConnectionMetadata(String key, String value) {
		_lcsConnectionMetadata.put(key, value);
	}

	@Override
	public void sendMessage(Message message)
		throws CompressionException, JSONWebServiceException {

		try {
			_lcsGatewayService.sendMessage(message);

			_lcsConnectionMetadata.put(
				"lastMessageSent", String.valueOf(System.currentTimeMillis()));
		}
		catch (JSONWebServiceException jsonwse) {
			_processJSONWebServiceException(jsonwse);
		}
	}

	@Override
	public void setLCSGatewayAvailable(boolean lcsGatewayAvailable) {
		_lcsGatewayAvailable = lcsGatewayAvailable;
	}

	@Override
	public synchronized void setReady(boolean ready) {
		_ready = ready;
	}

	@Override
	public void setShutdownRequested(boolean shutdownRequested) {
		_shutdownRequested = shutdownRequested;
	}

	@Override
	public Future<?> start() {
		if (isReady()) {
			return null;
		}

		return _scheduledExecutorService.submit(
			new HandshakeTask(
				_lcsClusterEntryTokenAdvisor.getLcsClusterEntryTokenId(),
				_lcsAlertAdvisor, this, _lcsKeyAdvisor, _threadFactory,
				_uptimeMonitoringAdvisor));
	}

	@Override
	public Future<?> stop(
		boolean delayReconnect, boolean reconnect,
		boolean serverManuallyShutdown) {

		if (!isReady()) {
			return null;
		}

		setReady(false);

		_cancelSchedulers();

		SignOffTask signOffTask = new SignOffTask(
			_lcsKeyAdvisor.getKey(), this);

		signOffTask.setServerManuallyShutdown(serverManuallyShutdown);

		Future<?> future = _scheduledExecutorService.submit(signOffTask);

		LCSUtil.processLCSPortletState(LCSPortletState.NO_CONNECTION);

		if (reconnect) {
			_executeLCSConnectorRunnable(delayReconnect);
		}

		return future;
	}

	protected synchronized void handleLCSGatewayUnavailable() {
		if (!isLCSGatewayAvailable()) {
			return;
		}

		setLCSGatewayAvailable(false);

		if (!isReady()) {
			return;
		}

		setReady(false);

		LCSUtil.processLCSPortletState(LCSPortletState.NO_CONNECTION);

		_cancelSchedulers();

		_executeLCSConnectorRunnable(true);
	}

	private void _cancelSchedulers() {
		_messageListenerSchedulerService.unscheduleAllMessageListeners();

		_taskSchedulerService.unscheduleAllTasks();

		for (ScheduledFuture<?> scheduledFuture : _scheduledFutures) {
			while (!scheduledFuture.isCancelled()) {
				scheduledFuture.cancel(true);
			}
		}

		_scheduledFutures.clear();

		if (_log.isDebugEnabled()) {
			_log.debug("All LCS schedulers canceled");
		}
	}

	private void _executeLCSConnectorRunnable(boolean delayRun) {
		LCSConnectorRunnable lcsConnectorRunnable = new LCSConnectorRunnable(
			delayRun);

		lcsConnectorRunnable.setLCSAlertAdvisor(_lcsAlertAdvisor);
		lcsConnectorRunnable.setLCSClusterEntryTokenAdvisor(
			_lcsClusterEntryTokenAdvisor);
		lcsConnectorRunnable.setLCSConnectionManager(this);

		_scheduledExecutorService.execute(lcsConnectorRunnable);

		if (_lcsConnectorRunnableWeakReference != null) {
			_lcsConnectorRunnableWeakReference.clear();
		}

		_lcsConnectorRunnableWeakReference = new WeakReference<>(
			lcsConnectorRunnable);

		if (_log.isTraceEnabled()) {
			_log.trace(lcsConnectorRunnable + " scheduled");
		}
	}

	private void _processJSONWebServiceException(
			JSONWebServiceException jsonwse)
		throws JSONWebServiceException {

		if (_log.isDebugEnabled()) {
			_log.debug(jsonwse.getMessage(), jsonwse);
		}

		if (_shutdownRequested) {
			if (_log.isTraceEnabled()) {
				_log.trace("Shut down requested. Skip exception handler.");
			}

			return;
		}

		if (jsonwse instanceof JSONWebServiceTransportException) {
			if (_log.isWarnEnabled()) {
				_log.warn("LCS gateway is unavailable");
			}

			if (jsonwse.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"LCS portlet is not authorized to access LCS " +
							"gateway. Will attempt to reauthorize.");
				}
			}

			handleLCSGatewayUnavailable();
		}

		throw jsonwse;
	}

	private void _scheduleUptimeMonitoringTask() {
		UptimeMonitoringTask uptimeMonitoringTask = new UptimeMonitoringTask();

		uptimeMonitoringTask.setUptimeMonitoringAdvisor(
			_uptimeMonitoringAdvisor);

		_scheduledExecutorService.scheduleAtFixedRate(
			uptimeMonitoringTask, 1, 1, TimeUnit.MINUTES);

		if (_log.isTraceEnabled()) {
			_log.trace(uptimeMonitoringTask.toString() + " scheduled");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSConnectionManagerImpl.class);

	private final long _heartbeatInterval;
	private final LCSAlertAdvisor _lcsAlertAdvisor;
	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final Map<String, String> _lcsConnectionMetadata = new HashMap<>();
	private WeakReference<LCSConnectorRunnable>
		_lcsConnectorRunnableWeakReference;
	private volatile boolean _lcsGatewayAvailable;
	private final LCSGatewayService _lcsGatewayService;
	private final LCSKeyAdvisor _lcsKeyAdvisor;
	private final MessageListenerSchedulerService
		_messageListenerSchedulerService;
	private volatile boolean _ready;
	private final ScheduledExecutorService _scheduledExecutorService;
	private final List<ScheduledFuture<?>> _scheduledFutures =
		new ArrayList<>();
	private volatile boolean _shutdownRequested;
	private final TaskSchedulerService _taskSchedulerService;
	private final ThreadFactory _threadFactory;
	private final UptimeMonitoringAdvisor _uptimeMonitoringAdvisor;

}