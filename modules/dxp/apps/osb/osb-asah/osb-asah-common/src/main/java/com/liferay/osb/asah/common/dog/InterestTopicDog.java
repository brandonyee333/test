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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.model.InterestTopic;
import com.liferay.osb.asah.common.repository.InterestTopicRepository;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class InterestTopicDog {

	public List<InterestTopic> addInterestTopics(
		List<InterestTopic> interestTopics) {

		return IterableUtils.toList(
			_interestTopicRepository.saveAll(interestTopics));
	}

	public void deleteInterestTopics() {
		_interestTopicRepository.deleteAll();
	}

	public List<InterestTopic> getInterestTopics() {
		return IterableUtils.toList(_interestTopicRepository.findAll());
	}

	public List<InterestTopic> getInterestTopics(
		Integer termsPerTopic, String termType, List<Integer> topics) {

		return _interestTopicRepository.
			findTopInterestTopicsByTermRankLessThanEqualAndTermTypeAndTopicIn(
				termsPerTopic, termType, topics);
	}

	public List<String> getInterestTopicTerms(
		int page, int size, List<String> termsExclude, String termType,
		List<Integer> topics) {

		return _interestTopicRepository.
			findTopTermsByTermRankBetweenAndTermsNotInAndTermTypeAndTopicIn(
				_calculateTermRankEnd(size, topics.size()),
				_calculateTermRankStart(page, size, topics.size()),
				termsExclude, termType, topics);
	}

	public List<Integer> getInterestTopicTopics(
		List<String> terms, String termType, double termWeightThreshold) {

		return _interestTopicRepository.
			findTopicsByTermInAndTermTypeAndTermWeightGreaterThanEqual(
				terms, termType, termWeightThreshold);
	}

	private int _calculateTermRankEnd(int size, int topicsCount) {
		int defaultSize = 2;

		if ((topicsCount * defaultSize) < size) {
			return (int)Math.ceil((double)size / topicsCount);
		}

		return defaultSize;
	}

	private int _calculateTermRankStart(int page, int size, int topicsCount) {
		if (page > 0) {
			return (int)Math.ceil((double)(page * size) / topicsCount);
		}

		return 0;
	}

	@Autowired
	private InterestTopicRepository _interestTopicRepository;

}