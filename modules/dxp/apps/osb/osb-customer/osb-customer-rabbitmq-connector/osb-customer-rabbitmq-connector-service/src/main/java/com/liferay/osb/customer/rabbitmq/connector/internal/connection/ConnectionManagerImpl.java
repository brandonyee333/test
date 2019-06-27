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

package com.liferay.osb.customer.rabbitmq.connector.internal.connection;

import com.liferay.osb.customer.rabbitmq.connector.configuration.RabbitMQConnectorConfigurationValues;
import com.liferay.osb.customer.rabbitmq.connector.connection.ConnectionManager;
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
 * @author Yury Butrymovich
 * @author Amos Fong
 * @author Vishal Reddy
 */
@Component(immediate = true, service = ConnectionManager.class)
public class ConnectionManagerImpl implements ConnectionManager {

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
		return createChannel(10);
	}

	@Override
	public Channel createChannel(int prefetchCount) throws IOException {
		if (!isConnected()) {
			connect();
		}

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

		if (!_connection.isOpen()) {
			_log.error(
				"Connection is unexpectedly closed",
				_connection.getCloseReason());

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
		ConnectionManagerImpl.class);

	private Connection _connection;

}