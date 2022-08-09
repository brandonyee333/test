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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.model.Acquisition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.AnalyticsEvents;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONArray;
import org.json.JSONObject;

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

	private UserSession _createUserSession(
		AnalyticsEvents analyticsEvents, boolean completed, String userId) {

		UserSession userSession = new UserSession();

		AnalyticsEvent firstAnalyticsEvent =
			analyticsEvents.getFirstAnalyticsEvent();

		Map<String, String> context = firstAnalyticsEvent.getContext();

		String url = MapUtil.getString(context, "url");

		userSession.setAcquisition(
			new Acquisition(MapUtil.getString(context, "referrer"), url));

		userSession.setBounced(_isSessionBounced(analyticsEvents));
		userSession.setBrowserName(MapUtil.getString(context, "browserName"));
		userSession.setCanonicalUrls(analyticsEvents.getCanonicalUrls());
		userSession.setChannelId(firstAnalyticsEvent.getChannelId());
		userSession.setCity(MapUtil.getString(context, "city"));
		userSession.setCompleted(completed);

		Date date = new Date();

		if (completed) {
			userSession.setCompleteDate(date);
		}

		userSession.setContentLanguageId(
			MapUtil.getString(context, "contentLanguageId"));
		userSession.setCountry(MapUtil.getString(context, "country"));
		userSession.setDataSourceId(firstAnalyticsEvent.getDataSourceId());
		userSession.setDate(firstAnalyticsEvent.getNormalizedEventDate());
		userSession.setDeviceType(MapUtil.getString(context, "deviceType"));
		userSession.setDevicePixelRatio(
			MapUtil.getString(context, "devicePixelRatio"));
		userSession.setDuration(0L);
		userSession.setEntryPage(url);
		userSession.setFinalized(false);
		userSession.setFirstEventDate(firstAnalyticsEvent.getEventDate());

		Individual individual = _fetchIndividual(firstAnalyticsEvent);

		if (individual != null) {
			userSession.setIndividualId(String.valueOf(individual.getId()));
		}

		userSession.setInteractionsCount(
			analyticsEvents.getInteractionsCount());
		userSession.setLanguageId(MapUtil.getString(context, "languageId"));
		userSession.setLastEventDate(
			analyticsEvents.getLastAnalyticsEventDate());
		userSession.setModifiedDate(date);
		userSession.setPageViewsCount(analyticsEvents.getPageViewsCount());
		userSession.setPlatformName(MapUtil.getString(context, "platformName"));
		userSession.setReferrers(analyticsEvents.getReferrers());
		userSession.setRegion(MapUtil.getString(context, "region"));
		userSession.setScreenHeight(MapUtil.getString(context, "screenHeight"));
		userSession.setScreenWidth(MapUtil.getString(context, "screenWidth"));
		userSession.setTimezoneOffset(
			MapUtil.getString(context, "timezoneOffset"));
		userSession.setUserAgent(MapUtil.getString(context, "userAgent"));
		userSession.setUrls(analyticsEvents.getUrls());
		userSession.setUserId(userId);

		JSONObject jsonObject = _cerebroInfoElasticsearchInvoker.add(
			getCollectionName(),
			_objectMapper.convertValue(userSession, JSONObject.class));

		return _objectMapper.convertValue(jsonObject, UserSession.class);
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
	}

	private Individual _fetchIndividual(AnalyticsEvent analyticsEvent) {
		if ((analyticsEvent.getDataSourceId() == null) ||
			(analyticsEvent.getUserId() == null)) {

			return null;
		}

		return _individualDog.fetchIndividual(
			Long.valueOf(analyticsEvent.getDataSourceId()),
			analyticsEvent.getUserId());
	}

	private List<UserSession> _getUserSessions(
		Date firstEventDate, Date lastEventDate, String userId) {

		LocalDateTime firstEventLocalDateTime = DateUtil.toLocalDateTime(
			firstEventDate, _timeZoneDog.getZoneId());
		LocalDateTime lastEventLocalDateTime = DateUtil.toLocalDateTime(
			lastEventDate, _timeZoneDog.getZoneId());

		JSONArray userSessionsJSONArray = _cerebroInfoElasticsearchInvoker.get(
			"user-sessions",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("userId", userId)
			).filter(
				BoolQueryBuilderUtil.should(
					BoolQueryBuilderUtil.should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.rangeQuery(
								"firstEventDate"
							).lte(
								DateUtil.toUTCString(
									firstEventLocalDateTime.plusMinutes(30))
							).timeZone(
								_timeZoneDog.getTimeZoneId()
							)
						).filter(
							QueryBuilders.rangeQuery(
								"lastEventDate"
							).gte(
								DateUtil.toUTCString(
									firstEventLocalDateTime.minusMinutes(30))
							).timeZone(
								_timeZoneDog.getTimeZoneId()
							)
						)
					).should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.rangeQuery(
								"firstEventDate"
							).lte(
								DateUtil.toUTCString(
									lastEventLocalDateTime.plusMinutes(30))
							).timeZone(
								_timeZoneDog.getTimeZoneId()
							)
						).filter(
							QueryBuilders.rangeQuery(
								"lastEventDate"
							).gte(
								DateUtil.toUTCString(
									lastEventLocalDateTime.minusMinutes(30))
							).timeZone(
								_timeZoneDog.getTimeZoneId()
							)
						)
					))
			));

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
		_sessionUpdateScriptSource = ScriptUtil.loadScriptSource(
			getClass(), "session_update_script.painless");
	}

	private boolean _isSessionBounced(AnalyticsEvents analyticsEvents) {
		long interactionsCount = analyticsEvents.getInteractionsCount();
		long pageViewsCount = analyticsEvents.getPageViewsCount();

		if ((interactionsCount < 1) && (pageViewsCount < 2)) {
			return true;
		}

		return false;
	}

	private UserSession _mergeUserSessions(
		AnalyticsEvents analyticsEvents, List<UserSession> userSessions) {

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_cerebroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		UserSession retaininingUserSession = userSessions.remove(0);

		Set<String> canonicalUrls = analyticsEvents.getCanonicalUrls();
		Set<String> referrers = analyticsEvents.getReferrers();
		Set<String> urls = analyticsEvents.getUrls();

		long totalInteractionsCount =
			analyticsEvents.getInteractionsCount() +
				retaininingUserSession.getInteractionsCount();

		long totalPageViewsCount =
			analyticsEvents.getPageViewsCount() +
				retaininingUserSession.getPageViewsCount();

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

			totalInteractionsCount += userSession.getInteractionsCount();
			totalPageViewsCount += userSession.getPageViewsCount();

			elasticsearchBulkRequestBuilder.delete(
				"user-sessions", userSession.getId());
		}

		long interactionsCount = totalInteractionsCount;
		long pageViewsCount = totalPageViewsCount;

		Date firstEventDate = Collections.min(firstEventDates);
		Date lastEventDate = Collections.max(lastEventDates);

		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			_sessionUpdateScriptSource,
			new HashMap<String, Object>() {
				{
					put(
						"bounced",
						(interactionsCount < 1) && (pageViewsCount < 2));
					put("canonicalUrls", new ArrayList<>(canonicalUrls));
					put("finalized", false);
					put("firstEventDate", DateUtil.toUTCString(firstEventDate));
					put("interactionsCount", interactionsCount);
					put("lastEventDate", DateUtil.toUTCString(lastEventDate));
					put("modifiedDate", DateUtil.newDateString());
					put("pageViewsCount", pageViewsCount);
					put("referrers", new ArrayList<>(referrers));
					put("urls", urls);
				}
			});

		elasticsearchBulkRequestBuilder.update(
			"user-sessions", retaininingUserSession.getId(), script);

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}

		return retaininingUserSession;
	}

	private void _processAnalyticsEvents(
		List<Message<AnalyticsEvent>> messages, String userId) {

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

			_processSessionAnalyticsEvents(sessionAnalyticsEvents, userId);
		}
	}

	private void _processSessionAnalyticsEvents(
		List<Message<AnalyticsEvent>> messages, String userId) {

		Stream<Message<AnalyticsEvent>> stream = messages.stream();

		AnalyticsEvents analyticsEvents = new AnalyticsEvents(
			stream.map(
				Message::getObject
			).collect(
				Collectors.toList()
			));

		List<UserSession> userSessions = _getUserSessions(
			analyticsEvents.getFirstAnalyticsEventDate(),
			analyticsEvents.getLastAnalyticsEventDate(), userId);

		UserSession userSession = null;

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
				userSession = _createUserSession(analyticsEvents, true, userId);
			}
			else {
				userSession = _createUserSession(
					analyticsEvents, false, userId);
			}
		}
		else if (userSessions.size() == 1) {
			userSession = userSessions.get(0);

			_updateUserSession(analyticsEvents, userSession);
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

					_processAnalyticsEvents(messages, tuple2.getT2());

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

	private void _updateUserSession(
		AnalyticsEvents analyticsEvents, UserSession userSession) {

		long interactionsCount =
			analyticsEvents.getInteractionsCount() +
				userSession.getInteractionsCount();
		long pageViewsCount =
			analyticsEvents.getPageViewsCount() +
				userSession.getPageViewsCount();

		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			_sessionUpdateScriptSource,
			new HashMap<String, Object>() {
				{
					put(
						"bounced",
						(interactionsCount < 1) && (pageViewsCount < 2));
					put(
						"canonicalUrls",
						new ArrayList<>(analyticsEvents.getCanonicalUrls()));
					put("finalized", false);
					put(
						"firstEventDate",
						DateUtil.toUTCString(
							analyticsEvents.getFirstAnalyticsEventDate()));
					put("interactionsCount", interactionsCount);
					put(
						"lastEventDate",
						DateUtil.toUTCString(
							analyticsEvents.getLastAnalyticsEventDate()));
					put("modifiedDate", DateUtil.newDateString());
					put("pageViewsCount", pageViewsCount);
					put(
						"referrers",
						new ArrayList<>(analyticsEvents.getReferrers()));
					put("urls", new ArrayList<>(analyticsEvents.getUrls()));
				}
			});

		_cerebroInfoElasticsearchInvoker.update(
			"user-sessions", userSession.getId(), script);
	}

	private static final Log _log = LogFactory.getLog(UserSessionNanite.class);

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(30, 20);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private EventStorageDog _eventStorageDog;

	@Autowired
	private IndividualDog _individualDog;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_SESSION)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private ObjectMapper _objectMapper;

	private String _sessionUpdateScriptSource;

	@Autowired
	private TimeZoneDog _timeZoneDog;

	@Value("${osb.asah.user.session.nanite.pull.messages.size:50}")
	private int _userSessionNanitePullMessagesSize;

}