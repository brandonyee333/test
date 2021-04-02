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

import com.liferay.osb.asah.common.model.InterestTopic;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface InterestTopicRepository
	extends CrudRepository<InterestTopic, Long> {

	public List<Integer>
		findTopicsByTermInAndTermTypeAndTermWeightGreaterThanEqual(
			@Param("terms") List<String> terms,
			@Param("termType") String termType,
			@Param("termWeight") Double termWeight);

	public List<InterestTopic>
		findTopInterestTopicsByTermRankLessThanEqualAndTermTypeAndTopicIn(
			@Param("termRankEnd") Integer termRankEnd,
			@Param("termType") String termType,
			@Param("topics") List<Integer> topics);

	public List<String>
		findTopTermsByTermRankBetweenAndTermsNotInAndTermTypeAndTopicIn(
			@Param("termRankEnd") Integer termRankEnd,
			@Param("termRankStart") Integer termRankStart,
			@Param("terms") List<String> terms,
			@Param("termType") String termType,
			@Param("topics") List<Integer> topics);

}