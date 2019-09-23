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

package com.liferay.lcs.client.internal.task.executor;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.event.LCSEvent;
import com.liferay.lcs.client.event.LCSEventListener;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.lifecycle.LCSModuleLifecycle;
import com.liferay.lcs.client.internal.task.HandshakeTask;
import com.liferay.lcs.client.internal.task.HeartbeatTask;
import com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask;
import com.liferay.lcs.client.internal.task.ScheduledTask;
import com.liferay.lcs.client.internal.task.ServerMetricsTask;
import com.liferay.lcs.client.internal.task.Task;
import com.liferay.lcs.client.internal.task.UptimeTask;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.client.task.advisor.TaskAdvisor;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.scope=lcs", "osgi.command.function=taskStatus"
	},
	service = LCSTaskExecutor.class
)
public class LCSTaskExecutor implements LCSEventListener {

	public LCSTaskExecutor() {
	}

	public LCSTaskExecutor(
		int defaultInterval, HandshakeTask handshakeTask,
		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask,
		LCSConfigurationProvider lcsConfigurationProvider,
		LCSEventManager lcsEventManager, LCSGatewayClient lcsGatewayClient,
		LCSKeyAdvisor lcsKeyAdvisor, TaskAdvisor taskAdvisor,
		ThreadFactory threadFactory, UptimeTask uptimeTask) {

		_defaultInterval = defaultInterval;
		_handshakeTask = handshakeTask;
		_lcsClusterEntryTokenCheckTask = lcsClusterEntryTokenCheckTask;
		_lcsConfigurationProvider = lcsConfigurationProvider;
		_lcsEventManager = lcsEventManager;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_taskAdvisor = taskAdvisor;
		_threadFactory = threadFactory;
		_uptimeTask = uptimeTask;

		_initExecutorService();

		_subscribeToLCSEvents();
	}

	@Override
	public void onLCSEvent(LCSEvent lcsEvent) {
		if (_shutdownPending.get()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting event processing. Shutdown is in progress.");
			}

			return;
		}

