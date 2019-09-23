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

package com.liferay.lcs.client.internal.command.advisor;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.constants.LCSClientConstants;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.internal.command.CheckHeartbeatCommand;
import com.liferay.lcs.client.internal.command.Command;
import com.liferay.lcs.client.internal.command.CommandValidationResult;
import com.liferay.lcs.client.internal.command.CommandValidator;
import com.liferay.lcs.client.internal.command.DownloadPatchCommand;
import com.liferay.lcs.client.internal.command.ExecuteScriptCommand;
import com.liferay.lcs.client.internal.command.ScheduleMessageListenersCommand;
import com.liferay.lcs.client.internal.command.ScheduleTasksCommand;
import com.liferay.lcs.client.internal.command.SendInstallationEnvironmentCommand;
import com.liferay.lcs.client.internal.command.SendPatchesCommand;
import com.liferay.lcs.client.internal.command.SendPortalPropertiesCommand;
import com.liferay.lcs.client.internal.command.SignOffCommand;
import com.liferay.lcs.client.internal.task.HeartbeatTask;
import com.liferay.lcs.client.internal.task.ScheduledTask;
import com.liferay.lcs.client.internal.task.ServerMetricsTask;
import com.liferay.lcs.client.internal.task.TaskSchedule;
import com.liferay.lcs.client.internal.task.executor.LCSTaskExecutor;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.CheckHeartbeatCommandMessage;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.DownloadPatchCommandMessage;
import com.liferay.lcs.messaging.ErrorResponseMessage;
import com.liferay.lcs.messaging.ExecuteScriptCommandMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.ScheduleMessageListenersCommandMessage;
import com.liferay.lcs.messaging.ScheduleTasksCommandMessage;
import com.liferay.lcs.messaging.SendInstallationEnvironmentCommandMessage;
import com.liferay.lcs.messaging.SendPatchesCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.lcs.messaging.security.DigitalSignature;
import com.liferay.lcs.messaging.security.DigitalSignatureFactory;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = CommandAdvisor.class)
public class CommandAdvisor {

