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
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageListener;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class MessageBusImpl implements MessageBus {

	@Override
	public void registerMessageListener(
		Channel channel, MessageListener messageListener) {

		Set<MessageListener> messageListeners =
			_messageListeners.computeIfAbsent(
				channel, key -> new CopyOnWriteArraySet<>());

		messageListeners.add(messageListener);
	}

	@Override
	public MessageSubscriber registerMessageSubscriber(
		Channel channel, String messageSubscriberName) {

		Set<MessageSubscriber> messageSubscribers =
			_messageSubscribers.computeIfAbsent(
				channel, key -> new CopyOnWriteArraySet<>());

		MessageSubscriber messageSubscriber = new MessageSubscriberImpl(
			channel, messageSubscriberName);

		messageSubscribers.add(messageSubscriber);

		return messageSubscriber;
	}

	@Override
	public void sendMessage(Channel channel, String message) {
		if (message == null) {
			throw new IllegalArgumentException("Message is null");
		}

		Set<MessageListener> messageListeners = _messageListeners.get(channel);

		if (messageListeners != null) {
			for (MessageListener messageListener : messageListeners) {
				_dispatch(message, messageListener);
			}
		}

		Set<MessageSubscriber> messageSubscribers = _messageSubscribers.get(
			channel);

		if (messageSubscribers != null) {
			for (MessageSubscriber messageSubscriber : messageSubscribers) {
				MessageSubscriberImpl messageSubscriberImpl =
					(MessageSubscriberImpl)messageSubscriber;

				messageSubscriberImpl.enqueue(message);
			}
		}
	}

	@Override
	public void unregisterMessageListener(MessageListener messageListener) {
		for (Set<MessageListener> messageListeners :
				_messageListeners.values()) {

			messageListeners.removeIf(
				messageListener1 -> Objects.equals(
					messageListener1, messageListener));
		}
	}

	@PreDestroy
	protected void destroy() {
		_executorService.shutdown();

		try {
			_executorService.awaitTermination(3, TimeUnit.SECONDS);
		}
		catch (InterruptedException ie) {
			_log.error(ie);
		}
		finally {
			_executorService.shutdownNow();
		}
	}

	@PostConstruct
	protected void init() {
		_executorService = Executors.newCachedThreadPool();
	}

	private void _dispatch(String message, MessageListener messageListener) {
		_executorService.execute(
			() -> {
				try {
					messageListener.onMessage(message);
				}
				catch (Exception e) {
					_log.error("Unable to deliver message: " + message, e);
				}
			});
	}

	private static final Log _log = LogFactory.getLog(MessageBusImpl.class);

	private ExecutorService _executorService;
	private final Map<Channel, Set<MessageListener>> _messageListeners =
		new ConcurrentHashMap<>();
	private final Map<Channel, Set<MessageSubscriber>> _messageSubscribers =
		new ConcurrentHashMap<>();

}