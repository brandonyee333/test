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

import com.liferay.osb.asah.common.dog.BQRoleDog;
import com.liferay.osb.asah.common.entity.BQRole;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Marcos Martins
 */
@BQSQLResource(resourcePath = "test_bq_role_dog.sql")
public class BQRoleDogTest extends BaseBQDXPEntityDogTestCase {

	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testGetBQRolePage() {
		Page<BQRole> bqRolePage = _bqRoleDog.getBQRolePage(
			11L, null, 10, Sort.asc("name"), 0);

		Assertions.assertEquals(2, bqRolePage.getTotalElements());

		List<BQRole> bqRoles = bqRolePage.getContent();

		Assertions.assertEquals(2, bqRoles.size(), bqRoles.toString());

		bqRolePage = _bqRoleDog.getBQRolePage(
			11L, "Test", 10, Sort.asc("name"), 0);

		Assertions.assertEquals(1, bqRolePage.getTotalElements());

		bqRoles = bqRolePage.getContent();

		Assertions.assertEquals(1, bqRoles.size(), bqRoles.toString());
	}

	@Autowired
	private BQRoleDog _bqRoleDog;

}