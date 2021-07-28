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

package com.liferay.osb.asah.spark.session.model;

import com.liferay.osb.asah.spark.common.DateUtil;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Robson Pastor
 */
public class SessionTest {

	@Test
	public void testAddEvent() {
		Event firstEvent = new Event();

		firstEvent.setAnalyticsKey("www.liferay.com");
		firstEvent.setApplicationId("WebContent");
		firstEvent.setChannelId("1");
		firstEvent.setClientIp("66.249.64.150");
		firstEvent.setContext(_context);
		firstEvent.setCreateDate(_firstEventDate);
		firstEvent.setDataSourceId("1");
		firstEvent.setDate(_date);
		firstEvent.setEventDate(_firstEventDate);
		firstEvent.setEventId("pageDepthReached");
		firstEvent.setEventProperties(_eventProperties);
		firstEvent.setId("310746565640329834");
		firstEvent.setKnownIndividual(false);
		firstEvent.setNormalizedEventDate(_firstEventDate.getTime());
		firstEvent.setProjectId("test");
		firstEvent.setSegmentNames(_segmentNames);
		firstEvent.setUserId("347780e0-7a66-11e8-a0fc-8356dd2944fd");

		Event secondEvent = new Event();

		secondEvent.setAnalyticsKey("www.liferay.com");
		secondEvent.setApplicationId("WebContent");
		secondEvent.setChannelId("1");
		secondEvent.setClientIp("66.249.64.150");
		secondEvent.setContext(_context);
		secondEvent.setCreateDate(_secondEventDate);
		secondEvent.setDataSourceId("1");
		secondEvent.setDate(_date);
		secondEvent.setEventDate(_secondEventDate);
		secondEvent.setEventId("webContentViewed");
		secondEvent.setEventProperties(_eventProperties);
		secondEvent.setId("310746565640329834");
		secondEvent.setKnownIndividual(false);
		secondEvent.setNormalizedEventDate(_secondEventDate.getTime());
		secondEvent.setProjectId("test");
		secondEvent.setSegmentNames(_segmentNames);
		secondEvent.setUserId("347780e0-7a66-11e8-a0fc-8356dd2944fd");

		Session session = new Session(firstEvent);

		session.addEvent(secondEvent);

		List<Event> events = session.getEvents();

		Assert.assertEquals(events.toString(), 2, events.size());

		Assert.assertEquals(
			_firstEventDate.toString(), _firstEventDate,
			session.getFirstEventDate());
		Assert.assertEquals(
			_secondEventDate.toString(), _secondEventDate,
			session.getLastEventDate());
		Assert.assertFalse(session.getFinished());
		Assert.assertFalse(session.isBounced());

		Set<String> canonicalUrls = session.getCanonicalUrls();

		Assert.assertEquals(canonicalUrls.toString(), 1, canonicalUrls.size());
	}

	@Test
	public void testSession() {
		Event event = new Event();

		event.setAnalyticsKey("www.liferay.com");
		event.setApplicationId("WebContent");
		event.setChannelId("1");
		event.setClientIp("66.249.64.150");
		event.setContext(_context);
		event.setCreateDate(_firstEventDate);
		event.setDataSourceId("1");
		event.setDate(_date);
		event.setEventDate(_firstEventDate);
		event.setEventId("documentPreviewed");
		event.setEventProperties(_eventProperties);
		event.setId("310746565640329834");
		event.setKnownIndividual(false);
		event.setNormalizedEventDate(_firstEventDate.getTime());
		event.setProjectId("test");
		event.setSegmentNames(_segmentNames);
		event.setUserId("347780e0-7a66-11e8-a0fc-8356dd2944fd");

		Session session = new Session(event);

		Assert.assertEquals(session.getUserId(), event.getUserId());

		List<Event> events = session.getEvents();

		Assert.assertEquals(events.toString(), 1, events.size());

		Assert.assertEquals(
			_firstEventDate.toString(), _firstEventDate,
			session.getFirstEventDate());
		Assert.assertEquals(
			_firstEventDate.toString(), _firstEventDate,
			session.getLastEventDate());
		Assert.assertEquals(
			event.getProjectId(), event.getProjectId(), session.getProjectId());
		Assert.assertTrue(session.isBounced());
		Assert.assertFalse(session.getFinished());

		Set<String> canonicalUrls = session.getCanonicalUrls();

		Assert.assertEquals(canonicalUrls.toString(), 1, canonicalUrls.size());
	}

	private final Map<String, String> _context = new HashMap<String, String>() {
		{
			put("browserName", "Unknown Crawler");
			put("canonicalUrl", "https://www.liferay.com/home");
			put("city", "Mountain View");
			put("country", "United States");
			put("deviceType", "Desktop");
			put("languageId", "en-US");
			put("platformName", "Unknown");
			put("referrer", "https://www.facebook.com");
			put("region", "California");
			put("title", "Liferay: 数字化体验软件可根据您的需求量身定制");
			put("url", "https://www.liferay.com/zh/home");
		}
	};
	private final Date _date = DateUtil.toDate("2021-06-22");
	private final Map<String, String> _eventProperties =
		new HashMap<String, String>() {
			{
				put("articleId", "223156009");
				put("numberOfWords", "9");
				put("title", "Footer Contact Us");
			}
		};
	private final Timestamp _firstEventDate = DateUtil.toTimestamp(
		"2021-06-22T09:00:30.001Z");
	private final Timestamp _secondEventDate = DateUtil.toTimestamp(
		"2021-06-22T09:01:30.001Z");
	private final Set<String> _segmentNames = new HashSet<String>() {
		{
			add("A");
			add("B");
			add("C");
		}
	};

}