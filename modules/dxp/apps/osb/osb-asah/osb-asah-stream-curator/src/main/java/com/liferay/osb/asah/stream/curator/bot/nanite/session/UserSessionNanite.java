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

package com.liferay.osb.asah.stream.curator.bot.nanite.session;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.EventStorageDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.AnalyticsEvents;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

/**
 * @author André Miranda
 */
@Component
public class UserSessionNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "user-sessions";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_boundedExecutor.awaitPendingTasks();
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
	}

	private List<UserSession> _getUserSessions(Date lastEventDate) {

		// TODO Get user sessions

		JSONArray userSessionsJSONArray = new JSONArray();

		if (userSessionsJSONArray.length() == 0) {
			return Collections.emptyList();
		}

		Stream<Object> stream = JSONUtil.toObjectStream(userSessionsJSONArray);

		return stream.map(
			jsonObject -> _objectMapper.convertValue(
				jsonObject, UserSession.class)
		).filter(
			userSession -> {
				Date userSessionLastEventDate = userSession.getLastEventDate();

				ZonedDateTime userSessionLastEventZonedDateTime =
					ZonedDateTime.ofInstant(
						userSessionLastEventDate.toInstant(),
						_timeZoneDog.getZoneId());

				ZonedDateTime lastEventZonedDateTime = ZonedDateTime.ofInstant(
					lastEventDate.toInstant(), _timeZoneDog.getZoneId());

				long deltaDays = ChronoUnit.DAYS.between(
					userSessionLastEventZonedDateTime.truncatedTo(
						ChronoUnit.DAYS),
					lastEventZonedDateTime.truncatedTo(ChronoUnit.DAYS));

				return deltaDays == 0;
			}
		).collect(
			Collectors.toList()
		);
	}

	@PostConstruct
	private void _init() {
	}

	private UserSession _mergeUserSessions(
		AnalyticsEvents analyticsEvents, List<UserSession> userSessions) {

		UserSession retaininingUserSession = userSessions.remove(0);

		Set<String> canonicalUrls = analyticsEvents.getCanonicalUrls();
		Set<String> referrers = analyticsEvents.getReferrers();
		Set<String> urls = analyticsEvents.getUrls();

		List<Date> firstEventDates = new ArrayList<Date>() {
			{
				add(analyticsEvents.getFirstAnalyticsEventDate());
				add(retaininingUserSession.getFirstEventDate());
			}
		};

		List<Date> lastEventDates = new ArrayList<Date>() {
			{
				add(analyticsEvents.getLastAnalyticsEventDate());
				add(retaininingUserSession.getLastEventDate());
			}
		};

		for (UserSession userSession : userSessions) {
			canonicalUrls.addAll(userSession.getCanonicalUrls());
			firstEventDates.add(userSession.getFirstEventDate());
			lastEventDates.add(userSession.getLastEventDate());
			referrers.addAll(userSession.getReferrers());
			urls.addAll(userSession.getUrls());

			// TODO delete user session

		}

		// TODO Update user session

		return retaininingUserSession;
	}

	private void _processAnalyticsEvents(
		List<Message<AnalyticsEvent>> messages) {

		List<List<Message<AnalyticsEvent>>> messageSessionsAnalyticsEvents =
			new ArrayList<>();

		List<Message<AnalyticsEvent>> currentMessageAnalyticsEvents =
			new ArrayList<>();

		AnalyticsEvent previousAnalyticsEvent = null;

		for (Message<AnalyticsEvent> message : messages) {
			AnalyticsEvent analyticsEvent = message.getObject();

			if (previousAnalyticsEvent != null) {
				Date previousEventDate = previousAnalyticsEvent.getEventDate();

				ZonedDateTime previousEventZonedDateTime =
					ZonedDateTime.ofInstant(
						previousEventDate.toInstant(),
						_timeZoneDog.getZoneId());

				Date eventDate = analyticsEvent.getEventDate();

				ZonedDateTime eventZonedDateTime = ZonedDateTime.ofInstant(
					eventDate.toInstant(), _timeZoneDog.getZoneId());

				long daysDelta = ChronoUnit.DAYS.between(
					previousEventZonedDateTime.truncatedTo(ChronoUnit.DAYS),
					eventZonedDateTime.truncatedTo(ChronoUnit.DAYS));

				long minutesDelta = ChronoUnit.MINUTES.between(
					previousEventZonedDateTime, eventZonedDateTime);

				if ((daysDelta > 0) || (minutesDelta >= 30)) {
					messageSessionsAnalyticsEvents.add(
						currentMessageAnalyticsEvents);

					currentMessageAnalyticsEvents = new ArrayList<>();
				}
			}

			currentMessageAnalyticsEvents.add(message);

			previousAnalyticsEvent = analyticsEvent;
		}

		if (!currentMessageAnalyticsEvents.isEmpty()) {
			messageSessionsAnalyticsEvents.add(currentMessageAnalyticsEvents);
		}

		for (List<Message<AnalyticsEvent>> sessionAnalyticsEvents :
				messageSessionsAnalyticsEvents) {

			_processSessionAnalyticsEvents(sessionAnalyticsEvents);
		}
	}

	private void _processSessionAnalyticsEvents(
		List<Message<AnalyticsEvent>> messages) {

		Stream<Message<AnalyticsEvent>> stream = messages.stream();

		AnalyticsEvents analyticsEvents = new AnalyticsEvents(
			stream.map(
				Message::getObject
			).collect(
				Collectors.toList()
			));

		List<UserSession> userSessions = _getUserSessions(
			analyticsEvents.getLastAnalyticsEventDate());

		if (userSessions.isEmpty()) {
			Date lastAnalyticsEventDate =
				analyticsEvents.getLastAnalyticsEventDate();

			ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(
				lastAnalyticsEventDate.toInstant(), _timeZoneDog.getZoneId());

			ZonedDateTime currentDayZonedDateTime = ZonedDateTime.now(
				_timeZoneDog.getZoneId());

			currentDayZonedDateTime = currentDayZonedDateTime.withHour(0);
			currentDayZonedDateTime = currentDayZonedDateTime.withMinute(0);
			currentDayZonedDateTime = currentDayZonedDateTime.withNano(0);
			currentDayZonedDateTime = currentDayZonedDateTime.withSecond(0);

			if (zonedDateTime.isBefore(currentDayZonedDateTime)) {
			}
			else {
			}
		}
		else if (userSessions.size() == 1) {
			_updateUserSession();
		}
		else {
			_mergeUserSessions(analyticsEvents, userSessions);
		}
	}

	private void _run() throws Exception {
		while (true) {
			long start = System.currentTimeMillis();

			List<Message<AnalyticsEvent>> messages =
				_messageSubscriber.pullMessages(
					_userSessionNanitePullMessagesSize,
					AnalyticsEvent::toAnalyticsEvent);

			if (messages.isEmpty()) {
				break;
			}

			Stream<Message<AnalyticsEvent>> stream = messages.stream();

			stream.sorted(
				Comparator.comparing(
					message -> {
						AnalyticsEvent analyticsEvent = message.getObject();

						return analyticsEvent.getEventDate();
					})
			).collect(
				Collectors.groupingBy(
					message -> {
						AnalyticsEvent analyticsEvent = message.getObject();

						return Tuples.of(
							analyticsEvent.getProjectId(),
							analyticsEvent.getUserId());
					})
			).forEach(
				this::_runAsync
			);

			if (_log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				_log.info(
					String.format(
						"%s dispatched %d analytics events in %d ms",
						clazz.getSimpleName(), messages.size(),
						System.currentTimeMillis() - start));
			}
		}
	}

	private void _runAsync(
		Tuple2<String, String> tuple2, List<Message<AnalyticsEvent>> messages) {

		_boundedExecutor.runAsync(
			() -> {
				try {
					long start = System.currentTimeMillis();

					ProjectIdThreadLocal.setProjectId(tuple2.getT1());

					_processAnalyticsEvents(messages);

					_messageSubscriber.sendAckIds(
						ListUtil.map(messages, Message::getAckId));

					if (_log.isDebugEnabled()) {
						Class<?> clazz = getClass();

						_log.debug(
							String.format(
								"%s processed %d analytics events in %d ms",
								clazz.getSimpleName(), messages.size(),
								System.currentTimeMillis() - start));
					}
				}
				catch (Exception exception) {
					messages.forEach(
						message -> _messageSubscriber.registerException(
							exception, message));

					List<String> analyticsEventsString = ListUtil.map(
						messages,
						message -> {
							AnalyticsEvent analyticsEvent = message.getObject();

							return analyticsEvent.toJSON();
						});

					_log.error(
						"Unable to process analytics events " +
							analyticsEventsString,
						exception);
				}
			},
			KeyReentrantLock.getReentrantLock(getClass(), tuple2));
	}

	private void _updateUserSession() {

		// TODO update user session

	}

	private static final Log _log = LogFactory.getLog(UserSessionNanite.class);

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(30, 20);

	@Autowired
	private EventStorageDog _eventStorageDog;

	@Autowired
	private IndividualDog _individualDog;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_SESSION)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

	@Value("${osb.asah.user.session.nanite.pull.messages.size:50}")
	private int _userSessionNanitePullMessagesSize;

}