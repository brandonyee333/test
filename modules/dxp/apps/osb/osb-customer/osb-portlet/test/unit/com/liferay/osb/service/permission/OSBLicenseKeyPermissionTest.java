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

import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Amos Fong
 */
public class OSBLicenseKeyPermissionTest extends BasePermissionTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		long accountEntryId = accountEntry.getAccountEntryId();

		when(
			licenseKey.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			licenseKey.isActive()
		).thenReturn(
			true
		);

		when(
			inactiveLicenseKey.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			inactiveLicenseKey.isActive()
		).thenReturn(
			false
		);

		long partnerManagedSupportAccountEntryId =
			partnerManagedSupportAccountEntry.getAccountEntryId();

		when(
			partnerLicenseKey.getAccountEntryId()
		).thenReturn(
			partnerManagedSupportAccountEntryId
		);

		when(
			partnerLicenseKey.isActive()
		).thenReturn(
			true
		);

		long restrictedAccountEntryId =
			restrictedAccountEntry.getAccountEntryId();

		when(
			restrictedLicenseKey.getAccountEntryId()
		).thenReturn(
			restrictedAccountEntryId
		);

		when(
			restrictedLicenseKey.isActive()
		).thenReturn(
			true
		);

		when(
			trialLicenseKey.getAccountEntry()
		).thenReturn(
			trialAccountEntry
		);

		when(
			trialLicenseKey.isActive()
		).thenReturn(
			true
		);
	}

	@Test
	public void testAccountCustomerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerDeveloper.getUserId());

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC);
		assertTrue(permissionChecker, licenseKey, OSBActionKeys.VIEW);

		assertFalse(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerManager.getUserId());

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC);
		assertTrue(permissionChecker, licenseKey, OSBActionKeys.VIEW);

		assertFalse(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerNoRole.getUserId());

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC);
		assertTrue(permissionChecker, licenseKey, OSBActionKeys.VIEW);

		assertFalse(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerSales.getUserId());

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC);
		assertTrue(permissionChecker, licenseKey, OSBActionKeys.VIEW);

		assertFalse(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerWatcher.getUserId());

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC);
		assertTrue(permissionChecker, licenseKey, OSBActionKeys.VIEW);

		assertFalse(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerAdvocacySpecialistPermissions()
		throws Exception {

		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerAdvocacySpecialist.getUserId());

		assertFalse(permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN);
		assertTrue(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.VIEW);

		assertTrue(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerManagedServicesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerManagedServices.getUserId());

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED);
		assertTrue(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);

		assertFalse(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED);
		assertTrue(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);

		assertFalse(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerSales.getUserId());

		assertFalse(permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN);
		assertTrue(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.VIEW);

		assertTrue(permissionChecker, inactiveLicenseKey, OSBActionKeys.VIEW);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testLiferayIncEmployeePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_LIFERAY_EMPLOYEE_ID);

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testOSBAccountAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ACCOUNT_ADMIN_ID);

		assertTrue(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, inactiveLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testOSBAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertTrue(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, inactiveLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testOSBTrialLicenseAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_TRIAL_LICENSE_ADMIN_ID);

		assertTrue(
			permissionChecker, trialLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, inactiveLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerManager.getUserId());

		assertFalse(
			permissionChecker, partnerLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerMemberPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerMember.getUserId());

		assertFalse(
			permissionChecker, partnerLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, partnerLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerWatcher.getUserId());

		assertFalse(
			permissionChecker, partnerLicenseKey, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, LicenseKey licenseKey,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBLicenseKeyPermission.contains(
					permissionChecker, licenseKey, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, LicenseKey licenseKey,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBLicenseKeyPermission.contains(
					permissionChecker, licenseKey, actionId));
		}
	}

	protected LicenseKey inactiveLicenseKey = mock(LicenseKey.class);
	protected LicenseKey licenseKey = mock(LicenseKey.class);
	protected LicenseKey partnerLicenseKey = mock(LicenseKey.class);
	protected LicenseKey restrictedLicenseKey = mock(LicenseKey.class);
	protected LicenseKey trialLicenseKey = mock(LicenseKey.class);

}