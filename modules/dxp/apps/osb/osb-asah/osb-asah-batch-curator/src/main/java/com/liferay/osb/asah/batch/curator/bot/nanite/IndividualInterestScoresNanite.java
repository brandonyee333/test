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

import com.liferay.osb.asah.batch.curator.bot.nanite.arm.InterestScoreArm;
import com.liferay.osb.asah.batch.curator.bot.nanite.arm.URLArm;
import com.liferay.osb.asah.batch.curator.bot.nanite.model.KeywordInfo;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoInterestDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualInterestScoresNanite extends BaseScoresNanite {

	public static final double MINIMUM_INTEREST_SCORE_THRESHOLD = 0.01;

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(String dayDateString) throws Exception {
		_deleteInterestScores(dayDateString);

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			faroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		long totalViews = _urlArm.getTotalPageViews(dayDateString);

		Map<String, List<KeywordInfo>> keywordInfosMap = _getKeywordInfosMap();

		Map<String, Long> totalKeywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap, _getURLsPageViewsMap(dayDateString, null, null));

		Set<String> individualsIds = _getIndividualsIds(dayDateString);

		for (String individualId : individualsIds) {
			if (isInterrupted()) {
				return;
			}

			_process(
				dayDateString, elasticsearchBulkRequestBuilder, individualId,
				keywordInfosMap, totalKeywordsPageViewsMap, totalViews);
		}

		JSONArrayIterator.of(
			"interests", faroInfoElasticsearchInvoker,
			interestJSONObject -> {
				try {
					if (individualsIds.contains(
							interestJSONObject.getString("ownerId"))) {

						return elasticsearchBulkRequestBuilder;
					}

					double score =
						InterestScoreArm.DECAY *
							interestJSONObject.getDouble("score");

					if (score >= MINIMUM_INTEREST_SCORE_THRESHOLD) {
						interestJSONObject.remove("id");

						elasticsearchBulkRequestBuilder.add(
							"interests",
							interestJSONObject.put(
								"dateRecorded", dayDateString
							).put(
								"score", score
							));
					}
				}
				catch (Exception e) {
					return e;
				}

				return elasticsearchBulkRequestBuilder;
			}
		).setBatchSize(
			10000
		).setQueryBuilder(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dateRecorded", DateUtil.addDays(dayDateString, -1))
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			)
		).iterate();

		faroInfoElasticsearchInvoker.refresh();

		_faroInfoInterestDog.clearCache();
	}

	@Override
	protected void afterOSBAsahMarkerUpdated(
		String lastSuccessfulDayDateString) {

		_faroInfoOSBAsahTaskDog.addOSBAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"addFilter", "contains(filter, 'interests.filter(')"
			).put(
				"dateModified", lastSuccessfulDayDateString
			).put(
				"removeFilter", "contains(filter, 'interests.filter(')"
			));
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected boolean isInterrupted() {
		return _interrupted;
	}

	@Override
	protected boolean isRunning() {
		return _running;
	}

	@Override
	protected void process(
		Map<String, Object> context, String dayDateString,
		JSONObject individualJSONObject) {
	}

	@Override
	protected void setInterrupted(boolean interrupted) {
		_interrupted = interrupted;
	}

	@Override
	protected void setRunning(boolean running) {
		_running = running;
	}

	private void _addVisitedPages(
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
		String dayDateString, String individualId, String keyword,
		List<KeywordInfo> keywordInfos, Map<String, Long> urlsPageViewsMap) {

		for (KeywordInfo keywordInfo : keywordInfos) {
			Long pageViews = urlsPageViewsMap.get(
				keywordInfo.getDataSourceAssetPK());

			if (pageViews == null) {
				continue;
			}

			elasticsearchBulkRequestBuilder.add(
				"visited-pages",
				JSONUtil.put(
					"canonicalUrl", keywordInfo.getCanonicalUrl()
				).put(
					"day", dayDateString
				).put(
					"interestName", keyword
				).put(
					"ownerId", individualId
				).put(
					"ownerType", "individual"
				).put(
					"title", keywordInfo.getName()
				).put(
					"uniqueVisitsCount", pageViews
				).put(
					"url", keywordInfo.getDataSourceAssetPK()
				));
		}
	}

	private void _deleteInterestScores(String dayDateString) throws Exception {
		faroInfoElasticsearchInvoker.deleteByQuery(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).lte(
					DateUtil.addDays(dayDateString, -2)
				)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			),
			false, "interests");
		faroInfoElasticsearchInvoker.deleteByQuery(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"day"
				).lt(
					DateUtil.addDays(dayDateString, -30)
				)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			),
			false, "visited-pages");
	}

	private long _getIndividualPageViews(
		String dayDateString, String individualId) {

		return faroInfoElasticsearchInvoker.count(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("day", dayDateString)
			).filter(
				QueryBuilders.termQuery("eventId", "pageViewed")
			).filter(
				QueryBuilders.termQuery("ownerId", individualId)
			));
	}

	private Set<String> _getIndividualsIds(String dayDateString) {
		Set<String> individualIds = new HashSet<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder termsValuesSourceBuilder =
			new TermsValuesSourceBuilder("ownerIds");

		termsValuesSourceBuilder.field("ownerId");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite", Collections.singletonList(termsValuesSourceBuilder)
			).size(
				10000
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("day", dayDateString)
			).filter(
				QueryBuilders.termQuery("eventId", "pageViewed")
			));

		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
				"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				individualIds.add((String)keys.get("ownerIds"));
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return individualIds;
	}

	private Map<String, List<KeywordInfo>> _getKeywordInfosMap() {
		Map<String, List<KeywordInfo>> keywordInfosMap = new HashMap<>();

		JSONArray assetsJSONArray = faroInfoElasticsearchInvoker.get(
			"assets",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("assetType", "Page")
			).filter(
				QueryBuilders.existsQuery("keywords.keyword")
			));

		for (int i = 0; i < assetsJSONArray.length(); i++) {
			JSONObject assetJSONObject = assetsJSONArray.getJSONObject(i);

			String dataSourceAssetPK = assetJSONObject.getString(
				"dataSourceAssetPK");

			String canonicalUrl = assetJSONObject.optString(
				"canonicalUrl", dataSourceAssetPK);

			String name = assetJSONObject.getString("name");

			JSONArray keywordsJSONArray = assetJSONObject.getJSONArray(
				"keywords");

			for (int j = 0; j < keywordsJSONArray.length(); j++) {
				JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(
					j);

				String keyword = keywordJSONObject.getString("keyword");

				List<KeywordInfo> keywordInfos =
					keywordInfosMap.computeIfAbsent(
						keyword, key -> new ArrayList<>());

				keywordInfos.add(
					new KeywordInfo(canonicalUrl, dataSourceAssetPK, name));
			}
		}

		return keywordInfosMap;
	}

	private Map<String, Long> _getKeywordsPageViewsMap(
		Map<String, List<KeywordInfo>> keywordInfosMap,
		Map<String, Long> urlsPageViewsMap) {

		Map<String, Long> keywordsPageViewsMap = new HashMap<>();

		for (Map.Entry<String, List<KeywordInfo>> entry :
				keywordInfosMap.entrySet()) {

			String keyword = entry.getKey();

			for (KeywordInfo keywordInfo : entry.getValue()) {
				keywordsPageViewsMap.merge(
					keyword,
					urlsPageViewsMap.getOrDefault(
						keywordInfo.getDataSourceAssetPK(), 0L),
					Long::sum);
			}
		}

		return keywordsPageViewsMap;
	}

	private Set<Map.Entry<String, Long>> _getURLsPageViewsEntrySet(
		String dayDateString, String individualId,
		Map<String, List<KeywordInfo>> keywordInfosMap) {

		Map<String, Long> keywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap,
			_getURLsPageViewsMap(dayDateString, individualId, null));

		return keywordsPageViewsMap.entrySet();
	}

	private Map<String, Long> _getURLsPageViewsMap(
		String endDayDateString, String individualId,
		String startDayDateString) {

		Map<String, Long> urlsPageViewsMap = new HashMap<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder termsValuesSourceBuilder =
			new TermsValuesSourceBuilder("urls");

		termsValuesSourceBuilder.field("object.dataSourceAssetPK");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				Collections.singletonList(termsValuesSourceBuilder));

		compositeAggregationBuilder.size(10000);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "Page")
		).filter(
			QueryBuilders.termQuery("eventId", "pageViewed")
		);

		if (startDayDateString != null) {
			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"day"
				).gte(
					startDayDateString
				).lte(
					endDayDateString
				));
		}
		else {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("day", endDayDateString));
		}

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "ownerId", individualId);

		searchSourceBuilder.query(boolQueryBuilder);

		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
				"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				urlsPageViewsMap.put(
					(String)keys.get("urls"), bucket.getDocCount());
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return urlsPageViewsMap;
	}

	private void _process(
			String dayDateString,
			ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
			String individualId, Map<String, List<KeywordInfo>> keywordInfosMap,
			Map<String, Long> totalKeywordsPageViewsMap, long totalViews)
		throws Exception {

		long individualPageViews = _getIndividualPageViews(
			dayDateString, individualId);

		Map<String, Long> urlsPageViewsMap = _getURLsPageViewsMap(
			dayDateString, individualId, DateUtil.addDays(dayDateString, -30));

		Map<String, Long> keywordsPageViewsMap = _getKeywordsPageViewsMap(
			keywordInfosMap, urlsPageViewsMap);

		for (Map.Entry<String, Long> entry :
				_getURLsPageViewsEntrySet(
					dayDateString, individualId, keywordInfosMap)) {

			String keyword = entry.getKey();
			long pageViews = entry.getValue();

			double score = 0;

			JSONObject interestJSONObject = faroInfoElasticsearchInvoker.fetch(
				"interests",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dateRecorded", DateUtil.addDays(dayDateString, -1))
				).filter(
					QueryBuilders.termQuery("name", keyword)
				).filter(
					QueryBuilders.termQuery("ownerId", individualId)
				).filter(
					QueryBuilders.termQuery("ownerType", "individual")
				));

			if (interestJSONObject != null) {
				score =
					InterestScoreArm.DECAY *
						interestJSONObject.getDouble("score");
			}

			score = _interestScoreArm.computeScore(
				pageViews, individualPageViews, score,
				totalKeywordsPageViewsMap.get(keyword), totalViews);

			if (score < MINIMUM_INTEREST_SCORE_THRESHOLD) {
				continue;
			}

			elasticsearchBulkRequestBuilder.add(
				"interests",
				JSONUtil.put(
					"dateRecorded", dayDateString
				).put(
					"name", keyword
				).put(
					"ownerId", individualId
				).put(
					"ownerType", "individual"
				).put(
					"score", score
				).put(
					"views", keywordsPageViewsMap.get(keyword)
				));

			_addVisitedPages(
				elasticsearchBulkRequestBuilder, dayDateString, individualId,
				keyword, keywordInfosMap.get(keyword), urlsPageViewsMap);
		}

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}
	}

	private static final Log _log = LogFactory.getLog(
		IndividualInterestScoresNanite.class);

	@Autowired
	private FaroInfoInterestDog _faroInfoInterestDog;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	@Autowired
	private InterestScoreArm _interestScoreArm;

	private boolean _interrupted;
	private boolean _running;

	@Autowired
	private URLArm _urlArm;

}