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

package com.liferay.osb.asah.extractor.processor.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.extractor.processor.AnalyticsEventsMessageProcessor;
import com.liferay.osb.asah.extractor.spring.OSBAsahExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Marcellus Tavares
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(
	classes = OSBAsahExtractorSpringBootApplication.class,
	properties = "osb.asah.analytics.events.storage.path:/tmp/analytics_events.snappy.parquet"
)
public class AnalyticsEventsMessageProcessorTest
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_messageMessageSubscriber = Mockito.spy(
			_messageBus.registerMessageSubscriber(
				Channel.ANALYTICS_EVENTS_MESSAGE,
				AnalyticsEventsMessageProcessor.class.getName()));

		ReflectionTestUtils.setField(
			_analyticsEventsMessageProcessor, "_messageSubscriber",
			_messageMessageSubscriber);
	}

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_1.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testProcessQueuedMessages() throws Exception {
		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<Message<AnalyticsEvent>> messages =
			_pageMessageSubscriber.pullMessages(
				50, AnalyticsEvent::toAnalyticsEvent);

		_pageMessageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));

		Assertions.assertNotEquals(0, messages.size());

		Message<AnalyticsEvent> message = messages.get(0);

		AnalyticsEvent analyticsEvent = message.getObject();

		Assertions.assertEquals("Page", analyticsEvent.getApplicationId());
		Assertions.assertEquals("pageViewed", analyticsEvent.getEventId());

		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		Assertions.assertEquals("", eventProperties.get("x"));
		Assertions.assertEquals(
			"http://www.google.com", eventProperties.get("referrer"));

		Assertions.assertEquals(
			analyticsEvent.getSegmentNames(),
			new HashSet<>(Arrays.asList("Developer", "Support")));

		Map<String, String> context = analyticsEvent.getContext();

		Assertions.assertEquals("", eventProperties.get("x"));
		Assertions.assertEquals("Chrome", context.get("browserName"));
		Assertions.assertEquals("Desktop", context.get("deviceType"));
		Assertions.assertEquals("macOS", context.get("platformName"));

		Mockito.verify(
			_messageMessageSubscriber, Mockito.never()
		).registerException(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_2.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesAssignMissingChannelId()
		throws Exception {

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<Message<AnalyticsEvent>> messages =
			_pageMessageSubscriber.pullMessages(
				50, AnalyticsEvent::toAnalyticsEvent);

		_pageMessageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));

		Assertions.assertEquals(1, messages.size(), messages.toString());

		Mockito.verify(
			_messageMessageSubscriber, Mockito.never()
		).registerException(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_4.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesDataSourceCreatedDynamically()
		throws Exception {

		DataSource dataSource = new DataSource();

		dataSource.setId(990121114030678099L);
		dataSource.setIsNew(Boolean.TRUE);
		dataSource.setProviderType("LIFERAY");
		dataSource.setStatus("ACTIVE");

		_dataSourceRepository.save(dataSource);

		com.liferay.osb.asah.common.entity.Channel channel =
			new com.liferay.osb.asah.common.entity.Channel();

		ChannelDataSource channelDataSource = new ChannelDataSource();

		channelDataSource.setDataSourceId(990121114030678099L);
		channelDataSource.setGroupIds(Collections.singleton(20122L));

		channel.setChannelDataSources(Collections.singleton(channelDataSource));

		channel.setDefaultChannel(true);
		channel.setId(999L);
		channel.setIsNew(true);
		channel.setName("channelA");

		_channelRepository.save(channel);

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<Message<AnalyticsEvent>> messages =
			_pageMessageSubscriber.pullMessages(
				50, AnalyticsEvent::toAnalyticsEvent);

		_pageMessageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));

		Assertions.assertNotEquals(0, messages.size());

		Mockito.verify(
			_messageMessageSubscriber, Mockito.never()
		).registerException(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_5.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesDataSourceInactive() throws Exception {
		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<Message<AnalyticsEvent>> messages =
			_pageMessageSubscriber.pullMessages(
				50, AnalyticsEvent::toAnalyticsEvent);

		_pageMessageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));

		Assertions.assertEquals(0, messages.size(), messages.toString());

		Mockito.verify(
			_messageMessageSubscriber, Mockito.never()
		).registerException(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_3.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesDiscardDueMissingChannelId()
		throws Exception {

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<Message<AnalyticsEvent>> messages =
			_pageMessageSubscriber.pullMessages(
				50, AnalyticsEvent::toAnalyticsEvent);

		_pageMessageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));

		Assertions.assertEquals(0, messages.size(), messages.toString());

		Mockito.verify(
			_messageMessageSubscriber, Mockito.never()
		).registerException(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_duplicated_events.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesDiscardDuplicatedEvents()
		throws Exception {

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<Message<AnalyticsEvent>> messages =
			_pageMessageSubscriber.pullMessages(
				50, AnalyticsEvent::toAnalyticsEvent);

		_pageMessageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));
		Assertions.assertEquals(1, messages.size(), messages.toString());

		Mockito.verify(
			_messageMessageSubscriber, Mockito.never()
		).registerException(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_canonical_url_1.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesMissingCanonicalUrl()
		throws Exception {

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<Message<AnalyticsEvent>> messages =
			_pageMessageSubscriber.pullMessages(
				50, AnalyticsEvent::toAnalyticsEvent);

		_pageMessageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));

		for (Message<AnalyticsEvent> message : messages) {
			AnalyticsEvent analyticsEvent = message.getObject();

			Map<String, String> context = analyticsEvent.getContext();

			Assertions.assertEquals(
				context.get("url"), context.get("canonicalUrl"));
		}

		Mockito.verify(
			_messageMessageSubscriber, Mockito.never()
		).registerException(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_canonical_url_2.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testProcessQueuedMessagesNotReplacingCanonicalUrl()
		throws Exception {

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<Message<AnalyticsEvent>> messages =
			_pageMessageSubscriber.pullMessages(
				50, AnalyticsEvent::toAnalyticsEvent);

		_pageMessageSubscriber.sendAckIds(
			ListUtil.map(messages, Message::getAckId));

		for (Message<AnalyticsEvent> message : messages) {
			AnalyticsEvent analyticsEvent = message.getObject();

			Map<String, String> context = analyticsEvent.getContext();

			Assertions.assertNotEquals(
				context.get("url"), context.get("canonicalUrl"));
		}

		Mockito.verify(
			_messageMessageSubscriber, Mockito.never()
		).registerException(
			ArgumentMatchers.any(), ArgumentMatchers.any()
		);
	}

	@Autowired
	private AnalyticsEventsMessageProcessor _analyticsEventsMessageProcessor;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private MessageBus _messageBus;

	private MessageSubscriber _messageMessageSubscriber;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_PAGE)
	private MessageSubscriber _pageMessageSubscriber;

}