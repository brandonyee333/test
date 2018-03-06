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

import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteUserLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;

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