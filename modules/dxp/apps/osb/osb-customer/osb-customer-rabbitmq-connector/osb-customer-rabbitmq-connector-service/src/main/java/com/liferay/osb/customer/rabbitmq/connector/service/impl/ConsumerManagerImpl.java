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

import com.liferay.osb.customer.rabbitmq.connector.configuration.RabbitMQConnectorConfigurationValues;
import com.liferay.osb.customer.rabbitmq.connector.connection.ConnectionManager;
import com.liferay.osb.customer.rabbitmq.connector.consumer.Consumer;
import com.liferay.osb.customer.rabbitmq.connector.exception.DuplicateConsumerException;
import com.liferay.osb.customer.rabbitmq.connector.internal.consumer.OSBConsumer;
import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.osb.customer.rabbitmq.connector.service.ConsumerManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 * @author Vishal Reddy
 */
@Component(immediate = true)
public class ConsumerManagerImpl implements ConsumerManager {

	public void addConsumer(String queue) throws Exception {
		if (_consumers.containsKey(queue)) {
			throw new DuplicateConsumerException();
		}

		Consumer consumer = createConsumer(queue);

		_consumers.put(queue, consumer);

		if (_log.isInfoEnabled()) {
			_log.info("Registered consumer on " + queue);
		}
	}

	public void consumeMessage() {
		if (!RabbitMQConnectorConfigurationValues.RABBITMQ_DEBUG_MODE_ENABLED) {
			return;
		}

		for (Map.Entry<String, Consumer> entry : _consumers.entrySet()) {
			try {
				String queue = entry.getKey();
				Consumer consumer = entry.getValue();

				Channel channel = consumer.getChannel();

				GetResponse getResponse = channel.basicGet(queue, false);

				consumer.handleDelivery(
					null, getResponse.getEnvelope(), getResponse.getProps(),
					getResponse.getBody());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	public void consumeMessages(
			String queue, long messageCount,
			final MessageProcessor messageProcessor)
		throws Exception {

		Channel channel = null;

		try {
			channel = _connectionManager.createChannel();

			if (messageCount <= 0) {
				messageCount = channel.messageCount(queue);
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"Consuming " + messageCount + " messages on " + queue);
			}

			for (int i = 0; i < messageCount; i++) {
				Consumer consumer = new OSBConsumer(channel, queue) {

					@Override
					protected void processMessage(
						String routingKey, String message,
						Map<String, Object> properties) {

						messageProcessor.process(
							routingKey, message, properties);
					}

				};

				GetResponse getResponse = channel.basicGet(queue, false);

				if (getResponse == null) {
					break;
				}

				consumer.handleDelivery(
					null, getResponse.getEnvelope(), getResponse.getProps(),
					getResponse.getBody());
			}
		}
		finally {
			if (channel != null) {
				channel.close();
			}
		}
	}

	public void deleteConsumer(String queue) {
		Consumer consumer = _consumers.remove(queue);

		if (consumer == null) {
			return;
		}

		closeChannel(consumer.getChannel());

		if (_log.isInfoEnabled()) {
			_log.info("Unregistered consumer on " + queue);
		}
	}

	public Consumer getConsumer(String queue) {
		return _consumers.get(queue);
	}

	public void resetChannels() throws Exception {
		for (Map.Entry<String, Consumer> entry : _consumers.entrySet()) {
			String queue = entry.getKey();
			Consumer consumer = entry.getValue();

			closeChannel(consumer.getChannel());

			Consumer newConsumer = createConsumer(queue);

			entry.setValue(newConsumer);

			if (_log.isInfoEnabled()) {
				_log.info("Reset channel for " + queue);
			}
		}
	}

	protected void closeChannel(Channel channel) {
		if (channel.isOpen()) {
			try {
				channel.close();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Closing channel " + channel.getChannelNumber());
		}
	}

	protected Consumer createConsumer(String queue) throws Exception {
		Channel channel = _connectionManager.createChannel();

		Consumer consumer = new OSBConsumer(channel, queue);

		if (!RabbitMQConnectorConfigurationValues.RABBITMQ_DEBUG_MODE_ENABLED) {
			channel.basicConsume(queue, false, consumer);
		}

		return consumer;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConsumerManagerImpl.class);

	@Reference
	private ConnectionManager _connectionManager;

	private final Map<String, Consumer> _consumers = new ConcurrentHashMap<>();

}