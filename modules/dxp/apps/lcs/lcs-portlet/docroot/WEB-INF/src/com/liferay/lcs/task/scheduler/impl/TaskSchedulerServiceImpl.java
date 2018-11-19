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

package com.liferay.lcs.task.scheduler.impl;

import com.liferay.lcs.advisor.LCSAlertAdvisor;
import com.liferay.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.advisor.UptimeAdvisor;
import com.liferay.lcs.internal.event.LCSEvent;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.task.CommandMessageTask;
import com.liferay.lcs.task.HandshakeTask;
import com.liferay.lcs.task.HeartbeatTask;
import com.liferay.lcs.task.LCSClusterEntryTokenCheckTask;
import com.liferay.lcs.task.ScheduledTask;
import com.liferay.lcs.task.SignOffTask;
import com.liferay.lcs.task.UptimeTask;
import com.liferay.lcs.task.advisor.TaskAdvisor;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Riccardo Ferrari
 * @author Igor Beslic
 */
public class TaskSchedulerServiceImpl implements TaskSchedulerService {

	public TaskSchedulerServiceImpl(
		int defaultInterval, LCSAlertAdvisor lcsAlertAdvisor,
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor,
		TaskAdvisor taskAdvisor, ThreadFactory threadFactory,
		UptimeAdvisor uptimeAdvisor) {

		_defaultInterval = defaultInterval;
		_lcsAlertAdvisor = lcsAlertAdvisor;
		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_taskAdvisor = taskAdvisor;
		_threadFactory = threadFactory;
		_uptimeAdvisor = uptimeAdvisor;

		_lcsGatewayClient.registerLCSEventListener(this);

		_scheduledExecutorService = Executors.newScheduledThreadPool(
			10, threadFactory);
	}

