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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.Composition;
import com.liferay.osb.asah.backend.model.CompositionResultBag;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SiteInterestCompositionDog {

	public CompositionResultBag getCompositionResultBag(
		String channelId, String dataSourceId, int size, int start,
		TimeRange timeRange) {

		List<Composition> compositions = _getKeywordCompositions(
			channelId, dataSourceId, timeRange);

		Comparator<Composition> comparator = Comparator.comparing(
			Composition::getCount, Comparator.reverseOrder());

		comparator.thenComparing(Composition::getName);

		compositions.sort(comparator);

		int end = start + size;

		if (compositions.size() < end) {
			end = compositions.size();
		}

		return new CompositionResultBag(
			compositions.subList(start, end), compositions.size(),
			_getTotalSessions(channelId, dataSourceId, timeRange));
	}

	private BoolQueryBuilder _getActivitiesBoolQueryBuilder(
		String channelId, String dataSourceId, TimeRange timeRange) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			_searchQueryHelper.createRangeQueryBuilder("endTime", timeRange)
		).filter(
			QueryBuilders.termQuery("applicationId", "Page")
		).filter(
			QueryBuilders.termQuery("eventId", "pageViewed")
		);

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelId", channelId);
		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "dataSourceId", dataSourceId);

		return boolQueryBuilder;
	}

	private List<Composition> _getKeywordCompositions(
		String channelId, String dataSourceId, TimeRange timeRange) {

		List<Composition> keywordCompositions = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"assets",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"keywords/keyword"
					).field(
						"keywords.keyword"
					).order(
						BucketOrder.compound(
							BucketOrder.count(false), BucketOrder.key(true))
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						AggregationBuilders.terms(
							"assetId"
						).field(
							"id"
						).size(
							Integer.MAX_VALUE
						)
					));

				BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("assetType", "Page")
				).filter(
					QueryBuilders.existsQuery("keywords.keyword")
				);

				BoolQueryBuilderUtil.filterTerm(
					boolQueryBuilder, "channelIds", channelId);
				BoolQueryBuilderUtil.filterTerm(
					boolQueryBuilder, "dataSourceId", dataSourceId);

				searchSourceBuilder.query(boolQueryBuilder);

				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return keywordCompositions;
		}

		Map<String, Long> userCounts = _getUserCounts(
			channelId, dataSourceId, timeRange);

		Terms terms = aggregations.get("keywords/keyword");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			String keyword = bucket.getKeyAsString();

			Aggregations assetIdAggregations = bucket.getAggregations();

			Terms assetIdTerms = assetIdAggregations.get("assetId");

			long keywordUserCounts = 0;

			for (Terms.Bucket assetIdBucket : assetIdTerms.getBuckets()) {
				String assetId = assetIdBucket.getKeyAsString();

				if (userCounts.containsKey(assetId)) {
					keywordUserCounts += userCounts.get(assetId);
				}
			}

			if (keywordUserCounts == 0) {
				if (_log.isWarnEnabled()) {
					_log.warn("No users found for keyword " + keyword);
				}

				continue;
			}

			keywordCompositions.add(
				new Composition(keywordUserCounts, keyword));
		}

		return keywordCompositions;
	}

	private long _getTotalSessions(
		String channelId, String dataSourceId, TimeRange timeRange) {

		BoolQueryBuilder boolQueryBuilder = _getActivitiesBoolQueryBuilder(
			channelId, dataSourceId, timeRange);

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"activities",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.cardinality(
						"sessionIds"
					).field(
						"sessionId"
					));
				searchSourceBuilder.query(boolQueryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return 0;
		}

		InternalCardinality internalCardinality = aggregations.get(
			"sessionIds");

		return internalCardinality.getValue();
	}

	private Map<String, Long> _getUserCounts(
		String channelId, String dataSourceId, TimeRange timeRange) {

		Map<String, Long> userCounts = new HashMap<>();

		BoolQueryBuilder boolQueryBuilder = _getActivitiesBoolQueryBuilder(
			channelId, dataSourceId, timeRange);

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"activities",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"assetId"
					).field(
						"object.id"
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						AggregationBuilders.dateHistogram(
							"endTimes"
						).dateHistogramInterval(
							DateHistogramInterval.DAY
						).field(
							"endTime"
						).minDocCount(
							1
						).subAggregation(
							AggregationBuilders.cardinality(
								"userCount"
							).field(
								"userId"
							)
						)
					));

				searchSourceBuilder.query(boolQueryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return userCounts;
		}

		Terms terms = aggregations.get("assetId");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			String assetId = bucket.getKeyAsString();

			Aggregations userIdAggregations = bucket.getAggregations();

			Histogram histogram = userIdAggregations.get("endTimes");

			long userCount = 0;

			for (Histogram.Bucket histogramBucket : histogram.getBuckets()) {
				Aggregations userCountAggregations =
					histogramBucket.getAggregations();

				InternalCardinality internalCardinality =
					userCountAggregations.get("userCount");

				userCount += internalCardinality.getValue();
			}

			userCounts.put(assetId, userCount);
		}

		return userCounts;
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private static final Log _log = LogFactory.getLog(
		SiteInterestCompositionDog.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}