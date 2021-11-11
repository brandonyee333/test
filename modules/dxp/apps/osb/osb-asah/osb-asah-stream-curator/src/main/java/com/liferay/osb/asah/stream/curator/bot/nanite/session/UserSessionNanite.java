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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.EventStorageDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.lock.KeyReentrantLock;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.Acquisition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.AnalyticsEvents;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.Assert;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

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

		try {
			_semaphore.acquireUninterruptibly(
				_userSessionNaniteConcurrentTasksLimit);
		}
		finally {
			_semaphore.release(_userSessionNaniteConcurrentTasksLimit);
		}
	}

	private void _createUserSession(
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

		_storeEvents(
			analyticsEvents.getAnalyticsEventsList(),
			jsonObject.getString("id"));
	}

	@PreDestroy
	private void _destroy() {
		_reentrantLock.lock();

		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				_executorService.shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
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

	private JSONObject _getUserSession(Date firstEventDate, String userId) {
		LocalDateTime firstEventLocalDateTime = DateUtil.toLocalDateTime(
			firstEventDate, _timeZoneDog.getZoneId());

		LocalDateTime nowLocalDateTime = LocalDateTime.now(
			_timeZoneDog.getZoneId());

		return _cerebroInfoElasticsearchInvoker.fetch(
			"user-sessions",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("userId", userId)
			).filter(
				BoolQueryBuilderUtil.should(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("completed", true)
					).filter(
						QueryBuilders.rangeQuery(
							"firstEventDate"
						).lte(
							firstEventLocalDateTime.toString()
						).timeZone(
							_timeZoneDog.getTimeZoneId()
						)
					).filter(
						QueryBuilders.rangeQuery(
							"lastEventDate"
						).gte(
							DateUtil.minusMinutes(firstEventLocalDateTime, 30)
						).lt(
							DateUtil.minusMinutes(nowLocalDateTime, 30)
						).timeZone(
							_timeZoneDog.getTimeZoneId()
						)
					).filter(
						QueryBuilders.rangeQuery(
							"lastEventDate"
						).gte(
							DateUtil.newDayLocalDateTimeString(
								firstEventLocalDateTime)
						).timeZone(
							_timeZoneDog.getTimeZoneId()
						)
					)
				).should(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("completed", false)
					).filter(
						QueryBuilders.rangeQuery(
							"firstEventDate"
						).lte(
							firstEventLocalDateTime.toString()
						).timeZone(
							_timeZoneDog.getTimeZoneId()
						)
					).mustNot(
						BoolQueryBuilderUtil.should(
							QueryBuilders.rangeQuery(
								"lastEventDate"
							).lt(
								DateUtil.minusMinutes(nowLocalDateTime, 30)
							).timeZone(
								_timeZoneDog.getTimeZoneId()
							)
						).should(
							QueryBuilders.rangeQuery(
								"lastEventDate"
							).lt(
								DateUtil.newDayLocalDateTimeString(
									nowLocalDateTime)
							).timeZone(
								_timeZoneDog.getTimeZoneId()
							)
						)
					)
				)
			));
	}

	@PostConstruct
	private void _init() {
		_semaphore = new Semaphore(
			_userSessionNaniteConcurrentTasksLimit, true);
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

	private void _processAnalyticsEvents(
		List<AnalyticsEvent> analyticsEvents, String userId) {

		List<List<AnalyticsEvent>> sessionsAnalyticsEvents = new ArrayList<>();

		List<AnalyticsEvent> currentAnalyticsEvents = new ArrayList<>();

		AnalyticsEvent previousAnalyticsEvent = null;

		for (AnalyticsEvent analyticsEvent : analyticsEvents) {
			if (previousAnalyticsEvent != null) {
				Date previousEventDate = previousAnalyticsEvent.getEventDate();

				Instant previousEventInstant = previousEventDate.toInstant();

				Date eventDate = analyticsEvent.getEventDate();

				Instant eventInstant = eventDate.toInstant();

				long daysDelta = ChronoUnit.DAYS.between(
					previousEventInstant.truncatedTo(ChronoUnit.DAYS),
					eventInstant.truncatedTo(ChronoUnit.DAYS));

				long minutesDelta = ChronoUnit.MINUTES.between(
					previousEventInstant, eventInstant);

				if ((daysDelta > 0) || (minutesDelta >= 30)) {
					sessionsAnalyticsEvents.add(currentAnalyticsEvents);

					currentAnalyticsEvents = new ArrayList<>();
				}
			}

			currentAnalyticsEvents.add(analyticsEvent);

			previousAnalyticsEvent = analyticsEvent;
		}

		if (!currentAnalyticsEvents.isEmpty()) {
			sessionsAnalyticsEvents.add(currentAnalyticsEvents);
		}

		for (List<AnalyticsEvent> sessionAnalyticsEvents :
				sessionsAnalyticsEvents) {

			_processSessionAnalyticsEvents(sessionAnalyticsEvents, userId);
		}
	}

	private void _processSessionAnalyticsEvents(
		List<AnalyticsEvent> sessionAnalyticsEvents, String userId) {

		ReentrantLock reentrantLock = KeyReentrantLock.getReentrantLock(
			getClass(), ProjectIdThreadLocal.getProjectId(), userId);

		try {
			reentrantLock.lock();

			sessionAnalyticsEvents.sort(
				Comparator.comparing(AnalyticsEvent::getEventDate));

			AnalyticsEvents analyticsEvents = new AnalyticsEvents(
				sessionAnalyticsEvents);

			JSONObject userSessionJSONObject = _getUserSession(
				analyticsEvents.getFirstAnalyticsEventDate(), userId);

			if (userSessionJSONObject == null) {
				BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("completed", false)
				).mustNot(
					BoolQueryBuilderUtil.should(
						QueryBuilders.rangeQuery(
							"lastEventDate"
						).lt(
							"now-30m"
						)
					).should(
						QueryBuilders.rangeQuery(
							"lastEventDate"
						).lt(
							"now/d"
						)
					)
				).filter(
					QueryBuilders.termQuery("userId", userId)
				);

				if (!_cerebroInfoElasticsearchInvoker.exists(
						"user-sessions", boolQueryBuilder)) {

					try {
						Date lastAnalyticsEventDate =
							analyticsEvents.getLastAnalyticsEventDate();

						Date yesterday = DateUtil.toUTCDate(
							DateUtil.addDays(DateUtil.newDateString(), -1));

						if (lastAnalyticsEventDate.before(yesterday)) {
							_createUserSession(analyticsEvents, true, userId);
						}
						else {
							_createUserSession(analyticsEvents, false, userId);
						}
					}
					catch (Exception exception) {
						_log.error(exception, exception);
					}
				}
				else {
					_createUserSession(analyticsEvents, true, userId);
				}
			}
			else {
				_updateUserSession(analyticsEvents, userSessionJSONObject);
			}
		}
		finally {
			reentrantLock.unlock();
		}
	}

	private void _run() throws Exception {
		while (true) {
			try {
				_reentrantLock.lock();

				long start = System.currentTimeMillis();

				List<AnalyticsEvent> analyticsEvents =
					_messageSubscriber.pullMessages(
						_userSessionNanitePullMessagesSize,
						AnalyticsEvent::toAnalyticsEvent);

				if (analyticsEvents.isEmpty()) {
					break;
				}

				Stream<AnalyticsEvent> stream = analyticsEvents.stream();

				stream.sorted(
					Comparator.comparing(AnalyticsEvent::getEventDate)
				).collect(
					Collectors.groupingBy(
						analyticsEvent -> Tuples.of(
							analyticsEvent.getProjectId(),
							analyticsEvent.getUserId()))
				).forEach(
					this::_run
				);

				if (_log.isInfoEnabled()) {
					Class<?> clazz = getClass();

					_log.info(
						String.format(
							"%s dispatched %d events in %d ms",
							clazz.getSimpleName(), analyticsEvents.size(),
							System.currentTimeMillis() - start));
				}
			}
			finally {
				_reentrantLock.unlock();
			}
		}
	}

	private void _run(
		Tuple2<String, String> tuple2, List<AnalyticsEvent> analyticsEvents) {

		_semaphore.acquireUninterruptibly();

		CompletableFuture.runAsync(
			() -> {
				long start = System.currentTimeMillis();

				try {
					ProjectIdThreadLocal.setProjectId(tuple2.getT1());

					_processAnalyticsEvents(analyticsEvents, tuple2.getT2());
				}
				catch (Exception exception) {
					List<String> analyticsEventsString = ListUtil.map(
						analyticsEvents, AnalyticsEvent::toJSON);

					_log.error(
						"Unable to process analytics events messages " +
							analyticsEventsString,
						exception);
				}
				finally {
					_semaphore.release();
				}

				if (_log.isInfoEnabled()) {
					Class<?> clazz = getClass();

					_log.info(
						String.format(
							"%s processed %d events in %d ms",
							clazz.getSimpleName(), analyticsEvents.size(),
							System.currentTimeMillis() - start));
				}
			},
			_executorService);
	}

	private void _storeEvents(
		List<AnalyticsEvent> analyticsEvents, String sessionId) {

		try {
			Assert.notBlank(sessionId, "Session ID is blank");

			for (AnalyticsEvent analyticsEvent : analyticsEvents) {
				try {
					_eventStorageDog.store(analyticsEvent, sessionId);
				}
				catch (Exception exception) {
					_log.error(
						"Unable to store event " + analyticsEvent.toJSON(),
						exception);
				}
			}
		}
		catch (Exception exception) {
			List<String> analyticsEventsString = ListUtil.map(
				analyticsEvents, AnalyticsEvent::toJSON);

			_log.error(
				"Unable to store analytics events " + analyticsEventsString,
				exception);
		}
	}

	private void _updateUserSession(
		AnalyticsEvents analyticsEvents, JSONObject userSessionJSONObject) {

		long interactionsCount =
			analyticsEvents.getInteractionsCount() +
				userSessionJSONObject.optLong("interactionsCount");
		long pageViewsCount =
			analyticsEvents.getPageViewsCount() +
				userSessionJSONObject.optLong("pageViewsCount");

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
			"user-sessions", userSessionJSONObject.getString("id"), script);

		userSessionJSONObject = _cerebroInfoElasticsearchInvoker.fetch(
			"user-sessions",
			QueryBuilders.termQuery(
				"id", userSessionJSONObject.getString("id")));

		_storeEvents(
			analyticsEvents.getAnalyticsEventsList(),
			userSessionJSONObject.getString("id"));
	}

	private static final Log _log = LogFactory.getLog(UserSessionNanite.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private EventStorageDog _eventStorageDog;

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(20);

	@Autowired
	private IndividualDog _individualDog;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_SESSION)
	private MessageSubscriber _messageSubscriber;

	@Autowired
	private ObjectMapper _objectMapper;

	private final ReentrantLock _reentrantLock = new ReentrantLock(true);
	private Semaphore _semaphore;
	private String _sessionUpdateScriptSource;

	@Autowired
	private TimeZoneDog _timeZoneDog;

	@Value("${osb.asah.user.session.nanite.concurrent.tasks.limit:30}")
	private int _userSessionNaniteConcurrentTasksLimit;

	@Value("${osb.asah.user.session.nanite.pull.messages.size:50}")
	private int _userSessionNanitePullMessagesSize;

}