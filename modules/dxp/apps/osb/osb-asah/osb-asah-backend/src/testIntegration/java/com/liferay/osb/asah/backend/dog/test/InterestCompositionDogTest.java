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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.InterestCompositionDog;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualInterestScoreRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.annotation.SQLResource;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Matthew Kong
 */
public class InterestCompositionDogTest extends BaseCompositionDogTestCase {

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_scores_info.json"
	)
	@SQLResource(
		resourcePath = "bq_individual_interest_score_identity_activities.sql"
	)
	@Test
	public void testGetIndividualCompositionResultBag() {
		checkResults(
			_interestCompositionDog.getIndividualCompositionResultBag(
				1L, "e", 1, Sort.asc("count"), 1),
			new LinkedHashMap<String, Long>() {
				{
					put("compelling metrics", 4L);
				}
			},
			4, 2, 4);
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_scores_info.json"
	)
	@SQLResource(
		resourcePath = "bq_individual_interest_score_identity_activities.sql"
	)
	@Test
	public void testGetIndividualCompositionResultBagWithOrderByAsc() {
		checkResults(
			_interestCompositionDog.getIndividualCompositionResultBag(
				1L, null, 10, Sort.asc("name"), 0),
			new LinkedHashMap<String, Long>() {
				{
					put("clicks-and-mortar e-tailers", 2L);
					put("compelling metrics", 4L);
					put("javascript", 2L);
				}
			},
			4, 3, 4);
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_scores_info.json"
	)
	@SQLResource(
		resourcePath = "bq_individual_interest_score_identity_activities.sql"
	)
	@Test
	public void testGetIndividualSegmentCompositionResultBagWithKeyword() {
		checkResults(
			_interestCompositionDog.getIndividualSegmentCompositionResultBag(
				Boolean.TRUE, 1L, "compel", 366637689379787789L, 10,
				Sort.desc("count"), 0),
			new LinkedHashMap<String, Long>() {
				{
					put("compelling metrics", 2L);
				}
			},
			2, 1, 2);
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_scores_info.json"
	)
	@SQLResource(
		resourcePath = "bq_individual_interest_score_identity_activities.sql"
	)
	@Test
	public void testGetIndividualSegmentCompositionResultBagWithSortAsc() {
		checkResults(
			_interestCompositionDog.getIndividualSegmentCompositionResultBag(
				Boolean.TRUE, 1L, null, 366637689379787789L, 10,
				Sort.asc("count"), 0),
			new LinkedHashMap<String, Long>() {
				{
					put("javascript", 1L);
					put("clicks-and-mortar e-tailers", 2L);
					put("compelling metrics", 2L);
				}
			},
			2, 3, 2);
	}

	@RepositoryResource(
		repositoryClass = BQIdentityRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_identities.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_score_memberships.json"
	)
	@RepositoryResource(
		repositoryClass = BQIndividualInterestScoreRepository.class,
		resourcePath = "osbasahfaroinfo/bq_individual_interest_scores_info.json"
	)
	@SQLResource(
		resourcePath = "bq_individual_interest_score_identity_activities.sql"
	)
	@Test
	public void testGetIndividualSegmentCompositionResultBagWithSortName() {
		checkResults(
			_interestCompositionDog.getIndividualSegmentCompositionResultBag(
				Boolean.TRUE, 1L, null, 366637689379787789L, 10,
				Sort.asc("name"), 0),
			new LinkedHashMap<String, Long>() {
				{
					put("clicks-and-mortar e-tailers", 2L);
					put("compelling metrics", 2L);
					put("javascript", 1L);
				}
			},
			2, 3, 2);
	}

	@Autowired
	private InterestCompositionDog _interestCompositionDog;

}