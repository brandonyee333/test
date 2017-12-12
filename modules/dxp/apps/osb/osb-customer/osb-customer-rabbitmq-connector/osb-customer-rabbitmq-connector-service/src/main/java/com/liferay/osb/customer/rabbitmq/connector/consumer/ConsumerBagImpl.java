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

import com.liferay.portal.kernel.util.StringBundler;

import com.rabbitmq.client.Channel;

/**
 * @author Amos Fong
 */
public class ConsumerBagImpl implements ConsumerBag {

	public ConsumerBagImpl() {
		this(null, null, 0, null);
	}

	public ConsumerBagImpl(
		Object rabbitMQConsumer, Channel channel, int prefetchCount,
		String queue) {

		_rabbitMQConsumer = rabbitMQConsumer;
		_channel = channel;
		_prefetchCount = prefetchCount;
		_queue = queue;
	}

	public Channel getChannel() {
		return _channel;
	}

	public int getPrefetchCount() {
		return _prefetchCount;
	}

	public String getQueue() {
		return _queue;
	}

	public Object getRabbitMQConsumer() {
		return _rabbitMQConsumer;
	}

	public boolean isActive() {
		return _active;
	}

	public void setChannel(Channel channel) {
		_channel = channel;

		if (_channel != null) {
			_active = true;
		}
		else {
			_active = false;
		}
	}

	public String toString() {
		StringBundler sb = new StringBundler(11);
		Class<?> rabbitMQClass = _rabbitMQConsumer.getClass();

		sb.append("{active=");
		sb.append(_active);
		sb.append(", channel=");

		if (_channel != null) {
			sb.append(_channel.getChannelNumber());
		}

		sb.append(", prefetchCount=");
		sb.append(_prefetchCount);
		sb.append(", queue=");
		sb.append(_queue);
		sb.append(", rabbitMQConsumer=");
		sb.append(rabbitMQClass.getName());
		sb.append("}");

		return sb.toString();
	}

	private boolean _active = true;
	private Channel _channel;
	private final int _prefetchCount;
	private final String _queue;
	private final Object _rabbitMQConsumer;

}