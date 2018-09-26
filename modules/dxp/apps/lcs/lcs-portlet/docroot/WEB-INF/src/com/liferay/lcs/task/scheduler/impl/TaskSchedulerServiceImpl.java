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
import com.liferay.lcs.advisor.UptimeMonitoringAdvisor;
import com.liferay.lcs.platform.LCSEvent;
import com.liferay.lcs.platform.gateway.LCSGatewayStateListener;
import com.liferay.lcs.runnable.LCSConnectorRunnable;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.task.CommandMessageTask;
import com.liferay.lcs.task.HandshakeTask;
import com.liferay.lcs.task.HeartbeatTask;
import com.liferay.lcs.task.ScheduledTask;
import com.liferay.lcs.task.Scope;
import com.liferay.lcs.task.SignOffTask;
import com.liferay.lcs.task.UptimeMonitoringTask;
import com.liferay.lcs.task.advisor.TaskAdvisor;
import com.liferay.lcs.task.scheduler.TaskSchedulerService;
import com.liferay.lcs.util.ClusterNodeUtil;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
public class TaskSchedulerServiceImpl
	implements LCSGatewayStateListener, TaskSchedulerService {

	public TaskSchedulerServiceImpl(
		int defaultInterval, LCSAlertAdvisor lcsAlertAdvisor,
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor,
		LCSGatewayService lcsGatewayService, LCSKeyAdvisor lcsKeyAdvisor,
		int scheduleDelayMax, TaskAdvisor taskAdvisor,
		ThreadFactory threadFactory,
		UptimeMonitoringAdvisor uptimeMonitoringAdvisor) {

		_defaultInterval = defaultInterval;
		_lcsAlertAdvisor = lcsAlertAdvisor;
		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
		_lcsGatewayService = lcsGatewayService;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_scheduleDelayMax = scheduleDelayMax;
		_taskAdvisor = taskAdvisor;
		_threadFactory = threadFactory;
		_uptimeMonitoringAdvisor = uptimeMonitoringAdvisor;

		_lcsGatewayService.registerLCSGatewayStateListener(this);

		_scheduledExecutorService = Executors.newScheduledThreadPool(
			10, threadFactory);
	}

	public void destroy() {
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
	}

	@Override
	public void executeLCSConnectorRunnable(boolean delayRun) {
		LCSConnectorRunnable lcsConnectorRunnable = new LCSConnectorRunnable(
			delayRun);

		lcsConnectorRunnable.setLCSClusterEntryTokenAdvisor(
			_lcsClusterEntryTokenAdvisor);
		lcsConnectorRunnable.setTaskSchedulerService(this);

		_scheduledExecutorService.execute(lcsConnectorRunnable);

		if (_log.isTraceEnabled()) {
			_log.trace(lcsConnectorRunnable + " executed");
		}
	}

	@Override
	public void onLCSGatewayStateChanged(LCSEvent lcsEvent) {
		if (lcsEvent == LCSEvent.AVAILABLE) {
			_onLCSGatewayServiceAvailable();
		}
		else if (lcsEvent == LCSEvent.UNAVAILABLE) {
			_onLCSGatewayServiceUnavailable();
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

			if (scheduledTask.getScope() == Scope.CLUSTER) {
				_scheduleClusteredScheduledTask(schedulerContext);
			}
			else if (scheduledTask.getScope() == Scope.NODE) {
				scheduleLocalScheduledTask(schedulerContext);
			}

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
	public void scheduleUptimeMonitoringTask() {
		UptimeMonitoringTask uptimeMonitoringTask = new UptimeMonitoringTask();

		_scheduledExecutorService.scheduleAtFixedRate(
			uptimeMonitoringTask, 1, 1, TimeUnit.MINUTES);

		if (_log.isTraceEnabled()) {
			_log.trace(uptimeMonitoringTask.toString() + " scheduled");
		}
	}

	@Override
	public Future<?> submitHandshakeTask() {
		return _scheduledExecutorService.submit(
			new HandshakeTask(
				_lcsClusterEntryTokenAdvisor.getLcsClusterEntryTokenId(),
				_lcsAlertAdvisor, _lcsGatewayService, _lcsKeyAdvisor,
				_threadFactory, _uptimeMonitoringAdvisor));
	}

	@Override
	public Future<?> submitSignOffTask(boolean serverManuallyShutdown) {
		SignOffTask signOffTask = new SignOffTask(
			_lcsKeyAdvisor.getKey(), _lcsGatewayService,
			serverManuallyShutdown);

		Future<?> future = _scheduledExecutorService.submit(signOffTask);

		return future;
	}

	@Override
	public void unscheduleAllTasks() {
		unscheduleLocalScheduledTasks();

		unscheduleClusteredScheduledTasks();
	}

	protected int getInterval(Map<String, String> schedulerContext) {
		String interval = schedulerContext.get("interval");

		return GetterUtil.getInteger(interval, _defaultInterval);
	}

	protected String getJobName(Map<String, String> schedulerContext) {
		return schedulerContext.get("taskName");
	}

	protected List<String> getScheduledTaskJobNames() {
		List<String> scheduledTaskJobNames = new ArrayList<>();

		try {
			List<SchedulerResponse> schedulerResponses =
				SchedulerEngineHelperUtil.getScheduledJobs(
					_LCS_SCHEDULE_GROUP_NAME, StorageType.MEMORY_CLUSTERED);

			for (SchedulerResponse schedulerResponse : schedulerResponses) {
				scheduledTaskJobNames.add(schedulerResponse.getJobName());
			}
		}
		catch (SchedulerException se) {
			_log.error(se, se);
		}

		return scheduledTaskJobNames;
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

	protected void unscheduleClusteredScheduledTasks() {
		try {
			if (ClusterExecutorUtil.isEnabled()) {
				try {
					Thread.sleep(RandomUtil.nextInt(1000));
				}
				catch (InterruptedException ie) {
					_log.error(ie, ie);
				}

				boolean otherClusterNodesConnected = false;

				List<Map<String, Object>> clusterNodeInfos =
					ClusterNodeUtil.getClusterNodeInfos();

				for (Map<String, Object> clusterNodeInfo : clusterNodeInfos) {
					if (GetterUtil.getBoolean(clusterNodeInfo.get("ready"))) {
						otherClusterNodesConnected = true;

						break;
					}
				}

				if (!otherClusterNodesConnected) {
					List<String> scheduledTaskJobNames =
						getScheduledTaskJobNames();

					for (String scheduledTaskJobName : scheduledTaskJobNames) {
						SchedulerEngineHelperUtil.delete(
							scheduledTaskJobName, _LCS_SCHEDULE_GROUP_NAME,
							StorageType.MEMORY_CLUSTERED);

						if (_log.isDebugEnabled()) {
							_log.debug(
								"Unscheduled clustered task " +
									scheduledTaskJobName);
						}
					}
				}
			}
			else {
				List<String> scheduledTaskJobNames = getScheduledTaskJobNames();

				for (String scheduledTaskJobName : scheduledTaskJobNames) {
					SchedulerEngineHelperUtil.delete(
						scheduledTaskJobName, _LCS_SCHEDULE_GROUP_NAME,
						StorageType.MEMORY_CLUSTERED);

					if (_log.isDebugEnabled()) {
						_log.debug("Unscheduled task " + scheduledTaskJobName);
					}
				}
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}
	}

	protected void unscheduleLocalScheduledTasks() {
		for (String taskName : _scheduledFuturesMap.keySet()) {
			ScheduledFuture<?> scheduledFuture = _scheduledFuturesMap.get(
				taskName);

			while (!scheduledFuture.isCancelled()) {
				scheduledFuture.cancel(true);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Unscheduled task " + taskName);
			}
		}

		_scheduledFuturesMap.clear();
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
					_lcsKeyAdvisor.getKey(), _lcsGatewayService),
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
				new HeartbeatTask(_lcsKeyAdvisor.getKey(), _lcsGatewayService),
				10000L,
				GetterUtil.getLong(
					PortletPropsValues.COMMUNICATION_HEARTBEAT_INTERVAL),
				TimeUnit.MILLISECONDS));
	}

	private void _onLCSGatewayServiceUnavailable() {
		unscheduleAllTasks();

		_taskAdvisor.reset();

		executeLCSConnectorRunnable(true);
	}

	private void _scheduleClusteredScheduledTask(
		Map<String, String> schedulerContext) {

		try {
			Thread.sleep(RandomUtil.nextInt(_scheduleDelayMax));
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		String jobName = getJobName(schedulerContext);

		for (String scheduledTaskJobName : getScheduledTaskJobNames()) {
			if (jobName.equals(scheduledTaskJobName)) {
				if (_log.isDebugEnabled()) {
					_log.debug("Already scheduled job " + jobName);
				}

				return;
			}
		}

		try {
			Date endDate = null;

			long endTime = GetterUtil.getLong(schedulerContext.get("endTime"));

			if (endTime > 0) {
				endDate = new Date(endTime);
			}

			Date startDate = null;

			long startTime = GetterUtil.getLong(
				schedulerContext.get("startTime"));

			if (startTime > 0) {
				startDate = new Date(startTime);
			}

			Trigger trigger = TriggerFactoryUtil.createTrigger(
				jobName, _LCS_SCHEDULE_GROUP_NAME, startDate, endDate,
				getInterval(schedulerContext),
				com.liferay.portal.kernel.scheduler.TimeUnit.SECOND);

			SchedulerEngineHelperUtil.schedule(
				trigger, StorageType.MEMORY_CLUSTERED, StringPool.BLANK,
				"liferay/lcs_scheduler", schedulerContext, 0);

			if (_log.isDebugEnabled()) {
				_log.debug("Scheduled job " + jobName);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final String _LCS_SCHEDULE_GROUP_NAME = "com.liferay.lcs";

	private static final Log _log = LogFactoryUtil.getLog(
		TaskSchedulerServiceImpl.class);

	private final int _defaultInterval;
	private final LCSAlertAdvisor _lcsAlertAdvisor;
	private final LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private final LCSGatewayService _lcsGatewayService;
	private final LCSKeyAdvisor _lcsKeyAdvisor;
	private final int _scheduleDelayMax;
	private final ScheduledExecutorService _scheduledExecutorService;
	private final Map<String, ScheduledFuture<?>> _scheduledFuturesMap =
		new HashMap<>();
	private final TaskAdvisor _taskAdvisor;
	private final ThreadFactory _threadFactory;
	private final UptimeMonitoringAdvisor _uptimeMonitoringAdvisor;

}