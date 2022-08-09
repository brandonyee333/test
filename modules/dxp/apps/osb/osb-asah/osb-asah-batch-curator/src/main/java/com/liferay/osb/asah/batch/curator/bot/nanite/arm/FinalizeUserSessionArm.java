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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm;

import com.liferay.osb.asah.batch.curator.bot.nanite.PastUserSessionFinalizerNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class FinalizeUserSessionArm {

	public Map<Date, List<String>> getFinalizablePastUserSessions() {
		Map<Date, Set<Pair<String, Long>>> pastUserSessionDatesWithCount =
			new TreeMap<>(Collections.reverseOrder());

		for (Project project : _projectDog.getProjects()) {
			Map<Date, Long> pastUserSessionDates = getPastUserSessionDates(
				project.getId());

			for (Map.Entry<Date, Long> entry :
					pastUserSessionDates.entrySet()) {

				Date date = entry.getKey();

				Set<Pair<String, Long>> projectIds =
					pastUserSessionDatesWithCount.computeIfAbsent(
						date,
						userSessionDate -> new TreeSet<>(
							(project1, project2) -> {
								Long userSessionCount1 = project1.getValue();
								Long userSessionCount2 = project2.getValue();

								if (userSessionCount1 > userSessionCount2) {
									return -1;
								}

								if (userSessionCount1 < userSessionCount2) {
									return 1;
								}

								String projectId1 = project1.getKey();

								return projectId1.compareTo(project2.getKey());
							}));

				projectIds.add(Pair.of(project.getId(), entry.getValue()));
			}
		}

		Map<Date, List<String>> pastUserSessionDatesToFinalize = new TreeMap<>(
			Collections.reverseOrder());

		for (Map.Entry<Date, Set<Pair<String, Long>>> entry :
				pastUserSessionDatesWithCount.entrySet()) {

			Set<Pair<String, Long>> projectIdCounts = entry.getValue();

			Stream<Pair<String, Long>> stream = projectIdCounts.stream();

			pastUserSessionDatesToFinalize.put(
				entry.getKey(),
				stream.map(
					Pair::getKey
				).collect(
					Collectors.toList()
				));
		}

		return pastUserSessionDatesToFinalize;
	}

	public Map<Date, Long> getPastUserSessionDates(String projectId) {
		try {
			ProjectIdThreadLocal.setProjectId(projectId);

			SearchResponse searchResponse =
				_cerebroInfoElasticsearchInvoker.search(
					"user-sessions",
					searchSourceBuilder -> {
						DateHistogramAggregationBuilder
							dateHistogramAggregationBuilder =
								AggregationBuilders.dateHistogram(
									"sessions_by_day");

						dateHistogramAggregationBuilder.calendarInterval(
							DateHistogramInterval.DAY);
						dateHistogramAggregationBuilder.field("lastEventDate");
						dateHistogramAggregationBuilder.minDocCount(1);
						dateHistogramAggregationBuilder.order(
							BucketOrder.key(false));
						dateHistogramAggregationBuilder.timeZone(
							_timeZoneDog.getZoneId());

						searchSourceBuilder.aggregation(
							dateHistogramAggregationBuilder);

						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								BoolQueryBuilderUtil.should(
									BoolQueryBuilderUtil.mustNot(
										QueryBuilders.existsQuery("finalized"))
								).should(
									QueryBuilders.termQuery("finalized", false)
								)
							).filter(
								QueryBuilders.rangeQuery(
									"lastEventDate"
								).lt(
									"now/d"
								)
							));
						searchSourceBuilder.size(0);
					});

			Aggregations aggregations = searchResponse.getAggregations();

			if (aggregations == null) {
				return Collections.emptyMap();
			}

			Histogram histogram = aggregations.get("sessions_by_day");

			Map<Date, Long> dateStrings = new TreeMap<>(
				Collections.reverseOrder());

			List<? extends Histogram.Bucket> buckets = histogram.getBuckets();

			for (Histogram.Bucket bucket : buckets) {
				ZonedDateTime zonedDateTime = ZonedDateTime.parse(
					bucket.getKeyAsString());

				Set<String> userSessionFinalizeDateStrings =
					PastUserSessionFinalizerNanite.
						getUserSessionFinalizeDateStrings(projectId);

				Date date = DateUtil.toUTCDate(zonedDateTime.toLocalDateTime());

				if (userSessionFinalizeDateStrings.contains(
						DateUtil.toUTCString(date))) {

					continue;
				}

				dateStrings.put(date, bucket.getDocCount());
			}

			return dateStrings;
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	public void processSession(UserSession userSession) {
		updateActivitiesAndAssets(userSession);

		long start = System.currentTimeMillis();

		JSONObject partialUserSessionJSONObject = new JSONObject();

		if (!userSession.getCompleted()) {
			partialUserSessionJSONObject.put("completed", true);
			partialUserSessionJSONObject.put(
				"completeDate", DateUtil.newDateString());
		}

		partialUserSessionJSONObject.put(
			"completeReason", _getCompleteReason(userSession));
		partialUserSessionJSONObject.put("duration", _getDuration(userSession));
		partialUserSessionJSONObject.put(
			"exitPage", _getExitPageURL(userSession));
		partialUserSessionJSONObject.put("finalized", true);
		partialUserSessionJSONObject.put(
			"modifiedDate", DateUtil.newDateString());

		_cerebroInfoElasticsearchInvoker.update(
			"user-sessions", userSession.getId(), partialUserSessionJSONObject);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Updated session in %d ms",
					System.currentTimeMillis() - start));
		}

		start = System.currentTimeMillis();

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", DateUtil.newDateString()
			).put(
				"filter", "contains(filter, 'sessions.filter')"
			).put(
				"individualJSONObject",
				Optional.ofNullable(
					userSession.getIndividualId()
				).map(
					individualId -> JSONUtil.put(
						"id", Long.valueOf(individualId))
				).orElse(
					null
				)
			));

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Scheduled Asah task in %d ms",
					System.currentTimeMillis() - start));
		}
	}

	public void updateActivitiesAndAssets(UserSession userSession) {
		long start = System.currentTimeMillis();

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Updated activities in %d ms",
					System.currentTimeMillis() - start));
		}

		start = System.currentTimeMillis();

		_updateAssetBuckets(userSession);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Updated session asset in %d ms",
					System.currentTimeMillis() - start));
		}

		start = System.currentTimeMillis();

		JSONArray pagesJSONArray = _getPagesJSONArray(
			userSession.getDataSourceId(), userSession);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Fetched session pages in %d ms",
					System.currentTimeMillis() - start));
		}

		start = System.currentTimeMillis();

		if (pagesJSONArray.length() == 0) {
			return;
		}

		_updatePageTimeOnPage(pagesJSONArray, userSession);

		_updatePageEntrancesAndExits(pagesJSONArray);

		_updatePageBounces(pagesJSONArray, userSession);

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Computed page metrics in %d ms",
					System.currentTimeMillis() - start));
		}

		start = System.currentTimeMillis();

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_cerebroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		for (int i = 0; i < pagesJSONArray.length(); i++) {
			JSONObject pageJSONObject = pagesJSONArray.getJSONObject(i);

			elasticsearchBulkRequestBuilder.update("pages", pageJSONObject);

			if ((elasticsearchBulkRequestBuilder.getSize() % 1000) == 0) {
				elasticsearchBulkRequestBuilder.get();

				elasticsearchBulkRequestBuilder =
					_cerebroInfoElasticsearchInvoker.
						createElasticsearchBulkRequestBuilder();
			}
		}

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Updated page metrics in %d ms",
					System.currentTimeMillis() - start));
		}
	}

	private String _getCompleteReason(UserSession userSession) {
		Instant instant = Instant.now();

		Date lastEventDate = userSession.getLastEventDate();

		Instant lastEventInstant = lastEventDate.toInstant();

		long daysDelta = ChronoUnit.DAYS.between(
			lastEventInstant.truncatedTo(ChronoUnit.DAYS),
			instant.truncatedTo(ChronoUnit.DAYS));

		if (daysDelta > 0) {
			return "expired";
		}

		long minutesDelta = ChronoUnit.MINUTES.between(
			lastEventInstant, instant);

		if (minutesDelta >= _INACTIVITY_TIMEOUT_MINUTES) {
			return "inactivity";
		}

		return "logout";
	}

	private long _getDuration(UserSession userSession) {
		if (userSession.getBounced()) {
			return 0L;
		}

		Date firstEventDate = userSession.getFirstEventDate();
		Date lastEventDate = userSession.getLastEventDate();

		Instant firstEventInstant = firstEventDate.toInstant();
		Instant lastEventInstant = lastEventDate.toInstant();

		return ChronoUnit.MILLIS.between(firstEventInstant, lastEventInstant);
	}

	private JSONObject _getExitPageJSONObject(UserSession userSession) {
		JSONArray exitPageJSONArray = _cerebroInfoElasticsearchInvoker.get(
			"pages", SortBuilderUtil.fieldSort("lastEventDate", SortOrder.DESC),
			_getUserSessionAssetQueryBuilder(userSession), 1);

		if (exitPageJSONArray.length() == 0) {
			return null;
		}

		return exitPageJSONArray.getJSONObject(0);
	}

	private String _getExitPageURL(UserSession userSession) {
		if (userSession.getBounced()) {
			return userSession.getEntryPage();
		}

		JSONObject exitPageJSONObject = _getExitPageJSONObject(userSession);

		if (exitPageJSONObject == null) {
			return null;
		}

		return exitPageJSONObject.getString("url");
	}

	private Map<Pair<String, Date>, String> _getPageEventDateMap(
		JSONArray pagesJSONArray) {

		Map<Pair<String, Date>, String> pageEventDateMap = new HashMap<>();

		for (int i = 0; i < pagesJSONArray.length(); i++) {
			JSONObject pageJSONObject = pagesJSONArray.getJSONObject(i);

			pageEventDateMap.put(
				Pair.of(
					pageJSONObject.getString("url"),
					DateUtil.toUTCDate(pageJSONObject.getString("eventDate"))),
				pageJSONObject.getString("id"));
		}

		return pageEventDateMap;
	}

	private JSONArray _getPagesJSONArray(
		String dataSourceId, UserSession userSession) {

		JSONArray pagesJSONArray = new JSONArray();

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		searchSourceBuilder.size(10000);
		searchSourceBuilder.sort("lastEventDate");

		String lastEventDate = null;

		while (true) {
			BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
				_getUserSessionAssetQueryBuilder(userSession)
			).filter(
				QueryBuilders.termQuery("dataSourceId", dataSourceId)
			);

			if (lastEventDate != null) {
				boolQueryBuilder.filter(
					QueryBuilders.rangeQuery(
						"lastEventDate"
					).gt(
						lastEventDate
					));
			}

			searchSourceBuilder.query(boolQueryBuilder);

			SearchResponse searchResponse =
				_cerebroInfoElasticsearchInvoker.search(
					"pages", searchSourceBuilder);

			SearchHits searchHits = searchResponse.getHits();

			SearchHit[] searchHitsArray = searchHits.getHits();

			if (searchHitsArray.length == 0) {
				break;
			}

			for (SearchHit searchHit : searchHitsArray) {
				JSONObject pageJSONObject = new JSONObject(
					searchHit.getSourceAsString());

				pageJSONObject.put("bounce", 0);
				pageJSONObject.put("entrances", 0);
				pageJSONObject.put("exits", 0);
				pageJSONObject.put("sessionId", userSession.getId());

				pagesJSONArray.put(pageJSONObject);
			}

			JSONObject jsonObject = pagesJSONArray.getJSONObject(
				pagesJSONArray.length() - 1);

			lastEventDate = jsonObject.getString("lastEventDate");
		}

		return pagesJSONArray;
	}

	private List<Pair<String, Date>> _getURLEventDatePairs(
		JSONArray pagesJSONArray) {

		List<Pair<String, Date>> urlEventDatePairs = new ArrayList<>();

		for (int i = 0; i < pagesJSONArray.length(); i++) {
			JSONObject pageJSONObject = pagesJSONArray.getJSONObject(i);

			String url = pageJSONObject.getString("url");

			JSONArray directAccessDates = pageJSONObject.optJSONArray(
				"directAccessDates");

			if (directAccessDates != null) {
				for (int j = 0; j < directAccessDates.length(); j++) {
					Date directAccessDate = DateUtil.toUTCDate(
						directAccessDates.getString(j));

					urlEventDatePairs.add(Pair.of(url, directAccessDate));
				}
			}

			JSONArray indirectAccessDates = pageJSONObject.optJSONArray(
				"indirectAccessDates");

			if (indirectAccessDates != null) {
				for (int j = 0; j < indirectAccessDates.length(); j++) {
					Date indirectAccessDate = DateUtil.toUTCDate(
						indirectAccessDates.getString(j));

					urlEventDatePairs.add(Pair.of(url, indirectAccessDate));
				}
			}

			JSONArray interactionDates = pageJSONObject.optJSONArray(
				"interactionDates");

			if (interactionDates != null) {
				for (int j = 0; j < interactionDates.length(); j++) {
					Date interactionDate = DateUtil.toUTCDate(
						interactionDates.getString(j));

					urlEventDatePairs.add(Pair.of(url, interactionDate));
				}
			}
		}

		Stream<Pair<String, Date>> stream = urlEventDatePairs.stream();

		return stream.sorted(
			(pair1, pair2) -> {
				Date date1 = pair1.getRight();
				Date date2 = pair2.getRight();

				return date1.compareTo(date2);
			}
		).collect(
			Collectors.toList()
		);
	}

	private QueryBuilder _getUserSessionAssetQueryBuilder(
		UserSession userSession) {

		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"dataSourceId", userSession.getDataSourceId())
		).filter(
			QueryBuilders.rangeQuery(
				"lastEventDate"
			).gte(
				DateUtil.toUTCString(userSession.getFirstEventDate())
			).lte(
				DateUtil.toUTCString(userSession.getLastEventDate())
			)
		).filter(
			QueryBuilders.termQuery("userId", userSession.getUserId())
		);
	}

	private void _updateAssetBuckets(UserSession userSession) {
		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			"ctx._source.sessionId = params.sessionId",
			Collections.singletonMap("sessionId", userSession.getId()));

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				_getUserSessionAssetQueryBuilder(userSession)
			).mustNot(
				QueryBuilders.existsQuery("sessionId")
			),
			false, script, _COLLECTION_NAMES);
	}

	private void _updatePageBounces(
		JSONArray pagesJSONArray, UserSession userSession) {

		if (!userSession.getBounced()) {
			return;
		}

		JSONObject exitPageJSONObject = pagesJSONArray.getJSONObject(
			pagesJSONArray.length() - 1);

		exitPageJSONObject.put("bounce", 1);
	}

	private void _updatePageEntrancesAndExits(JSONArray pagesJSONArray) {
		JSONObject entryPageJSONObject = pagesJSONArray.getJSONObject(0);

		entryPageJSONObject.put("entrances", 1);

		JSONObject exitPageJSONObject = pagesJSONArray.getJSONObject(
			pagesJSONArray.length() - 1);

		exitPageJSONObject.put("exits", 1);
	}

	private void _updatePageTimeOnPage(
		JSONArray pagesJSONArray, UserSession userSession) {

		if (userSession.getBounced()) {
			return;
		}

		List<Pair<String, Date>> urlEventDatePairs = _getURLEventDatePairs(
			pagesJSONArray);

		if (urlEventDatePairs.size() < 2) {
			return;
		}

		Map<String, JSONObject> jsonObjectMap = JSONUtil.toJSONObjectMap(
			pagesJSONArray, "id");

		Map<Pair<String, Date>, List<Long>> viewDurations = new HashMap<>();

		Date eventDate = null;
		Date normalizedEventDate = null;
		String url = null;

		for (Pair<String, Date> urlEventDatePair : urlEventDatePairs) {
			String currentURL = urlEventDatePair.getKey();

			Calendar calendar = new GregorianCalendar(
				TimeZone.getTimeZone("UTC"));

			calendar.setTime(urlEventDatePair.getValue());

			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);

			Date currentNormalizedEventDate = calendar.getTime();

			if (url != null) {
				Date currentEventDate = urlEventDatePair.getValue();

				Pair<String, Date> valuesKey = Pair.of(
					url, normalizedEventDate);

				List<Long> values = viewDurations.getOrDefault(
					valuesKey, new ArrayList<>());

				values.add(
					ChronoUnit.MILLIS.between(
						eventDate.toInstant(), currentEventDate.toInstant()));

				viewDurations.put(valuesKey, values);
			}

			url = currentURL;
			eventDate = urlEventDatePair.getValue();
			normalizedEventDate = currentNormalizedEventDate;
		}

		if (!viewDurations.containsKey(Pair.of(url, normalizedEventDate))) {
			viewDurations.put(
				Pair.of(url, normalizedEventDate), Collections.emptyList());
		}

		Map<Pair<String, Date>, String> pageEventDateMap = _getPageEventDateMap(
			pagesJSONArray);

		for (Map.Entry<Pair<String, Date>, List<Long>> entry :
				viewDurations.entrySet()) {

			Pair<String, Date> pageEventDatePair = entry.getKey();

			if (pageEventDateMap.containsKey(pageEventDatePair)) {
				JSONObject pageJSONObject = jsonObjectMap.get(
					pageEventDateMap.get(pageEventDatePair));

				if (pageJSONObject == null) {
					continue;
				}

				List<Long> values = entry.getValue();

				Stream<Long> valuesStream = values.stream();

				pageJSONObject.put(
					"timeOnPage",
					valuesStream.mapToLong(
						value -> value
					).sum());
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to find page bucket with url " +
						pageEventDatePair.getKey() + " and event date " +
							pageEventDatePair.getValue() + " and session ID " +
								userSession.getId());
			}
		}
	}

	private static final String[] _COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "document-libraries", "forms", "journal-clicks",
		"journals", "page-referrers"
	};

	private static final int _INACTIVITY_TIMEOUT_MINUTES = 30;

	private static final Log _log = LogFactory.getLog(
		FinalizeUserSessionArm.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}