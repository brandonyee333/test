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

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.base.CorpProjectServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Amos Fong
 */
@JSONWebService
public class CorpProjectServiceImpl extends CorpProjectServiceBaseImpl {

	public CorpProject addCorpProject(
			String userUuid, String dossieraProjectKey,
			String salesforceProjectKey, String name, long organizationId,
			ServiceContext serviceContext)
		throws PortalException {

		validateJSONWebServicePermissions();

		User user = userLocalService.getUserByUuidAndCompanyId(
			userUuid, OSBConstants.COMPANY_ID);

		return corpProjectLocalService.addCorpProject(
			user.getUserId(), dossieraProjectKey, salesforceProjectKey, name,
			organizationId, serviceContext);
	}

	public void addUserCorpProjectRoles(
			long organizationId, long userId, long roleId)
		throws PortalException {

		validateJSONWebServicePermissions();

		Organization organization = organizationLocalService.getOrganization(
			organizationId);

		userGroupRoleLocalService.addUserGroupRoles(
			new long[] {userId}, organization.getGroupId(), roleId);
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

}