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
import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.entity.BQIdentityActivity;
import com.liferay.osb.asah.common.entity.BQIdentityChannel;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.model.IndividualMetricType;
import com.liferay.osb.asah.common.repository.BQIdentityActivityRepository;
import com.liferay.osb.asah.common.repository.BQIdentityChannelRepository;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Ivica Cardic
 */
@Import(JDBCTestConfiguration.class)
public class BQIdentityRepositoryTest
	extends BaseRepositoryTestCase<BQIdentity, String> {

	@BeforeEach
	public void setUp() {
		List<BQIdentity> bqIdentities = _addBQIdentities();

		setUpRepository(bqIdentities.toArray(new BQIdentity[0]));

		_addBQIdentityActivities();
		_addBQIdentityChannels();
		_addBQIndividuals();
	}

	@AfterEach
	public void tearDown() {
		super.tearDown();

		_bqIdentityActivityRepository.deleteAll();
		_bqIdentityChannelRepository.deleteAll();
		_bqIndividualRepository.deleteAll();
	}

	@Test
	public void testGetIndividualsCount() {
		LocalDate localDate = LocalDate.now();

		long anonymousIndividualsCount =
			_bqIdentityRepository.getIndividualsCount(
				false, 1L, localDate,
				IndividualMetricType.ANONYMOUS_INDIVIDUALS);

		Assertions.assertEquals(1, anonymousIndividualsCount, 0);

		long knownIndividualsCount = _bqIdentityRepository.getIndividualsCount(
			false, 1L, localDate, IndividualMetricType.KNOWN_INDIVIDUALS);

		Assertions.assertEquals(4, knownIndividualsCount, 0);

		long totalIndividualsCount = _bqIdentityRepository.getIndividualsCount(
			false, 1L, localDate, IndividualMetricType.TOTAL_INDIVIDUALS);

		Assertions.assertEquals(5, totalIndividualsCount, 0);

		long totalActiveIndividualsCount =
			_bqIdentityRepository.getIndividualsCount(
				true, 1L, localDate, IndividualMetricType.TOTAL_INDIVIDUALS);

		Assertions.assertEquals(2, totalActiveIndividualsCount, 0);
	}

	@Override
	@Test
	public void testSave() {
		BQIdentity bqIdentity = _randomBQIdentity();

		Assertions.assertEquals(
			bqIdentity, _bqIdentityRepository.save(bqIdentity));
	}

	@Override
	@Test
	public void testSaveAll() {
		BQIdentity bqIdentity = _randomBQIdentity();

		Assertions.assertEquals(
			Arrays.asList(bqIdentity),
			_bqIdentityRepository.saveAll(Arrays.asList(bqIdentity)));
	}

	@Override
	protected PagingAndSortingRepository<BQIdentity, String>
		getPagingAndSortingRepository() {

		return _bqIdentityRepository;
	}

	private List<BQIdentity> _addBQIdentities() {
		List<BQIdentity> bqIdentities = new ArrayList<>();

		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(DateUtil.addDays(new Date(), -3));
		bqIdentity.setId("1");
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("test1@liferay.com"));
		bqIdentity.setIsNew(Boolean.TRUE);

		bqIdentities.add(bqIdentity);

		bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(DateUtil.addDays(new Date(), -5));
		bqIdentity.setId("2");
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("test2@liferay.com"));
		bqIdentity.setIsNew(Boolean.TRUE);

		bqIdentities.add(bqIdentity);

		bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(DateUtil.addDays(new Date(), -11));
		bqIdentity.setId("3");
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("test3@liferay.com"));
		bqIdentity.setIsNew(Boolean.TRUE);

		bqIdentities.add(bqIdentity);

		bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(DateUtil.addDays(new Date(), -36));
		bqIdentity.setId("4");
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("test4@liferay.com"));
		bqIdentity.setIsNew(Boolean.TRUE);

		bqIdentities.add(bqIdentity);

		bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(DateUtil.addDays(new Date(), -11));
		bqIdentity.setId("5");
		bqIdentity.setIsNew(Boolean.TRUE);

		bqIdentities.add(bqIdentity);

		return bqIdentities;
	}

	private void _addBQIdentityActivities() {
		BQIdentityActivity bqIdentityActivity = new BQIdentityActivity();

		bqIdentityActivity.setChannelId(1L);
		bqIdentityActivity.setCreateDate(DateUtil.addDays(new Date(), -3));
		bqIdentityActivity.setId("1");
		bqIdentityActivity.setIndividualId(
			DigestUtils.sha256Hex("test1@liferay.com"));
		bqIdentityActivity.setIsNew(Boolean.TRUE);
		bqIdentityActivity.setIdentityId("1");

		_bqIdentityActivityRepository.save(bqIdentityActivity);

		bqIdentityActivity = new BQIdentityActivity();

		bqIdentityActivity.setChannelId(1L);
		bqIdentityActivity.setCreateDate(DateUtil.addDays(new Date(), -5));
		bqIdentityActivity.setId("2");
		bqIdentityActivity.setIndividualId(
			DigestUtils.sha256Hex("test2@liferay.com"));
		bqIdentityActivity.setIsNew(Boolean.TRUE);
		bqIdentityActivity.setIdentityId("2");

		_bqIdentityActivityRepository.save(bqIdentityActivity);

		bqIdentityActivity = new BQIdentityActivity();

		bqIdentityActivity.setChannelId(1L);
		bqIdentityActivity.setCreateDate(DateUtil.addDays(new Date(), -11));
		bqIdentityActivity.setId("3");
		bqIdentityActivity.setIndividualId(
			DigestUtils.sha256Hex("test3@liferay.com"));
		bqIdentityActivity.setIsNew(Boolean.TRUE);
		bqIdentityActivity.setIdentityId("3");

		_bqIdentityActivityRepository.save(bqIdentityActivity);

		bqIdentityActivity = new BQIdentityActivity();

		bqIdentityActivity.setChannelId(1L);
		bqIdentityActivity.setCreateDate(DateUtil.addDays(new Date(), -36));
		bqIdentityActivity.setId("4");
		bqIdentityActivity.setIndividualId(
			DigestUtils.sha256Hex("test4@liferay.com"));
		bqIdentityActivity.setIsNew(Boolean.TRUE);
		bqIdentityActivity.setIdentityId("4");

		_bqIdentityActivityRepository.save(bqIdentityActivity);

		bqIdentityActivity = new BQIdentityActivity();

		bqIdentityActivity.setChannelId(1L);
		bqIdentityActivity.setCreateDate(DateUtil.addDays(new Date(), -11));
		bqIdentityActivity.setId("5");
		bqIdentityActivity.setIsNew(Boolean.TRUE);
		bqIdentityActivity.setIdentityId("5");

		_bqIdentityActivityRepository.save(bqIdentityActivity);
	}

	private void _addBQIdentityChannels() {
		BQIdentityChannel bqIdentityChannel = new BQIdentityChannel();

		bqIdentityChannel.setChannelId(1L);
		bqIdentityChannel.setCreateDate(DateUtil.addDays(new Date(), -2));
		bqIdentityChannel.setIndividualId(
			DigestUtils.sha256Hex("test1@liferay.com"));
		bqIdentityChannel.setId("1");
		bqIdentityChannel.setIsNew(Boolean.TRUE);
		bqIdentityChannel.setLastActivityDate(DateUtil.addDays(new Date(), -2));
		bqIdentityChannel.setIdentityId("1");

		_bqIdentityChannelRepository.save(bqIdentityChannel);

		bqIdentityChannel = new BQIdentityChannel();

		bqIdentityChannel.setChannelId(1L);
		bqIdentityChannel.setCreateDate(DateUtil.addDays(new Date(), -4));
		bqIdentityChannel.setIndividualId(
			DigestUtils.sha256Hex("test2@liferay.com"));
		bqIdentityChannel.setId("2");
		bqIdentityChannel.setIsNew(Boolean.TRUE);
		bqIdentityChannel.setLastActivityDate(DateUtil.addDays(new Date(), -4));
		bqIdentityChannel.setIdentityId("2");

		_bqIdentityChannelRepository.save(bqIdentityChannel);

		bqIdentityChannel = new BQIdentityChannel();

		bqIdentityChannel.setChannelId(1L);
		bqIdentityChannel.setCreateDate(DateUtil.addDays(new Date(), -35));
		bqIdentityChannel.setIndividualId(
			DigestUtils.sha256Hex("test4@liferay.com"));
		bqIdentityChannel.setId("4");
		bqIdentityChannel.setIsNew(Boolean.TRUE);
		bqIdentityChannel.setLastActivityDate(
			DateUtil.addDays(new Date(), -35));
		bqIdentityChannel.setIdentityId("4");

		_bqIdentityChannelRepository.save(bqIdentityChannel);
	}

	private void _addBQIndividuals() {
		BQIndividual bqIndividual = new BQIndividual();

		bqIndividual.setCreateDate(DateUtil.addDays(new Date(), -2));
		bqIndividual.setEmailAddress("test1@liferay.com");
		bqIndividual.setId(DigestUtils.sha256Hex("test1@liferay.com"));
		bqIndividual.setIsNew(Boolean.TRUE);

		_bqIndividualRepository.save(bqIndividual);

		bqIndividual = new BQIndividual();

		bqIndividual.setCreateDate(DateUtil.addDays(new Date(), -4));
		bqIndividual.setEmailAddress("test2@liferay.com");
		bqIndividual.setId(DigestUtils.sha256Hex("test2@liferay.com"));
		bqIndividual.setIsNew(Boolean.TRUE);

		_bqIndividualRepository.save(bqIndividual);

		bqIndividual = new BQIndividual();

		bqIndividual.setCreateDate(DateUtil.addDays(new Date(), -10));
		bqIndividual.setEmailAddress("test3@liferay.com");
		bqIndividual.setId(DigestUtils.sha256Hex("test3@liferay.com"));
		bqIndividual.setIsNew(Boolean.TRUE);

		_bqIndividualRepository.save(bqIndividual);

		bqIndividual = new BQIndividual();

		bqIndividual.setCreateDate(DateUtil.addDays(new Date(), -35));
		bqIndividual.setEmailAddress("test4@liferay.com");
		bqIndividual.setId(DigestUtils.sha256Hex("test4@liferay.com"));
		bqIndividual.setIsNew(Boolean.TRUE);

		_bqIndividualRepository.save(bqIndividual);
	}

	private BQIdentity _randomBQIdentity() {
		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());
		bqIdentity.setId(RandomTestUtil.randomUUID());
		bqIdentity.setIndividualId(RandomTestUtil.randomEmailAddress());
		bqIdentity.setIsNew(Boolean.TRUE);

		return bqIdentity;
	}

	@Autowired
	private BQIdentityActivityRepository _bqIdentityActivityRepository;

	@Autowired
	private BQIdentityChannelRepository _bqIdentityChannelRepository;

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

}