	public CommandAdvisor() {
		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public CommandAdvisor(
		DigitalSignature digitalSignature,
		LCSConfigurationProvider lcsConfigurationProvider,
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor,
		SendPatchesCommand sendPatchesCommand,
		SendPortalPropertiesCommand sendPortalPropertiesCommand) {

		_digitalSignature = digitalSignature;
		_lcsConfigurationProvider = lcsConfigurationProvider;
		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;
		_sendPatchesCommand = sendPatchesCommand;
		_sendPortalPropertiesCommand = sendPortalPropertiesCommand;

		_initCommands();

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	public void execute(List<Message> messages) {
		if (_log.isTraceEnabled()) {
			_log.trace("Executing command messages");
		}

		for (Message message : messages) {
			if (!(message instanceof CommandMessage)) {
				_log.error("Unknown message " + message);

				continue;
			}
			else if (message instanceof ScheduleTasksCommandMessage) {
				_scheduleTasksCommand((ScheduleTasksCommandMessage)message);

				continue;
			}
			else if (message instanceof SignOffCommandMessage) {
				_taskSchedules.clear();

				_execute((CommandMessage)message);

				return;
			}

			_execute((CommandMessage)message);
		}
	}

	public void checkTaskSchedules() {
		for (TaskSchedule taskSchedule : _taskSchedules) {
			if (!taskSchedule.isOnSchedule()) {
				continue;
			}

			_lcsTaskExecutor.submit(taskSchedule);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_initDigitalSignature(_lcsConfigurationProvider.getLCSConfiguration());

		_initCommands();

		_taskSchedules.add(_getHeartBeatTaskSchedule());
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		if (_log.isTraceEnabled()) {
			_log.trace("Finalized " + this);
		}
	}

	protected void sendErrorResponseMessage(
		CommandMessage commandMessage, String errorMessage) {

		ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage();

		errorResponseMessage.setCorrelationId(
			commandMessage.getCorrelationId());
		errorResponseMessage.setCreateTime(System.currentTimeMillis());
		errorResponseMessage.setErrorMessage(errorMessage);
		errorResponseMessage.setKey(commandMessage.getKey());

		try {
			if (_log.isTraceEnabled()) {
				_log.trace("Sending response message");
			}

			_lcsGatewayClient.sendMessage(errorResponseMessage);
		}
		catch (Exception e) {
			_log.error("Unable to send error response message to LCS", e);
		}
	}

	private void _execute(CommandMessage commandMessage) {
		if (_log.isTraceEnabled()) {
			_log.trace("Processing message: " + commandMessage);
		}

		CommandValidator commandValidator = _getCommandValidator(
			commandMessage);

		if (!commandValidator.isValid()) {
			sendErrorResponseMessage(
				commandMessage, commandValidator.getErrorMessage());

			return;
		}

		Class<?> commandMessageClass = commandMessage.getClass();

		Command<? extends CommandMessage> command = _commands.get(
			commandMessageClass.getName());

		if (_log.isDebugEnabled()) {
			_log.debug("Executing command: " + command.getClass());
		}

		Command<CommandMessage> castCommand = (Command<CommandMessage>)command;

		try {
			castCommand.execute(commandMessage);
		}
		catch (Exception e) {
			_log.error("Unable to execute command: " + command.getClass(), e);

			if (!(e instanceof JSONWebServiceException)) {
				sendErrorResponseMessage(commandMessage, e.getMessage());
			}
		}
	}

	private CommandValidator _getCommandValidator(
		CommandMessage commandMessage) {

		if (_log.isTraceEnabled()) {
			_log.trace("Verifying digital signature");
		}

		try {
			if (_digitalSignature.verifyMessage(
					LCSClientConstants.LCS_CLIENT_BUILD_NUMBER,
					commandMessage)) {

				return new CommandValidator(CommandValidationResult.VALID);
			}

			return new CommandValidator(
				CommandValidationResult.DIGITAL_SIGNATURE_INVALID);
		}
		catch (Exception e) {
			_log.error("Unable to perform digital signature check", e);

			return new CommandValidator(
				CommandValidationResult.UNABLE_TO_VERIFY_DIGITAL_SIGNATURE,
				e.getMessage());
		}
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

	private void _initCommands() {
		_commands.put(
			CheckHeartbeatCommandMessage.class.getName(),
			_checkHeartbeatCommand);
		_commands.put(
			DownloadPatchCommandMessage.class.getName(), _downloadPatchCommand);
		_commands.put(
			ExecuteScriptCommandMessage.class.getName(), _executeScriptCommand);
		_commands.put(
			ScheduleMessageListenersCommandMessage.class.getName(),
			_scheduleMessageListenersCommand);
		_commands.put(
			ScheduleTasksCommandMessage.class.getName(), _scheduleTasksCommand);
		_commands.put(
			SendInstallationEnvironmentCommandMessage.class.getName(),
			_sendInstallationEnvironmentCommand);
		_commands.put(
			SendPatchesCommandMessage.class.getName(), _sendPatchesCommand);
		_commands.put(
			SendPortalPropertiesCommandMessage.class.getName(),
			_sendPortalPropertiesCommand);
		_commands.put(SignOffCommandMessage.class.getName(), _signOffCommand);
	}

	private void _initDigitalSignature(LCSConfiguration lcsConfiguration) {
		Map<String, Object> dictionary = new HashMap<String, Object>() {
			{
				put("keyName", lcsConfiguration.digitalSignatureKeyName());
				put(
					"keyStorePath",
					lcsConfiguration.digitalSignatureKeyStorePath());
				put(
					"keyStoreType",
					lcsConfiguration.digitalSignatureKeyStoreType());
				put(
					"signingAlgorithm",
					lcsConfiguration.digitalSignatureSigningAlgorithm());
			}
		};

		_digitalSignature = _digitalSignatureFactory.getInstance(dictionary);
	}

	private void _scheduleTasksCommand(
		ScheduleTasksCommandMessage scheduleTasksCommandMessage) {

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
				TaskSchedule taskSchedule = _getTaskSchedule(
					priorityKey, schedulerContext);

				ScheduledTask scheduledTask = _getScheduledTask(
					taskSchedule.getTaskName());

				if (scheduledTask instanceof ServerMetricsTask) {
					try {
						((ServerMetricsTask)scheduledTask).afterPropertiesSet();
					}
					catch (Exception e) {
						_log.error("Unable to initialize scheduled task", e);

						scheduledTask = null;
					}
				}

				taskSchedule.setScheduledTask(scheduledTask);

				if (!taskSchedule.isValidTaskSchedule()) {
					_log.error(
						"Ignoring invalid task schedule " + taskSchedule);

					continue;
				}

				_taskSchedules.add(taskSchedule);
			}
		}
	}

	private TaskSchedule _getTaskSchedule(
		String priorityKey, Map<String, String> schedulerContext) {

		return new TaskSchedule(
			GetterUtil.getInteger(
				schedulerContext.get("interval"), _defaultInterval),
			GetterUtil.getInteger(schedulerContext.get("initialDelay")),
			GetterUtil.getInteger(priorityKey),
			schedulerContext.get("taskName"));
	}

	private TaskSchedule _getHeartBeatTaskSchedule() {
		Class<HeartbeatTask> heartbeatTaskClass = HeartbeatTask.class;

		TaskSchedule taskSchedule = new TaskSchedule(
			LCSClientConstants.HEARTBEAT_INTERVAL, 10000L, 0,
			heartbeatTaskClass.getName());

		HeartbeatTask heartbeatTask = new HeartbeatTask(
			_lcsKeyAdvisor.getKey(), _lcsGatewayClient);

		taskSchedule.setScheduledTask(heartbeatTask);

		return taskSchedule;
	}

	private static final Log _log = LogFactoryUtil.getLog(CommandAdvisor.class);

	private static final int _defaultInterval = 86400;

	private BundleContext _bundleContext;

	@Reference
	private CheckHeartbeatCommand _checkHeartbeatCommand;

	private Map<String, Command<? extends CommandMessage>> _commands =
		new HashMap<>();
	private DigitalSignature _digitalSignature;

	@Reference
	private DigitalSignatureFactory _digitalSignatureFactory;

	@Reference
	private DownloadPatchCommand _downloadPatchCommand;

	@Reference
	private ExecuteScriptCommand _executeScriptCommand;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private ScheduleMessageListenersCommand _scheduleMessageListenersCommand;

	@Reference
	private ScheduleTasksCommand _scheduleTasksCommand;

	@Reference
	private SendInstallationEnvironmentCommand
		_sendInstallationEnvironmentCommand;

	@Reference
	private SendPatchesCommand _sendPatchesCommand;

	@Reference
	private SendPortalPropertiesCommand _sendPortalPropertiesCommand;

	@Reference
	private SignOffCommand _signOffCommand;

	private List<TaskSchedule> _taskSchedules = new ArrayList<>();

	@Reference
	private LCSTaskExecutor _lcsTaskExecutor;

}