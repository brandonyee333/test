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

import com.liferay.lcs.advisor.LCSKeyAdvisor;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class LCSMessageBusMessageListener extends BaseMessageListener {

	public LCSMessageBusMessageListener(
		LCSGatewayClient lcsGatewayClient, LCSKeyAdvisor lcsKeyAdvisor) {

		_lcsGatewayClient = lcsGatewayClient;
		_lcsKeyAdvisor = lcsKeyAdvisor;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		MessageBusMessage messageBusMessage = new MessageBusMessage();

		messageBusMessage.setDestinationName(
			message.getString("LCS_DESTINATION"));
		messageBusMessage.setKey(_lcsKeyAdvisor.getKey());
		messageBusMessage.setResponseDestinationName(
			message.getResponseDestinationName());
		messageBusMessage.setResponseId(message.getResponseId());

		Map<String, Object> values = message.getValues();

		for (String key : values.keySet()) {
			Object value = message.get(key);

			Class<? extends Object> clazz = value.getClass();

			if (clazz.isPrimitive() || isWrapper(clazz) ||
				clazz.equals(String.class)) {

				messageBusMessage.put(key, message.get(key));
			}
		}

		messageBusMessage.setPayload(message.getPayload());

		_lcsGatewayClient.sendMessage(messageBusMessage);
	}

	protected boolean isWrapper(Class<? extends Object> clazz) {
		if ((clazz == Byte.class) || (clazz == Boolean.class) ||
			(clazz == Character.class) || (clazz == Double.class) ||
			(clazz == Float.class) || (clazz == Long.class) ||
			(clazz == Integer.class) || (clazz == Short.class)) {

			return true;
		}

		return false;
	}

	private final LCSGatewayClient _lcsGatewayClient;
	private final LCSKeyAdvisor _lcsKeyAdvisor;

}