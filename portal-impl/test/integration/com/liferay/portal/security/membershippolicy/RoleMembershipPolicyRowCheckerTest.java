/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portlet.rolesadmin.search.SetUserRoleChecker;
import com.liferay.portlet.rolesadmin.search.UnsetUserRoleChecker;

import javax.portlet.RenderResponse;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;

/**
 * @author Roberto Díaz
 */
public class RoleMembershipPolicyRowCheckerTest
	extends BaseRoleMembershipPolicyTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testIsCheckerDisabledWhenSettingForbiddenRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenRoleId = addForbiddenRoles()[0];

		Role forbiddenRole = RoleLocalServiceUtil.getRole(forbiddenRoleId);

		SetUserRoleChecker setUserRoleChecker = new SetUserRoleChecker(
			renderResponse, forbiddenRole);

		User user = UserTestUtil.addUser();

		Assert.assertTrue(setUserRoleChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenSettingRequiredRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredRoleId = addRequiredRoles()[0];

		Role requiredRole = RoleLocalServiceUtil.getRole(requiredRoleId);

		SetUserRoleChecker setUserRoleChecker = new SetUserRoleChecker(
			renderResponse, requiredRole);

		User user = UserTestUtil.addUser();

		Assert.assertFalse(setUserRoleChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingForbiddenRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long forbiddenRoleId = addForbiddenRoles()[0];

		Role forbiddenRole = RoleLocalServiceUtil.getRole(forbiddenRoleId);

		UnsetUserRoleChecker unsetUserRoleChecker = new UnsetUserRoleChecker(
			renderResponse, forbiddenRole);

		User user = UserTestUtil.addUser();

		RoleLocalServiceUtil.addUserRole(user.getUserId(), forbiddenRoleId);

		Assert.assertFalse(unsetUserRoleChecker.isDisabled(user));
	}

	@Test
	public void testIsCheckerDisabledWhenUnsettingRequiredRoleToUser()
		throws Exception {

		RenderResponse renderResponse = PowerMockito.mock(RenderResponse.class);

		long requiredRoleId = addRequiredRoles()[0];

		Role requiredRole = RoleLocalServiceUtil.getRole(requiredRoleId);

		UnsetUserRoleChecker unsetUserRoleChecker = new UnsetUserRoleChecker(
			renderResponse, requiredRole);

		User user = UserTestUtil.addUser();

		RoleLocalServiceUtil.addUserRole(user.getUserId(), requiredRoleId);

		Assert.assertTrue(unsetUserRoleChecker.isDisabled(user));
	}

}