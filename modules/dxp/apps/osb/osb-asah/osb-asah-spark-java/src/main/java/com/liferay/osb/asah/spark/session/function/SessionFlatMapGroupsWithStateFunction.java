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

import com.liferay.osb.asah.spark.session.model.Event;
import com.liferay.osb.asah.spark.session.model.Session;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.api.java.function.FlatMapGroupsWithStateFunction;
import org.apache.spark.sql.streaming.GroupState;

import scala.Tuple2;

/**
 * @author Robson Pastor
 */
public class SessionFlatMapGroupsWithStateFunction
	implements FlatMapGroupsWithStateFunction
		<Tuple2<String, Date>, Event, Session, Session> {

	public SessionFlatMapGroupsWithStateFunction(long sessionDuration) {
		_sessionDuration = sessionDuration;
	}

	@Override
	public Iterator<Session> call(
		Tuple2<String, Date> keyTuple, Iterator<Event> events,
		GroupState<Session> groupState) {

		ArrayList<Session> sessions = new ArrayList<>();

		// Session has expired

		if (groupState.hasTimedOut()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Group state has timed out");
			}

			Session expiredSession = groupState.get();

			expiredSession.setFinished(true);
			expiredSession.setIterationNumber(
				expiredSession.getIterationNumber() + 1);

			sessions.add(expiredSession);

			return sessions.iterator();
		}

		// Session is active

		if (groupState.exists()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Group state has an open session");
			}

			Session activeSession = groupState.get();

			activeSession.setIterationNumber(
				activeSession.getIterationNumber() + 1);

			sessions.add(activeSession);
		}

		// Add events to the active session and finalize the last active
		// session if expired

		while (events.hasNext()) {
			Event event = events.next();

			if (sessions.isEmpty()) {
				sessions.add(new Session(event));
			}
			else {
				Session activeSession = sessions.get(sessions.size() - 1);

				Timestamp eventDate = event.getEventDate();
				Timestamp lastEventDate = activeSession.getLastEventDate();

				if (eventDate.getTime() >
						(lastEventDate.getTime() + _sessionDuration)) {

					activeSession.setFinished(true);

					sessions.add(new Session(event));
				}
				else {
					activeSession.addEvent(event);
				}
			}
		}

		// Update the active session timeout

		Session activeSession = sessions.get(sessions.size() - 1);

		Timestamp lastEventDate = activeSession.getLastEventDate();

		groupState.setTimeoutTimestamp(
			lastEventDate.getTime() + _sessionDuration);

		groupState.update(activeSession);

		if (_log.isDebugEnabled()) {
			List<Event> activeSessionEvents = activeSession.getEvents();

			_log.debug(
				String.format(
					"Extended session ID %s timeout timestamp to %d for %d" +
						"events",
					activeSession.getSessionId(),
					lastEventDate.getTime() + _sessionDuration,
					activeSessionEvents.size()));
		}

		return sessions.iterator();
	}

	private static final Log _log = LogFactory.getLog(
		SessionFlatMapGroupsWithStateFunction.class);

	private final long _sessionDuration;

}