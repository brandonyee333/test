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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Robson Pastor
 */
public class SessionTest {

	@Test
	public void testAddEvent() {
		Event firstEvent = new Event();

		firstEvent.setApplicationId("WebContent");
		firstEvent.setChannelId("1");
		firstEvent.setContext(_context);
		firstEvent.setCreateDate(_firstEventDate);
		firstEvent.setDataSourceId("1");
		firstEvent.setDate(_date);
		firstEvent.setEventDate(_firstEventDate);
		firstEvent.setEventId("pageDepthReached");
		firstEvent.setId("310746565640329834");
		firstEvent.setKnownIndividual(false);
		firstEvent.setNormalizedEventDate(_firstEventDate.getTime());
		firstEvent.setProjectId("test");
		firstEvent.setSegmentNames(_segmentNames);
		firstEvent.setUserId("347780e0-7a66-11e8-a0fc-8356dd2944fd");

		Event secondEvent = new Event();

		secondEvent.setApplicationId("WebContent");
		secondEvent.setChannelId("1");
		secondEvent.setContext(_context);
		secondEvent.setCreateDate(_secondEventDate);
		secondEvent.setDataSourceId("1");
		secondEvent.setDate(_date);
		secondEvent.setEventDate(_secondEventDate);
		secondEvent.setEventId("webContentViewed");
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

		List<String> canonicalUrls = session.getCanonicalUrls();

		Assert.assertEquals(canonicalUrls.toString(), 1, canonicalUrls.size());
	}

	@Test
	public void testSession() {
		Event event = new Event();

		event.setApplicationId("WebContent");
		event.setChannelId("1");
		event.setContext(_context);
		event.setCreateDate(_firstEventDate);
		event.setDataSourceId("1");
		event.setDate(_date);
		event.setEventDate(_firstEventDate);
		event.setEventId("documentPreviewed");
		event.setId("310746565640329834");
		event.setKnownIndividual(false);
		event.setNormalizedEventDate(_firstEventDate.getTime());
		event.setProjectId("test");
		event.setSegmentNames(_segmentNames);
		event.setUserId("347780e0-7a66-11e8-a0fc-8356dd2944fd");

		Session session = new Session(event);

		Map<String, String> acquisition = session.getAcquisition();

		Assert.assertEquals(
			acquisition.toString(), "7010g000000nK1uAAE",
			acquisition.get("campaign"));
		Assert.assertEquals(
			acquisition.toString(), "social", acquisition.get("channel"));
		Assert.assertEquals(
			acquisition.toString(), "linkedin-page-button",
			acquisition.get("content"));
		Assert.assertEquals(
			acquisition.toString(), "social", acquisition.get("medium"));
		Assert.assertEquals(
			acquisition.toString(), "www.liferay.com",
			acquisition.get("referrerHost"));

		List<String> canonicalUrls = session.getCanonicalUrls();

		Assert.assertEquals(canonicalUrls.toString(), 1, canonicalUrls.size());

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
		Assert.assertFalse(session.getFinished());
		Assert.assertEquals(session.getUserId(), event.getUserId());
		Assert.assertTrue(session.isBounced());
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
			put("referrer", "https://www.liferay.com/pt/home");
			put("region", "California");
			put(
				"title",
				"Liferay DXP | A Plataforma que Unifica a Experiência");
			put(
				"url",
				"https://www.liferay.com/pt/home?utm_source=linkedin&" +
					"utm_medium=social&utm_campaign=7010g000000nK1uAAE&" +
						"utm_content=linkedin-page-button");
		}
	};
	private final Date _date = DateUtil.toDate("2021-06-22");
	private final Timestamp _firstEventDate = DateUtil.toTimestamp(
		"2021-06-22T09:00:30.001Z");
	private final Timestamp _secondEventDate = DateUtil.toTimestamp(
		"2021-06-22T09:01:30.001Z");
	private final List<String> _segmentNames = new ArrayList<String>() {
		{
			add("A");
			add("B");
			add("C");
		}
	};

}