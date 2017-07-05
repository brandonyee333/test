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

import com.liferay.osb.exception.NoSuchSupportWorkerException;
import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.apache.commons.lang.math.RandomUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Amos Fong
 */
public class OSBSupportTeamPermissionTest extends BasePermissionTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		long supportTeamId = RandomUtils.nextLong();

		when(
			supportTeam.getSupportTeamId()
		).thenReturn(
			supportTeamId
		);

		long restrictedSupportTeamId = RandomUtils.nextLong();

		when(
			restrictedSupportTeam.getSupportTeamId()
		).thenReturn(
			restrictedSupportTeamId
		);

		when(
			SupportWorkerLocalServiceUtil.getSupportWorker(
				0, restrictedSupportTeamId)
		).thenThrow(
			new NoSuchSupportWorkerException()
		);

		when(
			SupportWorkerLocalServiceUtil.getSupportWorker(
				supportWorkerDeveloper.getUserId(), restrictedSupportTeamId)
		).thenThrow(
			new NoSuchSupportWorkerException()
		);

		when(
			SupportWorkerLocalServiceUtil.getSupportWorker(
				supportWorkerDeveloper.getUserId(), supportTeamId)
		).thenReturn(
			supportWorkerDeveloper
		);

		when(
			SupportWorkerLocalServiceUtil.getSupportWorker(
				supportWorkerManager.getUserId(), restrictedSupportTeamId)
		).thenThrow(
			new NoSuchSupportWorkerException()
		);

		when(
			SupportWorkerLocalServiceUtil.getSupportWorker(
				supportWorkerManager.getUserId(), supportTeamId)
		).thenReturn(
			supportWorkerManager
		);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(permissionChecker, restrictedSupportTeam);
	}

	@Test
	public void testOSBAdministrator() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertTrue(permissionChecker, supportTeam);

		assertTrue(permissionChecker, restrictedSupportTeam);
	}

	@Test
	public void testSupportWorkerDeveloper() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerDeveloper.getUserId());

		assertFalse(permissionChecker, supportTeam);

		assertFalse(permissionChecker, restrictedSupportTeam);
	}

	@Test
	public void testSupportWorkerManager() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerManager.getUserId());

		assertTrue(permissionChecker, supportTeam);

		assertFalse(permissionChecker, restrictedSupportTeam);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, SupportTeam supportTeam)
		throws Exception {

		Assert.assertFalse(
			OSBSupportTeamPermission.contains(permissionChecker, supportTeam));
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, SupportTeam supportTeam)
		throws Exception {

		Assert.assertTrue(
			OSBSupportTeamPermission.contains(permissionChecker, supportTeam));
	}

	protected SupportTeam restrictedSupportTeam = mock(SupportTeam.class);
	protected SupportTeam supportTeam = mock(SupportTeam.class);

}