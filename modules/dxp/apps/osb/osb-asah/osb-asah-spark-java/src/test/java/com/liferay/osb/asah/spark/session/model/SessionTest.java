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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

		Assertions.assertEquals(2, events.size(), events.toString());

		Assertions.assertEquals(
			_firstEventDate, session.getFirstEventDate(),
			_firstEventDate.toString());
		Assertions.assertEquals(
			_secondEventDate, session.getLastEventDate(),
			_secondEventDate.toString());
		Assertions.assertFalse(session.getFinished());
		Assertions.assertFalse(session.isBounced());

		List<String> canonicalUrls = session.getCanonicalUrls();

		Assertions.assertEquals(
			1, canonicalUrls.size(), canonicalUrls.toString());
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

		Assertions.assertEquals(
			"7010g000000nK1uAAE", acquisition.get("campaign"),
			acquisition.toString());
		Assertions.assertEquals(
			"social", acquisition.get("channel"), acquisition.toString());
		Assertions.assertEquals(
			"linkedin-page-button", acquisition.get("content"),
			acquisition.toString());
		Assertions.assertEquals(
			"social", acquisition.get("medium"), acquisition.toString());
		Assertions.assertEquals(
			"www.liferay.com", acquisition.get("referrerHost"),
			acquisition.toString());

		List<String> canonicalUrls = session.getCanonicalUrls();

		Assertions.assertEquals(
			1, canonicalUrls.size(), canonicalUrls.toString());

		List<Event> events = session.getEvents();

		Assertions.assertEquals(1, events.size(), events.toString());

		Assertions.assertEquals(
			_firstEventDate, session.getFirstEventDate(),
			_firstEventDate.toString());
		Assertions.assertEquals(
			_firstEventDate, session.getLastEventDate(),
			_firstEventDate.toString());
		Assertions.assertEquals(
			event.getProjectId(), session.getProjectId(), event.getProjectId());
		Assertions.assertFalse(session.getFinished());
		Assertions.assertEquals(session.getUserId(), event.getUserId());
		Assertions.assertTrue(session.isBounced());
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