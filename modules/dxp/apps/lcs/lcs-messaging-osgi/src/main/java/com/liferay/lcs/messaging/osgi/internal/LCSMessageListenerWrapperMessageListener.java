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

import com.liferay.lcs.messaging.bus.LCSMessageListener;
import com.liferay.lcs.messaging.bus.LCSMessageListenerException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.MessageListenerException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class LCSMessageListenerWrapperMessageListener
	implements MessageListener {

	public LCSMessageListenerWrapperMessageListener(
		LCSMessageListener lcsMessageListener) {

		_lcsMessageListener = lcsMessageListener;
	}

	public LCSMessageListener getLCSMessageListener() {
		return _lcsMessageListener;
	}

	@Override
	public void receive(Message message) throws MessageListenerException {
		try {
			Map<String, String> metadata = new HashMap<>();

			Map<String, Object> values = message.getValues();

			for (Map.Entry<String, Object> entry : values.entrySet()) {
				metadata.put(entry.getKey(), String.valueOf(entry.getValue()));
			}

			_lcsMessageListener.receive(
				message.getDestinationName(), metadata,
				String.valueOf(message.getPayload()),
				message.getResponseDestinationName());
		}
		catch (LCSMessageListenerException lcsmle) {
			throw new MessageListenerException(lcsmle);
		}
	}

	private final LCSMessageListener _lcsMessageListener;

}