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

package com.liferay.osb.customer.rabbitmq.connector.internal.publisher;

import com.liferay.osb.customer.rabbitmq.connector.connection.ConnectionManager;
import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.portal.kernel.json.JSONObject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Vishal Reddy
 */
@Component(immediate = true)
public class MessagePublisherImpl implements MessagePublisher {

	@Override
	public void sendMessage(
			String exchange, String routingKey, JSONObject messageJSONObject)
		throws IOException, TimeoutException {

		Channel channel = null;

		try {
			channel = _connectionManager.createChannel();

			String messageJSONObjectString = messageJSONObject.toString();

			channel.basicPublish(
				exchange, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,
				messageJSONObjectString.getBytes());
		}
		finally {
			if (channel != null) {
				channel.close();
			}
		}
	}

	@Reference
	private ConnectionManager _connectionManager;

}