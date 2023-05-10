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

package com.liferay.osb.asah.test.util.messaging;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.ProjectTopicName;
import com.google.pubsub.v1.Subscription;

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.impl.MessageSubscriberImpl;
import com.liferay.osb.asah.common.messaging.impl.PubSubClient;
import com.liferay.osb.asah.common.messaging.impl.PubSubClientFactory;
import com.liferay.osb.asah.common.messaging.impl.PubSubMessageBusImpl;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;

/**
 * @author Marcellus Tavares
 */
public class MessageBusTestHelper {

	public MessageBusTestHelper(MessageBus messageBus) {
		if (!(messageBus instanceof PubSubMessageBusImpl)) {
			throw new IllegalArgumentException(
				"Message bus is not an instance of PubSubMessageBusImpl");
		}

		_pubSubMessageBusImpl = (PubSubMessageBusImpl)messageBus;
	}

	public void clearMessageBusChannel(Channel channel) {
		PubSubClientFactory pubSubClientFactory =
			_pubSubMessageBusImpl.getPubSubClientFactory();

		try (PubSubClient<SubscriptionAdminClient>
				subscriptionAdminClientPubSubClient =
					pubSubClientFactory.createSubscriptionAdminClient()) {

			SubscriptionAdminClient subscriptionAdminClient =
				subscriptionAdminClientPubSubClient.get();

			SubscriptionAdminClient.ListSubscriptionsPagedResponse
				listSubscriptionsPagedResponse =
					subscriptionAdminClient.listSubscriptions(
						_pubSubMessageBusImpl.getProjectName());

			for (Subscription subscription :
					listSubscriptionsPagedResponse.iterateAll()) {

				if (StringUtils.containsIgnoreCase(
						subscription.getTopic(), String.valueOf(channel))) {

					_pullAllSubscriptionMessages(subscription);
				}
			}
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	public void clearMessageBusChannel(MessageBusChannel messageBusChannel) {
		clearMessageBusChannel(messageBusChannel.channel());
	}

	public void prepareMessageBusChannel(
			Channel channel, JSONArray messagesJSONArray)
		throws Exception {

		Publisher publisher = _createPublisher(channel);

		if (channel == Channel.DXP_ENTITIES_MESSAGE) {
			ApiFuture<String> apiFuture = publisher.publish(
				_pubSubMessageBusImpl.createPubsubMessage(
					String.valueOf(messagesJSONArray),
					Collections.singletonMap(
						"projectId", ProjectIdThreadLocal.getProjectId()),
					Channel.DXP_ENTITIES_MESSAGE.name() + "_test"));

			apiFuture.get();

			return;
		}

		for (int i = 0; i < messagesJSONArray.length(); i++) {
			ApiFuture<String> apiFuture = publisher.publish(
				_pubSubMessageBusImpl.createPubsubMessage(
					String.valueOf(messagesJSONArray.getJSONObject(i))));

			apiFuture.get();
		}
	}

	public void prepareMessageBusChannel(
			Class<?> clazz, MessageBusChannel messageBusChannel)
		throws Exception {

		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/" + messageBusChannel.resourcePath(),
					clazz)));

		prepareMessageBusChannel(messageBusChannel.channel(), jsonArray);
	}

	private Publisher _createPublisher(Channel channel) throws Exception {
		ProjectTopicName projectTopicName =
			_pubSubMessageBusImpl.getProjectTopicName(channel);

		PubSubClientFactory pubSubClientFactory =
			_pubSubMessageBusImpl.getPubSubClientFactory();

		return pubSubClientFactory.createPublisher(
			channel.isOrderingEnabled(), projectTopicName);
	}

	private void _pullAllSubscriptionMessages(Subscription subscription)
		throws Exception {

		MessageSubscriberImpl messageSubscriberImpl = new MessageSubscriberImpl(
			_pubSubMessageBusImpl.getPubSubClientFactory(), subscription);

		while (true) {
			List<Message<String>> messages = messageSubscriberImpl.pullMessages(
				50, String::valueOf);

			if (messages.isEmpty()) {
				break;
			}

			messageSubscriberImpl.sendAckIds(
				ListUtil.map(messages, Message::getAckId));
		}
	}

	private final PubSubMessageBusImpl _pubSubMessageBusImpl;

}