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
import com.liferay.lcs.security.DigitalSignature;
import com.liferay.lcs.util.LCSConnectionManager;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.lcs.util.ResponseMessageUtil;
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

	@Override
	public void receive(Message message) {
		if (_log.isTraceEnabled()) {
			_log.trace("Receiving message: " + message.getPayload());
		}

		CommandMessage commandMessage = (CommandMessage)message.getPayload();

		String error = null;

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
					Command command = _commands.get(
						commandMessage.getCommandType());

					if (_log.isDebugEnabled()) {
						_log.debug(
							"Executing command: " +
								commandMessage.getCommandType());
					}

					command.execute(commandMessage);
				}
				catch (Exception e) {
					_log.error(e, e);

					error = e.getMessage();
				}
			}
			else {
				error = "Unable to verify digital signature";

				_log.error(error + ": " + commandMessage);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw e;
		}

		if (error != null) {
			ResponseMessage responseMessage =
				ResponseMessageUtil.createResponseMessage(
					commandMessage, null, error);

			try {
				if (_log.isTraceEnabled()) {
					_log.trace("Sending response message");
				}

				_lcsConnectionManager.sendMessage(responseMessage);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	public void setCommands(Map<String, Command> commands) {
		_commands = commands;
	}

	public void setDigitalSignature(DigitalSignature digitalSignature) {
		_digitalSignature = digitalSignature;
	}

	public void setLCSConnectionManager(
		LCSConnectionManager lcsConnectionManager) {

		_lcsConnectionManager = lcsConnectionManager;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommandMessageListener.class);

	private Map<String, Command> _commands;
	private DigitalSignature _digitalSignature;
	private LCSConnectionManager _lcsConnectionManager;

}