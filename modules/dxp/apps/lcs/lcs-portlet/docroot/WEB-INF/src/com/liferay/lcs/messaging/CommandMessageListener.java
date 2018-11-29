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

package com.liferay.lcs.messaging;

import com.liferay.lcs.command.Command;
import com.liferay.lcs.messaging.security.DigitalSignature;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class CommandMessageListener implements MessageListener {

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

		String errorMessage = null;

		if (_log.isTraceEnabled()) {
			_log.trace("Verifying digital signature");
		}

		try {
			if (_digitalSignature.verifyMessage(
					LCSUtil.getLCSPortletBuildNumber(), commandMessage)) {

				if (_log.isTraceEnabled()) {
					_log.trace("Verified digital signature");
				}

				try {
					Class<?> commandMessageClass = commandMessage.getClass();

					Command<? extends CommandMessage> command = _commands.get(
						commandMessageClass.getName());

					if (_log.isDebugEnabled()) {
						_log.debug(
							"Executing command: " +
								commandMessageClass.getName());
					}

					Command<CommandMessage> castCommand =
						(Command<CommandMessage>)command;

					castCommand.execute(commandMessage);
				}
				catch (Exception e) {
					_log.error(e, e);

					errorMessage = e.getMessage();
				}
			}
			else {
				_log.error("Unable to verify digital signature of a message");
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw e;
		}

		if (errorMessage != null) {
			ErrorResponseMessage errorResponseMessage = getErrorResponseMessage(
				commandMessage, errorMessage);

			try {
				if (_log.isTraceEnabled()) {
					_log.trace("Sending response message");
				}

				_lcsGatewayClient.sendMessage(errorResponseMessage);
			}
			catch (Exception e) {
				String logErrorMessage =
					"Unable to send error response message to LCS";

				if (e instanceof JSONWebServiceException) {
					_log.error(logErrorMessage);
				}
				else {
					_log.error(logErrorMessage, e);
				}
			}
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

	private static final Log _log = LogFactoryUtil.getLog(
		CommandMessageListener.class);

	private final Map<String, Command<? extends CommandMessage>> _commands;
	private final DigitalSignature _digitalSignature;
	private final LCSGatewayClient _lcsGatewayClient;

}