	public void destroy() {
		if (_log.isInfoEnabled()) {
			_log.info("Destroying " + this);
		}

		_shutdownPending = true;

		_cancelAllTasks();

		_executeSignOffTask();

		if (_uptimeTaskScheduledFuture != null) {
			_uptimeTaskScheduledFuture.cancel(true);
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
	public void onLCSEvent(LCSEvent lcsEvent) {
		if (_shutdownPending) {
			if (_log.isTraceEnabled()) {
				_log.trace(
					"Shutdown pending. No action is taken for LCS event " +
						lcsEvent);
			}

			return;
		}

		if (lcsEvent == LCSEvent.LCS_GATEWAY_AVAILABLE) {
			_onLCSGatewayServiceAvailable();
		}
		else if (lcsEvent == LCSEvent.LCS_GATEWAY_UNAVAILABLE) {
			_onLCSGatewayServiceUnavailable();
		}
		else if ((lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED) ||
			(lcsEvent ==
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED) ||
			(lcsEvent ==
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID) ||
			(lcsEvent ==
				LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING) ||
			(lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS)) {

			_executeLCSClusterEntryTokenCheckTask(true);
		}
		else if (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS) {
			_executeHandshakeTask(false);
		}
		else if ((lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED) ||
				 (lcsEvent == LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED) ||
				 (lcsEvent == LCSEvent.SIGNOFF_REQUESTED)) {

			_restart();
		}
		else if (lcsEvent == LCSEvent.HANDSHAKE_FAILED) {
			_executeHandshakeTask(true);
		}
	}

	@Override
	public void scheduleTask(Map<String, String> schedulerContext) {
		BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
			"lcs-portlet");

		String taskName = schedulerContext.get("taskName");

		try {
			ScheduledTask scheduledTask = (ScheduledTask)beanLocator.locate(
				taskName);

			if (scheduledTask == null) {
				if (_log.isDebugEnabled()) {
					_log.debug("Unable to find task " + taskName);
				}

				return;
			}

			scheduleLocalScheduledTask(schedulerContext);

			_taskAdvisor.registerActivity(scheduledTask);
		}
		catch (Exception e) {
			StringBundler sb = new StringBundler(4);

			sb.append("Unable to create ");
			sb.append(taskName);
			sb.append(". This is likely because Liferay Connected Services ");
			sb.append("does not support such task for this app server.");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString(), e);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(sb.toString());
			}
		}
	}

	@Override
	public void start() {
		_scheduleUptimeMonitoringTask();

		_executeLCSClusterEntryTokenCheckTask(false);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isInfoEnabled()) {
			_log.info("Finalized " + this);
		}
	}

	protected int getInterval(Map<String, String> schedulerContext) {
		String interval = schedulerContext.get("interval");

		return GetterUtil.getInteger(interval, _defaultInterval);
	}

	protected void scheduleLocalScheduledTask(
		Map<String, String> schedulerContext) {

		int interval = getInterval(schedulerContext);

		String taskName = schedulerContext.get("taskName");

		BeanLocator beanLocator = PortletBeanLocatorUtil.getBeanLocator(
			"lcs-portlet");

		ScheduledTask scheduledTask = (ScheduledTask)beanLocator.locate(
			taskName);

		_scheduledFuturesMap.put(
			taskName,
			_scheduledExecutorService.scheduleAtFixedRate(
				scheduledTask, interval, interval, TimeUnit.SECONDS));

		if (_log.isDebugEnabled()) {
			_log.debug("Scheduled task " + taskName);
		}
	}

	private void _cancelAllTasks() {
		_taskAdvisor.reset();

		_cancelLocalScheduledTasks();
	}

	private void _cancelLocalScheduledTasks() {
		for (String taskName : _scheduledFuturesMap.keySet()) {
			ScheduledFuture<?> scheduledFuture = _scheduledFuturesMap.get(
				taskName);

			while (!scheduledFuture.isCancelled()) {
				scheduledFuture.cancel(true);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Canceled task " + taskName);
			}
		}

		_scheduledFuturesMap.clear();
	}

	private void _executeHandshakeTask(boolean delayRun) {
		if (_shutdownPending) {
			if (_log.isTraceEnabled()) {
				_log.trace("Shutdown pending. Aborting handshake task.");
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("LCS portlet is not connected. Retry in 60 seconds.");
		}

		HandshakeTask handshakeTask = new HandshakeTask(
			_lcsClusterEntryTokenAdvisor.getLcsClusterEntryTokenId(),
			_lcsAlertAdvisor, _lcsClusterEntryTokenAdvisor, _lcsGatewayClient,
			_lcsKeyAdvisor, this, _threadFactory, _uptimeAdvisor);

		if (delayRun) {
			_scheduledExecutorService.schedule(
				handshakeTask, 60, TimeUnit.SECONDS);
		}
		else {
			_scheduledExecutorService.submit(handshakeTask);
		}
	}

	private void _executeLCSClusterEntryTokenCheckTask(boolean delayRun) {
		if (_shutdownPending) {
			if (_log.isTraceEnabled()) {
				_log.trace("Shutdown pending. Aborting AATF check task.");
			}

			return;
		}

		if (delayRun) {
			if (_log.isInfoEnabled()) {
				_log.info("LCS portlet is not connected. Retry in 60 seconds.");
			}
		}

		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask =
			new LCSClusterEntryTokenCheckTask(
				_lcsAlertAdvisor, _lcsClusterEntryTokenAdvisor, this);

		if (delayRun) {
			_scheduledExecutorService.schedule(
				lcsClusterEntryTokenCheckTask, 60, TimeUnit.SECONDS);
		}
		else {
			_scheduledExecutorService.submit(lcsClusterEntryTokenCheckTask);
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Executed " + lcsClusterEntryTokenCheckTask);
		}
	}

	private void _executeSignOffTask() {
		if (_signOffPending) {
			return;
		}

		_signOffPending = true;

		SignOffTask signOffTask = new SignOffTask(
			_lcsKeyAdvisor.getKey(), _lcsGatewayClient, true);

		Future<?> future = _scheduledExecutorService.submit(signOffTask);

		while (!future.isDone()) {
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException ie) {
				if (_log.isWarnEnabled()) {
					_log.warn("Interrupted while waiting for SignOff task");
				}
				else if (_log.isDebugEnabled()) {
					_log.debug(
						"Interrupted while waiting for SignOff task", ie);
				}
			}
		}

		_signOffPending = false;

		if (_log.isTraceEnabled()) {
			_log.trace("Sign off executed");
		}
	}

	private void _onLCSGatewayServiceAvailable() {
		if (_log.isTraceEnabled()) {
			_log.trace("Scheduling command message task");
		}

		Class<?> clazz = CommandMessageTask.class;

		_scheduledFuturesMap.put(
			clazz.getName(),
			_scheduledExecutorService.scheduleAtFixedRate(
				new CommandMessageTask(
					_lcsKeyAdvisor.getKey(), _lcsGatewayClient),
				LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD,
				LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD,
				TimeUnit.SECONDS));

		if (_log.isTraceEnabled()) {
			_log.trace("Scheduling heartbeat task");
		}

		clazz = HeartbeatTask.class;

		_scheduledFuturesMap.put(
			clazz.getName(),
			_scheduledExecutorService.scheduleAtFixedRate(
				new HeartbeatTask(_lcsKeyAdvisor.getKey(), _lcsGatewayClient),
				10000L,
				GetterUtil.getLong(
					PortletPropsValues.COMMUNICATION_HEARTBEAT_INTERVAL),
				TimeUnit.MILLISECONDS));
	}

	private void _onLCSGatewayServiceUnavailable() {
		if (_signOffPending) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Skip connection recovery while SignOffTask is running");
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("LCS Gateway unavailable. Start connection recovery.");
		}

		_cancelAllTasks();

		_executeLCSClusterEntryTokenCheckTask(false);
	}

	private void _restart() {
		if (_log.isDebugEnabled()) {
			_log.debug("Restarting LCS life cycle");
		}

		_cancelAllTasks();

		_executeSignOffTask();

		_executeLCSClusterEntryTokenCheckTask(true);
	}

	private void _scheduleUptimeMonitoringTask() {
		try {
			_uptimeAdvisor.init();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		UptimeTask uptimeTask = new UptimeTask();

		_uptimeTaskScheduledFuture =
			_scheduledExecutorService.scheduleAtFixedRate(
				uptimeTask, 1, 1, TimeUnit.MINUTES);

		if (_log.isTraceEnabled()) {
			_log.trace(uptimeTask.toString() + " scheduled");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TaskSchedulerServiceImpl.class);

	private final int _defaultInterval;
	private final LCSAlertAdvisor _lcsAlertAdvisor;
	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final LCSGatewayClient _lcsGatewayClient;
	private final LCSKeyAdvisor _lcsKeyAdvisor;
	private final ScheduledExecutorService _scheduledExecutorService;
	private final Map<String, ScheduledFuture<?>> _scheduledFuturesMap =
		new HashMap<>();
	private volatile boolean _shutdownPending;
	private volatile boolean _signOffPending;
	private final TaskAdvisor _taskAdvisor;
	private final ThreadFactory _threadFactory;
	private final UptimeAdvisor _uptimeAdvisor;
	private ScheduledFuture<?> _uptimeTaskScheduledFuture;

}