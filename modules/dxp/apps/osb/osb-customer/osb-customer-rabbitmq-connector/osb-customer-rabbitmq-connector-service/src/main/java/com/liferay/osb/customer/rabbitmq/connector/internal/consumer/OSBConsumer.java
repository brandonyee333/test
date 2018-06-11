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

import com.liferay.osb.customer.rabbitmq.connector.consumer.Consumer;
import com.liferay.osb.customer.rabbitmq.connector.processor.MessagePropertyKeys;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class OSBConsumer extends DefaultConsumer implements Consumer {

	public OSBConsumer(Channel channel, MessageRouter messageRouter)
		throws Exception {

		super(channel);

		_messageRouter = messageRouter;
	}

	@Override
	public MessageRouter getMessageRouter() {
		return _messageRouter;
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
			route(envelope.getRoutingKey(), message, translate(properties));

			basicAck(envelope);
		}
		catch (Exception e) {
			_log.error(e, e);

			basicReject(envelope);
		}
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

	protected void route(
		String routingKey, String message, Map<String, Object> properties) {

		if (_log.isDebugEnabled()) {
			_log.debug("Message: " + message);
			_log.debug("Properties: " + MapUtil.toString(properties));
		}

		_messageRouter.route(routingKey, message, properties);
	}

	protected Map<String, Object> translate(AMQP.BasicProperties properties) {
		Map<String, Object> translatedProperties = new HashMap<>();

		translatedProperties.put(
			MessagePropertyKeys.APP_ID, properties.getAppId());
		translatedProperties.put(
			MessagePropertyKeys.BODY_SIZE, properties.getBodySize());
		translatedProperties.put(
			MessagePropertyKeys.CLASS_ID, properties.getClassId());
		translatedProperties.put(
			MessagePropertyKeys.CLASS_NAME, properties.getClassName());
		translatedProperties.put(
			MessagePropertyKeys.CLUSTER_ID, properties.getClusterId());
		translatedProperties.put(
			MessagePropertyKeys.CONTENT_ENCODING,
			properties.getContentEncoding());
		translatedProperties.put(
			MessagePropertyKeys.CONTENT_TYPE, properties.getContentType());
		translatedProperties.put(
			MessagePropertyKeys.CORRELATION_ID, properties.getCorrelationId());
		translatedProperties.put(
			MessagePropertyKeys.DELIVERY_MODE, properties.getDeliveryMode());
		translatedProperties.put(
			MessagePropertyKeys.EXPIRATION, properties.getExpiration());
		translatedProperties.put(
			MessagePropertyKeys.HEADERS, properties.getHeaders());
		translatedProperties.put(
			MessagePropertyKeys.MESSAGE_ID, properties.getMessageId());
		translatedProperties.put(
			MessagePropertyKeys.PRIORITY, properties.getPriority());
		translatedProperties.put(
			MessagePropertyKeys.REPLY_TO, properties.getReplyTo());
		translatedProperties.put(
			MessagePropertyKeys.TIMESTAMP, properties.getTimestamp());
		translatedProperties.put(
			MessagePropertyKeys.TYPE, properties.getType());
		translatedProperties.put(
			MessagePropertyKeys.USER_ID, properties.getUserId());

		return translatedProperties;
	}

	private static Log _log = LogFactoryUtil.getLog(OSBConsumer.class);

	private final MessageRouter _messageRouter;

}