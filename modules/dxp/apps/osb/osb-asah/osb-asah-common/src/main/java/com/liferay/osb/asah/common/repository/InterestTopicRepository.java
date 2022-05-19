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

package com.liferay.osb.asah.common.repository;

import com.liferay.osb.asah.common.entity.InterestTopic;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;

/**
 * @author Marcellus Tavares
 */
public interface InterestTopicRepository
	extends Repository<InterestTopic, Long> {

	@Cacheable
	public List<Integer>
		findTopicsByTermInAndTermTypeAndTermWeightGreaterThanEqual(
			@Param("terms") List<String> terms,
			@Param("termType") String termType,
			@Param("termWeight") Double termWeight);

	@Cacheable
	public List<InterestTopic>
		findTopInterestTopicsByTermRankLessThanEqualAndTermTypeAndTopicIn(
			@Param("termRankEnd") Integer termRankEnd,
			@Param("termType") String termType,
			@Param("topics") List<Integer> topics);

	@Cacheable
	public List<String>
		findTopTermsByTermRankBetweenAndTermsNotInAndTermTypeAndTopicIn(
			@Param("termRank1") Integer termRank1,
			@Param("termRank2") Integer termRank2,
			@Param("terms") List<String> terms,
			@Param("termType") String termType,
			@Param("topics") List<Integer> topics);

}