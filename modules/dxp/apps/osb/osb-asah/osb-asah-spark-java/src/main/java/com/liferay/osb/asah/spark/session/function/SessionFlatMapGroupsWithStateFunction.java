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
		Tuple2<String, Date> key, Iterator<Event> events,
		GroupState<Session> groupState) {

		ArrayList<Session> sessions = new ArrayList<>();

		if (groupState.hasTimedOut()) {
			Session expiredSession = groupState.get();

			expiredSession.setInteractionNumber(
				expiredSession.getIterationNumber() + 1);
			expiredSession.setFinished(true);

			sessions.add(expiredSession);
		}
		else {
			if (groupState.exists()) {
				Session expiredSession = groupState.get();

				expiredSession.setInteractionNumber(
					expiredSession.getIterationNumber() + 1);

				sessions.add(expiredSession);
			}

			while (events.hasNext()) {
				Event event = events.next();

				if (sessions.isEmpty()) {
					sessions.add(new Session(event));
				}
				else {
					Session session = sessions.get(sessions.size() - 1);

					Timestamp eventDate = event.getEventDate();
					Timestamp lastEventDate = session.getLastEventDate();

					if (eventDate.getTime() >
							(lastEventDate.getTime() + _sessionDuration)) {

						session.setFinished(true);
						sessions.add(new Session(event));
					}
					else {
						session.addEvent(event);
					}
				}
			}

			if (!sessions.isEmpty()) {
				Session lastSession = sessions.get(sessions.size() - 1);

				if (!lastSession.getFinished()) {
					Timestamp lastEventDate = lastSession.getLastEventDate();

					groupState.setTimeoutTimestamp(
						lastEventDate.getTime() + _sessionDuration);

					groupState.update(lastSession);
				}
			}
		}

		return sessions.iterator();
	}

	private final long _sessionDuration;

}