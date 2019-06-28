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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	property = "lcs.client.scheduled.task.name=com.liferay.lcs.task.CommandMessageTask",
	service = ScheduledTask.class
)
public class CommandMessageTask extends BaseScheduledTask {

	public CommandMessageTask(String key, LCSGatewayClient lcsGatewayClient) {
		_key = key;
		_lcsGatewayClient = lcsGatewayClient;

		if (_log.isTraceEnabled()) {
			_log.trace("Initialized " + this);
		}
	}

	@Override
	public Scope getScope() {
		return Scope.NODE;
	}

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			String errorMessage = "Unable to get messages from LCS";

			if (e instanceof JSONWebServiceException) {
				_log.error(errorMessage);
			}
			else {
				_log.error(errorMessage, e);
			}
		}
	}

	@Activate
	protected void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		_initDigitalSignature(lcsConfiguration);

		_initCommands();
	}

	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running command message task");
		}

		if (!_lcsGatewayClient.isAvailable()) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Aborting command messages retrieving. LCS gateway is " +
						"not available.");
			}

			return;
		}

		if (_log.isTraceEnabled()) {
			_log.trace("Checking messages for " + _key);
		}

		List<Message> messages = _lcsGatewayClient.getMessages(_key);

		for (Message message : messages) {
			if (!(message instanceof CommandMessage)) {
				_log.error("Unknown message " + message);

				continue;
			}

			CommandMessage commandMessage = (CommandMessage)message;

			if (_log.isTraceEnabled()) {
				_log.trace("Processing message: " + commandMessage);
			}

			CommandValidator commandValidator = _getCommandValidator(
				commandMessage);

			if (!commandValidator.isValid()) {
				sendErrorResponseMessage(
					commandMessage, commandValidator.getErrorMessage());

				continue;
			}

			Class<?> commandMessageClass = commandMessage.getClass();

			Command<? extends CommandMessage> command = _commands.get(
				commandMessageClass.getName());

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Executing command: " + commandMessageClass.getName());
			}

			Command<CommandMessage> castCommand =
				(Command<CommandMessage>)command;

			castCommand.execute(commandMessage);
		}
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

	private CommandValidator _getCommandValidator(
		CommandMessage commandMessage) {

		if (_log.isTraceEnabled()) {
			_log.trace("Verifying digital signature");
		}

		try {
			if (_digitalSignature.verifyMessage(
					_getLCSClientBuildNumber(), commandMessage)) {

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

	private int _getLCSClientBuildNumber() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		return lcsConfiguration.lcsClientBuildNumber();
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

	private static final Log _log = LogFactoryUtil.getLog(
		CommandMessageTask.class);

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

	private final String _key;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	private final LCSGatewayClient _lcsGatewayClient;

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

}