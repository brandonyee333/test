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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Amos Fong
 */
public class OSBAccountEnvironmentPermissionTest
	extends BasePermissionTestCase {

	@Test
	public void testAccountCustomerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerDeveloper.getUserId());

		assertTrue(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, restrictedAccountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testAccountCustomerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerManager.getUserId());

		assertTrue(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, restrictedAccountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testAccountCustomerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerNoRole.getUserId());

		assertFalse(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testAccountCustomerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerSales.getUserId());

		assertFalse(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testAccountCustomerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerWatcher.getUserId());

		assertFalse(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testLiferayIncEmployeePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_LIFERAY_EMPLOYEE_ID);

		assertFalse(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testOSBAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertTrue(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testPartnerWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerManager.getUserId());

		assertTrue(
			permissionChecker, partnerManagedSupportAccountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, restrictedAccountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testPartnerWorkerMemberPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerMember.getUserId());

		assertFalse(
			permissionChecker, partnerManagedSupportAccountEntry,
			OSBActionKeys.DELETE);
		assertTrue(
			permissionChecker, partnerManagedSupportAccountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, restrictedAccountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testPartnerWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, partnerManagedSupportAccountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testPartnerWorkerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerWatcher.getUserId());

		assertFalse(
			permissionChecker, partnerManagedSupportAccountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testSupportWorkerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerDeveloper.getUserId());

		assertFalse(permissionChecker, accountEntry, OSBActionKeys.DELETE);
		assertTrue(
			permissionChecker, accountEntry,
			OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT, OSBActionKeys.UPDATE);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, AccountEntry accountEntry,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBAccountEnvironmentPermission.contains(
					permissionChecker, accountEntry, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, AccountEntry accountEntry,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBAccountEnvironmentPermission.contains(
					permissionChecker, accountEntry, actionId));
		}
	}

}