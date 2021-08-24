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

package com.liferay.osb.asah.spark.session.function;

import com.liferay.osb.asah.spark.common.DateUtil;
import com.liferay.osb.asah.spark.session.model.Event;
import com.liferay.osb.asah.spark.session.model.Session;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.spark.sql.execution.streaming.GroupStateImpl;
import org.apache.spark.sql.streaming.GroupState;
import org.apache.spark.sql.streaming.GroupStateTimeout;

import org.junit.Assert;
import org.junit.Test;

import scala.None$;
import scala.Option;
import scala.Some;

/**
 * @author Robson Pastor
 */
public class SessionFlatMapGroupsWithStateFunctionTest {

	@Test
	public void testCall() {
		SessionFlatMapGroupsWithStateFunction function =
			new SessionFlatMapGroupsWithStateFunction(_SESSION_DURATION);

		Event event0 = new Event();

		event0.setContext(_context);
		event0.setDate(_DATE);
		event0.setEventDate(_EVENT_DATE1);

		Session expiredSession = new Session(event0);

		Option<Session> some = new Some<>(expiredSession);

		Option none = None$.MODULE$;

		GroupState<Session> groupStateExpired =
			GroupStateImpl.createForStreaming(
				some, _SESSION_DURATION, _SESSION_DURATION,
				GroupStateTimeout.EventTimeTimeout(), true, true);

		ArrayList<Event> emptyEventList = new ArrayList<>();

		Iterator<Session> sessionIterator = function.call(
			null, emptyEventList.iterator(), groupStateExpired);

		Session sessionExpired = sessionIterator.next();

		Assert.assertFalse(
			groupStateExpired.toString(), groupStateExpired.exists());
		Assert.assertTrue(
			sessionExpired.toString(), sessionExpired.getFinished());

		Event event1 = new Event();

		event1.setContext(_context);
		event1.setDate(_DATE);
		event1.setEventDate(_EVENT_DATE1);

		ArrayList<Event> iterationInput1 = new ArrayList<Event>() {
			{
				add(event1);
			}
		};

		GroupState<Session> groupState = GroupStateImpl.createForStreaming(
			none, _SESSION_DURATION, _SESSION_DURATION,
			GroupStateTimeout.EventTimeTimeout(), false, true);

		Iterator<Session> iterationOutput1 = function.call(
			null, iterationInput1.iterator(), groupState);

		Session session1 = iterationOutput1.next();

		Assert.assertFalse(session1.toString(), session1.getFinished());

		Assert.assertTrue(groupState.toString(), groupState.exists());

		Assert.assertEquals(groupState.toString(), groupState.get(), session1);

		Event event2 = new Event();

		event2.setContext(_context);
		event2.setDate(_DATE);
		event2.setEventDate(_EVENT_DATE2);

		Event event3 = new Event();

		event3.setContext(_context);
		event3.setDate(_DATE);
		event3.setEventDate(_EVENT_DATE3);

		ArrayList<Event> iterationInput2 = new ArrayList<Event>() {
			{
				add(event2);
				add(event3);
			}
		};

		Iterator<Session> iterationOutput2 = function.call(
			null, iterationInput2.iterator(), groupState);

		Session session2 = iterationOutput2.next();

		Assert.assertTrue(groupState.toString(), groupState.exists());

		Assert.assertEquals(groupState.toString(), groupState.get(), session2);

		Event event4 = new Event();

		event4.setContext(_context);
		event4.setDate(_DATE);
		event4.setEventDate(_EVENT_DATE4);

		ArrayList<Event> iterationInput3 = new ArrayList<Event>() {
			{
				add(event4);
			}
		};

		Iterator<Session> iterationOutput3 = function.call(
			null, iterationInput3.iterator(), groupState);

		Session session3 = iterationOutput3.next();

		Session sessionState = groupState.get();

		List<Event> events = session3.getEvents();

		Assert.assertTrue(session3.toString(), session3.getFinished());
		Assert.assertFalse(sessionState.toString(), sessionState.getFinished());
		Assert.assertEquals(events.toString(), 3, events.size());
	}

	private static final Date _DATE = DateUtil.toDate("2021-06-22");

	private static final Timestamp _EVENT_DATE1 = DateUtil.toTimestamp(
		"2021-06-22T09:00:00.001Z");

	private static final Timestamp _EVENT_DATE2 = DateUtil.toTimestamp(
		"2021-06-22T09:30:00.001Z");

	private static final Timestamp _EVENT_DATE3 = DateUtil.toTimestamp(
		"2021-06-22T09:31:00.001Z");

	private static final Timestamp _EVENT_DATE4 = DateUtil.toTimestamp(
		"2021-06-22T10:01:30.001Z");

	private static final long _SESSION_DURATION = 30 * 1000 * 60;

	private static final Map<String, String> _context =
		new HashMap<String, String>() {
			{
				put("browserName", "Unknown Crawler");
				put("canonicalUrl", "https://www.liferay.com/home");
				put("city", "Mountain View");
				put("country", "United States");
				put("referrer", "https://www.liferay.com/pt/home");
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

}