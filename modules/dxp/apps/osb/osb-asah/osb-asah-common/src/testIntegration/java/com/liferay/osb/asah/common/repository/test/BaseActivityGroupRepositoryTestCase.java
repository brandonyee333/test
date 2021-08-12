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

import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.OSBAsahRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Inácio Nery
 */
public abstract class BaseActivityGroupRepositoryTestCase
	extends BaseRepositoryTestCase<ActivityGroup, Long> {

	@Before
	public void setUp() {
		ActivityGroup activityGroup = new ActivityGroup();

		activityGroup.setActivityType("BROWSER");
		activityGroup.setChannelId(12L);
		activityGroup.setDataSourceId(34L);
		activityGroup.setDayDate(new Date());
		activityGroup.setEndDate(new Date());
		activityGroup.setEndLocalDateTime(LocalDateTime.now());
		activityGroup.setOwnerId(56L);
		activityGroup.setStartDate(new Date());
		activityGroup.setStartLocalDateTime(LocalDateTime.now());
		activityGroup.setUserId("347780e0-7a66-11e8-a0fc-8356dd2944fd");

		setUpRepository(activityGroup);

		_activityGroup = entityModels.get(0);
	}

	@Test
	public void testCountActivityGroups() {
		Assert.assertEquals(
			1, _activityGroupRepository.countActivityGroups(null));
		Assert.assertEquals(
			1, _activityGroupRepository.countActivityGroups("channelId eq 12"));
		Assert.assertEquals(
			0, _activityGroupRepository.countActivityGroups("channelId eq 34"));

		Date dayDate = _activityGroup.getDayDate();

		Assert.assertEquals(
			0,
			_activityGroupRepository.countActivityGroups(
				"day lt '" + _getDate(dayDate, false, false) + "'"));
		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				"day lt '" + _getDate(dayDate, true, false) + "' and day ge '" +
					_getDate(dayDate, false, true) + "'"));
		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				"day ge '" + _getDate(dayDate, false, false) + "'"));

		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups(
				"day gt 'last24Hours'"));
		Assert.assertEquals(
			1,
			_activityGroupRepository.countActivityGroups("day gt 'yesterday'"));
		Assert.assertEquals(
			1, _activityGroupRepository.countActivityGroups("ownerId eq 56"));
		Assert.assertEquals(
			0, _activityGroupRepository.countActivityGroups("ownerId eq 78"));
	}

	@Test
	public void testDeleteByChannelIdIn() {
		_activityGroupRepository.deleteByChannelIdIn(
			Collections.singleton(_activityGroup.getChannelId()));

		Assert.assertEquals(0, _activityGroupRepository.count());
	}

	@Test
	public void testFindByActivityTypeAndChannelIdAndDataSourceIdAndDayDateAndUserId() {
		Assert.assertEquals(
			_activityGroup,
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
				null, PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());
		Assert.assertEquals(_activityGroup, activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			"channelId eq 12",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());
		Assert.assertEquals(_activityGroup, activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			"channelId eq 34",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 0, activityGroups.size());

		Date dayDate = _activityGroup.getDayDate();

		activityGroups = _activityGroupRepository.searchActivityGroups(
			"day lt '" + _getDate(dayDate, false, false) + "'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 0, activityGroups.size());

		activityGroups = _activityGroupRepository.searchActivityGroups(
			"day lt '" + _getDate(dayDate, true, false) + "' and day ge '" +
				_getDate(dayDate, false, true) + "'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());
		Assert.assertEquals(_activityGroup, activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			"day ge '" + _getDate(dayDate, false, true) + "'",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());
		Assert.assertEquals(_activityGroup, activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			"ownerId eq 56",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(
			activityGroups.toString(), 1, activityGroups.size());
		Assert.assertEquals(_activityGroup, activityGroups.get(0));

		activityGroups = _activityGroupRepository.searchActivityGroups(
			"ownerId eq 78",
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
			1, _activityGroupRepository.countActivityGroups("ownerId eq 90"));
	}

	@Override
	protected OSBAsahRepository<ActivityGroup, Long> getOSBAsahRepository() {
		return _activityGroupRepository;
	}

	private String _getDate(Date date, boolean end, boolean start) {
		if (date == null) {
			return null;
		}

		LocalDateTime localDateTime = LocalDateTime.ofInstant(
			date.toInstant(), ZoneOffset.UTC);

		if (!end && !start) {
			return String.valueOf(localDateTime.toInstant(ZoneOffset.UTC));
		}

		LocalTime localTime = LocalTime.MIN;

		if (end) {
			localTime = LocalTime.MAX;
		}

		localDateTime = localDateTime.with(localTime);

		return String.valueOf(localDateTime.toInstant(ZoneOffset.UTC));
	}

	private ActivityGroup _activityGroup;

	@Autowired
	private ActivityGroupRepository _activityGroupRepository;

}