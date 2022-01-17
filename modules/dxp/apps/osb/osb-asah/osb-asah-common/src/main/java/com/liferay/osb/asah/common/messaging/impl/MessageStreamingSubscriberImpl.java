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

package com.liferay.osb.asah.common.messaging.impl;

import com.google.api.core.ApiService;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.Subscription;

import com.liferay.osb.asah.common.messaging.MessageStreamingSubscriber;

/**
 * @author Robson Pastor
 */
public class MessageStreamingSubscriberImpl
	implements MessageStreamingSubscriber {

	public MessageStreamingSubscriberImpl(
		PubSubClientFactory pubSubClientFactory, Subscription subscription) {

		_pubSubClientFactory = pubSubClientFactory;
		_subscription = subscription;
	}

	@Override
	public void subscribe(
		long maxOutstandingMessages, MessageReceiver messageReceiver) {

		Subscriber subscriber = _pubSubClientFactory.createSubscriber(
			maxOutstandingMessages, messageReceiver, _subscription.getName());

		ApiService apiService = subscriber.startAsync();

		apiService.awaitRunning();

		subscriber.awaitTerminated();
	}

	private final PubSubClientFactory _pubSubClientFactory;
	private final Subscription _subscription;

}