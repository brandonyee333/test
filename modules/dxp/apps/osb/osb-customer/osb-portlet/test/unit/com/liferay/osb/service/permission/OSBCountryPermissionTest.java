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

import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Haijian Yang
 */
public class OSBCountryPermissionTest extends BasePermissionTestCase {

	@Test
	public void testAdministrator() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_ADMINISTRATOR_ID);

		assertTrue(
			permissionChecker, OSBActionKeys.DELETE, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker, OSBActionKeys.DELETE, OSBActionKeys.UPDATE);

		assertTrue(permissionChecker, OSBActionKeys.VIEW);
	}

	@Test
	public void testSupportWorkerDeveloper() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerDeveloper.getUserId());

		assertFalse(
			permissionChecker, OSBActionKeys.DELETE, OSBActionKeys.UPDATE);

		assertTrue(permissionChecker, OSBActionKeys.VIEW);
	}

	@Test
	public void testSupportWorkerManager() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertFalse(permissionChecker, OSBActionKeys.DELETE);

		assertTrue(permissionChecker, OSBActionKeys.UPDATE, OSBActionKeys.VIEW);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBCountryPermission.contains(permissionChecker, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBCountryPermission.contains(permissionChecker, actionId));
		}
	}

}