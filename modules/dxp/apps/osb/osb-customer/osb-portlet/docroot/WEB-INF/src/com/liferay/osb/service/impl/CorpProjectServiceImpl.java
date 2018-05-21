/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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