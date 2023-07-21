/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.task.advisor;

import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.event.LCSEventManager;
import com.liferay.lcs.client.internal.messaging.advisor.MessageBusAdvisor;
import com.liferay.lcs.client.internal.task.CommandMessageCheckTask;
import com.liferay.lcs.client.internal.task.CommandQueueCheckTask;
import com.liferay.lcs.client.internal.task.DownloadPatchTask;
import com.liferay.lcs.client.internal.task.HandshakeTask;
import com.liferay.lcs.client.internal.task.HeartbeatTask;
import com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask;
import com.liferay.lcs.client.internal.task.ScheduleMessageListenersTask;
import com.liferay.lcs.client.internal.task.SendInstallationEnvironmentTask;
import com.liferay.lcs.client.internal.task.SendPatchesTask;
import com.liferay.lcs.client.internal.task.SendPortalPropertiesTask;
import com.liferay.lcs.client.internal.task.SignOffTask;
import com.liferay.lcs.client.internal.task.Task;
import com.liferay.lcs.client.internal.task.TaskDefinition;
import com.liferay.lcs.client.internal.task.UptimeTask;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.CheckHeartbeatCommandMessage;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.DownloadPatchCommandMessage;
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
import com.liferay.portal.kernel.util.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
@Component(immediate = true, service = TaskAdvisor.class)
public class TaskAdvisor {

	public TaskAdvisor() {
	}

	public TaskAdvisor(
		BundleContext bundleContext,
		CommandMessageCheckTask commandMessageCheckTask,
		HandshakeTask handshakeTask,
		LCSClusterEntryTokenCheckTask lcsClusterEntryTokenCheckTask,
		LCSKeyAdvisor lcsKeyAdvisor, UptimeTask uptimeTask) {

		_bundleContext = bundleContext;
		_commandMessageCheckTask = commandMessageCheckTask;
		_handshakeTask = handshakeTask;
		_lcsClusterEntryTokenCheckTask = lcsClusterEntryTokenCheckTask;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_uptimeTask = uptimeTask;
	}

	public TaskDefinition findSignOffTaskDefinition(
		List<TaskDefinition> taskDefinitions) {

		for (TaskDefinition taskDefinition : taskDefinitions) {
			Task task = taskDefinition.getTask();

			if (task instanceof SignOffTask) {
				return taskDefinition;
			}
		}

		return null;
	}

	public TaskDefinition getCommandMessageCheckTaskDefinition() {
		return new TaskDefinition(
			10, LCSClientConstants.COMMAND_MESSAGE_CHECK_INTERVAL, 0,
			_commandMessageCheckTask);
	}

	public TaskDefinition getCommandQueueCheckTaskDefinition() {
		return new TaskDefinition(
			10, LCSClientConstants.COMMAND_QUEUE_CHECK_INTERVAL, 0,
			_getTask(CommandQueueCheckTask.class.getName(), "component.name"));
	}

	public TaskDefinition getHandshakeTaskDefinition(boolean delayed) {
		return new TaskDefinition(_getDelay(delayed), 0, 0, _handshakeTask);
	}

	public TaskDefinition getHeartBeatTaskDefinition() {
		return new TaskDefinition(
			10, LCSClientConstants.HEARTBEAT_INTERVAL, 0,
			new HeartbeatTask(_lcsKeyAdvisor.getKey(), _lcsGatewayClient));
	}

	public TaskDefinition getLCSClusterEntryTokenCheckTaskDefinition(
		boolean delayed) {

		return new TaskDefinition(
			_getDelay(delayed), 0, 0, _lcsClusterEntryTokenCheckTask);
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

		List<TaskDefinition> taskDefinitions = new ArrayList<>();

		try {
			TaskDefinition taskDefinition = new TaskDefinition(
				0, 0, 0, _getTask(commandMessage));

			taskDefinitions.add(taskDefinition);

			if (_log.isInfoEnabled()) {
				_log.info("New task definition " + taskDefinition);
			}
		}
		catch (Throwable t) {
			_log.error(
				"Unable to create task definition for " + commandMessage, t);
		}

		return taskDefinitions;
	}

