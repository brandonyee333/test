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

package com.liferay.osb.asah.dataflow.ingestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.beam.sdk.coders.AvroCoder;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

import org.junit.Rule;
import org.junit.Test;

/**
 * @author Leslie Wong
 */
public class EventIngestionPipelineTest {

	@Test
	public void testAnalyticsEventExtractor() {
		List<AnalyticsEvent> testAnalyticsEvents = new ArrayList<>();

		testAnalyticsEvents.add(
			_createTestAnalyticsEvent(
				"", _USER_AGENT_MAC_OS_FIREFOX_DESKTOP,
				"https://liferay.com/web/guest/home/"));
		testAnalyticsEvents.add(
			_createTestAnalyticsEvent(
				"https://liferay.com", _USER_AGENT_MAC_OS_CHROME_DESKTOP,
				"https://liferay.com/web/guest/home/"));
		testAnalyticsEvents.add(
			_createTestAnalyticsEvent(
				"https://liferay.com", _USER_AGENT_MAC_OS_FIREFOX_DESKTOP,
				"https://liferay.com/test-page-1/"));
		testAnalyticsEvents.add(
			_createTestAnalyticsEvent(
				"", _USER_AGENT_ANDROID_CHROME_MOBILE,
				"https://liferay.com/test-page-2/"));
		testAnalyticsEvents.add(
			_createTestAnalyticsEvent(
				"https://liferay.com", _USER_AGENT_MAC_OS_FIREFOX_DESKTOP,
				"https://liferay.com/test-page-3/"));

		PCollection<AnalyticsEvent> pCollection = testPipeline.apply(
			"Create Test Batch",
			Create.of(
				testAnalyticsEvents
			).withCoder(
				AvroCoder.of(AnalyticsEvent.class)
			)
		).apply(
			"Extract Geolocation/Device Information",
			ParDo.of(new EventIngestionPipeline.AnalyticsEventExtractor())
		);

		List<AnalyticsEvent> expectedAnalyticsEvents = new ArrayList<>();

		expectedAnalyticsEvents.add(
			_createExpectedAnalyticsEvent(
				"Firefox", "https://liferay.com/web/guest/home/", "Desktop",
				"macOS", _USER_AGENT_MAC_OS_FIREFOX_DESKTOP,
				"https://liferay.com/web/guest/home/"));
		expectedAnalyticsEvents.add(
			_createExpectedAnalyticsEvent(
				"Chrome", "https://liferay.com", "Desktop", "macOS",
				_USER_AGENT_MAC_OS_CHROME_DESKTOP,
				"https://liferay.com/web/guest/home/"));
		expectedAnalyticsEvents.add(
			_createExpectedAnalyticsEvent(
				"Firefox", "https://liferay.com", "Desktop", "macOS",
				_USER_AGENT_MAC_OS_FIREFOX_DESKTOP,
				"https://liferay.com/test-page-1/"));
		expectedAnalyticsEvents.add(
			_createExpectedAnalyticsEvent(
				"Chrome", "https://liferay.com/test-page-2/", "SmartPhone",
				"Android", _USER_AGENT_ANDROID_CHROME_MOBILE,
				"https://liferay.com/test-page-2/"));
		expectedAnalyticsEvents.add(
			_createExpectedAnalyticsEvent(
				"Firefox", "https://liferay.com", "Desktop", "macOS",
				_USER_AGENT_MAC_OS_FIREFOX_DESKTOP,
				"https://liferay.com/test-page-3/"));

		PAssert.that(
			pCollection
		).containsInAnyOrder(
			expectedAnalyticsEvents
		);

		testPipeline.run();
	}

