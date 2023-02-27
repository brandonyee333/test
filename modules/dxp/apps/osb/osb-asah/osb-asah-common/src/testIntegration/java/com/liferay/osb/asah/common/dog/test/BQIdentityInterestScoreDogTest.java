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
import com.liferay.osb.asah.common.model.IdentityInterestScore;
import com.liferay.osb.asah.common.repository.BQIdentityInterestScoreRepository;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
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
		resourcePath = "osbasahfaroinfo/bq_identity_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQIdentityInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_identity_interest_score_info.json"
	)
	@Test
	public void testGetBQIdentityInterestScore() {
		IdentityInterestScore identityInterestScore =
			_bqIdentityInterestScoreDog.getIdentityInterestScore(
				"635452168436521350");

		Assertions.assertEquals(
			"635452168436521350", identityInterestScore.getId());

		Assertions.assertEquals("sales", identityInterestScore.getKeyword());
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_identity_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQIdentityInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_identity_interest_score_info.json"
	)
	@Test
	public void testGetBQIdentityInterestScoreBadRequest() {
		Assertions.assertThrows(
			OSBAsahException.class,
			() -> _bqIdentityInterestScoreDog.getIdentityInterestScore(
				"374790572703144534"));
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_identity_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQIdentityInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_identity_interest_score_info.json"
	)
	@Test
	public void testGetBQIdentityInterestScorePage() {
		Page<BQIdentityInterestScore> interestPage =
			_bqIdentityInterestScoreDog.getBQIdentityInterestScorePage(
				"374790572703144534", 20, 0);

		Assertions.assertEquals(1, interestPage.getTotalElements());

		List<BQIdentityInterestScore> bqIdentityInterestScores =
			interestPage.getContent();

		BQIdentityInterestScore bqIdentityInterestScore =
			bqIdentityInterestScores.get(0);

		Assertions.assertEquals(
			"compelling metrics", bqIdentityInterestScore.getKeyword());
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_identity_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQIdentityInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_identity_interest_score_info.json"
	)
	@Test
	public void testGetBQIdentityInterestScorePageByFilterString() {
		Page<IdentityInterestScore> individualInterestScorePage =
			_bqIdentityInterestScoreDog.getIdentityInterestScorePage(
				"name eq 'javascript'", 0, 20, null);

		Assertions.assertEquals(
			2, individualInterestScorePage.getTotalElements());

		List<IdentityInterestScore> individualInterestScores =
			individualInterestScorePage.getContent();

		IdentityInterestScore interest = individualInterestScores.get(0);

		Assertions.assertEquals("javascript", interest.getKeyword());
	}

	@Autowired
	private BQIdentityInterestScoreDog _bqIdentityInterestScoreDog;

}