	public TaskDefinition getUptimeTaskDefinition() {
		return new TaskDefinition(
			10 * Time.SECOND, Time.MINUTE, 0, _uptimeTask);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	@Deactivate
	protected void deactivate() {
		_bundleContext = null;
	}

	private long _getDelay(boolean delayed) {
		if (delayed) {
			return Time.MINUTE;
		}

		return 0;
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
		else if (clazz.equals(ScheduleMessageListenersCommandMessage.class)) {
			return new ScheduleMessageListenersTask(
				_messageBusAdvisor,
				(ScheduleMessageListenersCommandMessage)commandMessage);
		}
		else if (clazz.equals(
					SendInstallationEnvironmentCommandMessage.class)) {

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
				_lcsEventManager, (SignOffCommandMessage)commandMessage);
		}

		throw new IllegalArgumentException(
			"Unable to find task for command message " + commandMessage);
	}

	private Task _getTask(String taskName, String filterAttribute) {
		try {
			String filterExpression = String.format(
				"(%s=%s)", filterAttribute, taskName);

			ServiceReference<?>[] serviceReferences =
				_bundleContext.getServiceReferences(
					Task.class.getName(), filterExpression);

			if ((serviceReferences != null) && (serviceReferences.length > 0)) {
				return (Task)_bundleContext.getService(serviceReferences[0]);
			}
		}
		catch (InvalidSyntaxException ise) {
			throw new IllegalArgumentException(ise);
		}

		StringBundler sb = new StringBundler(6);

		sb.append("Unable to create the scheduled task ");
		sb.append(taskName);
		sb.append(". This may be either because LCS does not support ");
		sb.append("execution of this task in your installation environment ");
		sb.append("or task is deprecated. Please see LCS documentation or ");
		sb.append("contact Liferay support.");

		if (_log.isWarnEnabled()) {
			_log.warn(sb.toString());
		}

		return null;
	}

	private TaskDefinition _getTaskDefinition(
		String priorityKey, Map<String, String> schedulerContext) {

		Task task = _getTask(
			schedulerContext.get("taskName"), "lcs.client.scheduled.task.name");

		if (task == null) {
			return null;
		}

		return new TaskDefinition(
			GetterUtil.getInteger(schedulerContext.get("initialDelay")) *
				Time.SECOND,
			GetterUtil.getInteger(
				schedulerContext.get("interval"), _DEFAULT_PERIOD) *
					Time.SECOND,
			GetterUtil.getInteger(priorityKey), task);
	}

	private List<TaskDefinition> _getTaskDefinitions(
		ScheduleTasksCommandMessage scheduleTasksCommandMessage) {

		List<TaskDefinition> taskDefinitions = new ArrayList<>();

		if (_log.isTraceEnabled()) {
			_log.trace("Resolving tasks from schedule tasks command");
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

				if (taskDefinition == null) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Unable to create task definition for " +
								schedulerContext.get("taskName"));
					}

					continue;
				}

				if (_log.isInfoEnabled()) {
					_log.info("New task definition " + taskDefinition);
				}

				if (!taskDefinition.hasValidTask()) {
					_log.error(
						"Ignoring invalid task schedule " + taskDefinition);

					continue;
				}

				taskDefinitions.add(taskDefinition);
			}
		}

		return taskDefinitions;
	}

	private static final int _DEFAULT_PERIOD = 60;

	private static final Log _log = LogFactoryUtil.getLog(TaskAdvisor.class);

	private BundleContext _bundleContext;

	@Reference(
		target = "(component.name=com.liferay.lcs.client.internal.task.CommandMessageCheckTask)",
		unbind = "-"
	)
	private Task _commandMessageCheckTask;

	@Reference(
		target = "(component.name=com.liferay.lcs.client.internal.task.HandshakeTask)",
		unbind = "-"
	)
	private Task _handshakeTask;

	@Reference
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;

	@Reference(
		target = "(component.name=com.liferay.lcs.client.internal.task.LCSClusterEntryTokenCheckTask)",
		unbind = "-"
	)
	private Task _lcsClusterEntryTokenCheckTask;

	@Reference
	private LCSEventManager _lcsEventManager;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private MessageBusAdvisor _messageBusAdvisor;

	@Reference(
		target = "(component.name=com.liferay.lcs.client.internal.task.UptimeTask)",
		unbind = "-"
	)
	private Task _uptimeTask;

}