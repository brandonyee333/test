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

package com.liferay.osb.service.impl;

import com.liferay.osb.hook.model.impl.RemoteUserImpl;
import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteUserLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class RemoteUserLocalServiceImpl extends RemoteUserLocalServiceBaseImpl {

	@Override
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putOrganizationsUser(
				organization.getUuid(), user.getUuid());
		}
	}

	@Override
	public void addRoleUsers(long roleId, long[] userIds)
		throws PortalException {

		Role role = roleLocalService.getRole(roleId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putRolesUser(role.getUuid(), user.getUuid());
		}
	}

	@Override
	public void deleteRoleUser(long roleId, long userId)
		throws PortalException {

		Role role = roleLocalService.getRole(roleId);
		User user = userLocalService.getUser(userId);

		WebRESTWebServiceUtil.deleteRolesUser(role.getUuid(), user.getUuid());
	}

	@Override
	public User fetchUserByEmailAddress(String emailAddress)
		throws PortalException {

		JSONObject jsonObject = WebRESTWebServiceUtil.getUsersEmailAddress(
			emailAddress);

		if (jsonObject == null) {
			return null;
		}

		User user = userLocalService.createUser(0);

		RemoteUserImpl remoteUser = new RemoteUserImpl(user);

		remoteUser.setCreateDate(new Date(jsonObject.getLong("createDate")));
		remoteUser.setEmailAddress(jsonObject.getString("emailAddress"));
		remoteUser.setFirstName(jsonObject.getString("firstName"));
		remoteUser.setLanguageId(jsonObject.getString("languageId"));
		remoteUser.setLastName(jsonObject.getString("lastName"));
		remoteUser.setMiddleName(jsonObject.getString("middleName"));

		List<Organization> organizations = new ArrayList<>();

		JSONArray organizationsJSONArray = jsonObject.getJSONArray(
			"organizations");

		for (int i = 0; i < organizationsJSONArray.length(); i++) {
			JSONObject organizationJSONObject =
				organizationsJSONArray.getJSONObject(i);

			Organization organization =
				organizationLocalService.fetchOrganizationByUuidAndCompanyId(
					organizationJSONObject.getString("uuid"),
					OSBConstants.COMPANY_ID);

			if (organization != null) {
				organizations.add(organization);
			}
		}

		remoteUser.setOrganizations(organizations);

		List<Role> roles = new ArrayList<>();

		JSONArray rolesJSONArray = jsonObject.getJSONArray("roles");

		for (int i = 0; i < rolesJSONArray.length(); i++) {
			JSONObject roleJSONObject = rolesJSONArray.getJSONObject(i);

			Role role = roleLocalService.fetchRoleByUuidAndCompanyId(
				roleJSONObject.getString("uuid"), OSBConstants.COMPANY_ID);

			if (role != null) {
				roles.add(role);
			}
		}

		remoteUser.setRoles(roles);

		remoteUser.setScreenName(jsonObject.getString("screenName"));
		remoteUser.setUuid(jsonObject.getString("uuid"));

		return remoteUser;
	}

	@Override
	public void unsetOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException {

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.deleteOrganizationsUser(
				organization.getUuid(), user.getUuid());
		}
	}

}