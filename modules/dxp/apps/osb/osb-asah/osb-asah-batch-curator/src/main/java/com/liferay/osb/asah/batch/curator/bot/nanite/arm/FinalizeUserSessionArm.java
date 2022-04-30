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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.time.Instant;
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
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Sum;
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

	public void processSession(UserSession userSession) {
		if (userSession.getCompleted()) {
			_clearPageSessionAttributes(userSession);
		}

		updateActivitiesAndAssets(userSession);

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
	}

	public void updateActivitiesAndAssets(UserSession userSession) {
		_faroInfoActivityDog.updateSessionId(userSession);

		_updateAssetBuckets(userSession);

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_cerebroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		_updatePageTimeOnPage(elasticsearchBulkRequestBuilder, userSession);

		_updatePageEntrancesAndExits(
			elasticsearchBulkRequestBuilder, userSession);

		_updatePageBounces(elasticsearchBulkRequestBuilder, userSession);

		_updatePageViews(elasticsearchBulkRequestBuilder, userSession);

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}
	}

	private void _clearPageSessionAttributes(UserSession userSession) {
		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("sessionId", userSession.getId())
		).filter(
			BoolQueryBuilderUtil.should(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery("bounce", 0))
			).should(
				QueryBuilders.termQuery("entrances", 1)
			).should(
				QueryBuilders.termQuery("exits", 1)
			).should(
				QueryBuilders.termQuery("views", 1)
			)
		);

		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			"ctx._source.bounce = 0; ctx._source.entrances = 0; " +
				"ctx._source.exits = 0; ctx._source.views = " +
					"ctx._source.directAccessDates.size() + " +
						"ctx._source.indirectAccessDates.size();",
			Collections.emptyMap());

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			boolQueryBuilder, true, script, "pages");
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
			QueryBuilders.termQuery("sessionId", userSession.getId()), 1);

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

	private void _updateAssetBuckets(UserSession userSession) {
		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			"ctx._source.sessionId = params.sessionId",
			Collections.singletonMap("sessionId", userSession.getId()));

		_cerebroInfoElasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				BoolQueryBuilderUtil.filter(
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
				)
			).mustNot(
				QueryBuilders.existsQuery("sessionId")
			),
			true, script, _COLLECTION_NAMES);
	}

	private void _updatePageBounces(
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		UserSession userSession) {

		if (!userSession.getBounced()) {
			return;
		}

		JSONObject exitPageJSONObject = _getExitPageJSONObject(userSession);

		if (exitPageJSONObject != null) {
			elasticsearchBulkRequestBuilder.update(
				"pages",
				JSONUtil.put(
					"bounce", 1
				).put(
					"id", exitPageJSONObject.getString("id")
				));
		}
	}

	private void _updatePageEntrancesAndExits(
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		UserSession userSession) {

		JSONArray entryPageJSONArray = _cerebroInfoElasticsearchInvoker.get(
			"pages", SortBuilderUtil.fieldSort("firstEventDate", SortOrder.ASC),
			QueryBuilders.termQuery("sessionId", userSession.getId()), 1);

		if (entryPageJSONArray.length() > 0) {
			JSONObject entryPageJSONObject = entryPageJSONArray.getJSONObject(
				0);

			elasticsearchBulkRequestBuilder.update(
				"pages",
				JSONUtil.put(
					"entrances", 1
				).put(
					"id", entryPageJSONObject.getString("id")
				));
		}

		JSONObject exitPageJSONObject = _getExitPageJSONObject(userSession);

		if (exitPageJSONObject != null) {
			elasticsearchBulkRequestBuilder.update(
				"pages",
				JSONUtil.put(
					"exits", 1
				).put(
					"id", exitPageJSONObject.getString("id")
				));
		}
	}

	private void _updatePageTimeOnPage(
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		UserSession userSession) {

		if (userSession.getBounced()) {
			return;
		}

		JSONArray pagesJSONArray = _cerebroInfoElasticsearchInvoker.get(
			"pages",
			new String[] {
				"directAccessDates", "eventDate", "id", "indirectAccessDates",
				"interactionDates", "url"
			},
			QueryBuilders.termQuery("sessionId", userSession.getId()));

		if (pagesJSONArray.length() == 0) {
			return;
		}

		List<Pair<String, Date>> urlEventDatePairs = _getURLEventDatePairs(
			pagesJSONArray);

		if (urlEventDatePairs.size() < 2) {
			return;
		}

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
				List<Long> values = entry.getValue();

				Stream<Long> valuesStream = values.stream();

				elasticsearchBulkRequestBuilder.update(
					"pages",
					JSONUtil.put(
						"id", pageEventDateMap.get(pageEventDatePair)
					).put(
						"timeOnPage",
						valuesStream.mapToLong(
							value -> value
						).sum()
					));
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

	private void _updatePageViews(
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		UserSession userSession) {

		QueryBuilder queryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery(
				"dataSourceId", userSession.getDataSourceId())
		).filter(
			QueryBuilders.termQuery("sessionId", userSession.getId())
		);

		SearchResponse searchResponse = _cerebroInfoElasticsearchInvoker.search(
			"pages",
			searchSourceBuilder -> {
				TermsAggregationBuilder termsAggregationBuilder =
					AggregationBuilders.terms("pages");

				termsAggregationBuilder.script(
					new Script(
						Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
						"doc['title'] + ' ' + doc['url']",
						Collections.emptyMap()));
				termsAggregationBuilder.subAggregation(
					AggregationBuilders.sum(
						"pageViews"
					).field(
						"views"
					));

				searchSourceBuilder.aggregation(termsAggregationBuilder);

				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to find pages buckets relevant to user session " +
						userSession.getId());
			}

			return;
		}

		Terms terms = aggregations.get("pages");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			Aggregations termsAggregations = bucket.getAggregations();

			Sum sumAggregation = termsAggregations.get("pageViews");

			if (sumAggregation.getValue() > 0) {
				continue;
			}

			Matcher matcher = _pattern.matcher(bucket.getKeyAsString());

			if (!matcher.matches()) {
				continue;
			}

			JSONArray pagesJSONArray = _cerebroInfoElasticsearchInvoker.get(
				"pages", SortBuilderUtil.fieldSort("eventDate", SortOrder.ASC),
				BoolQueryBuilderUtil.filter(
					queryBuilder
				).filter(
					QueryBuilders.termQuery("title", matcher.group("title"))
				).filter(
					QueryBuilders.termQuery("url", matcher.group("url"))
				),
				1);

			if (pagesJSONArray.length() == 0) {
				continue;
			}

			JSONObject pageJSONObject = pagesJSONArray.getJSONObject(0);

			elasticsearchBulkRequestBuilder.update(
				"pages",
				JSONUtil.put(
					"id", pageJSONObject.getString("id")
				).put(
					"views", 1
				));
		}
	}

	private static final String[] _COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "document-libraries", "forms", "journal-clicks",
		"journals", "pages", "page-referrers"
	};

	private static final int _INACTIVITY_TIMEOUT_MINUTES = 30;

	private static final Log _log = LogFactory.getLog(
		FinalizeUserSessionArm.class);

	private static final Pattern _pattern = Pattern.compile(
		"\\[(?<title>[^]]+)] \\[(?<url>[^]]+)]");

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

}