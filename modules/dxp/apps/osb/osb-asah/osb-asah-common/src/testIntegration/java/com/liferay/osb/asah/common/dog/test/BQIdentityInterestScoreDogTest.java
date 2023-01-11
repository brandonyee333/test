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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.BQIdentityInterestScoreDog;
import com.liferay.osb.asah.common.entity.BQIdentityInterestScore;
import com.liferay.osb.asah.common.model.IndividualInterestScore;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualInterestScoreRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Marcellus Tavares
 */
public class BQIdentityInterestScoreDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_info.json"
	)
	@Test
	public void testGetBQIndividualInterestScore() {
		IndividualInterestScore individualInterestScore =
			_bqIndividualInterestScoreDog.getIndividualInterestScore(
				635452168436521350L);

		Assertions.assertEquals(
			635452168436521350L, individualInterestScore.getId());

		Assertions.assertEquals("sales", individualInterestScore.getKeyword());
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_info.json"
	)
	@Test
	public void testGetBQIndividualInterestScoreBadRequest() {
		Assertions.assertThrows(
			OSBAsahException.class,
			() -> _bqIndividualInterestScoreDog.getIndividualInterestScore(
				374790572703144534L));
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_info.json"
	)
	@Test
	public void testGetBQIndividualInterestScorePage() {
		Page<BQIdentityInterestScore> interestPage =
			_bqIndividualInterestScoreDog.getBQIndividualInterestScorePage(
				"374790572703144534", 20, 0);

		Assertions.assertEquals(1, interestPage.getTotalElements());

		List<BQIdentityInterestScore> bqIndividualInterestScores =
			interestPage.getContent();

		BQIdentityInterestScore bqIndividualInterestScore =
			bqIndividualInterestScores.get(0);

		Assertions.assertEquals(
			"compelling metrics", bqIndividualInterestScore.getKeyword());
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_info.json"
	)
	@Test
	public void testGetBQIndividualInterestScorePageByFilterStringAndScore() {
		Page<IndividualInterestScore> individualInterestScorePage =
			_bqIndividualInterestScoreDog.getIndividualInterestScorePage(
				"name eq 'javascript'", 0.1, 0, 20, null);

		Assertions.assertEquals(
			2, individualInterestScorePage.getTotalElements());

		List<IndividualInterestScore> individualInterestScores =
			individualInterestScorePage.getContent();

		IndividualInterestScore interest = individualInterestScores.get(0);

		Assertions.assertEquals("javascript", interest.getKeyword());
	}

	@Autowired
	private BQIdentityInterestScoreDog _bqIndividualInterestScoreDog;

}