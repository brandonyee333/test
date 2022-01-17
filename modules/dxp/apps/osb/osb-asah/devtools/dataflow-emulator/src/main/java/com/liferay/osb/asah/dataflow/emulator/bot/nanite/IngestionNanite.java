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

package com.liferay.osb.asah.dataflow.emulator.bot.nanite;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.dataflow.emulator.entity.Event;
import com.liferay.osb.asah.dataflow.emulator.entity.EventProperty;
import com.liferay.osb.asah.dataflow.emulator.entity.Session;
import com.liferay.osb.asah.dataflow.emulator.repository.EventPropertyRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.EventRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.SessionRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.util.InMemoryResource;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class IngestionNanite {

	public void checkWatermark() {

		// Data driven watermark wasn't advancing for the last minute

		if ((System.currentTimeMillis() - _watermarkModifiedTimestamp) <
				DateUtil.MINUTE) {

			return;
		}

		_advanceWatermark(System.currentTimeMillis());
	}

	public void closeOpenSessions() {
		for (Map.Entry<String, SessionContext> entry : _sessions.entrySet()) {
			SessionContext sessionContext = entry.getValue();

			if ((_watermarkTimestamp - sessionContext.sessionEnd.getTime()) >=
					0) {

				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"Closing session {%s, %s}", sessionContext.userId,
							sessionContext.id));
				}

				_writeSession(sessionContext);

				_sessions.remove(entry.getKey());
			}
		}
	}

	public void run() throws Exception {
		while (true) {
			List<Message<String>> messages = _messageSubscriber.pullMessages(
				100, String::valueOf);

			if (messages.isEmpty()) {
				break;
			}

			List<AnalyticsEvent> analyticsEvents = _parseMessages(messages);

			_processAnalyticsEvents(analyticsEvents);

			_advanceWatermark(analyticsEvents);

			_acknowledgeMessages(messages);
		}
	}

	private void _acknowledgeMessages(List<Message<String>> messages) {
		Stream<Message<String>> stream = messages.stream();

		_messageSubscriber.sendAckIds(
			stream.map(
				Message::getAckId
			).collect(
				Collectors.toList()
			));
	}

	private void _advanceWatermark(List<AnalyticsEvent> analyticsEvents) {
		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		Optional<Date> lastEventDateOptional = stream.map(
			AnalyticsEvent::getEventDate
		).max(
			Date::compareTo
		);

		if (!lastEventDateOptional.isPresent()) {
			return;
		}

		Date lastEventDate = lastEventDateOptional.get();

		_advanceWatermark(lastEventDate.getTime());
	}

	private void _advanceWatermark(long lastEventDateTimestamp) {
		long newWatermarkTimestamp =
			lastEventDateTimestamp - (_allowedLateness * DateUtil.MINUTE);

		if (newWatermarkTimestamp > _watermarkTimestamp) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Advancing watermark to " +
						new Date(newWatermarkTimestamp));
			}

			_watermarkTimestamp = newWatermarkTimestamp;
			_watermarkModifiedTimestamp = System.currentTimeMillis();
		}
	}

	private String _getSessionKey(AnalyticsEvent analyticsEvent) {
		return String.format(
			"%s#%s#%s#%s", analyticsEvent.getProjectId(),
			analyticsEvent.getDataSourceId(), analyticsEvent.getChannelId(),
			analyticsEvent.getUserId());
	}

	@PostConstruct
	private void _init() {
		if (_log.isInfoEnabled()) {
			_log.info("Initializing Ingestion Schema");
		}

		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new InMemoryResource(
						"CREATE SCHEMA IF NOT EXISTS " +
							ProjectIdThreadLocal.getProjectId())),
				_dataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("tables.sql")),
				_dataSource);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	private boolean _isLate(AnalyticsEvent analyticsEvent) {
		Date eventDate = analyticsEvent.getEventDate();

		if ((eventDate.getTime() - _watermarkTimestamp) >= 0) {
			return false;
		}

		return true;
	}

	private List<AnalyticsEvent> _parseMessages(
		List<Message<String>> messages) {

		Stream<Message<String>> stream = messages.stream();

		return stream.map(
			message -> {
				try {
					return AnalyticsEvent.toAnalyticsEvent(message.getObject());
				}
				catch (Exception exception) {
					_log.error(exception, exception);

					return null;
				}
			}
		).filter(
			Objects::nonNull
		).collect(
			Collectors.toList()
		);
	}

	private void _processAnalyticsEvent(AnalyticsEvent analyticsEvent) {
		try {
			SessionContext sessionContext = _updateOrCreateSessionContext(
				analyticsEvent);

			Date eventDate = analyticsEvent.getEventDate();

			if ((eventDate.getTime() - sessionContext.sessionStart.getTime()) <
					0) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Discarding late user event " +
							analyticsEvent.toJSON());
				}

				return;
			}

			_writeEvent(analyticsEvent, sessionContext);
			_writeEventProperties(analyticsEvent);
		}
		catch (Exception exception) {
			_log.error(
				"Unable to process analytics event " + analyticsEvent.toJSON() +
					". " + exception.getMessage());
		}
	}

	private void _processAnalyticsEvents(List<AnalyticsEvent> analyticsEvents) {
		for (AnalyticsEvent analyticsEvent : analyticsEvents) {
			if (_isLate(analyticsEvent)) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Discarding late analytics event " +
							analyticsEvent.toJSON());
				}

				continue;
			}

			_processAnalyticsEvent(analyticsEvent);
		}
	}

	private SessionContext _updateOrCreateSessionContext(
		AnalyticsEvent analyticsEvent) {

		Date eventDate = analyticsEvent.getEventDate();

		String sessionKey = _getSessionKey(analyticsEvent);

		SessionContext sessionContext = _sessions.get(sessionKey);

		if (sessionContext == null) {
			sessionContext = new SessionContext();

			sessionContext.channelId = analyticsEvent.getChannelId();
			sessionContext.id = DigestUtils.md5Hex(
				sessionKey + "#" + eventDate.getTime());
			sessionContext.projectId = analyticsEvent.getProjectId();
			sessionContext.sessionStart = analyticsEvent.getEventDate();
			sessionContext.userId = analyticsEvent.getUserId();

			_sessions.put(sessionKey, sessionContext);
		}

		sessionContext.maxEventDateTimestamp = Math.max(
			sessionContext.maxEventDateTimestamp, eventDate.getTime());

		Date sessionEnd = new Date(
			sessionContext.maxEventDateTimestamp +
				(_gapDuration * DateUtil.MINUTE));

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"User session {%s, %s} will be closed at %s",
					sessionContext.userId, sessionContext.id, sessionEnd));
		}

		sessionContext.sessionEnd = sessionEnd;

		return sessionContext;
	}

	private void _writeEvent(
		AnalyticsEvent analyticsEvent, SessionContext sessionContext) {

		Event event = new Event();

		Map<String, String> context = analyticsEvent.getContext();

		event.setApplicationId(analyticsEvent.getApplicationId());
		event.setBrowserName(context.get("browserName"));
		event.setCanonicalUrl(context.get("canonicalUrl"));
		event.setChannelId(Long.valueOf(analyticsEvent.getChannelId()));
		event.setCity(context.get("city"));
		event.setContentLanguageId(context.get("contentLanguageId"));
		event.setContext(context);
		event.setCountry(context.get("country"));
		event.setCreateDate(analyticsEvent.getCreateDate());
		event.setDataSourceId(Long.valueOf(analyticsEvent.getDataSourceId()));
		event.setDescription(context.get("description"));
		event.setDeviceType(context.get("deviceType"));
		event.setEventDate(analyticsEvent.getEventDate());
		event.setEventId(analyticsEvent.getEventId());
		event.setEventProperties(analyticsEvent.getEventProperties());
		event.setExperienceId(context.get("experienceId"));
		event.setId(analyticsEvent.getId());
		event.setLanguageId(context.get("languageId"));
		event.setPlatformName(context.get("platformName"));
		event.setProjectId(analyticsEvent.getProjectId());
		event.setProjectTimeZoneId(analyticsEvent.getProjectTimeZoneId());
		event.setReferrer(context.get("referrer"));
		event.setRegion(context.get("region"));
		event.setSessionId(sessionContext.id);
		event.setTimezoneOffset(context.get("timezoneOffset"));
		event.setTitle(context.get("title"));
		event.setUrl(context.get("url"));
		event.setUserId(analyticsEvent.getUserId());
		event.setVariantId(context.get("variantId"));

		_eventRepository.save(event);
	}

	private void _writeEventProperties(AnalyticsEvent analyticsEvent) {
		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
			EventProperty eventProperty = new EventProperty();

			eventProperty.setChannelId(
				Long.valueOf(analyticsEvent.getChannelId()));
			eventProperty.setEventDate(analyticsEvent.getEventDate());
			eventProperty.setId(analyticsEvent.getId());
			eventProperty.setName(entry.getKey());
			eventProperty.setProjectId(analyticsEvent.getProjectId());
			eventProperty.setValue(entry.getValue());

			_eventPropertyRepository.save(eventProperty);
		}
	}

	private void _writeSession(SessionContext sessionContext) {
		Session session = new Session();

		session.setChannelId(Long.valueOf(sessionContext.channelId));
		session.setId(sessionContext.id);
		session.setProjectId(sessionContext.projectId);
		session.setSessionEnd(sessionContext.sessionEnd);
		session.setSessionStart(sessionContext.sessionStart);

		_sessionRepository.save(session);
	}

	private static final Log _log = LogFactory.getLog(IngestionNanite.class);

	@Value("${session.window.allowed.lateness:1}")
	private long _allowedLateness;

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _dataSource;

	@Autowired
	private EventPropertyRepository _eventPropertyRepository;

	@Autowired
	private EventRepository _eventRepository;

	@Value("${session.window.gap.duration:3}")
	private long _gapDuration;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private SessionRepository _sessionRepository;

	private final Map<String, SessionContext> _sessions =
		new ConcurrentHashMap<>();
	private long _watermarkModifiedTimestamp;
	private long _watermarkTimestamp;

	private static class SessionContext {

		public String channelId;
		public String id;
		public long maxEventDateTimestamp;
		public String projectId;
		public Date sessionEnd;
		public Date sessionStart;
		public String userId;

	}

}