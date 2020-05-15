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
import com.liferay.osb.asah.common.model.Acquisition;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_sessionUpdateScriptSource = ScriptUtil.loadScriptSource(
			getClass(), "session-update-script.painless");
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
		List<AnalyticsEvent> analyticsEvents, boolean completed,
		String userId) {

		AnalyticsEvent firstAnalyticsEvent = analyticsEvents.get(0);
		AnalyticsEvent lastAnalyticsEvent = analyticsEvents.get(
			analyticsEvents.size() - 1);

		UserSession userSession = new UserSession();

		Map<String, Object> context = firstAnalyticsEvent.getContext();

		String url = MapUtil.getString(context, "url");

		userSession.setAcquisition(
			new Acquisition(MapUtil.getString(context, "referrer"), url));

		userSession.setBounced(_isSessionBounced(analyticsEvents));
		userSession.setBrowserName(MapUtil.getString(context, "browserName"));
		userSession.setChannelId(firstAnalyticsEvent.getChannelId());
		userSession.setCity(MapUtil.getString(context, "city"));
		userSession.setCompleted(completed);

		if (completed) {
			userSession.setCompleteDate(new Date());
		}

		userSession.setCountry(MapUtil.getString(context, "country"));
		userSession.setDataSourceId(firstAnalyticsEvent.getDataSourceId());
		userSession.setDate(firstAnalyticsEvent.getNormalizedEventDate());
		userSession.setDuration(0L);
		userSession.setEntryPage(url);
		userSession.setFirstEventDate(firstAnalyticsEvent.getEventDate());

		JSONObject individualJSONObject = _fetchIndividualJSONObject(
			firstAnalyticsEvent);

		if (individualJSONObject != null) {
			userSession.setIndividualId(individualJSONObject.getString("id"));
		}

		userSession.setInteractionsCount(
			_getInteractionsCount(analyticsEvents));
		userSession.setInteractions(analyticsEvents);
		userSession.setLastEventDate(lastAnalyticsEvent.getEventDate());
		userSession.setPageViewsCount(_getPageViewsCount(analyticsEvents));
		userSession.setPlatformName(MapUtil.getString(context, "platformName"));
		userSession.setRegion(MapUtil.getString(context, "region"));
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

	private long _getInteractionsCount(List<AnalyticsEvent> analyticsEvents) {
		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		return stream.map(
			AnalyticsEvent::getEventId
		).filter(
			eventId -> !_nonInteractionEvents.contains(eventId)
		).count();
	}

	private JSONObject _getOSBAsahMarkerJSONObject() {
		Class<?> clazz = getClass();

		JSONObject osbAsahMarkerJSONObject =
			_cerebroInfoElasticsearchInvoker.fetch(
				"OSBAsahMarkers", clazz.getSimpleName());

		if (osbAsahMarkerJSONObject == null) {
			osbAsahMarkerJSONObject = new JSONObject();

			osbAsahMarkerJSONObject.put("id", clazz.getSimpleName());

			_cerebroInfoElasticsearchInvoker.add(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);
		}

		return osbAsahMarkerJSONObject;
	}

	private long _getPageViewsCount(List<AnalyticsEvent> analyticsEvents) {
		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		return stream.filter(
			analyticsEvent ->
				Objects.equals(analyticsEvent.getApplicationId(), "Page") &&
				Objects.equals(analyticsEvent.getEventId(), "pageViewed")
		).count();
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

	private boolean _isSessionBounced(List<AnalyticsEvent> analyticsEvents) {
		long interactionsCount = _getInteractionsCount(analyticsEvents);
		long pageViewsCount = _getPageViewsCount(analyticsEvents);

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
		String userId, List<AnalyticsEvent> analyticsEvents) {

		analyticsEvents.sort(
			Comparator.comparing(AnalyticsEvent::getEventDate));

		AnalyticsEvent firstAnalyticsEvent = analyticsEvents.get(0);

		JSONObject userSessionJSONObject = _getUserSession(
			userId, firstAnalyticsEvent.getEventDate());

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
					AnalyticsEvent lastAnalyticsEvent = analyticsEvents.get(
						analyticsEvents.size() - 1);

					Date lastAnalyticsEventDate =
						lastAnalyticsEvent.getEventDate();

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
		JSONObject osbAsahMarkerJSONObject = _getOSBAsahMarkerJSONObject();

		while (true) {
			long start = System.currentTimeMillis();

			String lastSuccessfulAnalyticsEventId =
				osbAsahMarkerJSONObject.optString(
					"lastSuccessfulAnalyticsEventId", "0");

			String analyticsEventsJSON = _cerebroRawElasticsearchInvoker.get(
				"analytics-events",
				searchSourceBuilder -> {
					searchSourceBuilder.searchAfter(
						new Object[] {lastSuccessfulAnalyticsEventId});
					searchSourceBuilder.size(500);
					searchSourceBuilder.sort("id");
				});

			List<AnalyticsEvent> analyticsEvents =
				AnalyticsEvent.toAnalyticsEvents(analyticsEventsJSON);

			if (analyticsEvents.isEmpty()) {
				break;
			}

			_processAnalyticsEvents(analyticsEvents);

			AnalyticsEvent lastAnalyticsEvent = analyticsEvents.get(
				analyticsEvents.size() - 1);

			osbAsahMarkerJSONObject.put(
				"lastSuccessfulAnalyticsEventId", lastAnalyticsEvent.getId());

			_cerebroInfoElasticsearchInvoker.update(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);

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
		List<AnalyticsEvent> analyticsEvents,
		JSONObject userSessionJSONObject) {

		AnalyticsEvent lastAnalyticsEvent = analyticsEvents.get(
			analyticsEvents.size() - 1);

		Stream<AnalyticsEvent> stream = analyticsEvents.stream();

		List<Map> analyticsEventMaps = stream.map(
			analyticsEvent -> _objectMapper.convertValue(
				analyticsEvent, Map.class)
		).collect(
			Collectors.toList()
		);

		long interactionsCount =
			_getInteractionsCount(analyticsEvents) +
				userSessionJSONObject.optLong("interactionsCount");
		long pageViewsCount =
			_getPageViewsCount(analyticsEvents) +
				userSessionJSONObject.optLong("pageViewsCount");

		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			_sessionUpdateScriptSource,
			new HashMap<String, Object>() {
				{
					put(
						"bounced",
						(interactionsCount < 1) && (pageViewsCount < 2));
					put("interactions", analyticsEventMaps);
					put("interactionsCount", interactionsCount);
					put(
						"lastEventDate",
						DateUtil.toUTCString(
							lastAnalyticsEvent.getEventDate()));
					put("pageViewsCount", pageViewsCount);
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

	private static final Set<String> _nonInteractionEvents =
		new HashSet<String>() {
			{
				add("blogViewed");
				add("documentPreviewed");
				add("formViewed");
				add("pageLoaded");
				add("pageUnloaded");
				add("pageViewed");
				add("webContentViewed");
			}
		};

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FinalizeSessionArm _finalizeSessionArm;

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