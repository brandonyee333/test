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

package com.liferay.lcs.messaging.osgi.internal;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = MessageRouterMessageListener.class)
public class LCSMessageRouterMessageListener
	extends BaseMessageListener implements MessageRouterMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		message.put("LCS_DESTINATION", message.getDestinationName());

		message.setDestinationName(_LCS_MESSAGE_BUS_DESTINATION_NAME);

		_messageBus.sendMessage(_LCS_MESSAGE_BUS_DESTINATION_NAME, message);
	}

	private static final String _LCS_MESSAGE_BUS_DESTINATION_NAME =
		"liferay/lcs_message_bus";

	@Reference
	private MessageBus _messageBus;

}