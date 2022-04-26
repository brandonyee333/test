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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.BQUserGroupDog;
import com.liferay.osb.asah.common.entity.BQUserGroup;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQUserGroupRepository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Marcos Martins
 */
public class BQUserGroupDogTest extends BaseBQDXPEntityDogTestCase {

	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();

		BQUserGroup bqUserGroup1 = new BQUserGroup();

		bqUserGroup1.setDataSourceId(dataSource.getId());
		bqUserGroup1.setId("1");
		bqUserGroup1.setIsNew(true);
		bqUserGroup1.setModifiedDate(DateUtil.newDate());
		bqUserGroup1.setName("Test");
		bqUserGroup1.setUserGroupId(1L);

		BQUserGroup bqUserGroup2 = new BQUserGroup();

		bqUserGroup2.setDataSourceId(dataSource.getId());
		bqUserGroup2.setId("2");
		bqUserGroup2.setIsNew(true);
		bqUserGroup2.setModifiedDate(DateUtil.newDate());
		bqUserGroup2.setName("Liferay");
		bqUserGroup2.setUserGroupId(2L);

		_bqUserGroupRepository.saveAll(
			Arrays.asList(bqUserGroup1, bqUserGroup2));
	}

	@Test
	public void testGetBQUserGroupPage() {
		Page<BQUserGroup> bqUserGroupPage = _bqUserGroupDog.getBQUserGroupPage(
			11L, null, 10, Sort.asc("name"), 0);

		Assertions.assertEquals(2, bqUserGroupPage.getTotalElements());

		List<BQUserGroup> bqUserGroups = bqUserGroupPage.getContent();

		Assertions.assertEquals(
			2, bqUserGroups.size(), bqUserGroups.toString());

		bqUserGroupPage = _bqUserGroupDog.getBQUserGroupPage(
			11L, "Test", 10, Sort.asc("name"), 0);

		Assertions.assertEquals(1, bqUserGroupPage.getTotalElements());

		bqUserGroups = bqUserGroupPage.getContent();

		Assertions.assertEquals(
			1, bqUserGroups.size(), bqUserGroups.toString());
	}

	@Autowired
	private BQUserGroupDog _bqUserGroupDog;

	@Autowired
	private BQUserGroupRepository _bqUserGroupRepository;

}