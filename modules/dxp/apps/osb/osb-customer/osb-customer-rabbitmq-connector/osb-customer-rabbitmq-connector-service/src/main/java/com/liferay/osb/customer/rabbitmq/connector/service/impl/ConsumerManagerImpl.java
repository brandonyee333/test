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

	public void addConsumer(MessageProcessor messageProcessor)
		throws Exception {

		if (_consumers.containsKey(messageProcessor)) {
			return;
		}

		Consumer consumer = createConsumer(messageProcessor);

		_consumers.put(messageProcessor, consumer);

		if (_log.isInfoEnabled()) {
			Class<?> clazz = messageProcessor.getClass();

			_log.info("Registered " + clazz.getName());
		}
	}

	public void consumeMessage() {
		if (!RabbitMQConnectorConfigurationValues.RABBITMQ_DEBUG_MODE_ENABLED) {
			return;
		}

		for (Map.Entry<MessageProcessor, Consumer> entry :
				_consumers.entrySet()) {

			try {
				MessageProcessor messageProcessor = entry.getKey();
				Consumer consumer = entry.getValue();

				Channel channel = consumer.getChannel();

				GetResponse getResponse = channel.basicGet(
					messageProcessor.getQueue(), false);

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
			MessageProcessor messageProcessor, long messageCount)
		throws Exception {

		Channel channel = null;

		try {
			channel = _connectionManager.createChannel();

			if (messageCount <= 0) {
				messageCount = channel.messageCount(
					messageProcessor.getQueue());
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"Consuming " + messageCount + " messages on " +
						messageProcessor.getQueue());
			}

			for (int i = 0; i < messageCount; i++) {
				Consumer consumer = new OSBConsumer(channel, messageProcessor);

				GetResponse getResponse = channel.basicGet(
					messageProcessor.getQueue(), false);

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

	public void deleteConsumer(MessageProcessor messageProcessor) {
		Consumer consumer = _consumers.remove(messageProcessor);

		if (consumer == null) {
			return;
		}

		closeChannel(consumer.getChannel());

		if (_log.isInfoEnabled()) {
			Class<?> clazz = messageProcessor.getClass();

			_log.info("Unregistered " + clazz.getName());
		}
	}

	public Consumer getConsumer(MessageProcessor messageProcessor) {
		return _consumers.get(messageProcessor);
	}

	public void resetChannels() throws Exception {
		for (Map.Entry<MessageProcessor, Consumer> entry :
				_consumers.entrySet()) {

			MessageProcessor messageProcessor = entry.getKey();
			Consumer consumer = entry.getValue();

			closeChannel(consumer.getChannel());

			Consumer newConsumer = createConsumer(messageProcessor);

			entry.setValue(newConsumer);

			if (_log.isInfoEnabled()) {
				Class<?> clazz = messageProcessor.getClass();

				_log.info("Reset channel for " + clazz.getName());
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

	protected Consumer createConsumer(MessageProcessor messageProcessor)
		throws Exception {

		Channel channel = _connectionManager.createChannel(
			messageProcessor.getPrefetchCount());

		Consumer consumer = new OSBConsumer(channel, messageProcessor);

		if (!RabbitMQConnectorConfigurationValues.RABBITMQ_DEBUG_MODE_ENABLED) {
			channel.basicConsume(messageProcessor.getQueue(), false, consumer);
		}

		return consumer;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConsumerManagerImpl.class);

	@Reference
	private ConnectionManager _connectionManager;

	private final Map<MessageProcessor, Consumer> _consumers =
		new ConcurrentHashMap<>();

}