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
public class OSBPartnerEntryPermissionTest extends BasePermissionTestCase {

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker,
			partnerManagedSupportAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);
	}

	@Test
	public void testLiferayIncEmployeePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_LIFERAY_EMPLOYEE_ID);

		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);
	}

	@Test
	public void testOSBAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerManager.getUserId());

		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerMemberPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerMember.getUserId());

		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerNoRole.getUserId());

		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerWatcher.getUserId());

		assertTrue(
			permissionChecker,
			partnerManagedSupportAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedAccountEntry.getPartnerEntryId(),
			OSBActionKeys.VIEW);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, long partnerEntryId,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBPartnerEntryPermission.contains(
					permissionChecker, partnerEntryId, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, long partnerEntryId,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBPartnerEntryPermission.contains(
					permissionChecker, partnerEntryId, actionId));
		}
	}

}