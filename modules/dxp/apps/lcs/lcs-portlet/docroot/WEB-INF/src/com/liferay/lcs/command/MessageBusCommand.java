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

package com.liferay.lcs.command;

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.MessageBusCommandMessage;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

/**
 * @author Riccardo Ferrari
 */
public class MessageBusCommand implements Command {

	@Override
	public void execute(CommandMessage commandMessage) throws PortalException {
		if (_log.isTraceEnabled()) {
			_log.trace("Executing message bus command");
		}

		MessageBusCommandMessage messageBusCommandMessage =
			(MessageBusCommandMessage)commandMessage;

		String destinationName = messageBusCommandMessage.getDestinationName();

		Message message = new Message();

		message.setDestinationName(destinationName);
		message.setPayload(messageBusCommandMessage.getPayload());
		message.setResponse(messageBusCommandMessage.getResponse());
		message.setResponseDestinationName(
			messageBusCommandMessage.getResponseDestinationName());
		message.setResponseId(messageBusCommandMessage.getResponseId());
		message.setValues(messageBusCommandMessage.getValues());

		MessageBusUtil.sendMessage(destinationName, message);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageBusCommand.class);

}