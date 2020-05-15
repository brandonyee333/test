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

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.cloud.pubsub.v1.SubscriptionAdminSettings;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.cloud.pubsub.v1.stub.GrpcSubscriberStub;
import com.google.cloud.pubsub.v1.stub.SubscriberStub;
import com.google.cloud.pubsub.v1.stub.SubscriberStubSettings;
import com.google.pubsub.v1.ProjectTopicName;

import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;

import io.grpc.ManagedChannel;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@MonolithExclude
public class PubSubClientFactory {

	public Publisher createPublisher(ProjectTopicName projectTopicName)
		throws Exception {

		Publisher.Builder builder = Publisher.newBuilder(projectTopicName);

		ManagedChannel managedChannel = _managedChannelSupplier.get();

		_setClientSettingsBuilderProviders(
			builder::setCredentialsProvider, builder::setChannelProvider,
			managedChannel);

		return builder.build();
	}

	public Subscriber createSubscriber(
		String projectSubscriptionName, MessageReceiver messageReceiver) {

		Subscriber.Builder builder = Subscriber.newBuilder(
			projectSubscriptionName, messageReceiver);

		ManagedChannel managedChannel = _managedChannelSupplier.get();

		_setClientSettingsBuilderProviders(
			builder::setCredentialsProvider, builder::setChannelProvider,
			managedChannel);

		return builder.build();
	}

	public PubSubClient<SubscriberStub> createSubscriberStub()
		throws Exception {

		SubscriberStubSettings.Builder builder =
			SubscriberStubSettings.newBuilder();

		ManagedChannel managedChannel = _managedChannelSupplier.get();

		_setClientSettingsBuilderProviders(
			builder::setCredentialsProvider,
			builder::setTransportChannelProvider, managedChannel);

		return new PubSubClient(
			GrpcSubscriberStub.create(builder.build()), managedChannel);
	}

	public PubSubClient<SubscriptionAdminClient> createSubscriptionAdminClient()
		throws Exception {

		SubscriptionAdminSettings.Builder builder =
			SubscriptionAdminSettings.newBuilder();

		ManagedChannel managedChannel = _managedChannelSupplier.get();

		_setClientSettingsBuilderProviders(
			builder::setCredentialsProvider,
			builder::setTransportChannelProvider, managedChannel);

		return new PubSubClient(
			SubscriptionAdminClient.create(builder.build()), managedChannel);
	}

	public PubSubClient<TopicAdminClient> createTopicAdminClient()
		throws Exception {

		TopicAdminSettings.Builder builder = TopicAdminSettings.newBuilder();

		ManagedChannel managedChannel = _managedChannelSupplier.get();

		_setClientSettingsBuilderProviders(
			builder::setCredentialsProvider,
			builder::setTransportChannelProvider, managedChannel);

		return new PubSubClient(
			TopicAdminClient.create(builder.build()), managedChannel);
	}

	private void _setClientSettingsBuilderProviders(
		Function<CredentialsProvider, ?> credentialsProviderSetterFunction,
		Function<TransportChannelProvider, ?>
			transportChannelProviderSetterFunction,
		ManagedChannel managedChannel) {

		if (managedChannel == null) {
			return;
		}

		credentialsProviderSetterFunction.apply(NoCredentialsProvider.create());
		transportChannelProviderSetterFunction.apply(
			FixedTransportChannelProvider.create(
				GrpcTransportChannel.create(managedChannel)));
	}

	@Autowired
	@Qualifier("managedChannelSupplier")
	private Supplier<ManagedChannel> _managedChannelSupplier;

}