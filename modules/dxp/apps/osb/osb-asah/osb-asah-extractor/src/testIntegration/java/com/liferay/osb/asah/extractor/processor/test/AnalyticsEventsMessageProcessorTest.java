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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.extractor.cache.DataSourceCache;
import com.liferay.osb.asah.extractor.processor.AnalyticsEventsMessageProcessor;
import com.liferay.osb.asah.extractor.spring.OSBAsahExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.elasticsearch.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahExtractorSpringBootApplication.class)
public class AnalyticsEventsMessageProcessorTest {

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_1.json"
	)
	@Test
	public void testProcessQueuedMessages() throws Exception {
		ReflectionTestUtils.invokeMethod(_dataSourceCache, "_cacheDataSources");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<AnalyticsEvent> analyticsEvents = _messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);

		Assert.assertNotEquals(0, analyticsEvents.size());

		AnalyticsEvent analyticsEvent = analyticsEvents.get(0);

		Assert.assertEquals("Page", analyticsEvent.getApplicationId());
		Assert.assertEquals("pageViewed", analyticsEvent.getEventId());

		Map<String, Object> context = analyticsEvent.getContext();

		Assert.assertEquals("Chrome", context.get("browserName"));
		Assert.assertEquals("Desktop", context.get("deviceType"));
		Assert.assertEquals("macOS", context.get("platformName"));
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_2.json"
	)
	@Test
	public void testProcessQueuedMessagesAssignMissingChannelId()
		throws Exception {

		ReflectionTestUtils.invokeMethod(_dataSourceCache, "_cacheDataSources");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<AnalyticsEvent> analyticsEvents = _messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);

		Assert.assertNotEquals(0, analyticsEvents.size());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_4.json"
	)
	@Test
	public void testProcessQueuedMessagesDataSourceCreatedDynamically()
		throws Exception {

		ReflectionTestUtils.invokeMethod(_dataSourceCache, "_cacheDataSources");

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		elasticsearchInvoker.add(
			"data-sources",
			JSONUtil.put(
				"channelId", "999"
			).put(
				"id", "990121114030678099"
			));

		_dataSourceCache.onMessage("");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<AnalyticsEvent> analyticsEvents = _messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);

		Assert.assertNotEquals(0, analyticsEvents.size());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_channel_3.json"
	)
	@Test
	public void testProcessQueuedMessagesDiscardDueMissingChannelId()
		throws Exception {

		ReflectionTestUtils.invokeMethod(_dataSourceCache, "_cacheDataSources");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<AnalyticsEvent> analyticsEvents = _messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);

		Assert.assertEquals(
			analyticsEvents.toString(), 0, analyticsEvents.size());
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_canonical_url_1.json"
	)
	@Test
	public void testProcessQueuedMessagesMissingCanonicalUrl()
		throws Exception {

		ReflectionTestUtils.invokeMethod(_dataSourceCache, "_cacheDataSources");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<AnalyticsEvent> analyticsEvents = _messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);

		for (AnalyticsEvent analyticsEvent : analyticsEvents) {
			Map<String, Object> context = analyticsEvent.getContext();

			Assert.assertEquals(
				context.get("url"), context.get("canonicalUrl"));
		}
	}

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data-sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@MessageBusChannel(
		channel = Channel.ANALYTICS_EVENTS_MESSAGE,
		resourcePath = "analytics_events_message_canonical_url_2.json"
	)
	@Test
	public void testProcessQueuedMessagesNotReplacingCanonicalUrl()
		throws Exception {

		ReflectionTestUtils.invokeMethod(_dataSourceCache, "_cacheDataSources");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		List<AnalyticsEvent> analyticsEvents = _messageSubscriber.pullMessages(
			50, AnalyticsEvent::toAnalyticsEvent);

		for (AnalyticsEvent analyticsEvent : analyticsEvents) {
			Map<String, Object> context = analyticsEvent.getContext();

			Assert.assertNotEquals(
				context.get("url"), context.get("canonicalUrl"));
		}
	}

	@Autowired
	private AnalyticsEventsMessageProcessor _analyticsEventsMessageProcessor;

	@Autowired
	private DataSourceCache _dataSourceCache;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_PAGE)
	private MessageSubscriber _messageSubscriber;

}