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

package com.liferay.lcs.client.internal.task.advisor;

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.messaging.advisor.MessageBusAdvisor;
import com.liferay.lcs.client.internal.task.DownloadPatchTask;
import com.liferay.lcs.client.internal.task.ExecuteScriptTask;
import com.liferay.lcs.client.internal.task.HeartbeatTask;
import com.liferay.lcs.client.internal.task.ScheduleMessageListenersTask;
import com.liferay.lcs.client.internal.task.SendInstallationEnvironmentTask;
import com.liferay.lcs.client.internal.task.SendPatchesTask;
import com.liferay.lcs.client.internal.task.SendPortalPropertiesTask;
import com.liferay.lcs.client.internal.task.CacheMetricsTask;
import com.liferay.lcs.client.internal.task.JVMMetricsTask;
import com.liferay.lcs.client.internal.task.LicenseManagerTask;
import com.liferay.lcs.client.internal.task.PortalMetricsTask;
import com.liferay.lcs.client.internal.task.ScheduledTask;
import com.liferay.lcs.client.internal.task.ServerMetricsTask;
import com.liferay.lcs.client.internal.task.SignOffTask;
import com.liferay.lcs.client.internal.task.Task;
import com.liferay.lcs.client.internal.task.TaskDefinition;
import com.liferay.lcs.client.internal.task.UptimeTask;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.liferay.lcs.messaging.CheckHeartbeatCommandMessage;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.DownloadPatchCommandMessage;
import com.liferay.lcs.messaging.ExecuteScriptCommandMessage;
import com.liferay.lcs.messaging.ScheduleMessageListenersCommandMessage;
import com.liferay.lcs.messaging.ScheduleTasksCommandMessage;
import com.liferay.lcs.messaging.SendInstallationEnvironmentCommandMessage;
import com.liferay.lcs.messaging.SendPatchesCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	immediate = true, service = TaskAdvisor.class)
public class TaskAdvisor {

	public synchronized Set<String> getClassNameLabels(Set<String> classNames) {
		Set<String> activeServiceLabels = new HashSet<>();

		for (String className : classNames) {
			if (!_taskClassNamesParentServiceLabels.containsKey(className)) {
				continue;
			}

			activeServiceLabels.add(
				_taskClassNamesParentServiceLabels.get(className));
		}

		return activeServiceLabels;
	}

	private Task _getTask(CommandMessage commandMessage) {
		Class<? extends CommandMessage> clazz = commandMessage.getClass();

		if (clazz.equals(CheckHeartbeatCommandMessage.class)) {
			return new HeartbeatTask(
				_lcsKeyAdvisor.getKey(), _lcsGatewayClient);
		}
		else if (clazz.equals(DownloadPatchCommandMessage.class)) {
			return new DownloadPatchTask(
				(DownloadPatchCommandMessage)commandMessage, _lcsGatewayClient);
		}
		else if (clazz.equals(ExecuteScriptCommandMessage.class)) {
			return new ExecuteScriptTask(
				(ExecuteScriptCommandMessage)commandMessage, _lcsGatewayClient);
		}
		else if (clazz.equals(ScheduleMessageListenersCommandMessage.class)) {
			return new ScheduleMessageListenersTask(
				_messageBusAdvisor,
				(ScheduleMessageListenersCommandMessage)commandMessage);
		}
		else if (
			clazz.equals(SendInstallationEnvironmentCommandMessage.class)) {

			return new SendInstallationEnvironmentTask(
				_lcsGatewayClient,
				(SendInstallationEnvironmentCommandMessage)commandMessage);
		}
		else if (clazz.equals(SendPatchesCommandMessage.class)) {
			return new SendPatchesTask(
				_lcsGatewayClient, (SendPatchesCommandMessage)commandMessage);
		}
		else if (clazz.equals(SendPortalPropertiesCommandMessage.class)) {
			return new SendPortalPropertiesTask(
				_lcsClusterEntryTokenAdvisor, _lcsGatewayClient,
				(SendPortalPropertiesCommandMessage)commandMessage);
		}
		else if (clazz.equals(SignOffCommandMessage.class)) {
			return new SignOffTask(
				_lcsEventManager, _lcsGatewayClient, _lcsKeyAdvisor,
				(SignOffCommandMessage)commandMessage);
		}

		throw new IllegalArgumentException(
			"Unable to find task for command message " + commandMessage);
	}

	@Reference
	private LCSEventManager _lcsEventManager;
	@Reference
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;

	private final int _defaultInterval = 60;

	private final Map<String, String> _taskClassNamesParentServiceLabels =
		new HashMap<String, String>() {
			{
				put(CacheMetricsTask.class.getName(), "Portal Analytics");
				put(JVMMetricsTask.class.getName(), "Portal Analytics");
				put(
					LicenseManagerTask.class.getName(),
					"Subscription Management");
				put(PortalMetricsTask.class.getName(), "Portal Analytics");
				put(SendPatchesTask.class.getName(), "Fix Packs Management");
				put(
					SendPortalPropertiesTask.class.getName(),
					"Portal Properties Analysis");
				put(ServerMetricsTask.class.getName(), "Portal Analytics");
			}
		};

