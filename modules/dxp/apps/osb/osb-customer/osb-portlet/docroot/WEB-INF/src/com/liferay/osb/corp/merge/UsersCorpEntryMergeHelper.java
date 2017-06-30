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

package com.liferay.osb.corp.merge;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Douglas Wong
 */
public class UsersCorpEntryMergeHelper extends BaseCorpEntryMergeHelper {

	protected void doRun() throws Exception {
		List<User> users = UserLocalServiceUtil.getOrganizationUsers(
			corpEntry.getOrganizationId());

		List<User> mergeUsers = UserLocalServiceUtil.getOrganizationUsers(
			mergeCorpEntry.getOrganizationId());

		for (User mergeUser : mergeUsers) {
			if (!users.contains(mergeUser)) {
				UserLocalServiceUtil.addOrganizationUsers(
					corpEntry.getOrganizationId(),
					new long[] {mergeUser.getUserId()});
			}

			mergeRoles(mergeUser.getUserId());
		}
	}

	protected void mergeRoles(long userId) throws Exception {
		Organization mergeOrganization = mergeCorpEntry.getOrganization();

		List<UserGroupRole> mergeUserGroupRoles =
			UserGroupRoleLocalServiceUtil.getUserGroupRoles(
				userId, mergeOrganization.getGroupId());

		long[] roleIds = new long[0];

		for (UserGroupRole mergeUserGroupRole : mergeUserGroupRoles) {
			roleIds = ArrayUtil.append(roleIds, mergeUserGroupRole.getRoleId());
		}

		Organization organization = corpEntry.getOrganization();

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			userId, organization.getGroupId(), roleIds);
	}

}