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

package com.liferay.osb.asah.monolith.common.messaging.impl;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Marcellus Tavares
 */
public class MessageSubscriberImpl implements MessageSubscriber {

	public MessageSubscriberImpl(Channel channel, String name) {
		_channel = channel;
		_name = name;
	}

	public void enqueue(String message) {
		_queue.offer(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof MessageSubscriberImpl)) {
			return false;
		}

		MessageSubscriberImpl messageSubscriberImpl =
			(MessageSubscriberImpl)obj;

		if (Objects.equals(_channel, messageSubscriberImpl._channel) &&
			Objects.equals(_name, messageSubscriberImpl._name)) {

			return true;
		}

		return false;
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_channel, _name);
	}

	@Override
	public List<String> pullMessages(int maxMessages) {
		List<String> messages = new ArrayList<>();

		int i = 0;

		while (i++ < maxMessages) {
			String message = _queue.poll();

			if (message == null) {
				break;
			}

			messages.add(message);
		}

		return messages;
	}

	private final Channel _channel;
	private final String _name;
	private Queue<String> _queue = new ConcurrentLinkedQueue<>();

}