	private final List<TaskDefinition> _taskDefinitions = new ArrayList<>();
	private final static Log _log = LogFactoryUtil.getLog(
		TaskAdvisor.class);

	private TaskDefinition _getTaskDefinition(
		String priorityKey, Map<String, String> schedulerContext) {

		return new TaskDefinition(
			GetterUtil.getInteger(
				schedulerContext.get("interval"), _defaultInterval),
			GetterUtil.getInteger(schedulerContext.get("initialDelay")),
			GetterUtil.getInteger(priorityKey),
			schedulerContext.get("taskName"));
	}

	public Task getTask(String taskName) {
		try {
			ServiceReference<?>[] serviceReferences =
				_bundleContext.getServiceReferences(
					ScheduledTask.class.getName(),
					"(lcs.client.scheduled.task.name=" + taskName + ")");

			if (serviceReferences.length > 0) {
				return (Task)_bundleContext.getService(serviceReferences[0]);
			}
		}
		catch (InvalidSyntaxException ise) {
			throw new IllegalArgumentException(ise);
		}

		StringBundler sb = new StringBundler(5);

		sb.append("Unable to create the scheduled task ");
		sb.append(taskName);
		sb.append(". This may be because LCS does not support execution ");
		sb.append("of this task in your installation environment. Please ");
		sb.append("see LCS documentation or contact Liferay support.");

		if (_log.isWarnEnabled()) {
			_log.warn(sb.toString());
		}

		return null;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Deactivate
	protected void deactivate() {
		_bundleContext = null;
	}

	private TaskDefinition _getHeartBeatTaskSchedule() {
		Class<HeartbeatTask> heartbeatTaskClass = HeartbeatTask.class;

		TaskDefinition taskDefinition = new TaskDefinition(
			LCSClientConstants.HEARTBEAT_INTERVAL, 10, 0,
			heartbeatTaskClass.getName());

		HeartbeatTask heartbeatTask = new HeartbeatTask(
			_lcsKeyAdvisor.getKey(), _lcsGatewayClient);

		taskDefinition.setTask(heartbeatTask);

		return taskDefinition;
	}

	private TaskDefinition getUptimeTaskDefinition() {
		Class<UptimeTask> uptimeTaskClass = UptimeTask.class;

		TaskDefinition taskDefinition = new TaskDefinition(
			60, 10, 0, uptimeTaskClass.getName());

		taskDefinition.setTask(_uptimeTask);

		return taskDefinition;
	}

	public List<TaskDefinition> getTaskDefinitions(
		CommandMessage commandMessage) {

		if (commandMessage instanceof ScheduleTasksCommandMessage) {
			return _getTaskDefinitions(
				(ScheduleTasksCommandMessage)commandMessage);
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Task discovery for message: " + commandMessage);
		}

		Task task = _getTask(commandMessage);

		TaskDefinition taskDefinition = new TaskDefinition(
			0, 0, 0, String.valueOf(task.getClass()));

		taskDefinition.setTask(task);

		return Arrays.asList(taskDefinition);
	}

	private BundleContext _bundleContext;
	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;
	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private MessageBusAdvisor _messageBusAdvisor;

	private List<TaskDefinition> _getTaskDefinitions(
		ScheduleTasksCommandMessage scheduleTasksCommandMessage) {

		List<TaskDefinition> taskDefinitions = new ArrayList<>();

		if (_log.isTraceEnabled()) {
			_log.trace("Executing schedule tasks command");
		}

		Map<String, List<Map<String, String>>> prioritySchedulerContexts =
			scheduleTasksCommandMessage.getPrioritySchedulerContexts();

		List<String> priorityKeys = new ArrayList<>(
			prioritySchedulerContexts.keySet());

		Collections.sort(priorityKeys);

		for (String priorityKey : priorityKeys) {
			List<Map<String, String>> schedulerContexts =
				prioritySchedulerContexts.get(priorityKey);

			if (schedulerContexts == null) {
				continue;
			}

			for (Map<String, String> schedulerContext : schedulerContexts) {
				TaskDefinition taskDefinition = _getTaskDefinition(
					priorityKey, schedulerContext);

				Task task = getTask(taskDefinition.getTaskName());

				if (task instanceof ServerMetricsTask) {
					try {
						((ServerMetricsTask)task).afterPropertiesSet();
					}
					catch (Exception e) {
						_log.error("Unable to initialize scheduled task", e);

						task = null;
					}
				}

				taskDefinition.setTask(task);

				if (!taskDefinition.isValidTaskSchedule()) {
					_log.error(
						"Ignoring invalid task schedule " + taskDefinition);

					continue;
				}

				taskDefinitions.add(taskDefinition);
			}
		}

		return taskDefinitions;
	}

}