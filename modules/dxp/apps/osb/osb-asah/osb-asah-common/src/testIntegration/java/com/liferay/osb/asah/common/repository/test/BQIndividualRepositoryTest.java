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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.EventDog;
import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.entity.BQIdentityActivity;
import com.liferay.osb.asah.common.entity.BQIdentityChannel;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.entity.BQMembership;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.BQIdentityActivityRepository;
import com.liferay.osb.asah.common.repository.BQIdentityChannelRepository;
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
	public void setUp() throws Exception {
		BQIndividual bqIndividual = new BQIndividual();

		Date date = new Date();

		bqIndividual.setCreateDate(date);

		String emailAddress = "test@liferay.com";

		bqIndividual.setEmailAddress(emailAddress);
		bqIndividual.setId(DigestUtils.sha256Hex(emailAddress));

		bqIndividual.setModifiedDate(date);
		bqIndividual.setIsNew(Boolean.TRUE);

		setUpRepository(bqIndividual);

		bqIndividual = entityModels.get(0);

		bqIndividual.setIsNew(Boolean.FALSE);

		bqIndividual = _bqIndividualRepository.save(bqIndividual);

		BQIdentity bqIdentity1 = new BQIdentity();

		bqIdentity1.setCreateDate(new Date());
		bqIdentity1.setId(RandomTestUtil.randomString());
		bqIdentity1.setIndividualId(DigestUtils.sha256Hex(emailAddress));
		bqIdentity1.setIsNew(Boolean.TRUE);

		_bqIdentityRepository.save(bqIdentity1);

		BQIdentity bqIdentity2 = new BQIdentity();

		bqIdentity2.setCreateDate(new Date());
		bqIdentity2.setId(RandomTestUtil.randomString());
		bqIdentity2.setIndividualId(DigestUtils.sha256Hex(emailAddress));
		bqIdentity2.setIsNew(Boolean.TRUE);

		_bqIdentityRepository.save(bqIdentity2);

		BQIdentityActivity bqIdentityActivity1 = new BQIdentityActivity();

		bqIdentityActivity1.setChannelId(11L);
		bqIdentityActivity1.setCreateDate(new Date());
		bqIdentityActivity1.setDataSourceId(1L);
		bqIdentityActivity1.setId(RandomTestUtil.randomUUID());
		bqIdentityActivity1.setIdentityId(bqIdentity1.getId());
		bqIdentityActivity1.setIndividualId(bqIdentity1.getIndividualId());
		bqIdentityActivity1.setIsNew(Boolean.TRUE);

		_bqIdentityActivityRepository.save(bqIdentityActivity1);

		BQIdentityActivity bqIdentityActivity2 = new BQIdentityActivity();

		bqIdentityActivity2.setChannelId(11L);
		bqIdentityActivity2.setCreateDate(new Date());
		bqIdentityActivity2.setDataSourceId(1L);
		bqIdentityActivity2.setId(RandomTestUtil.randomUUID());
		bqIdentityActivity2.setIdentityId(bqIdentity2.getId());
		bqIdentityActivity2.setIndividualId(bqIdentity2.getIndividualId());
		bqIdentityActivity2.setIsNew(Boolean.TRUE);

		_bqIdentityActivityRepository.save(bqIdentityActivity2);

		_eventDog.addBQEvent(
			"WebContent", Collections.emptySet(), 11L, new Date(), 1L,
			DateUtil.toUTCDate("2022-12-14T23:59:59.999Z"),
			"webContentViewed",
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			bqIdentity1.getId());

		_eventDog.addBQEvent(
			"WebContent", Collections.emptySet(), 11L, new Date(), 1L,
			DateUtil.toUTCDate("2022-12-15T23:59:59.999Z"),
			"webContentViewed",
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			bqIdentity2.getId());

		_eventDog.addBQEvent(
			"WebContent", Collections.emptySet(), 11L, new Date(), 1L,
			DateUtil.toUTCDate("2022-12-16T23:59:59.999Z"),
			"webContentViewed",
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			bqIdentity2.getId());

		BQMembership bqMembership = new BQMembership();

		bqMembership.setCreateDate(new Date());
		bqMembership.setId(RandomTestUtil.randomNumber());
		bqMembership.setIndividualId(bqIdentity1.getIndividualId());
		bqMembership.setIsNew(Boolean.TRUE);
		bqMembership.setSegmentId(_SEGMENT_ID);

		_bqMembershipRepository.save(bqMembership);

		bqIndividual.setIsNew(Boolean.FALSE);

		_bqIndividualRepository.save(bqIndividual);

		entityModels = Collections.singletonList(bqIndividual);
	}

	@Autowired
	private EventDog _eventDog;

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

		Individual individual = individuals.get(0);

		Assertions.assertEquals(3L, individual.getActivitiesCount());
		Assertions.assertEquals(
			DateUtil.toUTCDate("2022-12-16T23:59:59.999Z"),
			individual.getLastActivityDate());
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
	private BQIdentityChannelRepository _bqIdentityChannelRepository;

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

}