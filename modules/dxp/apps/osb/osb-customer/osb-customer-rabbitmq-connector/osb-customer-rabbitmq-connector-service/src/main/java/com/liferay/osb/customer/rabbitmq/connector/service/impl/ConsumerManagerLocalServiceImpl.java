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
import com.liferay.osb.customer.rabbitmq.connector.connection.RabbitMQConnectionManager;
import com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerBag;
import com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerBagImpl;
import com.liferay.osb.customer.rabbitmq.connector.consumer.RabbitMQConsumerDelegator;
import com.liferay.osb.customer.rabbitmq.connector.service.ConsumerManagerLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
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
public class ConsumerManagerLocalServiceImpl
	implements ConsumerManagerLocalService {

	public void activateConsumer(String rabbitMQConsumerKey) throws Exception {
		ConsumerBag consumerBag = _consumers.get(rabbitMQConsumerKey);

		if (consumerBag.isActive()) {
			return;
		}

		Channel channel = createChannel(
			consumerBag.getQueue(), consumerBag.getPrefetchCount(),
			consumerBag.getRabbitMQConsumer());

		consumerBag.setChannel(channel);

		if (_log.isInfoEnabled()) {
			_log.info("Activated consumer " + rabbitMQConsumerKey);
		}
	}

	@Override
	public void connect() {
		_rabbitMQConnectionManager.connect();
	}

	public void consumeMessage() {
		if (!RabbitMQConnectorConfigurationValues.RABBITMQ_DEBUG_MODE_ENABLED) {
			return;
		}

		for (ConsumerBag consumerBag : _consumers.values()) {
			try {
				Channel channel = consumerBag.getChannel();

				Consumer consumer = new RabbitMQConsumerDelegator(
					channel, consumerBag.getRabbitMQConsumer());

				GetResponse getResponse = channel.basicGet(
					consumerBag.getQueue(), false);

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
			String queue, long messageCount, Object rabbitMQConsumer)
		throws Exception {

		Channel channel = null;

		try {
			channel = _rabbitMQConnectionManager.createChannel();

			if (messageCount <= 0) {
				messageCount = channel.messageCount(queue);
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					"Consuming " + messageCount + " messages on " + queue);
			}

			for (int i = 0; i < messageCount; i++) {
				Consumer consumer = new RabbitMQConsumerDelegator(
					channel, rabbitMQConsumer);

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

	public void deactivateConsumer(String rabbitMQConsumerKey) {
		ConsumerBag consumerBag = _consumers.get(rabbitMQConsumerKey);

		if (!consumerBag.isActive()) {
			return;
		}

		closeChannel(consumerBag.getChannel());

		consumerBag.setChannel(null);

		if (_log.isInfoEnabled()) {
			_log.info("Deactivated consumer " + rabbitMQConsumerKey);
		}
	}

	@Override
	public void disconnect() {
		_rabbitMQConnectionManager.disconnect();
	}

	public Map<String, ConsumerBag> getConsumersMap() {
		return _consumers;
	}

	@Override
	public boolean isConnected() {
		return _rabbitMQConnectionManager.isConnected();
	}

	@Override
	public void reconnect() {
		_rabbitMQConnectionManager.reconnect();
	}

	public String registerConsumer(
			String queue, int prefetchCount, Object rabbitMQConsumer)
		throws Exception {

		Channel channel = createChannel(queue, prefetchCount, rabbitMQConsumer);
		Class<?> rabbitMQClass = rabbitMQConsumer.getClass();

		ConsumerBag consumerBag = new ConsumerBagImpl(
			rabbitMQConsumer, channel, prefetchCount, queue);

		String rabbitMQConsumerKey =
			rabbitMQClass.getName() + StringPool.POUND + queue;

		_consumers.put(rabbitMQConsumerKey, consumerBag);

		if (_log.isInfoEnabled()) {
			_log.info("Registered " + consumerBag.toString());
		}

		return rabbitMQConsumerKey;
	}

	public void resetChannels() throws Exception {
		for (ConsumerBag consumerBag : _consumers.values()) {
			closeChannel(consumerBag.getChannel());

			Channel channel = createChannel(
				consumerBag.getQueue(), consumerBag.getPrefetchCount(),
				consumerBag.getRabbitMQConsumer());

			consumerBag.setChannel(channel);

			if (_log.isInfoEnabled()) {
				_log.info("Reset channel for " + consumerBag.toString());
			}
		}
	}

	public void unregisterConsumer(String rabbitMQConsumerKey) {
		ConsumerBag consumerBag = _consumers.remove(rabbitMQConsumerKey);

		if (consumerBag == null) {
			if (_log.isInfoEnabled()) {
				_log.info("No consumer exists with key " + rabbitMQConsumerKey);
			}

			return;
		}

		closeChannel(consumerBag.getChannel());

		if (_log.isInfoEnabled()) {
			_log.info("Unregistered " + consumerBag.toString());
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

	protected Channel createChannel(
			String queue, int prefetchCount, Object rabbitMQConsumer)
		throws Exception {

		Channel channel = _rabbitMQConnectionManager.createChannel(
			prefetchCount);

		if (!RabbitMQConnectorConfigurationValues.RABBITMQ_DEBUG_MODE_ENABLED) {
			Consumer consumer = new RabbitMQConsumerDelegator(
				channel, rabbitMQConsumer);

			channel.basicConsume(queue, false, consumer);
		}

		return channel;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConsumerManagerLocalServiceImpl.class);

	private final Map<String, ConsumerBag> _consumers =
		new ConcurrentHashMap<>();

	@Reference
	private RabbitMQConnectionManager _rabbitMQConnectionManager;

}