	@Test
	public void testAnalyticsEventParser() throws IOException {
		String analyticsEventsJSON = _readResourceAsString(
			"dependencies/test_analytics_event_parser.json");

		PCollection<AnalyticsEvent> pCollection = testPipeline.apply(
			Create.of(analyticsEventsJSON)
		).apply(
			"Parse Analytics Events",
			ParDo.of(new EventIngestionPipeline.AnalyticsEventParser())
		);

		AnalyticsEvent analyticsEvent = _createAnalyticsEvent(
			"Page", "123", null,
			new HashMap<String, String>() {
				{
					put(
						"description",
						"This is the Liferay Analytics JS client test page.");
					put("keywords", "Liferay, Analytics, Test, Javascript");
					put("languageId", "en-US");
					put("title", "Liferay Analytics JS Client Test Page");
					put("url", "http://www.liferay.com");
					put("userAgent", _USER_AGENT_MAC_OS_FIREFOX_DESKTOP);
				}
			},
			"2017-11-10T09:36:00.365Z", "350121114030678021",
			"2017-11-10T09:34:45.345Z", "pageViewed",
			Collections.singletonMap("referrer", "http://www.google.com"), "1",
			"test", "UTC", "aedfa915-c7a1-4309-abcf-024e247d414c");

		PAssert.that(
			pCollection
		).containsInAnyOrder(
			Collections.singletonList(analyticsEvent)
		);

		testPipeline.run();
	}

	@Rule
	public final transient TestPipeline testPipeline = TestPipeline.create();

	private AnalyticsEvent _createAnalyticsEvent(
		String applicationId, String channelId, String clientIP,
		Map<String, String> context, String createDate, String dataSourceId,
		String eventDate, String eventId, Map<String, String> eventProperties,
		String id, String projectId, String projectTimeZoneId, String userId) {

		AnalyticsEvent analyticsEvent = new AnalyticsEvent();

		analyticsEvent.applicationId = applicationId;
		analyticsEvent.channelId = channelId;
		analyticsEvent.clientIP = clientIP;
		analyticsEvent.context = context;
		analyticsEvent.createDate = createDate;
		analyticsEvent.dataSourceId = dataSourceId;
		analyticsEvent.eventDate = eventDate;
		analyticsEvent.eventId = eventId;
		analyticsEvent.eventProperties = eventProperties;
		analyticsEvent.id = id;
		analyticsEvent.projectId = projectId;
		analyticsEvent.projectTimeZoneId = projectTimeZoneId;
		analyticsEvent.userId = userId;

		return analyticsEvent;
	}

	private AnalyticsEvent _createExpectedAnalyticsEvent(
		String browserName, String canonicalUrl, String deviceType,
		String platformName, String userAgent, String url) {

		Map<String, String> context = new HashMap<>();

		context.put("browserName", browserName);
		context.put("canonicalUrl", canonicalUrl);
		context.put("crawler", "false");
		context.put("deviceType", deviceType);
		context.put("platformName", platformName);
		context.put("url", url);
		context.put("userAgent", userAgent);

		return _createAnalyticsEvent(
			"", "", "", context, "", "", "", "", Collections.emptyMap(), "", "",
			"", "");
	}

	private AnalyticsEvent _createTestAnalyticsEvent(
		String canonicalUrl, String userAgent, String url) {

		Map<String, String> context = new HashMap<>();

		context.put("canonicalUrl", canonicalUrl);
		context.put("url", url);
		context.put("userAgent", userAgent);

		return _createAnalyticsEvent(
			"", "", "", context, "", "", "", "", Collections.emptyMap(), "", "",
			"", "");
	}

	private String _readResourceAsString(String resourcePath)
		throws IOException {

		Class<?> clazz = getClass();

		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(
					clazz.getResourceAsStream(resourcePath),
					StandardCharsets.UTF_8))) {

			Stream<String> lines = bufferedReader.lines();

			return lines.collect(Collectors.joining());
		}
	}

	private static final String _USER_AGENT_ANDROID_CHROME_MOBILE =
		"Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) " +
			"AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 " +
				"Mobile Safari/535.19";

	private static final String _USER_AGENT_MAC_OS_CHROME_DESKTOP =
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 " +
			"(KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36";

	private static final String _USER_AGENT_MAC_OS_FIREFOX_DESKTOP =
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:45.0) " +
			"Gecko/20100101 Firefox/45.0";

}