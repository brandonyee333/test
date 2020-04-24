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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Timothy Bell
 */
@Component(immediate = true, service = LoopAMQPUtil.class)
public class LoopAMQPUtil {

	public static void publish(String routingKey, String message)
		throws IOException, TimeoutException {

		if (!LoopWebConfigurationValues.RABBIT_MQ_PUBLISH_ENABLED) {
			return;
		}

		Channel channel = null;

		try {
			channel = createChannel();

			channel.basicPublish(
				LoopWebConfigurationValues.RABBIT_MQ_EXCHANGE_NAME, routingKey,
				null, message.getBytes());
		}
		finally {
			if (channel != null) {
				channel.close();
			}
		}
	}

	protected static Channel createChannel()
		throws IOException, TimeoutException {

		return getConnection().createChannel();
	}

	protected static Connection getConnection()
		throws IOException, TimeoutException {

		if ((_connection == null) || !_connection.isOpen()) {
			ConnectionFactory connectionFactory = new ConnectionFactory();

			connectionFactory.setHost(
				LoopWebConfigurationValues.RABBIT_MQ_EXCHANGE_HOSTNAME);
			connectionFactory.setPassword(
				LoopWebConfigurationValues.RABBIT_MQ_EXCHANGE_PASSWORD);
			connectionFactory.setUsername(
				LoopWebConfigurationValues.RABBIT_MQ_EXCHANGE_USERNAME);

			_connection = connectionFactory.newConnection();
		}

		return _connection;
	}

	@Activate
	protected void activate() {
		if (!LoopWebConfigurationValues.RABBIT_MQ_CONSUME_ENABLED) {
			return;
		}

		try {
			_channel = createChannel();

			_channel.basicConsume(
				LoopWebConfigurationValues.RABBIT_MQ_QUEUE, false,
				new LoopConsumer(_channel));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Deactivate
	protected void deactivate() {
		try {
			if ((_channel != null) && _channel.isOpen()) {
				_channel.close();
			}

			if ((_connection != null) && _connection.isOpen()) {
				_connection.close();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(LoopAMQPUtil.class);

	private static Channel _channel;
	private static Connection _connection;

}