		if (lcsEvent == LCSEvent.HANDSHAKE_FAILED) {
			_executeHandshakeTask(true);
		}
		else if (lcsEvent == LCSEvent.HANDSHAKE_SUCCESS) {
			_onHandshakeSuccess();
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
					 LCSEvent.
						 LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS) ||
				 (lcsEvent == LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING) ||
				 (lcsEvent ==
					 LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS)) {

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
	}

	public String schedulerStatus() {
		return "Scheduler Status: " + _scheduledExecutorService;
	}

	public void scheduleTask(Map<String, String> schedulerContext) {
		String taskName = schedulerContext.get("taskName");

		try {
			ScheduledTask scheduledTask = _getScheduledTask(taskName);

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
			StringBundler sb = new StringBundler(5);

			sb.append("Unable to create the scheduled task ");
			sb.append(taskName);
			sb.append(". This may be because LCS does not support execution ");
			sb.append("of this task in your installation environment. Please ");
			sb.append("see LCS documentation or contact Liferay support.");

			if (_log.isWarnEnabled()) {
				_log.warn(sb.toString());

				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}
	}

	public void start() {
		_scheduleUptimeMonitoringTask();

		_executeLCSClusterEntryTokenCheckTask(false);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		_defaultInterval = lcsConfiguration.commandScheduleDefaultInterval();

		_initExecutorService();

		_subscribeToLCSEvents();

		if (_log.isTraceEnabled()) {
			_log.trace("Activated " + this);
		}

		start();
	}

	@Deactivate
	protected void deactivate() {
		_lcsEventManager.unsubscribe(this);

		if (_log.isTraceEnabled()) {
			_log.trace("Deactivate " + this);
		}

		_shutdownPending.set(true);

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
			_log.trace("Deactivated " + this);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	protected void scheduleLocalScheduledTask(
		Map<String, String> schedulerContext) {

		int initialDelay = _getInitialDelay(schedulerContext);

		int interval = _getInterval(schedulerContext);

		String taskName = schedulerContext.get("taskName");

		ScheduledTask scheduledTask = _getScheduledTask(taskName);

		if (scheduledTask instanceof ServerMetricsTask) {
			((ServerMetricsTask)scheduledTask).afterPropertiesSet();
		}

		_scheduledFuturesMap.put(
			taskName,
			_scheduledExecutorService.scheduleAtFixedRate(
				scheduledTask, initialDelay, interval, TimeUnit.SECONDS));

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

		if (_log.isTraceEnabled()) {
			_log.trace(
				"Scheduled executor service status after cancellation " +
					_scheduledExecutorService);
		}
	}

	private void _executeHandshakeTask(boolean delayRun) {
		if (_shutdownPending.get()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Aborting handshake. Shutdown is in progress.");
			}

			return;
		}

		if (delayRun) {
			if (_log.isInfoEnabled()) {
				_log.info("Retrying connection in 60 seconds");
			}

			_scheduledExecutorService.schedule(
				_handshakeTask, 60, TimeUnit.SECONDS);
		}
		else {
			_scheduledExecutorService.submit(_handshakeTask);
		}
	}

	private void _executeLCSClusterEntryTokenCheckTask(boolean delayRun) {
		if (_shutdownPending.get()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting environment token processing. Shutdown is in " +
						"progress.");
			}

			return;
		}

		long delay = 0;

		if (delayRun) {
			delay = 60;

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Checking environment token in %d seconds", delay));
			}
		}

		_scheduledExecutorService.schedule(
			_lcsClusterEntryTokenCheckTask, delay, TimeUnit.SECONDS);

		if (_log.isTraceEnabled()) {
			_log.trace("Executed " + _lcsClusterEntryTokenCheckTask);
		}
	}

	private void _executeSignOffTask() {
		if (_signOffPending) {
			return;
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Executing sign off task");
		}

		_signOffPending = true;

		Future<?> future = _scheduledExecutorService.submit(_signOffTask);

		while (!future.isDone()) {
			Thread.yield();
		}

		_signOffPending = false;
	}

	private int _getInitialDelay(Map<String, String> schedulerContext) {
		String initialDelay = schedulerContext.get("initialDelay");

		return GetterUtil.getInteger(initialDelay);
	}

	private int _getInterval(Map<String, String> schedulerContext) {
		String interval = schedulerContext.get("interval");

		return GetterUtil.getInteger(interval, _defaultInterval);
	}

	private ScheduledTask _getScheduledTask(String taskName) {
		try {
			ServiceReference<?>[] serviceReferences =
				_bundleContext.getServiceReferences(
					ScheduledTask.class.getName(),
					"(lcs.client.scheduled.task.name=" + taskName + ")");

			if (serviceReferences.length > 0) {
				return (ScheduledTask)_bundleContext.getService(
					serviceReferences[0]);
			}
		}
		catch (InvalidSyntaxException ise) {
			throw new IllegalArgumentException(ise);
		}

		return null;
	}

	private void _initExecutorService() {
		_scheduledExecutorService = Executors.newScheduledThreadPool(
			10, _threadFactory);

		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor =
			(ScheduledThreadPoolExecutor)_scheduledExecutorService;

		scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
	}

	private void _onHandshakeSuccess() {
		_scheduleCommandMessageCheckTask();

		_scheduleCommandQueueCheckTask();

		if (_log.isTraceEnabled()) {
			_log.trace("Scheduling heartbeat task");
		}

		Class<?> clazz = HeartbeatTask.class;

		_scheduledFuturesMap.put(
			clazz.getName(),
			_scheduledExecutorService.scheduleAtFixedRate(
				new HeartbeatTask(_lcsKeyAdvisor.getKey(), _lcsGatewayClient),
				10000L, LCSClientConstants.HEARTBEAT_INTERVAL,
				TimeUnit.MILLISECONDS));
	}

	private void _onLCSGatewayServiceUnavailable() {
		if (_signOffPending) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting connection recovery. Sign-out is in progress.");
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Starting connection recovery");
		}

		_cancelAllTasks();

		_executeLCSClusterEntryTokenCheckTask(false);
	}

	private void _restart() {
		if (_log.isDebugEnabled()) {
			_log.debug("Restarting LCS lifecycle");
		}

		_cancelAllTasks();

		_executeSignOffTask();

		_executeLCSClusterEntryTokenCheckTask(true);
	}

	private void _scheduleCommandMessageCheckTask() {
		Map<String, String> schedulerContext = new HashMap<>();

		schedulerContext.put(
			"initialDelay",
			String.valueOf(LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD));
		schedulerContext.put(
			"interval",
			String.valueOf(LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD));
		schedulerContext.put(
			"taskName", "com.liferay.lcs.task.CommandMessageCheckTask");

		if (_log.isTraceEnabled()) {
			_log.trace("Scheduling command message task");
		}

		scheduleLocalScheduledTask(schedulerContext);
	}

	private void _scheduleCommandQueueCheckTask() {
		Map<String, String> schedulerContext = new HashMap<>();

		schedulerContext.put(
			"initialDelay",
			String.valueOf(LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD));
		schedulerContext.put(
			"interval",
			String.valueOf(LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD));
		schedulerContext.put(
			"taskName", "com.liferay.lcs.task.CommandQueueCheckTask");

		if (_log.isTraceEnabled()) {
			_log.trace("Scheduling command queue task");
		}

		scheduleLocalScheduledTask(schedulerContext);
	}

	private void _scheduleUptimeMonitoringTask() {
		_uptimeTaskScheduledFuture =
			_scheduledExecutorService.scheduleAtFixedRate(
				_uptimeTask, 1, 1, TimeUnit.MINUTES);

		if (_log.isTraceEnabled()) {
			_log.trace(_uptimeTask.toString() + " scheduled");
		}
	}

	private void _subscribeToLCSEvents() {
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_FAILED, this);
		_lcsEventManager.subscribe(LCSEvent.HANDSHAKE_SUCCESS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_SUCCESS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_TOKEN_CORRUPTED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_CHECK_FAILED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MISSING, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_MULTIPLE_TOKENS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALIDATED, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_ENVIRONMENT_MISMATCH, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_ENTRY_TOKEN_INVALID_USER_CREDENTIALS, this);
		_lcsEventManager.subscribe(
			LCSEvent.LCS_CLUSTER_NODE_UNREGISTERED, this);
		_lcsEventManager.subscribe(LCSEvent.LCS_GATEWAY_UNAVAILABLE, this);
		_lcsEventManager.subscribe(LCSEvent.SIGNOFF_REQUESTED, this);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LCSTaskExecutor.class);

	private BundleContext _bundleContext;
	private int _defaultInterval;

	@Reference(
		target = "(component.name=com.liferay.lcs.client.internal.task.HandshakeTask)",
		unbind = "-"
	)
	private Task _handshakeTask;

	@Reference(
		target = "(component.name=com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask)",
		unbind = "-"
	)
	private Task _lcsClusterEntryTokenCheckTask;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSEventManager _lcsEventManager;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private LCSModuleLifecycle _lcsModuleLifecycle;

	private ScheduledExecutorService _scheduledExecutorService;
	private final Map<String, ScheduledFuture<?>> _scheduledFuturesMap =
		new HashMap<>();
	private AtomicBoolean _shutdownPending = new AtomicBoolean(false);
	private volatile boolean _signOffPending;

	@Reference(
		target = "(component.name=com.liferay.lcs.client.internal.task.SignOffTask)",
		unbind = "-"
	)
	private Task _signOffTask;

	@Reference
	private TaskAdvisor _taskAdvisor;

	@Reference
	private ThreadFactory _threadFactory;

	@Reference(
		target = "(component.name=com.liferay.lcs.client.internal.task.UptimeTask)",
		unbind = "-"
	)
	private Task _uptimeTask;

	private ScheduledFuture<?> _uptimeTaskScheduledFuture;

}