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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.pubsub.v1.stub.SubscriberStub;
import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import com.google.pubsub.v1.AcknowledgeRequest;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.PullRequest;
import com.google.pubsub.v1.PullResponse;
import com.google.pubsub.v1.ReceivedMessage;
import com.google.pubsub.v1.Subscription;

import com.liferay.osb.asah.common.messaging.MessageSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Marcellus Tavares
 */
public class MessageSubscriberImpl implements MessageSubscriber {

	public MessageSubscriberImpl(
		PubSubClientFactory pubSubClientFactory, Subscription subscription) {

		_pubSubClientFactory = pubSubClientFactory;
		_subscription = subscription;
	}

	@Override
	public List<String> pullMessages(int maxMessages) {
		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Pulling %s messages from %s", maxMessages,
					_subscription.getName()));
		}

		List<String> messages = new ArrayList<>();

		try (PubSubClient<SubscriberStub> subscriberStubPubSubClient =
				_pubSubClientFactory.createSubscriberStub()) {

			SubscriberStub subscriberStub = subscriberStubPubSubClient.get();

			UnaryCallable<PullRequest, PullResponse> pullRequestUnaryCallable =
				subscriberStub.pullCallable();

			PullResponse pullResponse = pullRequestUnaryCallable.call(
				_buildPullRequest(maxMessages));

			List<String> ackIds = new ArrayList<>();

			for (ReceivedMessage receivedMessage :
					pullResponse.getReceivedMessagesList()) {

				ackIds.add(receivedMessage.getAckId());

				PubsubMessage pubsubMessage = receivedMessage.getMessage();

				String messageId = _messageIds.getIfPresent(
					pubsubMessage.getMessageId());

				if (messageId == null) {
					ByteString byteString = pubsubMessage.getData();

					messages.add(byteString.toStringUtf8());

					_messageIds.put(
						pubsubMessage.getMessageId(),
						pubsubMessage.getMessageId());
				}
				else {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Duplicate Message ID: " +
								pubsubMessage.getMessageId());
					}
				}
			}

			if (!ackIds.isEmpty()) {
				UnaryCallable<AcknowledgeRequest, Empty>
					acknowledgeRequestUnaryCallable =
						subscriberStub.acknowledgeCallable();

				acknowledgeRequestUnaryCallable.call(
					_buildAcknowledgeRequest(ackIds));
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Received messages: " + messages);
		}

		return messages;
	}

	private AcknowledgeRequest _buildAcknowledgeRequest(
		Iterable<String> ackIds) {

		AcknowledgeRequest.Builder builder = AcknowledgeRequest.newBuilder();

		builder.addAllAckIds(ackIds);
		builder.setSubscription(_subscription.getName());

		return builder.build();
	}

	private PullRequest _buildPullRequest(int maxMessages) {
		PullRequest.Builder builder = PullRequest.newBuilder();

		builder.setMaxMessages(maxMessages);
		builder.setReturnImmediately(true);
		builder.setSubscription(_subscription.getName());

		return builder.build();
	}

	private static final Log _log = LogFactory.getLog(
		MessageSubscriberImpl.class);

	private static final Cache<String, String> _messageIds =
		Caffeine.newBuilder(
		).expireAfterAccess(
			10, TimeUnit.MINUTES
		).maximumSize(
			100000
		).build();

	private final PubSubClientFactory _pubSubClientFactory;
	private final Subscription _subscription;

}