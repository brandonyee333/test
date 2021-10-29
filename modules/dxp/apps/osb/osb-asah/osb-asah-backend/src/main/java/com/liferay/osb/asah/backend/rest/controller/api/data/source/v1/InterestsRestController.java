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
import com.liferay.osb.asah.common.dog.InterestDog;
import com.liferay.osb.asah.common.dog.InterestTopicDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.entity.InterestTopic;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
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
			termsPerTopic, topicsLength, individual.getLong("id"));

		List<Integer> topics = _getTopics(
			userInterestTerms, termWeightThreshold);

		if (topics.isEmpty()) {
			return toCollectionGetResponse("interest-topics", new JSONArray());
		}

		List<InterestTopic> interestTopics =
			_interestTopicDog.getInterestTopics(
				termsPerTopic, "keyword", topics);

		List<Topic> topTermsTopics = _getTopTermsTopics(
			interestTopics, topicsLength);

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

		if (((page * size) + size) > 100) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"page * size + size must not exceed 100");
		}

		List<Integer> topics = _getTopics(terms, termWeightThreshold);

		if (topics.isEmpty()) {
			return toCollectionGetResponse("interest-terms", new JSONArray());
		}

		List<String> topTerms = _interestTopicDog.getInterestTopicTerms(
			page, size, terms, "keyword", topics);

		size = Math.min(size, topTerms.size());

		return toCollectionGetResponse(
			"interest-topics", new JSONArray(topTerms.subList(0, size)),
			"interest-terms", page,
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery("term", terms)
			).filter(
				QueryBuilders.termQuery("termType", "keyword")
			).filter(
				QueryBuilders.termsQuery("topic", topics)
			),
			size);
	}

	protected String toCollectionGetResponse(
		String embeddedJSONArrayKey, JSONArray embeddedJSONArray) {

		return JSONUtil.put(
			"_embedded", JSONUtil.put(embeddedJSONArrayKey, embeddedJSONArray)
		).toString();
	}

	@Autowired
	protected InterestDog interestDog;

	private void _addTermPerTopic(
		InterestTopic interestTopic, Map<Integer, Topic> topics) {

		Topic topic = topics.getOrDefault(
			interestTopic.getTopic(),
			new Topic(
				interestTopic.getTopic(), interestTopic.getTopicWeight()));

		topic.addTopicTerm(
			new TopicTerm(
				interestTopic.getTerm(), interestTopic.getTermWeight()));

		topics.put(interestTopic.getTopic(), topic);
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

		List<Integer> topics = _interestTopicDog.getInterestTopicTopics(
			terms, "keyword", termWeightThreshold);

		if (topics.isEmpty() && _log.isInfoEnabled()) {
			_log.info("There are no topics related with " + terms.toString());
		}

		return topics;
	}

	private List<Topic> _getTopTermsTopics(
		List<InterestTopic> interestTopics, int topicsLength) {

		Map<Integer, Topic> topics = new HashMap<>();

		for (InterestTopic interestTopic : interestTopics) {
			_addTermPerTopic(interestTopic, topics);
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
		int termsPerTopic, int topicsLength, Long individualId) {

		List<Interest> interests = interestDog.getInterests(
			individualId, "individual", 0, topicsLength * termsPerTopic * 10,
			new String[] {"score", "desc"});

		Stream<Interest> stream = interests.stream();

		return stream.map(
			interest -> interest.getName()
		).collect(
			Collectors.toList()
		);
	}

	private static final Log _log = LogFactory.getLog(
		InterestsRestController.class);

	@Autowired
	private InterestTopicDog _interestTopicDog;

	private static class Topic {

		protected void addTopicTerm(TopicTerm topicTerm) {
			_topicTerms.add(topicTerm);
		}

		private Topic(int id, double weight) {
			_id = id;
			_weight = weight;
		}

		private final int _id;
		private final List<TopicTerm> _topicTerms = new ArrayList<>();
		private final double _weight;

	}

	private static class TopicTerm {

		private TopicTerm(String keyword, double weight) {
			_keyword = keyword;
			_weight = weight;
		}

		private final String _keyword;
		private final double _weight;

	}

}