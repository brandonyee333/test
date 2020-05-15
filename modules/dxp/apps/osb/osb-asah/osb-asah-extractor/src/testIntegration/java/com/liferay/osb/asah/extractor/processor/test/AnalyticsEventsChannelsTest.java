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

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.extractor.processor.AnalyticsEventsChannels;
import com.liferay.osb.asah.extractor.spring.OSBAsahExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahExtractorSpringBootApplication.class)
public class AnalyticsEventsChannelsTest {

	@Test
	public void testAnalyticsEventsBlogChannel() throws Exception {
		List<AnalyticsEvent> analyticsEvents = AnalyticsEvent.toAnalyticsEvents(
			ResourceUtil.readResourceToString(
				"dependencies/analytics_events.json", this));

		Map<Channel, List<AnalyticsEvent>> analyticsEventsMap =
			_getChannelAnalyticsEvents(analyticsEvents);

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/analytics_events_blog_channel.json", this)),
			JSONUtil.toJSONArray(
				analyticsEventsMap.get(Channel.ANALYTICS_EVENTS_BLOG),
				analyticsEvent -> new JSONObject(analyticsEvent.toJSON())),
			false);
	}

	@Test
	public void testAnalyticsEventsDocumentChannel() throws Exception {
		List<AnalyticsEvent> analyticsEvents = AnalyticsEvent.toAnalyticsEvents(
			ResourceUtil.readResourceToString(
				"dependencies/analytics_events.json", this));

		Map<Channel, List<AnalyticsEvent>> analyticsEventsMap =
			_getChannelAnalyticsEvents(analyticsEvents);

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/analytics_events_document_channel.json",
					this)),
			JSONUtil.toJSONArray(
				analyticsEventsMap.get(Channel.ANALYTICS_EVENTS_DOCUMENT),
				analyticsEvent -> new JSONObject(analyticsEvent.toJSON())),
			false);
	}

	private Map<Channel, List<AnalyticsEvent>> _getChannelAnalyticsEvents(
		List<AnalyticsEvent> analyticsEvents) {

		Map<Channel, List<AnalyticsEvent>> analyticsEventsMap = new HashMap<>();

		for (AnalyticsEvent analyticsEvent : analyticsEvents) {
			List<Channel> channels = _analyticsEventsChannels.getChannels(
				analyticsEvent);

			for (Channel channel : channels) {
				List<AnalyticsEvent> channelAnalyticsEvents =
					analyticsEventsMap.get(channel);

				if (channelAnalyticsEvents == null) {
					channelAnalyticsEvents = new ArrayList<>();

					analyticsEventsMap.put(channel, channelAnalyticsEvents);
				}

				channelAnalyticsEvents.add(analyticsEvent);
			}
		}

		return analyticsEventsMap;
	}

	@Autowired
	private AnalyticsEventsChannels _analyticsEventsChannels;

}