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

import com.liferay.osb.asah.common.model.ActivityGroup;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Inácio Nery
 */
public abstract class BaseActivityGroupRepositoryTestCase
	extends BaseRepositoryTestCase<ActivityGroup> {

	@Before
	public void setUp() {
		ActivityGroup activityGroup = new ActivityGroup();

		activityGroup.setActivityType("BROWSER");
		activityGroup.setChannelId(12L);
		activityGroup.setDataSourceId(34L);
		activityGroup.setDayDate(new Date());
		activityGroup.setEndDate(new Date());
		activityGroup.setEndLocalDate(new Date());
		activityGroup.setOwnerId(56L);
		activityGroup.setStartDate(new Date());
		activityGroup.setStartLocalDate(new Date());
		activityGroup.setUserId("347780e0-7a66-11e8-a0fc-8356dd2944fd");

		_activityGroup = _activityGroupRepository.save(activityGroup);
	}

	@Test
	public void testCountActivityGroups() {
		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				null, null, null, null));

		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				12L, null, null, null));

		Assert.assertEquals(
			0,
			_activityGroupRepository.countActivityGroups(
				34L, null, null, null));

		Assert.assertEquals(
			0,
			_activityGroupRepository.countActivityGroups(
				null, _activityGroup.getDayDate(), null, null));

		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				null, new Date(), _activityGroup.getDayDate(), null));

		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				null, null, _activityGroup.getDayDate(), null));

		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				null, null, null, 56L));

		Assert.assertEquals(
			0,
			_activityGroupRepository.countActivityGroups(
				null, null, null, 78L));
	}

	@Test
	public void testDeleteByChannelIdIn() {
		_activityGroupRepository.deleteByChannelIdIn(
			Collections.singleton(_activityGroup.getChannelId()));

		Assert.assertEquals(0, _activityGroupRepository.count());
	}

	@Test
	public void testFindByActivityTypeAndChannelIdAndDataSourceIdAndDayDateAndUserId() {
		assertModel(
			_activityGroupRepository.
				findByActivityTypeAndChannelIdAndDataSourceIdAndDayDateAndUserId(
					_activityGroup.getActivityType(),
					_activityGroup.getChannelId(),
					_activityGroup.getDataSourceId(),
					_activityGroup.getDayDate(), _activityGroup.getUserId()));
	}

	@Test
	public void testSearchActivityGroups() {
		List<ActivityGroup> activityGroups =
			_activityGroupRepository.searchActivityGroups(
				null, null, null, null,
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());

		assertModel(activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			12L, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());

		assertModel(activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			34L, null, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 0, activityGroups.size());

		activityGroups = _activityGroupRepository.searchActivityGroups(
			null, _activityGroup.getDayDate(), null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 0, activityGroups.size());

		activityGroups = _activityGroupRepository.searchActivityGroups(
			null, new Date(), _activityGroup.getDayDate(), null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());

		assertModel(activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			null, null, _activityGroup.getDayDate(), null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());

		assertModel(activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			null, null, null, 56L,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());

		assertModel(activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			null, null, null, 78L,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 0, activityGroups.size());
	}

	@Test
	public void testUpdateOwnerId() {
		Assert.assertTrue(
			_activityGroupRepository.updateOwnerId(
				90L, "347780e0-7a66-11e8-a0fc-8356dd2944fd"));

		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				null, null, null, 90L));
	}

	@Override
	protected void assertModel(ActivityGroup activityGroup) {
		Assert.assertNotNull(activityGroup);
		Assert.assertNotNull(activityGroup.getId());
		Assert.assertEquals(
			_activityGroup.getActivityType(), activityGroup.getActivityType());
		Assert.assertEquals(
			_activityGroup.getChannelId(), activityGroup.getChannelId());
		Assert.assertEquals(
			_activityGroup.getDataSourceId(), activityGroup.getDataSourceId());
		Assert.assertEquals(
			_activityGroup.getDayDate(), activityGroup.getDayDate());
		Assert.assertEquals(
			_activityGroup.getEndDate(), activityGroup.getEndDate());
		Assert.assertEquals(
			_activityGroup.getEndLocalDate(), activityGroup.getEndLocalDate());
		Assert.assertEquals(
			_activityGroup.getOwnerId(), activityGroup.getOwnerId());
		Assert.assertEquals(
			_activityGroup.getStartDate(), activityGroup.getStartDate());
		Assert.assertEquals(
			_activityGroup.getStartLocalDate(),
			activityGroup.getStartLocalDate());
		Assert.assertEquals(
			_activityGroup.getUserId(), activityGroup.getUserId());
	}

	@Override
	protected CrudRepository<ActivityGroup, Long> getCrudRepository() {
		return _activityGroupRepository;
	}

	@Override
	protected List<ActivityGroup> getModels() {
		return Collections.singletonList(_activityGroup);
	}

	private ActivityGroup _activityGroup;

	@Autowired
	private ActivityGroupRepository _activityGroupRepository;

}