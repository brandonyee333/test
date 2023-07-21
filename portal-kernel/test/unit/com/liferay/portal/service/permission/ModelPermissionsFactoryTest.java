/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.permission;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.service.permission.ModelPermissionsFactory;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Jorge Ferrer
 */
@PrepareForTest(RoleLocalServiceUtil.class)
@RunWith(PowerMockRunner.class)
public class ModelPermissionsFactoryTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		mockStatic(RoleLocalServiceUtil.class);

		Role guestRole = Mockito.mock(Role.class);

		Mockito.when(
			guestRole.getName()
		).thenReturn(
			RoleConstants.GUEST
		);

		when(
			RoleLocalServiceUtil.getRole(
				Mockito.anyLong(), Mockito.eq(RoleConstants.GUEST))
		).thenReturn(
			guestRole
		);

		Role siteMemberRole = Mockito.mock(Role.class);

		Mockito.when(
			siteMemberRole.getName()
		).thenReturn(
			RoleConstants.SITE_MEMBER
		);

		when(
			RoleLocalServiceUtil.getDefaultGroupRole(Mockito.anyLong())
		).thenReturn(
			siteMemberRole
		);

		when(
			RoleLocalServiceUtil.getRole(
				Mockito.anyLong(), Mockito.eq(RoleConstants.SITE_MEMBER))
		).thenReturn(
			siteMemberRole
		);
	}

	@Test
	public void testCreateWithEmptyPermissions() throws Exception {
		String[] groupPermissions = {};
		String[] guestPermissions = {};

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 0, roleNames.size());
	}

	@Test
	public void testCreateWithGroupPermissions() throws Exception {
		String[] groupPermissions = {ActionKeys.VIEW};
		String[] guestPermissions = {};

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 1, roleNames.size());

		Iterator<String> iterator = roleNames.iterator();

		String roleName = iterator.next();

		Assert.assertEquals(
			RoleConstants.PLACEHOLDER_DEFAULT_GROUP_ROLE, roleName);
		Assert.assertEquals(
			ListUtil.fromArray(groupPermissions),
			modelPermissions.getActionIdsList(roleName));
	}

	@Test
	public void testCreateWithGuestAndGroupPermissions() throws Exception {
		String[] groupPermissions = {ActionKeys.VIEW};
		String[] guestPermissions = {ActionKeys.VIEW};

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 2, roleNames.size());

		Collection<String> viewActionIdRoleNames =
			modelPermissions.getRoleNames(ActionKeys.VIEW);

		Assert.assertEquals(
			viewActionIdRoleNames.toString(), 2, viewActionIdRoleNames.size());
	}

	@Test
	public void testCreateWithGuestPermissions() throws Exception {
		String[] groupPermissions = {};
		String[] guestPermissions = {ActionKeys.VIEW};

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 1, roleNames.size());

		Iterator<String> iterator = roleNames.iterator();

		String roleName = iterator.next();

		Assert.assertEquals(RoleConstants.GUEST, roleName);
		Assert.assertArrayEquals(
			guestPermissions, modelPermissions.getActionIds(roleName));
	}

	@Test
	public void testCreateWithNullPermissions() throws Exception {
		String[] groupPermissions = null;
		String[] guestPermissions = null;

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			groupPermissions, guestPermissions);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertTrue(roleNames.toString(), roleNames.isEmpty());
	}

	@Test
	public void testCreateWithoutParameters() throws Exception {
		Map<String, String[]> parameterMap = new HashMap<>();

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			parameterMap);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 0, roleNames.size());
	}

	@Test
	public void testCreateWithParameterForOneRole() throws Exception {
		Map<String, String[]> parameterMap = new HashMap<>();

		String[] permissions = {ActionKeys.VIEW};

		parameterMap.put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.GUEST,
			permissions);

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			parameterMap);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 1, roleNames.size());

		Iterator<String> iterator = roleNames.iterator();

		String roleName = iterator.next();

		Assert.assertEquals(RoleConstants.GUEST, roleName);
		Assert.assertArrayEquals(
			permissions, modelPermissions.getActionIds(roleName));
	}

	@Test
	public void testCreateWithParameterForTwoRoles() throws Exception {
		Map<String, String[]> parameterMap = new HashMap<>();

		String[] permissions = {ActionKeys.VIEW};

		parameterMap.put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.GUEST,
			permissions);
		parameterMap.put(
			ModelPermissionsFactory.MODEL_PERMISSIONS_PREFIX +
				RoleConstants.SITE_MEMBER,
			permissions);

		ModelPermissions modelPermissions = ModelPermissionsFactory.create(
			parameterMap);

		Collection<String> roleNames = modelPermissions.getRoleNames();

		Assert.assertEquals(roleNames.toString(), 2, roleNames.size());

		Iterator<String> iterator = roleNames.iterator();

		String roleName = iterator.next();

		Assert.assertArrayEquals(
			permissions, modelPermissions.getActionIds(roleName));
	}

}