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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoEngagementDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.MapUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualEngagementScoresNanite
	extends BaseEngagementScoresNanite {

	public Map<String, Double> computeInteractionScores(String dayDateString)
		throws Exception {

		Map<String, Double> interactionScores = MapUtil.merge(
			Double::sum, _computeScrollScores(dayDateString),
			_computeTimingScores(dayDateString),
			_computeFormsSubmittedScores(dayDateString));

		Set<Map.Entry<String, Double>> entries = interactionScores.entrySet();

		Stream<Map.Entry<String, Double>> stream = entries.stream();

		return stream.collect(
			Collectors.toMap(
				Map.Entry::getKey,
				entry -> normalizeScore(
					entry.getValue(),
					_UNNORMALIZED_AVERAGE_SCORE_INTERACTION)));
	}

	public Map<String, Double> computeLoyaltyScores(String dayDateString)
		throws Exception {

		Map<String, Double> scores = new HashMap<>();

		Map<String, List<Pair<Date, Long>>> activityCounts =
			_getDocumentCountsByDay(dayDateString, null);

		Date dayDate = DateUtil.toUTCDate(dayDateString);

		for (Map.Entry<String, List<Pair<Date, Long>>> entry :
				activityCounts.entrySet()) {

			double score = 0;

			for (Pair<Date, Long> individualActivityCount : entry.getValue()) {
				score +=
					_DAYS -
						DateUtil.getDeltaDays(
							individualActivityCount.getKey(), dayDate);
			}

			scores.put(
				entry.getKey(),
				Math.sqrt(score) / Math.sqrt(_DAYS * (_DAYS + 1) / 2));
		}

		return scores;
	}

	public Map<String, Double> computeVarietyScores(String dayDateString)
		throws Exception {

		Map<String, Double> scores = new HashMap<>();

		Map<String, JSONArray> pageViewActivities = _getActivities(
			dayDateString,
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("eventId", "pageViewed")
			),
			SortBuilderUtil.fieldSort("day", SortOrder.DESC));

		for (Map.Entry<String, JSONArray> entry :
				pageViewActivities.entrySet()) {

			Set<String> dataSourceAssetPKs = new HashSet<>();
			double unnormalizedScore = 0;

			JSONArray activitiesJSONArray = entry.getValue();

			for (int i = 0; i < activitiesJSONArray.length(); i++) {
				JSONObject activityJSONObject =
					activitiesJSONArray.getJSONObject(i);

				JSONObject objectJSONObject = activityJSONObject.getJSONObject(
					"object");

				if (dataSourceAssetPKs.add(
						objectJSONObject.getString("dataSourceAssetPK"))) {

					unnormalizedScore +=
						_DAYS -
							DateUtil.getDeltaDays(
								activityJSONObject.getString("day"),
								dayDateString);
				}
			}

			scores.put(
				entry.getKey(),
				normalizeScore(
					unnormalizedScore, _UNNORMALIZED_AVERAGE_SCORE_VARIETY));
		}

		return scores;
	}

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void process(
		Map<String, Object> context, String dayDateString,
		JSONObject individualJSONObject) {
	}

	@Override
	protected void run(String dayDateString) throws Exception {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		Map<String, Double> engagementScores = _computeEngagementScores(
			dayDateString);

		String lastSuccessfulDayDateString = null;
		boolean updateIndividuals = true;

		JSONObject osbAsahMarkerJSONObject = getOSBAsahMarkerJSONObject();

		if (osbAsahMarkerJSONObject != null) {
			lastSuccessfulDayDateString = osbAsahMarkerJSONObject.optString(
				"lastSuccessfulDay", null);
		}

		if ((lastSuccessfulDayDateString != null) &&
			(DateUtil.getDeltaMilliseconds(
				lastSuccessfulDayDateString, dayDateString) < 0)) {

			updateIndividuals = false;
		}

		for (Map.Entry<String, Double> entry : engagementScores.entrySet()) {
			double engagementScore = entry.getValue();

			if (Double.isInfinite(engagementScore)) {
				continue;
			}

			String individualId = entry.getKey();

			JSONObject individualJSONObject =
				faroInfoElasticsearchInvoker.fetch("individuals", individualId);

			if (individualJSONObject == null) {
				continue;
			}

			elasticsearchBulkRequestBuilder.update(
				"individuals",
				JSONUtil.put(
					"engagementScore", engagementScore
				).put(
					"id", individualId
				));

			if (engagementScore <= 0) {
				continue;
			}

			JSONObject demographicsJSONObject =
				individualJSONObject.optJSONObject("demographics");

			_faroInfoEngagementDog.saveIndividualEngagement(
				dayDateString, elasticsearchBulkRequestBuilder,
				FaroInfoIndividualUtil.getIndividualEmail(
					demographicsJSONObject),
				individualJSONObject.getJSONArray("individualSegmentIds"),
				FaroInfoIndividualUtil.getIndividualName(
					demographicsJSONObject),
				individualId, engagementScore);

			if (updateIndividuals) {
				_updateIndividualEngagementScore(
					elasticsearchBulkRequestBuilder, engagementScore,
					individualJSONObject);
			}
		}

		if (updateIndividuals) {
			Set<String> individualIds = engagementScores.keySet();

			for (String previousActiveIndividualId :
					_getPreviousActiveIndividualIds(dayDateString)) {

				if (individualIds.contains(previousActiveIndividualId)) {
					continue;
				}

				_updateIndividualEngagementScore(
					elasticsearchBulkRequestBuilder, 0,
					previousActiveIndividualId);
			}
		}

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}

		_faroInfoEngagementDog.clearCache();
	}

	private Map<String, Double> _computeEngagementScores(String dayDateString)
		throws Exception {

		Map<String, Double> engagementScores = MapUtil.merge(
			Double::sum, computeInteractionScores(dayDateString),
			computeLoyaltyScores(dayDateString),
			computeVarietyScores(dayDateString));

		Set<Map.Entry<String, Double>> entries = engagementScores.entrySet();

		Stream<Map.Entry<String, Double>> stream = entries.stream();

		return stream.collect(
			Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() / 3));
	}

	private Map<String, Double> _computeFormsSubmittedScores(
			String dayDateString)
		throws Exception {

		Map<String, Double> scores = new HashMap<>();

		Map<String, List<Pair<Date, Long>>> formSubmissionCounts =
			_getDocumentCountsByDay(
				dayDateString,
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("applicationId", "Form")
				).filter(
					QueryBuilders.termQuery("eventId", "formSubmitted")
				));

		Date dayDate = DateUtil.toUTCDate(dayDateString);

		for (Map.Entry<String, List<Pair<Date, Long>>> entry :
				formSubmissionCounts.entrySet()) {

			double score = 0;

			for (Pair<Date, Long> individualFormSubmissionCount :
					entry.getValue()) {

				score +=
					individualFormSubmissionCount.getValue() *
						(_DAYS -
							DateUtil.getDeltaDays(
								individualFormSubmissionCount.getKey(),
								dayDate));
			}

			scores.put(entry.getKey(), score);
		}

		return scores;
	}

	private Map<String, Double> _computeScrollScores(String dayDateString)
		throws Exception {

		Map<String, Double> scores = new HashMap<>();

		Map<String, JSONArray> individualScrollingActivities = _getActivities(
			dayDateString,
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("eventId", "pageDepthReached")
			),
			null);

		for (Map.Entry<String, JSONArray> entry :
				individualScrollingActivities.entrySet()) {

			double score = 0;

			JSONArray scrollingActivitiesJSONArray =
				filterMaxActivitiesJSONArray(
					entry.getValue(),
					new ScrollingActivityJSONObjectComparator());

			for (int i = 0; i < scrollingActivitiesJSONArray.length(); i++) {
				JSONObject scrollingActivityJSONObject =
					scrollingActivitiesJSONArray.getJSONObject(i);

				double scrollDepthScore = computeScrollDepthScore(
					scrollingActivityJSONObject);
				double scrollTimeScore = computeScrollTimeScore(
					scrollingActivityJSONObject);

				score +=
					((scrollDepthScore + scrollTimeScore) / 3) *
						(_DAYS -
							DateUtil.getDeltaDays(
								scrollingActivityJSONObject.getString("day"),
								dayDateString));
			}

			scores.put(entry.getKey(), score);
		}

		return scores;
	}

	private Map<String, Double> _computeTimingScores(String dayDateString)
		throws Exception {

		Map<String, Double> scores = new HashMap<>();

		Map<String, JSONArray> individualTimingActivities = _getActivities(
			dayDateString,
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("eventId", "pageUnloaded")
			),
			null);

		for (Map.Entry<String, JSONArray> entry :
				individualTimingActivities.entrySet()) {

			double score = 0;

			JSONArray timingActivitiesJSONArray = filterMaxActivitiesJSONArray(
				entry.getValue(), new TimingActivityJSONObjectComparator());

			for (int i = 0; i < timingActivitiesJSONArray.length(); i++) {
				JSONObject timingActivityJSONObject =
					timingActivitiesJSONArray.getJSONObject(i);

				score +=
					(computeViewDurationScore(timingActivityJSONObject) / 3) *
						(_DAYS -
							DateUtil.getDeltaDays(
								timingActivityJSONObject.getString("day"),
								dayDateString));
			}

			scores.put(entry.getKey(), score);
		}

		return scores;
	}

	private Map<String, JSONArray> _getActivities(
			String dayDateString, BoolQueryBuilder eventQueryBuilder,
			SortBuilder sortBuilder)
		throws Exception {

		Map<String, JSONArray> activities = new HashMap<>();

		String startDayDateString = DateUtil.addDays(dayDateString, -_DAYS);

		long keepAliveSeconds = 120;

		SearchResponse searchResponse =
			faroInfoElasticsearchInvoker.searchScroll(
				"activities",
				searchSourceBuilder -> {
					BoolQueryBuilder boolQueryBuilder =
						BoolQueryBuilderUtil.filter(
							QueryBuilders.rangeQuery(
								"day"
							).gt(
								startDayDateString
							).lte(
								dayDateString
							));

					if (eventQueryBuilder != null) {
						boolQueryBuilder.filter(eventQueryBuilder);
					}

					searchSourceBuilder.query(boolQueryBuilder);

					if (sortBuilder != null) {
						searchSourceBuilder.sort(sortBuilder);
					}
				},
				keepAliveSeconds);

		String scrollId = searchResponse.getScrollId();

		SearchHits searchHits = searchResponse.getHits();

		SearchHit[] hits = searchHits.getHits();

		while ((hits != null) && (hits.length > 0)) {
			Stream<SearchHit> stream = Arrays.stream(hits);

			JSONArray jsonArray = new JSONArray(
				stream.map(
					searchHit -> new JSONObject(searchHit.getSourceAsString())
				).collect(
					Collectors.toList()
				));

			Iterator<Object> iterator = jsonArray.iterator();

			while (iterator.hasNext()) {
				JSONObject activityJSONObject = (JSONObject)iterator.next();

				String ownerId = activityJSONObject.getString("ownerId");

				JSONArray activitiesJSONArray = activities.getOrDefault(
					ownerId, new JSONArray());

				activities.put(
					ownerId, activitiesJSONArray.put(activityJSONObject));
			}

			searchResponse = faroInfoElasticsearchInvoker.searchScroll(
				scrollId, keepAliveSeconds);

			scrollId = searchResponse.getScrollId();

			searchHits = searchResponse.getHits();

			hits = searchHits.getHits();
		}

		faroInfoElasticsearchInvoker.clearScroll(scrollId);

		return activities;
	}

	private Map<String, List<Pair<Date, Long>>> _getDocumentCountsByDay(
			String dayDateString, QueryBuilder queryBuilder)
		throws Exception {

		Map<String, List<Pair<Date, Long>>> countsByDay = new HashMap<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder dayTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("days");

		dayTermsValuesSourceBuilder.field("day");

		TermsValuesSourceBuilder individualTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("individuals");

		individualTermsValuesSourceBuilder.field("ownerId");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				new ArrayList<CompositeValuesSourceBuilder<?>>() {
					{
						add(dayTermsValuesSourceBuilder);
						add(individualTermsValuesSourceBuilder);
					}
				}
			).size(
				10000
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.rangeQuery(
				"day"
			).gt(
				DateUtil.addDays(dayDateString, -_DAYS)
			).lte(
				dayDateString
			));

		if (queryBuilder != null) {
			boolQueryBuilder.filter(queryBuilder);
		}

		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
				"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			if (aggregations == null) {
				break;
			}

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				String individualId = (String)keys.get("individuals");

				List<Pair<Date, Long>> documentCounts =
					countsByDay.getOrDefault(individualId, new ArrayList<>());

				documentCounts.add(
					Pair.of(
						new Date((long)keys.get("days")),
						bucket.getDocCount()));

				countsByDay.put(individualId, documentCounts);
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return countsByDay;
	}

	private Set<String> _getPreviousActiveIndividualIds(String dayDateString)
		throws Exception {

		Set<String> individualIds = new HashSet<>();

		TermsValuesSourceBuilder individualTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("individuals");

		individualTermsValuesSourceBuilder.field("ownerId");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				Collections.singletonList(individualTermsValuesSourceBuilder)
			).size(
				10000
			);

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(compositeAggregationBuilder);
		searchSourceBuilder.query(
			QueryBuilders.termQuery(
				"dateRecorded", DateUtil.addDays(dayDateString, -(_DAYS + 1))));
		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
				"engagements", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			if (aggregations == null) {
				break;
			}

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				individualIds.add(String.valueOf(keys.get("individuals")));
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return individualIds;
	}

	private void _updateIndividualEngagementScore(
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		double engagementScore, JSONObject individualJSONObject) {

		if (individualJSONObject == null) {
			return;
		}

		elasticsearchBulkRequestBuilder.update(
			"individuals",
			JSONUtil.put(
				"engagementScore", engagementScore
			).put(
				"id", individualJSONObject.getString("id")
			));
	}

	private void _updateIndividualEngagementScore(
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		double engagementScore, String individualId) {

		_updateIndividualEngagementScore(
			elasticsearchBulkRequestBuilder, engagementScore,
			faroInfoElasticsearchInvoker.fetch("individuals", individualId));
	}

	private static final int _DAYS = 30;

	private static final double _UNNORMALIZED_AVERAGE_SCORE_INTERACTION = 180;

	private static final double _UNNORMALIZED_AVERAGE_SCORE_VARIETY = 135;

	private static final Log _log = LogFactory.getLog(
		IndividualEngagementScoresNanite.class);

	@Autowired
	private FaroInfoEngagementDog _faroInfoEngagementDog;

}