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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.entity.BQIdentityActivity;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.BQIdentityActivityRepository;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Ivica Cardic
 */
@Import(JDBCTestConfiguration.class)
public class BQIndividualRepositoryTest
	extends BaseRepositoryTestCase<BQIndividual, String> {

	@BeforeEach
	public void setUp() {
		BQIndividual bqIndividual = new BQIndividual();

		Date date = new Date();

		bqIndividual.setCreateDate(date);

		String emailAddress = "test@liferay.com";

		bqIndividual.setEmailAddress(emailAddress);
		bqIndividual.setId(DigestUtils.sha256Hex(emailAddress));

		bqIndividual.setModifiedDate(date);
		bqIndividual.setIsNew(true);

		setUpRepository(bqIndividual);

		bqIndividual = entityModels.get(0);

		bqIndividual.setIsNew(false);

		bqIndividual = _bqIndividualRepository.save(bqIndividual);

		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());
		bqIdentity.setIndividualId(DigestUtils.sha256Hex(emailAddress));
		bqIdentity.setIsNew(true);
		bqIdentity.setUserId(RandomTestUtil.randomString());

		bqIdentity = _bqIdentityRepository.save(bqIdentity);

		BQIdentityActivity bqIdentityActivity = new BQIdentityActivity();

		bqIdentityActivity.setChannelId(11L);
		bqIdentityActivity.setCreateDate(new Date());
		bqIdentityActivity.setDataSourceId(1L);
		bqIdentityActivity.setId(RandomTestUtil.randomUUID());
		bqIdentityActivity.setIdentityId(bqIdentity.getId());
		bqIdentityActivity.setIndividualId(bqIdentity.getIndividualId());
		bqIdentityActivity.setIsNew(true);

		_bqIdentityActivityRepository.save(bqIdentityActivity);

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(new Date());
		bqMembership.setId(RandomTestUtil.randomNumber());
		bqMembership.setIndividualId(bqIdentity.getIndividualId());
		bqMembership.setIsNew(true);
		bqMembership.setSegmentId(_SEGMENT_ID);

		_bqMembershipRepository.save(bqMembership);

		bqIndividual.setIsNew(false);

		_bqIndividualRepository.save(bqIndividual);

		entityModels = Collections.singletonList(bqIndividual);
	}

	@Test
	public void testCountBQIndividuals() {
		Assertions.assertEquals(
			1,
			_bqIndividualRepository.countBQIndividuals(
				null, 11L, null, null, null, _SEGMENT_ID));
	}

	@Disabled
	@Override
	@Test
	public void testFindAll1() {
		super.testFindAll1();
	}

	@Override
	@Test
	public void testFindAll2() {
		Page<BQIndividual> page = _bqIndividualRepository.findAll(
			PageRequest.of(0, entityModels.size(), Sort.by("id")));

		Assertions.assertEquals(entityModels, page.getContent());
	}

	@Override
	@Test
	public void testFindAll3() {
		Assertions.assertEquals(
			entityModels, _bqIndividualRepository.findAll(Sort.by("id")));
	}

	@Test
	public void testSearchBQIndividuals() {
		List<Individual> individuals =
			_bqIndividualRepository.searchBQIndividuals(
				null, 11L, null, null,
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))), null,
				null);

		Assertions.assertEquals(1, individuals.size(), individuals.toString());
	}

	@Override
	protected PagingAndSortingRepository<BQIndividual, String>
		getPagingAndSortingRepository() {

		return _bqIndividualRepository;
	}

	private static final Long _SEGMENT_ID = 11L;

	@Autowired
	private BQIdentityActivityRepository _bqIdentityActivityRepository;

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

}