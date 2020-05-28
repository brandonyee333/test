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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.nested.ReverseNested;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

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

		List<Composition> compositions = new ArrayList<>();

		Map<String, Set<String>> keywords = _getKeywords(
			channelId, dataSourceId, timeRange);

		for (Map.Entry<String, Set<String>> keyword : keywords.entrySet()) {
			Set<String> sessionIds = keyword.getValue();

			compositions.add(
				new Composition(sessionIds.size(), keyword.getKey()));
		}

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

	private void _addTimeRangeFilter(
		BoolQueryBuilder boolQueryBuilder, TimeRange timeRange) {

		boolQueryBuilder.filter(
			QueryBuilders.nestedQuery(
				"interactions",
				_searchQueryHelper.createRangeQueryBuilder(
					"interactions.eventDate", timeRange),
				ScoreMode.None));
	}

	private Map<String, Set<String>> _getKeywords(
		String channelId, String dataSourceId, TimeRange timeRange) {

		Map<String, Set<String>> keywords = new HashMap<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"assets",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"canonicalUrls"
					).field(
						"canonicalUrl"
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						AggregationBuilders.terms(
							"keywords/keyword"
						).field(
							"keywords.keyword"
						).order(
							BucketOrder.compound(
								BucketOrder.count(false), BucketOrder.key(true))
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
			return keywords;
		}

		Terms terms = aggregations.get("canonicalUrls");

		Map<String, Set<String>> sessionIds = _getSessionIds(
			channelId, dataSourceId, timeRange);

		for (Map.Entry<String, Set<String>> entry : sessionIds.entrySet()) {
			String canonicalUrl = entry.getKey();

			Terms.Bucket bucket = terms.getBucketByKey(canonicalUrl);

			if (bucket == null) {
				continue;
			}

			Aggregations bucketAggregations = bucket.getAggregations();

			Terms bucketTerms = bucketAggregations.get("keywords/keyword");

			for (Terms.Bucket termsBucket : bucketTerms.getBuckets()) {
				String keyword = termsBucket.getKeyAsString();

				Set<String> curSessionIds = null;

				if (keywords.containsKey(keyword)) {
					curSessionIds = keywords.get(keyword);
				}
				else {
					curSessionIds = new HashSet<>();
				}

				curSessionIds.addAll(sessionIds.get(canonicalUrl));

				keywords.put(keyword, curSessionIds);
			}
		}

		return keywords;
	}

	private Map<String, Set<String>> _getSessionIds(
		String channelId, String dataSourceId, TimeRange timeRange) {

		Map<String, Set<String>> sessionIds = new HashMap<>();

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelId", channelId);
		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "dataSourceId", dataSourceId);

		_addTimeRangeFilter(boolQueryBuilder, timeRange);

		SearchResponse searchResponse = _cerebroInfoElasticsearchInvoker.search(
			"user-sessions",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.nested(
						"interactions", "interactions"
					).subAggregation(
						AggregationBuilders.terms(
							"canonicalUrls"
						).field(
							"interactions.context.canonicalUrl"
						).size(
							Integer.MAX_VALUE
						).subAggregation(
							AggregationBuilders.reverseNested(
								"reverseNested"
							).subAggregation(
								AggregationBuilders.terms(
									"sessionIds"
								).field(
									"id"
								).size(
									Integer.MAX_VALUE
								)
							)
						)
					));
				searchSourceBuilder.query(boolQueryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return sessionIds;
		}

		InternalNested internalNested = aggregations.get("interactions");

		Aggregations nestedAggregations = internalNested.getAggregations();

		Terms terms = nestedAggregations.get("canonicalUrls");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			Aggregations bucketAggregations = bucket.getAggregations();

			ReverseNested reverseNested = bucketAggregations.get(
				"reverseNested");

			Aggregations reverseNestedAggregations =
				reverseNested.getAggregations();

			Terms sessionIdsTerms = reverseNestedAggregations.get("sessionIds");

			Set<String> curSessionIds = new HashSet<>();

			for (Terms.Bucket termBucket : sessionIdsTerms.getBuckets()) {
				curSessionIds.add(termBucket.getKeyAsString());
			}

			sessionIds.put(bucket.getKeyAsString(), curSessionIds);
		}

		return sessionIds;
	}

	private long _getTotalSessions(
		String channelId, String dataSourceId, TimeRange timeRange) {

		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "channelId", channelId);
		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "dataSourceId", dataSourceId);

		_addTimeRangeFilter(boolQueryBuilder, timeRange);

		return _cerebroInfoElasticsearchInvoker.count(
			"user-sessions", boolQueryBuilder);
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}