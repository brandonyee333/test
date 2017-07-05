/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.permission;

import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.apache.commons.lang.math.RandomUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author Amos Fong
 */
@PrepareForTest(TicketWorkerLocalServiceUtil.class)
public class OSBTicketCannedResponsePermissionTest
	extends BasePermissionTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		mockStatic(TicketWorkerLocalServiceUtil.class);

		when(
			TicketWorkerLocalServiceUtil.getUserTicketWorkersCount(
				USER_TICKER_WORKER_ID)
		).thenReturn(
			1
		);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker, OSBActionKeys.UPDATE, OSBActionKeys.VIEW);
	}

	@Test
	public void testLiferayIncEmployeePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_LIFERAY_EMPLOYEE_ID);

		assertTrue(permissionChecker, OSBActionKeys.UPDATE, OSBActionKeys.VIEW);
	}

	@Test
	public void testTicketWorkerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_TICKER_WORKER_ID);

		assertTrue(permissionChecker, OSBActionKeys.UPDATE, OSBActionKeys.VIEW);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBTicketCannedResponsePermission.contains(
					permissionChecker, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBTicketCannedResponsePermission.contains(
					permissionChecker, actionId));
		}
	}

	protected static final long USER_TICKER_WORKER_ID = RandomUtils.nextLong();

}