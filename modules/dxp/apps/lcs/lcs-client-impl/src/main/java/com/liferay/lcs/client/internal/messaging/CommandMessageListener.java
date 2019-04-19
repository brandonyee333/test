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

package com.liferay.lcs.client.internal.messaging;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.internal.command.CheckHeartbeatCommand;
import com.liferay.lcs.client.internal.command.Command;
import com.liferay.lcs.client.internal.command.DownloadPatchCommand;
import com.liferay.lcs.client.internal.command.ExecuteScriptCommand;
import com.liferay.lcs.client.internal.command.ScheduleMessageListenersCommand;
import com.liferay.lcs.client.internal.command.ScheduleTasksCommand;
import com.liferay.lcs.client.internal.command.SendInstallationEnvironmentCommand;
import com.liferay.lcs.client.internal.command.SendPatchesCommand;
import com.liferay.lcs.client.internal.command.SendPortalPropertiesCommand;
import com.liferay.lcs.client.internal.command.SignOffCommand;
import com.liferay.lcs.client.internal.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.internal.constants.LCSDestinationNames;
import com.liferay.lcs.client.internal.util.LCSUtil;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.CheckHeartbeatCommandMessage;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.DownloadPatchCommandMessage;
import com.liferay.lcs.messaging.ErrorResponseMessage;
import com.liferay.lcs.messaging.ExecuteScriptCommandMessage;
import com.liferay.lcs.messaging.ScheduleMessageListenersCommandMessage;
import com.liferay.lcs.messaging.ScheduleTasksCommandMessage;
import com.liferay.lcs.messaging.SendInstallationEnvironmentCommandMessage;
import com.liferay.lcs.messaging.SendPatchesCommandMessage;
import com.liferay.lcs.messaging.SendPortalPropertiesCommandMessage;
import com.liferay.lcs.messaging.SignOffCommandMessage;
import com.liferay.lcs.messaging.security.DigitalSignature;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(
	immediate = true,
	property = "destination.name=" + LCSDestinationNames.LCS_COMMANDS,
	service = MessageListener.class
)
public class CommandMessageListener implements MessageListener {

	public CommandMessageListener() {
	}

	public CommandMessageListener(
		Map<String, Command<? extends CommandMessage>> commands,
		DigitalSignature digitalSignature, LCSGatewayClient lcsGatewayClient) {

		_commands = commands;
		_digitalSignature = digitalSignature;
		_lcsGatewayClient = lcsGatewayClient;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void receive(Message message) {
		if (_log.isTraceEnabled()) {
			_log.trace("Receiving message: " + message.getPayload());
		}

		CommandMessage commandMessage = (CommandMessage)message.getPayload();

		String responseErrorMessage = null;

		if (_log.isTraceEnabled()) {
			_log.trace("Verifying digital signature");
		}

		try {
			if (_digitalSignature.verifyMessage(
					LCSUtil.getLCSPortletBuildNumber(), commandMessage)) {

				if (_log.isTraceEnabled()) {
					_log.trace("Verified digital signature");
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

				return;
			}
			else {
				responseErrorMessage =
					"Unable to verify digital signature of a message";
			}
		}
		catch (Exception e) {
			responseErrorMessage = e.getMessage();
		}

		if (responseErrorMessage != null) {
			_log.error(responseErrorMessage);

			ErrorResponseMessage errorResponseMessage = getErrorResponseMessage(
				commandMessage, responseErrorMessage);

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
	}

	@Activate
	protected void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		_initDigitalSignature(lcsConfiguration);

		_initCommands();
	}

	@Deactivate
	protected void deactivate() {
		if (_digitalSignatureComponentInstance != null) {
			_digitalSignatureComponentInstance.dispose();
		}
	}

	protected ErrorResponseMessage getErrorResponseMessage(
		CommandMessage commandMessage, String errorMessage) {

		ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage();

		errorResponseMessage.setCorrelationId(
			commandMessage.getCorrelationId());
		errorResponseMessage.setCreateTime(System.currentTimeMillis());
		errorResponseMessage.setErrorMessage(errorMessage);
		errorResponseMessage.setKey(commandMessage.getKey());

		return errorResponseMessage;
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
		Dictionary<String, String> dictionary =
			new HashMapDictionary<String, String>() {
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

		_digitalSignatureComponentInstance =
			_digitalSignatureComponentFactory.newInstance(dictionary);

		_digitalSignature =
			(DigitalSignature)_digitalSignatureComponentInstance.getInstance();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommandMessageListener.class);

	@Reference
	private CheckHeartbeatCommand _checkHeartbeatCommand;

	private Map<String, Command<? extends CommandMessage>> _commands =
		new HashMap<>();
	private DigitalSignature _digitalSignature;

	@Reference(target = "(component.factory=DigitalSignature)")
	private ComponentFactory _digitalSignatureComponentFactory;

	private ComponentInstance _digitalSignatureComponentInstance;

	@Reference
	private DownloadPatchCommand _downloadPatchCommand;

	@Reference
	private ExecuteScriptCommand _executeScriptCommand;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

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