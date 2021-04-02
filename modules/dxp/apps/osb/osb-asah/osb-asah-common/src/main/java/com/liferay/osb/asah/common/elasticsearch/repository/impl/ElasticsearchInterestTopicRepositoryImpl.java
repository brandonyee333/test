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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.InterestTopic;
import com.liferay.osb.asah.common.repository.InterestTopicRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.InternalTopHits;
import org.elasticsearch.search.aggregations.metrics.TopHitsAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchInterestTopicRepositoryImpl
	extends BaseElasticsearchRepository<InterestTopic, Long>
	implements InterestTopicRepository {

	@Override
	public List<Integer>
		findTopicsByTermInAndTermTypeAndTermWeightGreaterThanEqual(
			List<String> terms, String termType, Double termWeight) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"topics"
					).field(
						"topic"
					));

				BoolQueryBuilder queryBuilder = BoolQueryBuilderUtil.filter(
					QueryBuilders.termsQuery("term", terms)
				).filter(
					QueryBuilders.termQuery("termType", termType)
				);

				if (termWeight > 0) {
					queryBuilder.filter(
						QueryBuilders.rangeQuery(
							"termWeight"
						).gte(
							termWeight
						));
				}

				searchSourceBuilder.query(queryBuilder);

				searchSourceBuilder.size(0);
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("topicWeight", SortOrder.DESC));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("termWeight", SortOrder.DESC));
			});

		if (searchResponse.status() != RestStatus.OK) {
			_logSearchResponseErrors(searchResponse);

			return Collections.emptyList();
		}

		Aggregations aggregations = searchResponse.getAggregations();

		Terms topicsTerms = aggregations.get("topics");

		List<Integer> topics = new ArrayList<>();

		for (Terms.Bucket bucket : topicsTerms.getBuckets()) {
			Number number = bucket.getKeyAsNumber();

			topics.add(number.intValue());
		}

		return topics;
	}

	@Override
	public List<InterestTopic>
		findTopInterestTopicsByTermRankLessThanEqualAndTermTypeAndTopicIn(
			Integer termRankEnd, String termType, List<Integer> topics) {

		TopHitsAggregationBuilder termsTopHitsAggregationBuilder =
			_buildTermsTopHitsAggregationBuilder(0, termRankEnd);

		TermsAggregationBuilder topicTermsAggregationBuilder =
			_buildTopicTermsAggregationBuilder(
				termsTopHitsAggregationBuilder, topics.size());

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(topicTermsAggregationBuilder);
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("termType", termType)
					).filter(
						QueryBuilders.termsQuery("topic", topics)
					));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("topicWeight", SortOrder.DESC));
				searchSourceBuilder.size(0);
			});

		if (searchResponse.status() != RestStatus.OK) {
			_logSearchResponseErrors(searchResponse);

			return Collections.emptyList();
		}

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("topics");

		List<InterestTopic> interestTopics = new ArrayList<>();

		for (Terms.Bucket topicBucket : terms.getBuckets()) {
			for (Aggregation aggregation : topicBucket.getAggregations()) {
				_addInterestTopics(
					(InternalTopHits)aggregation, interestTopics);
			}
		}

		return interestTopics;
	}

	@Override
	public List<String>
		findTopTermsByTermRankBetweenAndTermsNotInAndTermTypeAndTopicIn(
			Integer termRankEnd, Integer termRankStart, List<String> terms,
			String termType, List<Integer> topics) {

		TopHitsAggregationBuilder termsTopHitsAggregationBuilder =
			_buildTermsTopHitsAggregationBuilder(termRankStart, termRankEnd);

		TermsAggregationBuilder topicTermsAggregationBuilder =
			_buildTopicTermsAggregationBuilder(
				termsTopHitsAggregationBuilder, topics.size());

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(topicTermsAggregationBuilder);
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termsQuery("term", terms)
					).filter(
						QueryBuilders.termQuery("termType", termType)
					).filter(
						QueryBuilders.termsQuery("topic", topics)
					));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("topicWeight", SortOrder.DESC));
				searchSourceBuilder.size(0);
			});

		if (searchResponse.status() != RestStatus.OK) {
			_logSearchResponseErrors(searchResponse);

			return Collections.emptyList();
		}

		Aggregations aggregations = searchResponse.getAggregations();

		Terms topicTerms = aggregations.get("topics");

		List<Pair<Double, String>> termPairs = new ArrayList<>();

		for (Terms.Bucket topicBucket : topicTerms.getBuckets()) {
			for (Aggregation aggregation : topicBucket.getAggregations()) {
				_addTermPairs((InternalTopHits)aggregation, termPairs);
			}
		}

		Stream<Pair<Double, String>> stream = termPairs.stream();

		return stream.sorted(
			(pair1, pair2) -> Double.compare(pair2.getLeft(), pair1.getLeft())
		).map(
			Pair::getRight
		).collect(
			Collectors.toList()
		);
	}

	@Override
	protected String getCollectionName() {
		return "interest-topics";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private void _addInterestTopics(
		InternalTopHits aggregation, List<InterestTopic> interestTopics) {

		for (SearchHit searchHit : aggregation.getHits()) {
			Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();

			InterestTopic interestTopic = new InterestTopic();

			interestTopic.setId(Long.valueOf((String)sourceAsMap.get("id")));
			interestTopic.setTerm((String)sourceAsMap.get("term"));
			interestTopic.setTermType((String)sourceAsMap.get("termType"));
			interestTopic.setTermWeight((Double)sourceAsMap.get("termWeight"));
			interestTopic.setTopic((Integer)sourceAsMap.get("topic"));
			interestTopic.setTopicWeight(
				(Double)sourceAsMap.get("topicWeight"));

			interestTopics.add(interestTopic);
		}
	}

	private void _addTermPairs(
		InternalTopHits internalTopHits, List<Pair<Double, String>> termPairs) {

		for (SearchHit hit : internalTopHits.getHits()) {
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();

			termPairs.add(
				Pair.of(
					(Double)sourceAsMap.get("topicWeight"),
					(String)sourceAsMap.get("term")));
		}
	}

	private TopHitsAggregationBuilder _buildTermsTopHitsAggregationBuilder(
		int from, int size) {

		TopHitsAggregationBuilder topHitsAggregationBuilder =
			AggregationBuilders.topHits("top_terms");

		topHitsAggregationBuilder.from(from);
		topHitsAggregationBuilder.size(size);
		topHitsAggregationBuilder.sorts(
			Arrays.asList(
				SortBuilderUtil.fieldSort("topicWeight", SortOrder.DESC),
				SortBuilderUtil.fieldSort("termWeight", SortOrder.DESC)));

		return topHitsAggregationBuilder;
	}

	private TermsAggregationBuilder _buildTopicTermsAggregationBuilder(
		TopHitsAggregationBuilder termsTopHitsAggregationBuilder,
		int topicsLength) {

		TermsAggregationBuilder topicTermsAggregationBuilder =
			AggregationBuilders.terms("topics");

		topicTermsAggregationBuilder.field("topic");
		topicTermsAggregationBuilder.size(topicsLength);
		topicTermsAggregationBuilder.subAggregation(
			termsTopHitsAggregationBuilder);

		return topicTermsAggregationBuilder;
	}

	private void _logSearchResponseErrors(SearchResponse searchResponse) {
		for (ShardSearchFailure shardSearchFailure :
				searchResponse.getShardFailures()) {

			_log.error(shardSearchFailure.getCause());
		}
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchInterestTopicRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}