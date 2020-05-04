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
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.extractor.processor.AnalyticsEventsMessageProcessor;
import com.liferay.osb.asah.extractor.spring.OSBAsahExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.elasticsearch.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

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
		ReflectionTestUtils.invokeMethod(
			_analyticsEventsMessageProcessor, "_cacheActiveDataSourceIds");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();

		JSONArray analyticsEventsJSONArray = elasticsearchInvoker.get(
			"analytics-events");

		Assert.assertNotEquals(0, analyticsEventsJSONArray.length());

		JSONObject analyticsEvent = analyticsEventsJSONArray.getJSONObject(0);

		Assert.assertEquals("Page", analyticsEvent.getString("applicationId"));
		Assert.assertEquals("pageViewed", analyticsEvent.getString("eventId"));

		JSONObject contextJSONObject = analyticsEvent.getJSONObject("context");

		Assert.assertEquals(
			"Chrome", contextJSONObject.getString("browserName"));
		Assert.assertEquals(
			"Desktop", contextJSONObject.getString("deviceType"));
		Assert.assertEquals(
			"macOS", contextJSONObject.getString("platformName"));
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

		ReflectionTestUtils.invokeMethod(
			_analyticsEventsMessageProcessor, "_cacheActiveDataSourceIds");
		ReflectionTestUtils.invokeMethod(
			_analyticsEventsMessageProcessor, "_cacheChannelIds");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();

		Assert.assertNotEquals(
			0,
			elasticsearchInvoker.count(
				"analytics-events", QueryBuilders.matchAllQuery()));
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

		ReflectionTestUtils.invokeMethod(
			_analyticsEventsMessageProcessor, "_cacheActiveDataSourceIds");
		ReflectionTestUtils.invokeMethod(
			_analyticsEventsMessageProcessor, "_cacheChannelIds");

		_messageBus.sendMessage(
			Channel.DATA_SOURCES,
			JSONUtil.put(
				"channelId", "999"
			).put(
				"dataSourceId", "990121114030678099"
			).put(
				"event", "add"
			).toString());

		_analyticsEventsMessageProcessor.processQueuedMessages();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();

		Assert.assertNotEquals(
			0,
			elasticsearchInvoker.count(
				"analytics-events", QueryBuilders.matchAllQuery()));
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

		ReflectionTestUtils.invokeMethod(
			_analyticsEventsMessageProcessor, "_cacheActiveDataSourceIds");
		ReflectionTestUtils.invokeMethod(
			_analyticsEventsMessageProcessor, "_cacheChannelIds");

		_analyticsEventsMessageProcessor.processQueuedMessages();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();

		Assert.assertEquals(
			0,
			elasticsearchInvoker.count(
				"analytics-events", QueryBuilders.matchAllQuery()));
	}

	@Autowired
	private AnalyticsEventsMessageProcessor _analyticsEventsMessageProcessor;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private MessageBus _messageBus;

}