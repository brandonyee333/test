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
import com.liferay.osb.asah.common.dog.BQUserDog;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.model.ExpandoField;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.BQUserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Marcos Martins
 */
public class BQUserDogTest extends BaseBQDXPEntityDogTestCase {

	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();

		BQUser bqUser1 = new BQUser();

		bqUser1.setDataSourceId(dataSource.getId());
		bqUser1.setDXPUserId(1L);
		bqUser1.setFirstName("Test");
		bqUser1.setId("1");
		bqUser1.setIsNew(true);
		bqUser1.setModifiedDate(DateUtil.newDate());

		BQUser bqUser2 = new BQUser();

		bqUser2.setDataSourceId(dataSource.getId());
		bqUser2.setDXPUserId(2L);
		bqUser2.setExpandoColumnIds(new Long[] {bqExpandoColumn.getColumnId()});
		bqUser2.setExpandoValueIds(new String[] {bqExpandoValue.getId()});
		bqUser2.setFirstName("Liferay");
		bqUser2.setId("2");
		bqUser2.setIsNew(true);
		bqUser2.setModifiedDate(DateUtil.newDate());

		_bqUserRepository.saveAll(Arrays.asList(bqUser1, bqUser2));
	}

	@Test
	public void testGetBQUserPage() {
		Page<BQUser> bqUserPage = _bqUserDog.getBQUserPage(
			11L, null, 10, Sort.asc("firstName"), 0);

		Assertions.assertEquals(2, bqUserPage.getTotalElements());

		List<BQUser> bqUsers = bqUserPage.getContent();

		Assertions.assertEquals(2, bqUsers.size(), bqUsers.toString());

		bqUserPage = _bqUserDog.getBQUserPage(
			11L, "Test", 10, Sort.asc("firstName"), 0);

		Assertions.assertEquals(1, bqUserPage.getTotalElements());

		bqUsers = bqUserPage.getContent();

		Assertions.assertEquals(1, bqUsers.size(), bqUsers.toString());
	}

	@Test
	public void testGetBQUsers() {
		List<BQUser> bqUsers = _bqUserDog.getBQUsers(
			Collections.singletonMap("userId", 2), PageRequest.of(0, 10));

		Assertions.assertEquals(1, bqUsers.size(), bqUsers.toString());

		BQUser bqUser = bqUsers.get(0);

		List<ExpandoField> expandoFields = bqUser.getExpandoFields();

		Assertions.assertEquals(
			1, expandoFields.size(), expandoFields.toString());

		ExpandoField expandoField = expandoFields.get(0);

		Assertions.assertEquals(1, expandoField.getColumnId());
		Assertions.assertEquals("column", expandoField.getName());
		Assertions.assertEquals("test", expandoField.getValue());
	}

	@Autowired
	private BQUserDog _bqUserDog;

	@Autowired
	private BQUserRepository _bqUserRepository;

}