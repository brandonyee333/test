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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.sql.execution.streaming.GroupStateImpl;
import org.apache.spark.sql.streaming.GroupState;
import org.apache.spark.sql.streaming.GroupStateTimeout;

import org.junit.Assert;
import org.junit.Test;

import scala.Option;
import scala.Some;

/**
 * @author Robson Pastor
 */
public class SessionFlatMapGroupsWithStateFunctionTest {

	@Test
	public void testCallWithActiveGroupState() {
		SessionFlatMapGroupsWithStateFunction
			sessionFlatMapGroupsWithStateFunction =
				new SessionFlatMapGroupsWithStateFunction(_SESSION_DURATION);

		List<Event> events = Arrays.asList(
			_createEvent("2021-06-22T09:00:00.001Z"));

		GroupState<Session> groupState = GroupStateImpl.createForStreaming(
			Option.empty(), _SESSION_DURATION, _SESSION_DURATION,
			GroupStateTimeout.EventTimeTimeout(), false, true);

		Iterator<Session> sessionIterator =
			sessionFlatMapGroupsWithStateFunction.call(
				null, events.iterator(), groupState);

		Session session = sessionIterator.next();

		Assert.assertFalse(session.getFinished());
	}

	@Test
	public void testCallWithActiveGroupStateUpdate() {
		SessionFlatMapGroupsWithStateFunction
			sessionFlatMapGroupsWithStateFunction =
				new SessionFlatMapGroupsWithStateFunction(_SESSION_DURATION);

		List<Event> firstIterationEvents = Arrays.asList(
			_createEvent("2021-06-22T09:30:00.001Z"),
			_createEvent("2021-06-22T09:31:00.001Z"));

		GroupState<Session> groupState = GroupStateImpl.createForStreaming(
			Option.empty(), _SESSION_DURATION, _SESSION_DURATION,
			GroupStateTimeout.EventTimeTimeout(), false, true);

		Iterator<Session> sessionIterator =
			sessionFlatMapGroupsWithStateFunction.call(
				null, firstIterationEvents.iterator(), groupState);

		Session session = sessionIterator.next();

		Assert.assertFalse(session.getFinished());

		List<Event> secondIterationEvents = Arrays.asList(
			_createEvent("2021-06-22T10:01:30.001Z"));

		sessionIterator = sessionFlatMapGroupsWithStateFunction.call(
			null, secondIterationEvents.iterator(), groupState);

		Session expiredSession = sessionIterator.next();

		Assert.assertTrue(session.getFinished());
		Assert.assertEquals(firstIterationEvents, expiredSession.getEvents());

		Session activeSession = sessionIterator.next();

		Assert.assertFalse(activeSession.getFinished());
		Assert.assertEquals(secondIterationEvents, activeSession.getEvents());
	}

	@Test
	public void testCallWithTimedOutGroupState() {
		SessionFlatMapGroupsWithStateFunction
			sessionFlatMapGroupsWithStateFunction =
				new SessionFlatMapGroupsWithStateFunction(_SESSION_DURATION);

		Event event = _createEvent("2021-06-22T09:00:00.001Z");

		GroupState<Session> timedOutGroupState =
			GroupStateImpl.createForStreaming(
				new Some<>(new Session(event)), _SESSION_DURATION,
				_SESSION_DURATION, GroupStateTimeout.EventTimeTimeout(), true,
				true);

		ArrayList<Event> events = new ArrayList<>();

		Iterator<Session> sessionIterator =
			sessionFlatMapGroupsWithStateFunction.call(
				null, events.iterator(), timedOutGroupState);

		Session session = sessionIterator.next();

		Assert.assertTrue(session.getFinished());
	}

	private Event _createEvent(String eventDate) {
		Event event = new Event();

		event.setEventDate(DateUtil.toTimestamp(eventDate));

		return event;
	}

	private static final long _SESSION_DURATION = 30 * 60 * 1000;

}