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

package com.liferay.osb.customer.rabbitmq.connector.consumer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MapUtil;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class RabbitMQConsumerDelegator extends DefaultConsumer {

	public RabbitMQConsumerDelegator(Channel channel, Object delegate)
		throws Exception {

		super(channel);

		_delegate = delegate;

		Class<?> clazz = delegate.getClass();

		_delegateMethod = clazz.getMethod(
			"parse", String.class, String.class, Map.class);
	}

	@Override
	public void handleDelivery(
		String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
		byte[] body) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Received message " + envelope.getDeliveryTag() +
					" with routing key " + envelope.getRoutingKey());
		}

		String message = null;

		try {
			message = new String(body, "UTF-8");
		}
		catch (UnsupportedEncodingException uee) {
			_log.error(uee, uee);

			basicAck(envelope);

			return;
		}

		try {
			int response = parse(
				envelope.getRoutingKey(), message, translate(properties));

			if (_log.isDebugEnabled()) {
				_log.debug("Received response " + response);
			}

			if (response == RabbitMQConsumer.RESPONSE_ACK) {
				basicAck(envelope);

				return;
			}
			else if (response == RabbitMQConsumer.RESPONSE_REJECT) {
				basicReject(envelope);

				return;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		basicRepublish(envelope, properties, body);
	}

	protected void basicAck(Envelope envelope) {
		Channel channel = getChannel();

		try {
			channel.basicAck(envelope.getDeliveryTag(), false);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	protected void basicReject(Envelope envelope) {
		Channel channel = getChannel();

		try {
			channel.basicReject(envelope.getDeliveryTag(), false);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	protected void basicRepublish(
		Envelope envelope, AMQP.BasicProperties properties, byte[] body) {

		Channel channel = getChannel();

		try {
			Map<String, Object> headers = properties.getHeaders();

			if (headers == null) {
				headers = new HashMap<>();
			}

			int republishCount = GetterUtil.getInteger(
				headers.get("republishCount"));

			headers.put("republishCount", republishCount + 1);

			AMQP.BasicProperties.Builder builder = properties.builder();

			builder.headers(headers);

			channel.basicPublish(
				envelope.getExchange(), envelope.getRoutingKey(), properties,
				body);

			channel.basicAck(envelope.getDeliveryTag(), false);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}
	}

	protected int parse(
			String routingKey, String message, Map<String, Object> properties)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Message: " + message);
			_log.debug("Properties: " + MapUtil.toString(properties));
		}

		return (Integer)_delegateMethod.invoke(
			_delegate, routingKey, message, properties);
	}

	protected Map<String, Object> translate(AMQP.BasicProperties properties) {
		Map<String, Object> translatedProperties = new HashMap<>();

		translatedProperties.put(
			RabbitMQPropertyKeys.APP_ID, properties.getAppId());
		translatedProperties.put(
			RabbitMQPropertyKeys.BODY_SIZE, properties.getBodySize());
		translatedProperties.put(
			RabbitMQPropertyKeys.CLASS_ID, properties.getClassId());
		translatedProperties.put(
			RabbitMQPropertyKeys.CLASS_NAME, properties.getClassName());
		translatedProperties.put(
			RabbitMQPropertyKeys.CLUSTER_ID, properties.getClusterId());
		translatedProperties.put(
			RabbitMQPropertyKeys.CONTENT_ENCODING,
			properties.getContentEncoding());
		translatedProperties.put(
			RabbitMQPropertyKeys.CONTENT_TYPE, properties.getContentType());
		translatedProperties.put(
			RabbitMQPropertyKeys.CORRELATION_ID, properties.getCorrelationId());
		translatedProperties.put(
			RabbitMQPropertyKeys.DELIVERY_MODE, properties.getDeliveryMode());
		translatedProperties.put(
			RabbitMQPropertyKeys.EXPIRATION, properties.getExpiration());
		translatedProperties.put(
			RabbitMQPropertyKeys.HEADERS, properties.getHeaders());
		translatedProperties.put(
			RabbitMQPropertyKeys.MESSAGE_ID, properties.getMessageId());
		translatedProperties.put(
			RabbitMQPropertyKeys.PRIORITY, properties.getPriority());
		translatedProperties.put(
			RabbitMQPropertyKeys.REPLY_TO, properties.getReplyTo());
		translatedProperties.put(
			RabbitMQPropertyKeys.TIMESTAMP, properties.getTimestamp());
		translatedProperties.put(
			RabbitMQPropertyKeys.TYPE, properties.getType());
		translatedProperties.put(
			RabbitMQPropertyKeys.USER_ID, properties.getUserId());

		return translatedProperties;
	}

	private static Log _log = LogFactoryUtil.getLog(
		RabbitMQConsumerDelegator.class);

	private final Object _delegate;
	private final Method _delegateMethod;

}