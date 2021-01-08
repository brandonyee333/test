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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1;

import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.tophits.InternalTopHits;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 * @author Victor Oliveira
 */
@RequestMapping(produces = "application/json", value = "/api/1.0/interests")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.InterestsRestController"
)
public class InterestsRestController extends BaseRestController {

	@GetMapping("/terms/{userId}")
	public String getTerms(
			@RequestParam(defaultValue = "3") int termsPerTopic,
			@RequestParam(defaultValue = "0.01") double termWeightThreshold,
			@RequestParam(defaultValue = "3") int topicsLength,
			@PathVariable String userId)
		throws Exception {

		if ((termsPerTopic * topicsLength) > 1000) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"termsPerTopic * topicsLength must not exceed 1000");
		}

		JSONObject individual = _fetchIndividual(userId);

		if (individual == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no individual with user ID " + userId);
		}

		List<String> userInterestTerms = _getUserInterestTerms(
			termsPerTopic, topicsLength, individual.getString("id"));

		List<Integer> topics = _getTopics(
			userInterestTerms, termWeightThreshold);

		if (topics.isEmpty()) {
			return toCollectionGetResponse("interest-topics", new JSONArray());
		}

		TopHitsAggregationBuilder termsTopHitsAggregationBuilder =
			_buildTermsTopHitsAggregationBuilder(
				0, new String[] {"term", "termWeight", "topic", "topicWeight"},
				termsPerTopic);

		TermsAggregationBuilder topicTermsAggregationBuilder =
			_buildTopicTermsAggregationBuilder(
				termsTopHitsAggregationBuilder, topics.size());

		SearchResponse topTermsSearchResponse = _searchTopTerms(
			topicTermsAggregationBuilder,
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("termType", "keyword")
			).filter(
				QueryBuilders.termsQuery("topic", topics)
			));

		if (topTermsSearchResponse.status() != RestStatus.OK) {
			_logSearchResponseErrors(topTermsSearchResponse);

			throw new OSBAsahException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		List<Topic> topTermsTopics = _getTopTermsTopics(
			topTermsSearchResponse.getAggregations(), topicsLength);

		return toCollectionGetResponse(
			"interest-topics",
			JSONUtil.toJSONArray(
				topTermsTopics,
				topic -> JSONUtil.put(
					"id", topic._id
				).put(
					"terms",
					JSONUtil.toJSONArray(
						topic._topicTerms,
						topicTerm -> JSONUtil.put(
							"keyword", topicTerm._keyword
						).put(
							"weight", topicTerm._weight
						))
				).put(
					"weight", topic._weight
				)));
	}

	@GetMapping("/terms/related")
	public String getTermsRelated(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam List<String> terms,
			@RequestParam(defaultValue = "0.01") double termWeightThreshold)
		throws Exception {

		if ((page * size + size) > 100) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"page * size + size must not exceed 100");
		}

		List<Integer> topics = _getTopics(terms, termWeightThreshold);

		if (topics.isEmpty()) {
			return toCollectionGetResponse("interest-terms", new JSONArray());
		}

		TopHitsAggregationBuilder termsTopHitsAggregationBuilder =
			_buildTermsTopHitsAggregationBuilder(
				_calculateTermsTopHitsAggregationBuilderFrom(
					page, size, topics.size()),
				new String[] {"term", "topicWeight"},
				_calculateTermsTopHitsAggregationBuilderSize(
					size, topics.size()));

		TermsAggregationBuilder topicTermsAggregationBuilder =
			_buildTopicTermsAggregationBuilder(
				termsTopHitsAggregationBuilder, topics.size());

		QueryBuilder queryBuilder = BoolQueryBuilderUtil.mustNot(
			QueryBuilders.termsQuery("term", terms)
		).filter(
			QueryBuilders.termQuery("termType", "keyword")
		).filter(
			QueryBuilders.termsQuery("topic", topics)
		);

		SearchResponse topTermsSearchResponse = _searchTopTerms(
			topicTermsAggregationBuilder, queryBuilder);

		if (topTermsSearchResponse.status() != RestStatus.OK) {
			_logSearchResponseErrors(topTermsSearchResponse);

			throw new OSBAsahException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		List<String> topTerms = _getTopTerms(
			topTermsSearchResponse.getAggregations());

		size = Math.min(size, topTerms.size());

		return toCollectionGetResponse(
			"interest-topics", new JSONArray(topTerms.subList(0, size)),
			"interest-terms", page, queryBuilder, size);
	}

	protected String toCollectionGetResponse(
		String embeddedJSONArrayKey, JSONArray embeddedJSONArray) {

		return JSONUtil.put(
			"_embedded", JSONUtil.put(embeddedJSONArrayKey, embeddedJSONArray)
		).toString();
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

	private void _addTermPerTopic(
		InternalTopHits aggregation, Map<Integer, Topic> topics) {

		for (SearchHit hit : aggregation.getHits()) {
			Map<String, Object> sourceAsMap = hit.getSourceAsMap();

			Integer topicId = (Integer)sourceAsMap.get("topic");

			Topic topic = topics.getOrDefault(
				topicId,
				new Topic(topicId, (Double)sourceAsMap.get("topicWeight")));

			topic.addTopicTerm(
				new TopicTerm(
					(String)sourceAsMap.get("term"),
					(Double)sourceAsMap.get("termWeight")));

			topics.put(topicId, topic);
		}
	}

	private TopHitsAggregationBuilder _buildTermsTopHitsAggregationBuilder(
		int from, String[] includes, int size) {

		TopHitsAggregationBuilder topHitsAggregationBuilder =
			AggregationBuilders.topHits("top_terms");

		topHitsAggregationBuilder.fetchSource(includes, null);
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

	private int _calculateTermsTopHitsAggregationBuilderFrom(
		int page, int size, int topicsCount) {

		if (page > 0) {
			return (int)Math.ceil((double)(page * size) / topicsCount);
		}

		return 0;
	}

	private int _calculateTermsTopHitsAggregationBuilderSize(
		int size, int topicsCount) {

		int defaultSize = 2;

		if ((topicsCount * defaultSize) < size) {
			return (int)Math.ceil((double)size / topicsCount);
		}

		return defaultSize;
	}

	private JSONObject _fetchIndividual(String userId) {
		return faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.matchQuery(
						"dataSourceIndividualPKs.individualPKs", userId)),
				ScoreMode.None));
	}

	private List<Pair<Double, String>> _getTermPairs(
		List<? extends Terms.Bucket> topicBuckets) {

		List<Pair<Double, String>> termPairs = new ArrayList<>();

		for (Terms.Bucket topicBucket : topicBuckets) {
			for (Aggregation aggregation : topicBucket.getAggregations()) {
				_addTermPairs((InternalTopHits)aggregation, termPairs);
			}
		}

		return termPairs;
	}

	private List<Integer> _getTopics(
		List<String> terms, double termWeightThreshold) {

		if ((terms == null) || terms.isEmpty()) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Unable to retrieve the topics because the search terms " +
						"was empty");
			}

			return Collections.emptyList();
		}

		SearchResponse interestTopicsSearchResponse = _searchInterestTopics(
			terms, termWeightThreshold);

		if (interestTopicsSearchResponse.status() != RestStatus.OK) {
			_logSearchResponseErrors(interestTopicsSearchResponse);

			return Collections.emptyList();
		}

		Aggregations aggregations =
			interestTopicsSearchResponse.getAggregations();

		Terms topicsTerms = aggregations.get("topics");

		List<Integer> topics = new ArrayList<>();

		for (Terms.Bucket bucket : topicsTerms.getBuckets()) {
			Number relatedTopicNumber = bucket.getKeyAsNumber();

			topics.add(relatedTopicNumber.intValue());
		}

		if (topics.isEmpty() && _log.isInfoEnabled()) {
			_log.info("There are no topics related with " + terms.toString());
		}

		return topics;
	}

	private List<String> _getTopTerms(Aggregations termsAggregation) {
		Terms topicTerms = termsAggregation.get("topics");

		List<Pair<Double, String>> termPairs = _getTermPairs(
			topicTerms.getBuckets());

		Stream<Pair<Double, String>> stream = termPairs.stream();

		return stream.sorted(
			(pair1, pair2) -> Double.compare(pair2.getLeft(), pair1.getLeft())
		).map(
			Pair::getRight
		).collect(
			Collectors.toList()
		);
	}

	private List<Topic> _getTopTermsTopics(
		Aggregations termsAggregation, int topicsLength) {

		Terms terms = termsAggregation.get("topics");

		Map<Integer, Topic> topics = new HashMap<>();

		for (Terms.Bucket topicBucket : terms.getBuckets()) {
			for (Aggregation aggregation : topicBucket.getAggregations()) {
				_addTermPerTopic((InternalTopHits)aggregation, topics);
			}
		}

		Set<Map.Entry<Integer, Topic>> entries = topics.entrySet();

		Stream<Map.Entry<Integer, Topic>> stream = entries.stream();

		return stream.sorted(
			(entry1, entry2) -> {
				Topic topic1 = entry1.getValue();
				Topic topic2 = entry2.getValue();

				return Double.compare(topic2._weight, topic1._weight);
			}
		).limit(
			topicsLength
		).map(
			Map.Entry::getValue
		).collect(
			Collectors.toList()
		);
	}

	private List<String> _getUserInterestTerms(
		int termsPerTopic, int topicsLength, String individualId) {

		SearchResponse userInterestSearchResponse =
			faroInfoElasticsearchInvoker.search(
				"interests",
				searchSourceBuilder -> {
					BoolQueryBuilder query = BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("ownerId", individualId)
					).filter(
						QueryBuilders.termQuery("ownerType", "individual")
					);

					searchSourceBuilder.query(query);

					searchSourceBuilder.size(topicsLength * termsPerTopic * 10);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort("score", SortOrder.DESC));
				});

		if (userInterestSearchResponse.status() != RestStatus.OK) {
			_logSearchResponseErrors(userInterestSearchResponse);

			return Collections.emptyList();
		}

		SearchHits searchHits = userInterestSearchResponse.getHits();

		Stream<SearchHit> stream = Arrays.stream(searchHits.getHits());

		return stream.map(
			SearchHit::getSourceAsMap
		).map(
			searchHitMap -> (String)searchHitMap.get("name")
		).collect(
			Collectors.toList()
		);
	}

	private void _logSearchResponseErrors(SearchResponse searchResponse) {
		for (ShardSearchFailure shardSearchFailure :
				searchResponse.getShardFailures()) {

			_log.error(shardSearchFailure.getCause());
		}
	}

	private SearchResponse _searchInterestTopics(
		List<String> terms, double termWeightThreshold) {

		return faroInfoElasticsearchInvoker.search(
			"interest-topics",
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
					QueryBuilders.termQuery("termType", "keyword")
				);

				if (termWeightThreshold > 0) {
					queryBuilder.filter(
						QueryBuilders.rangeQuery(
							"termWeight"
						).gte(
							termWeightThreshold
						));
				}

				searchSourceBuilder.query(queryBuilder);

				searchSourceBuilder.size(0);
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("topicWeight", SortOrder.DESC));
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("termWeight", SortOrder.DESC));
			});
	}

	private SearchResponse _searchTopTerms(
		AggregationBuilder aggregationBuilder, QueryBuilder queryBuilder) {

		return faroInfoElasticsearchInvoker.search(
			"interest-topics",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(aggregationBuilder);
				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("topicWeight", SortOrder.DESC));
				searchSourceBuilder.size(0);
			});
	}

	private static final Log _log = LogFactory.getLog(
		InterestsRestController.class);

	private static class Topic {

		protected void addTopicTerm(TopicTerm topicTerm) {
			_topicTerms.add(topicTerm);
		}

		private Topic(int id, double weight) {
			_id = id;
			_weight = weight;
		}

		private final int _id;
		private List<TopicTerm> _topicTerms = new ArrayList<>();
		private double _weight;

	}

	private static class TopicTerm {

		private TopicTerm(String keyword, double weight) {
			_keyword = keyword;
			_weight = weight;
		}

		private final String _keyword;
		private double _weight;

	}

}