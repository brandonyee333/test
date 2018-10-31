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

package com.liferay.osb.customer.rabbitmq.connector.internal.consumer;

import com.liferay.osb.customer.rabbitmq.connector.configuration.RabbitMQConnectorConfigurationValues;
import com.liferay.osb.customer.rabbitmq.connector.connection.ConnectionManager;
import com.liferay.osb.customer.rabbitmq.connector.consumer.Consumer;
import com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerManager;
import com.liferay.osb.customer.rabbitmq.connector.exception.DuplicateConsumerException;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
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

	@Override
	public void addConsumer(String queue, MessageRouter messageRouter)
		throws Exception {

		if (_consumers.containsKey(queue)) {
			throw new DuplicateConsumerException();
		}

		Consumer consumer = createConsumer(queue, messageRouter);

		_consumers.put(queue, consumer);

		if (_log.isInfoEnabled()) {
			_log.info("Registered consumer on " + queue);
		}
	}

	@Override
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

	@Override
	public void consumeMessages(
			String queue, long messageCount, final MessageRouter messageRouter)
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
				GetResponse getResponse = channel.basicGet(queue, false);

				if (getResponse == null) {
					break;
				}

				Consumer consumer = new OSBConsumer(channel, messageRouter) {

					@Override
					protected void route(
						String routingKey, String message,
						Map<String, Object> properties) {

						messageRouter.route(routingKey, message, properties);
					}

				};

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

	@Override
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

	@Override
	public Consumer getConsumer(String queue) {
		return _consumers.get(queue);
	}

	@Override
	public void resetChannels() throws Exception {
		for (Map.Entry<String, Consumer> entry : _consumers.entrySet()) {
			String queue = entry.getKey();

			Consumer consumer = entry.getValue();

			closeChannel(consumer.getChannel());

			Consumer newConsumer = createConsumer(
				queue, consumer.getMessageRouter());

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

	protected Consumer createConsumer(String queue, MessageRouter messageRouter)
		throws Exception {

		Channel channel = _connectionManager.createChannel();

		Consumer consumer = new OSBConsumer(channel, messageRouter);

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