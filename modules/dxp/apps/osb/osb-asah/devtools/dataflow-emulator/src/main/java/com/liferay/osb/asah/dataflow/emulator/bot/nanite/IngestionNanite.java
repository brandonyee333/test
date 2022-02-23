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
import com.liferay.osb.asah.dataflow.emulator.entity.BQEvent;
import com.liferay.osb.asah.dataflow.emulator.entity.BQEventProperty;
import com.liferay.osb.asah.dataflow.emulator.entity.BQSession;
import com.liferay.osb.asah.dataflow.emulator.repository.BQEventPropertyRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQEventRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQSessionRepository;

import java.util.ArrayList;
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

		// Data driven watermark was not advancing for the last minute

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

				_writeBQSession(sessionContext);

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

			Stream<AnalyticsEvent> stream = analyticsEvents.stream();

			stream.collect(
				Collectors.groupingBy(
					analyticsEvent -> analyticsEvent.getProjectId())
			).forEach(
				(projectId, analyticsEventsList) -> {
					ProjectIdThreadLocal.setProjectId(projectId);

					_processAnalyticsEvents(analyticsEvents);
				}
			);

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

			_writeBQEvent(analyticsEvent, sessionContext);
			_writeBQEventProperties(analyticsEvent);
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
			sessionContext.id = DigestUtils.sha256Hex(
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

	private void _writeBQEvent(
		AnalyticsEvent analyticsEvent, SessionContext sessionContext) {

		BQEvent bqEvent = new BQEvent();

		Map<String, String> context = analyticsEvent.getContext();

		bqEvent.setApplicationId(analyticsEvent.getApplicationId());
		bqEvent.setBrowserName(context.get("browserName"));
		bqEvent.setCanonicalUrl(context.get("canonicalUrl"));
		bqEvent.setChannelId(Long.valueOf(analyticsEvent.getChannelId()));
		bqEvent.setCity(context.get("city"));
		bqEvent.setContentLanguageId(context.get("contentLanguageId"));
		bqEvent.setContext(String.valueOf(context));
		bqEvent.setCountry(context.get("country"));
		bqEvent.setCreateDate(analyticsEvent.getCreateDate());
		bqEvent.setDataSourceId(Long.valueOf(analyticsEvent.getDataSourceId()));
		bqEvent.setDescription(context.get("description"));
		bqEvent.setDeviceType(context.get("deviceType"));
		bqEvent.setEventDate(analyticsEvent.getEventDate());
		bqEvent.setEventId(analyticsEvent.getEventId());
		bqEvent.setEventProperties(
			String.valueOf(analyticsEvent.getEventProperties()));
		bqEvent.setExperienceId(context.get("experienceId"));
		bqEvent.setId(analyticsEvent.getId());
		bqEvent.setIndividualId(Long.valueOf(analyticsEvent.getIndividualId()));
		bqEvent.setKeywords(context.get("keywords"));
		bqEvent.setKnownIndividual(analyticsEvent.isKnownIndividual());
		bqEvent.setLanguageId(context.get("languageId"));
		bqEvent.setPlatformName(context.get("platformName"));
		bqEvent.setProjectTimeZoneId(analyticsEvent.getProjectTimeZoneId());
		bqEvent.setReferrer(context.get("referrer"));
		bqEvent.setRegion(context.get("region"));
		bqEvent.setSegmentNames(
			new ArrayList<>(analyticsEvent.getSegmentNames()));
		bqEvent.setSessionId(sessionContext.id);
		bqEvent.setTimezoneOffset(context.get("timezoneOffset"));
		bqEvent.setTitle(context.get("title"));
		bqEvent.setUrl(context.get("url"));
		bqEvent.setUserId(analyticsEvent.getUserId());
		bqEvent.setVariantId(context.get("variantId"));

		_bqEventRepository.save(bqEvent);
	}

	private void _writeBQEventProperties(AnalyticsEvent analyticsEvent) {
		Map<String, String> eventProperties =
			analyticsEvent.getEventProperties();

		for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
			BQEventProperty bqEventProperty = new BQEventProperty();

			bqEventProperty.setChannelId(
				Long.valueOf(analyticsEvent.getChannelId()));
			bqEventProperty.setEventDate(analyticsEvent.getEventDate());
			bqEventProperty.setId(analyticsEvent.getId());
			bqEventProperty.setName(entry.getKey());
			bqEventProperty.setProjectId(analyticsEvent.getProjectId());
			bqEventProperty.setValue(entry.getValue());

			_bqEventPropertyRepository.save(bqEventProperty);
		}
	}

	private void _writeBQSession(SessionContext sessionContext) {
		BQSession bqSession = new BQSession();

		bqSession.setChannelId(Long.valueOf(sessionContext.channelId));
		bqSession.setId(sessionContext.id);
		bqSession.setSessionEnd(sessionContext.sessionEnd);
		bqSession.setSessionStart(sessionContext.sessionStart);

		_bqSessionRepository.save(bqSession);
	}

	private static final Log _log = LogFactory.getLog(IngestionNanite.class);

	@Value("${session.window.allowed.lateness:1}")
	private long _allowedLateness;

	@Autowired
	private BQEventPropertyRepository _bqEventPropertyRepository;

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _dataSource;

	@Value("${session.window.gap.duration:3}")
	private long _gapDuration;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS)
	private MessageSubscriber _messageSubscriber;

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