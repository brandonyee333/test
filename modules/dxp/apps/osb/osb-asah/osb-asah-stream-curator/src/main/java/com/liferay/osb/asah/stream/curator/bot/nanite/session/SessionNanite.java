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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageSubscriber;
import com.liferay.osb.asah.common.model.Acquisition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.model.AnalyticsEvents;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.MapUtil;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.session.arm.FinalizeSessionArm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class SessionNanite implements Nanite {

	@Override
	public String getCollectionName() {
		return "user-sessions";
	}

	@Override
	public long getInterval() {
		return DateUtil.MINUTE;
	}

	@PostConstruct
	public void init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_sessionUpdateScriptSource = ScriptUtil.loadScriptSource(
			getClass(), "session_update_script.painless");
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private void _createUserSession(
		AnalyticsEvents analyticsEvents, boolean completed, String userId) {

		UserSession userSession = new UserSession();

		AnalyticsEvent firstAnalyticsEvent =
			analyticsEvents.getFirstAnalyticsEvent();

		Map<String, Object> context = firstAnalyticsEvent.getContext();

		String url = MapUtil.getString(context, "url");

		userSession.setAcquisition(
			new Acquisition(MapUtil.getString(context, "referrer"), url));

		userSession.setBounced(_isSessionBounced(analyticsEvents));
		userSession.setBrowserName(MapUtil.getString(context, "browserName"));
		userSession.setCanonicalUrls(analyticsEvents.getCanonicalUrls());
		userSession.setChannelId(firstAnalyticsEvent.getChannelId());
		userSession.setCity(MapUtil.getString(context, "city"));
		userSession.setCompleted(completed);

		if (completed) {
			userSession.setCompleteDate(new Date());
		}

		userSession.setCountry(MapUtil.getString(context, "country"));
		userSession.setDataSourceId(firstAnalyticsEvent.getDataSourceId());
		userSession.setDate(firstAnalyticsEvent.getNormalizedEventDate());
		userSession.setDeviceType(MapUtil.getString(context, "deviceType"));
		userSession.setDuration(0L);
		userSession.setEntryPage(url);
		userSession.setFirstEventDate(firstAnalyticsEvent.getEventDate());

		JSONObject individualJSONObject = _fetchIndividualJSONObject(
			firstAnalyticsEvent);

		if (individualJSONObject != null) {
			userSession.setIndividualId(individualJSONObject.getString("id"));
		}

		userSession.setInteractionsCount(
			analyticsEvents.getInteractionsCount());
		userSession.setInteractions(analyticsEvents.getAnalyticsEventsList());
		userSession.setLastEventDate(
			analyticsEvents.getLastAnalyticsEventDate());
		userSession.setPageViewsCount(analyticsEvents.getPageViewsCount());
		userSession.setPlatformName(MapUtil.getString(context, "platformName"));
		userSession.setReferrers(analyticsEvents.getReferrers());
		userSession.setRegion(MapUtil.getString(context, "region"));
		userSession.setUrls(analyticsEvents.getUrls());
		userSession.setUserId(userId);

		JSONObject jsonObject = _cerebroInfoElasticsearchInvoker.add(
			getCollectionName(),
			_objectMapper.convertValue(userSession, JSONObject.class));

		if (completed) {
			try {
				_finalizeSessionArm.processSession(
					_objectMapper.readValue(
						jsonObject.toString(), UserSession.class));
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	private JSONObject _fetchIndividualJSONObject(
		AnalyticsEvent analyticsEvent) {

		if ((analyticsEvent.getDataSourceId() == null) ||
			(analyticsEvent.getUserId() == null)) {

			return null;
		}

		try {
			return _faroInfoElasticsearchInvoker.fetch(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"dataSourceIndividualPKs.dataSourceId",
							analyticsEvent.getDataSourceId())
					).filter(
						QueryBuilders.termsQuery(
							"dataSourceIndividualPKs.individualPKs",
							analyticsEvent.getUserId())
					),
					ScoreMode.None));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private JSONObject _getUserSession(String userId, Date firstEventDate) {
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));

		calendar.setTime(firstEventDate);

		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

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
							DateUtil.toUTCString(firstEventDate)
						)
					).filter(
						QueryBuilders.rangeQuery(
							"lastEventDate"
						).gte(
							firstEventDate.getTime() - (DateUtil.MINUTE * 30)
						).lt(
							System.currentTimeMillis() - (DateUtil.MINUTE * 30)
						)
					).filter(
						QueryBuilders.rangeQuery(
							"lastEventDate"
						).gte(
							calendar.getTimeInMillis()
						)
					)
				).should(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("completed", false)
					).filter(
						QueryBuilders.rangeQuery(
							"firstEventDate"
						).lte(
							DateUtil.toUTCString(firstEventDate)
						)
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
					)
				)
			),
			"interactions", null);
	}

	private boolean _isSessionBounced(AnalyticsEvents analyticsEvents) {
		long interactionsCount = analyticsEvents.getInteractionsCount();
		long pageViewsCount = analyticsEvents.getPageViewsCount();

		if ((interactionsCount < 1) && (pageViewsCount < 2)) {
			return true;
		}

		return false;
	}

	private void _processAnalyticsEvents(List<AnalyticsEvent> analyticsEvents) {
		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		Map<String, List<AnalyticsEvent>> groupedAnalyticEvents = stream.sorted(
			Comparator.comparing(AnalyticsEvent::getEventDate)
		).collect(
			Collectors.groupingBy(AnalyticsEvent::getUserId)
		);

		List<Pair<String, List<AnalyticsEvent>>> sessionAnalyticsEventsPairs =
			new ArrayList<>();

		for (Map.Entry<String, List<AnalyticsEvent>> entry :
				groupedAnalyticEvents.entrySet()) {

			String userId = entry.getKey();

			List<AnalyticsEvent> currentAnalyticsEvents = new ArrayList<>();

			AnalyticsEvent previousAnalyticsEvent = null;

			for (AnalyticsEvent analyticsEvent : entry.getValue()) {
				if (previousAnalyticsEvent != null) {
					Date previousEventDate =
						previousAnalyticsEvent.getEventDate();

					Instant previousEventInstant =
						previousEventDate.toInstant();

					Date eventDate = analyticsEvent.getEventDate();

					Instant eventInstant = eventDate.toInstant();

					long daysDelta = ChronoUnit.DAYS.between(
						previousEventInstant.truncatedTo(ChronoUnit.DAYS),
						eventInstant.truncatedTo(ChronoUnit.DAYS));

					long minutesDelta = ChronoUnit.MINUTES.between(
						previousEventInstant, eventInstant);

					if ((daysDelta > 0) || (minutesDelta >= 30)) {
						sessionAnalyticsEventsPairs.add(
							Pair.of(userId, currentAnalyticsEvents));

						currentAnalyticsEvents = new ArrayList<>();
					}
				}

				currentAnalyticsEvents.add(analyticsEvent);

				previousAnalyticsEvent = analyticsEvent;
			}

			if (!currentAnalyticsEvents.isEmpty()) {
				sessionAnalyticsEventsPairs.add(
					Pair.of(userId, currentAnalyticsEvents));
			}
		}

		for (Pair<String, List<AnalyticsEvent>> sessionAnalyticsEventsPair :
				sessionAnalyticsEventsPairs) {

			_processAnalyticsEvents(
				sessionAnalyticsEventsPair.getKey(),
				sessionAnalyticsEventsPair.getValue());
		}
	}

	private void _processAnalyticsEvents(
		String userId, List<AnalyticsEvent> sessionAnalyticsEvents) {

		sessionAnalyticsEvents.sort(
			Comparator.comparing(AnalyticsEvent::getEventDate));

		AnalyticsEvents analyticsEvents = new AnalyticsEvents(
			sessionAnalyticsEvents);

		JSONObject userSessionJSONObject = _getUserSession(
			userId, analyticsEvents.getFirstAnalyticsEventDate());

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
				catch (Exception e) {
					_log.error(e, e);
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

	private void _run() throws Exception {
		while (true) {
			long start = System.currentTimeMillis();

			List<AnalyticsEvent> analyticsEvents =
				_messageSubscriber.pullMessages(
					50, AnalyticsEvent::toAnalyticsEvent);

			if (analyticsEvents.isEmpty()) {
				break;
			}

			_processAnalyticsEvents(analyticsEvents);

			if (_log.isInfoEnabled()) {
				Class<?> clazz = getClass();

				_log.info(
					String.format(
						"%s processed %d events in %d ms",
						clazz.getSimpleName(), analyticsEvents.size(),
						System.currentTimeMillis() - start));
			}
		}
	}

	private void _updateUserSession(
		AnalyticsEvents analyticsEvents, JSONObject userSessionJSONObject) {

		List<AnalyticsEvent> analyticsEventsList =
			analyticsEvents.getAnalyticsEventsList();

		Stream<AnalyticsEvent> stream = analyticsEventsList.stream();

		List<Map> analyticsEventMaps = stream.map(
			analyticsEvent -> _objectMapper.convertValue(
				analyticsEvent, Map.class)
		).collect(
			Collectors.toList()
		);

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
					put("interactions", analyticsEventMaps);
					put("interactionsCount", interactionsCount);
					put(
						"lastEventDate",
						DateUtil.toUTCString(
							analyticsEvents.getLastAnalyticsEventDate()));
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
				"id", userSessionJSONObject.getString("id")),
			"interactions", null);

		if (userSessionJSONObject.optBoolean("completed", false)) {
			try {
				_finalizeSessionArm.reprocessSession(
					_objectMapper.readValue(
						userSessionJSONObject.toString(), UserSession.class));
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(SessionNanite.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FinalizeSessionArm _finalizeSessionArm;

	@MessageSubscriber.Autowired(channel = Channel.ANALYTICS_EVENTS_SESSION)
	private MessageSubscriber _messageSubscriber;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

	private String _sessionUpdateScriptSource;

}