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

import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Amos Fong
 */
public class OSBOfferingEntryPermissionTest extends BasePermissionTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		long accountEntryId = accountEntry.getAccountEntryId();

		when(
			offeringEntry.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);
	}

	@Test
	public void testAccountCustomerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerDeveloper.getUserId());

		assertFalse(permissionChecker, offeringEntry, OSBActionKeys.UPDATE);
		assertTrue(permissionChecker, offeringEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerManager.getUserId());

		assertFalse(permissionChecker, offeringEntry, OSBActionKeys.UPDATE);
		assertTrue(permissionChecker, offeringEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerNoRole.getUserId());

		assertFalse(permissionChecker, offeringEntry, OSBActionKeys.UPDATE);
		assertTrue(permissionChecker, offeringEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerSales.getUserId());

		assertFalse(permissionChecker, offeringEntry, OSBActionKeys.UPDATE);
		assertTrue(permissionChecker, offeringEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerWatcher.getUserId());

		assertFalse(permissionChecker, offeringEntry, OSBActionKeys.UPDATE);
		assertTrue(permissionChecker, offeringEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerAdvocacySpecialistPermissions()
		throws Exception {

		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerAdvocacySpecialist.getUserId());

		assertTrue(
			permissionChecker, offeringEntry, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerManagedServicesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerManagedServices.getUserId());

		assertFalse(permissionChecker, offeringEntry, OSBActionKeys.UPDATE);
		assertTrue(permissionChecker, offeringEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerNoRole.getUserId());

		assertFalse(permissionChecker, offeringEntry, OSBActionKeys.UPDATE);
		assertTrue(permissionChecker, offeringEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerSales.getUserId());

		assertTrue(
			permissionChecker, offeringEntry, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker, offeringEntry, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testOSBAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertTrue(
			permissionChecker, offeringEntry, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, OfferingEntry offeringEntry,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBOfferingEntryPermission.contains(
					permissionChecker, offeringEntry, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, OfferingEntry offeringEntry,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBOfferingEntryPermission.contains(
					permissionChecker, offeringEntry, actionId));
		}
	}

	protected OfferingEntry offeringEntry = mock(OfferingEntry.class);

}