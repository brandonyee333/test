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

package com.liferay.rabbitmq.connection;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.rabbitmq.util.PortletPropsValues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author Yury Butrymovich
 * @author Amos Fong
 */
public class RabbitMQConnectionManager {

	public static RabbitMQConnectionManager getInstance() {
		if (_instance == null) {
			_instance = new RabbitMQConnectionManager();
		}

		return _instance;
	}

	public void connect() {
		String host = PortletPropsValues.RABBITMQ_SERVER_HOST;
		int port = PortletPropsValues.RABBITMQ_SERVER_PORT;

		ConnectionFactory connectionFactory = new ConnectionFactory();

		connectionFactory.setAutomaticRecoveryEnabled(true);
		connectionFactory.setHost(host);
		connectionFactory.setPassword(
			PortletPropsValues.RABBITMQ_LOGIN_PASSWORD);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(
			PortletPropsValues.RABBITMQ_LOGIN_USERNAME);

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

	public Channel createChannel(int prefetchCount) throws IOException {
		Channel channel = _connection.createChannel();

		if (prefetchCount > 0) {
			channel.basicQos(prefetchCount);
		}

		return channel;
	}

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

	public void reconnect() {
		disconnect();

		connect();
	}

	private RabbitMQConnectionManager() {
	}

	private static Log _log = LogFactoryUtil.getLog(
		RabbitMQConnectionManager.class);

	private static RabbitMQConnectionManager _instance;

	private Connection _connection;

}