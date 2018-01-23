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

package com.liferay.osb.customer.rabbitmq.connector.connection;

import com.liferay.osb.customer.rabbitmq.connector.configuration.RabbitMQConnectorConfigurationValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Vishal Reddy
 */
@Component
public class RabbitMQConnectionManagerImpl
	implements RabbitMQConnectionManager {

	@Activate
	@Override
	public void connect() {
		String host = RabbitMQConnectorConfigurationValues.RABBITMQ_SERVER_HOST;
		int port = RabbitMQConnectorConfigurationValues.RABBITMQ_SERVER_PORT;

		ConnectionFactory connectionFactory = new ConnectionFactory();

		connectionFactory.setAutomaticRecoveryEnabled(true);
		connectionFactory.setHost(host);
		connectionFactory.setPassword(
			RabbitMQConnectorConfigurationValues.RABBITMQ_LOGIN_PASSWORD);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(
			RabbitMQConnectorConfigurationValues.RABBITMQ_LOGIN_USERNAME);

		try {
			if (_log.isInfoEnabled()) {
				_log.info("Connecting to RabbitMQ at " + host + ":" + port);
			}

			_connection = connectionFactory.newConnection();

			if (_log.isInfoEnabled()) {
				_log.info("Connection successful");
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			disconnect();
		}
	}

	@Override
	public Channel createChannel() throws IOException {
		return _connection.createChannel(1);
	}

	@Override
	public Channel createChannel(int prefetchCount) throws IOException {
		Channel channel = _connection.createChannel();

		if (prefetchCount > 0) {
			channel.basicQos(prefetchCount);
		}

		return channel;
	}

	@Deactivate
	@Override
	public void disconnect() {
		try {
			if (_connection != null) {
				_connection.close();
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isInfoEnabled()) {
			_log.info("Disconnected from RabbitMQ");
		}
	}

	@Override
	public boolean isConnected() {
		if (_connection == null) {
			return false;
		}

		return true;
	}

	@Override
	public void reconnect() {
		disconnect();

		connect();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RabbitMQConnectionManagerImpl.class);

	private Connection _connection;

}