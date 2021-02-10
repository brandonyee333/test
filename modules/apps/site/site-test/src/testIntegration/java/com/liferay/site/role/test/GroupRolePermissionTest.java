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

package com.liferay.site.role.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.layout.test.util.LayoutTestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Arthur Chan
 */
@RunWith(Arquillian.class)
@Sync(cleanTransaction = true)
public class GroupRolePermissionTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws Exception {
		_parentGroup = GroupTestUtil.addGroup();

		_childGroup = GroupTestUtil.addGroup(_parentGroup.getGroupId());

		_name = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(TestPropsValues.getUserId());
	}

	@After
	public void tearDown() {
		PrincipalThreadLocal.setName(_name);
	}

	@Test
	public void testAddLayoutToChildSite() throws Exception {
		_user = UserTestUtil.addUser(null, _parentGroup.getGroupId());

		_giveSiteAdminRole(_parentGroup);

		try {
			LayoutTestUtil.addLayout(_childGroup);
		}
		catch (Exception exception) {
			_enableManageSubgroups();

			LayoutTestUtil.addLayout(_childGroup);

			return;
		}

		throw new PrincipalException(
			"Parent site admin should not be able to add page to child" +
				"site without MANAGE_SUBGROUPS permission");
	}

	private void _enableManageSubgroups() throws Exception {
		_resourcePermissionService.addResourcePermission(
			_parentGroup.getGroupId(), _parentGroup.getCompanyId(),
			Group.class.getName(), ResourceConstants.SCOPE_GROUP_TEMPLATE,
			String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID),
			_role.getRoleId(), ActionKeys.MANAGE_SUBGROUPS);
	}

	private void _giveSiteAdminRole(Group group) throws Exception {
		_role = _roleLocalService.getRole(
			TestPropsValues.getCompanyId(), RoleConstants.SITE_ADMINISTRATOR);

		_userGroupRoleLocalService.addUserGroupRoles(
			_user.getUserId(), group.getGroupId(),
			new long[] {_role.getRoleId()});
	}

	@Inject
	private static RoleLocalService _roleLocalService;

	private Group _childGroup;
	private String _name;
	private Group _parentGroup;

	@Inject
	private ResourcePermissionService _resourcePermissionService;

	@DeleteAfterTestRun
	private Role _role;

	@DeleteAfterTestRun
	private User _user;

	@Inject
	private UserGroupRoleLocalService _userGroupRoleLocalService;

}