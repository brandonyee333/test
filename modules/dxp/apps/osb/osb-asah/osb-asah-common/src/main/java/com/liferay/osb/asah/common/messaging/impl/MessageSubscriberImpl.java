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

import com.liferay.osb.asah.common.function.UnsafeFunction;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.List;

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
	public List<Message<String>> pullMessages(int maxMessages) {
		return _pullMessages(maxMessages, String::toString);
	}

	@Override
	public <T> List<Message<T>> pullMessages(
		int maxMessages,
		UnsafeFunction<String, T, Exception> modelMapperFunction) {

		return _pullMessages(maxMessages, modelMapperFunction);
	}

	@Override
	public <T> void sendAcknowledgements(List<Message<T>> messages) {
		List<String> ackIds = ListUtil.map(messages, Message::getAckId);

		if (ackIds.isEmpty()) {
			return;
		}

		try (PubSubClient<SubscriberStub> subscriberStubPubSubClient =
				_pubSubClientFactory.createSubscriberStub()) {

			SubscriberStub subscriberStub = subscriberStubPubSubClient.get();

			UnaryCallable<AcknowledgeRequest, Empty>
				acknowledgeRequestUnaryCallable =
					subscriberStub.acknowledgeCallable();

			acknowledgeRequestUnaryCallable.call(
				_buildAcknowledgeRequest(ackIds));

			if (_log.isDebugEnabled()) {
				_log.debug("Successfully sent acknowledge IDs " + ackIds);
			}
		}
		catch (Exception exception) {
			_log.error("Unable to send acknowledge IDs " + ackIds, exception);
		}
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

	private <T> List<Message<T>> _pullMessages(
		int maxMessages,
		UnsafeFunction<String, T, Exception> modelMapperFunction) {

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Pulling %s messages from %s", maxMessages,
					_subscription.getName()));
		}

		List<Message<T>> messages = new ArrayList<>();

		try (PubSubClient<SubscriberStub> subscriberStubPubSubClient =
				_pubSubClientFactory.createSubscriberStub()) {

			SubscriberStub subscriberStub = subscriberStubPubSubClient.get();

			UnaryCallable<PullRequest, PullResponse> pullRequestUnaryCallable =
				subscriberStub.pullCallable();

			PullResponse pullResponse = pullRequestUnaryCallable.call(
				_buildPullRequest(maxMessages));

			for (ReceivedMessage receivedMessage :
					pullResponse.getReceivedMessagesList()) {

				ackIds.add(receivedMessage.getAckId());

				PubsubMessage pubsubMessage = receivedMessage.getMessage();

				ByteString byteString = pubsubMessage.getData();

				messages.add(
					new Message(
						receivedMessage.getAckId(),
						pubsubMessage.getMessageId(),
						modelMapperFunction.apply(byteString.toStringUtf8())));
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

	private static final Log _log = LogFactory.getLog(
		MessageSubscriberImpl.class);

	private final PubSubClientFactory _pubSubClientFactory;
	private final Subscription _subscription;

}