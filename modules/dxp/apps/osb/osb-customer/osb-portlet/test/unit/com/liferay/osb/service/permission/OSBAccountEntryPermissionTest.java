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
 * @author Amos Fong
 */
public class OSBAccountEntryPermissionTest extends BasePermissionTestCase {

	@Test
	public void testAccountCustomerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerDeveloper.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ASSIGN_CUSTOMERS, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_TICKET, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerManager.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerNoRole.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerSales.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerWatcher.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerAdvocacySpecialistPermissions()
		throws Exception {

		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerAdvocacySpecialist.getUserId());

		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerManagedServicesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerManagedServices.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ASSIGN_CUSTOMERS, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ASSIGN_CUSTOMERS, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerSales.getUserId());

		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testClockedOutSupportWorkerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			clockedOutSupportWorker.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.VIEW);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testLiferayIncEmployeePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_LIFERAY_EMPLOYEE_ID);

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ASSIGN_CUSTOMERS, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_TICKET, OSBActionKeys.VIEW);
	}

	@Test
	public void testOSBAccountAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ACCOUNT_ADMIN_ID);

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_TICKET, OSBActionKeys.VIEW);
		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ASSIGN_CUSTOMERS, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);
	}

	@Test
	public void testOSBAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerManager.getUserId());

		assertFalse(
			permissionChecker,
			partnerManagedSupportAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ASSIGN_CUSTOMERS, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_TICKET, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerMemberPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerMember.getUserId());

		assertFalse(
			permissionChecker,
			partnerManagedSupportAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ASSIGN_CUSTOMERS, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_TICKET, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker,
			partnerManagedSupportAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getAccountEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerWatcher.getUserId());

		assertFalse(
			permissionChecker,
			partnerManagedSupportAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO);
		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getAccountEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ADD_TICKET, OSBActionKeys.ASSIGN_CUSTOMERS,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.UPDATE_ACCOUNT_INFO,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testSupportWorkerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerDeveloper.getUserId());

		assertFalse(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE, OSBActionKeys.ADD_LICENSE_KEY_SET,
			OSBActionKeys.ASSIGN_CUSTOMERS, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ACCOUNT_INFO);

		assertTrue(
			permissionChecker, accountEntry.getAccountEntryId(),
			OSBActionKeys.ADD_TICKET, OSBActionKeys.UPDATE_ACCOUNT_INSTRUCTIONS,
			OSBActionKeys.VIEW);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, long accountEntryId,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBAccountEntryPermission.contains(
					permissionChecker, accountEntryId, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, long accountEntryId,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBAccountEntryPermission.contains(
					permissionChecker, accountEntryId, actionId));
		}
	}

}