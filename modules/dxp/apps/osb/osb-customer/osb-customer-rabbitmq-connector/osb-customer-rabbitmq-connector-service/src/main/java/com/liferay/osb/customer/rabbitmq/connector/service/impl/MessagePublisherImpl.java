/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.rabbitmq.connector.service.impl;

import com.liferay.osb.customer.rabbitmq.connector.connection.ConnectionManager;
import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

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

		if (!_connectionManager.isConnected()) {
			_log.error("The RabbitMQ Connection has failed");
		}

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

	private static final Log _log = LogFactoryUtil.getLog(
		MessagePublisherImpl.class);

	@Reference
	private ConnectionManager _connectionManager;

}