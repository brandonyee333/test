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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;

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
			_getTotalUsers(channelId, dataSourceId, timeRange));
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

		TermsValuesSourceBuilder keywordTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("keywords/keyword");

		keywordTermsValuesSourceBuilder.field("keywords.keyword");

		TermsValuesSourceBuilder assetIdTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("assetIds");

		assetIdTermsValuesSourceBuilder.field("id");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				new ArrayList<CompositeValuesSourceBuilder<?>>() {
					{
						add(keywordTermsValuesSourceBuilder);
						add(assetIdTermsValuesSourceBuilder);
					}
				}
			).size(
				10000
			);

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

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

		Map<String, Set<String>> keywords = new HashMap<>();

		while (true) {
			SearchResponse searchResponse =
				_faroInfoElasticsearchInvoker.search(
					"assets", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			if (DogUtil.isEmpty(aggregations)) {
				return keywordCompositions;
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

				String keyword = (String)keys.get("keywords/keyword");

				Set<String> assetIds = keywords.getOrDefault(
					keyword, new HashSet<>());

				assetIds.add((String)keys.get("assetIds"));

				keywords.put(keyword, assetIds);
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		Map<String, Long> userCounts = _getUserCounts(
			channelId, dataSourceId, timeRange);

		for (Map.Entry<String, Set<String>> entry : keywords.entrySet()) {
			Set<String> assetIds = entry.getValue();

			long keywordUserCounts = 0;

			for (String assetId : assetIds) {
				if (userCounts.containsKey(assetId)) {
					keywordUserCounts += userCounts.get(assetId);
				}
			}

			String keyword = entry.getKey();

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

	private long _getTotalUsers(
		String channelId, String dataSourceId, TimeRange timeRange) {

		BoolQueryBuilder boolQueryBuilder = _getActivitiesBoolQueryBuilder(
			channelId, dataSourceId, timeRange);

		TermsValuesSourceBuilder termsValuesSourceBuilder =
			new TermsValuesSourceBuilder("day");

		termsValuesSourceBuilder.field("day");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite", Collections.singletonList(termsValuesSourceBuilder)
			).size(
				10000
			).subAggregation(
				AggregationBuilders.cardinality(
					"userCount"
				).field(
					"userId"
				)
			);

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(compositeAggregationBuilder);
		searchSourceBuilder.query(boolQueryBuilder);
		searchSourceBuilder.size(0);

		long totalUsers = 0;

		while (true) {
			SearchResponse searchResponse =
				_faroInfoElasticsearchInvoker.search(
					"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			if (DogUtil.isEmpty(aggregations)) {
				return 0;
			}

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Aggregations userCountAggregations = bucket.getAggregations();

				InternalCardinality internalCardinality =
					userCountAggregations.get("userCount");

				totalUsers += internalCardinality.getValue();
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return totalUsers;
	}

	private Map<String, Long> _getUserCounts(
		String channelId, String dataSourceId, TimeRange timeRange) {

		Map<String, Long> userCounts = new HashMap<>();

		TermsValuesSourceBuilder assetIdsTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("assetIds");

		assetIdsTermsValuesSourceBuilder.field("object.id");

		TermsValuesSourceBuilder dayTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("days");

		dayTermsValuesSourceBuilder.field("day");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				new ArrayList<CompositeValuesSourceBuilder<?>>() {
					{
						add(assetIdsTermsValuesSourceBuilder);
						add(dayTermsValuesSourceBuilder);
					}
				}
			).size(
				10000
			).subAggregation(
				AggregationBuilders.cardinality(
					"userCount"
				).field(
					"userId"
				)
			);

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(compositeAggregationBuilder);
		searchSourceBuilder.query(
			_getActivitiesBoolQueryBuilder(channelId, dataSourceId, timeRange));
		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse =
				_faroInfoElasticsearchInvoker.search(
					"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			if (DogUtil.isEmpty(aggregations)) {
				return userCounts;
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

				String assetId = (String)keys.get("assetIds");

				long userCount = userCounts.getOrDefault(assetId, 0L);

				Aggregations userCountAggregations = bucket.getAggregations();

				InternalCardinality internalCardinality =
					userCountAggregations.get("userCount");

				userCount += internalCardinality.getValue();

				userCounts.put(assetId, userCount);
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
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