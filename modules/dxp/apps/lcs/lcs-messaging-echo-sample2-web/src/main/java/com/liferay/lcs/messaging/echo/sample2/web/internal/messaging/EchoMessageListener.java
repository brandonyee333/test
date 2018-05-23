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

package com.liferay.lcs.messaging.echo.sample2.web.internal.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true, property = "destination.name=lcs_echo",
	service = MessageListener.class
)
public class EchoMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Received message:" + message);
		}

		String payload = (String)message.getPayload();

		if (_log.isInfoEnabled()) {
			_log.info("Message payload: " + payload);
		}

		String responseDestinationName = message.getResponseDestinationName();

		if ((responseDestinationName != null) &&
			(responseDestinationName.length() > 0)) {

			Message responseMessage = MessageBusUtil.createResponseMessage(
				message);

			StringBuilder sb = new StringBuilder(payload);

			sb.reverse();

			responseMessage.setPayload(sb.toString());

			MessageBusUtil.sendMessage(
				responseDestinationName, responseMessage);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EchoMessageListener.class);

}