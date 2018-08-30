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
import com.liferay.osb.model.impl.CorpProjectImpl;
import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteCorpProjectLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class RemoteCorpProjectLocalServiceImpl
	extends RemoteCorpProjectLocalServiceBaseImpl {

	@Override
	public CorpProject addCorpProject(
			long creatorUserId, long ownerUserId, String dossieraProjectKey,
			String salesforceProjectKey, String name)
		throws PortalException {

		User creatorUser = userLocalService.getUser(creatorUserId);

		String ownerUserUuid = StringPool.BLANK;

		if (ownerUserId > 0) {
			User ownerUser = userLocalService.getUser(ownerUserId);

			ownerUserUuid = ownerUser.getUuid();
		}

		JSONObject jsonObject = WebRESTWebServiceUtil.postCorpProjects(
			creatorUser.getUuid(), ownerUserUuid, dossieraProjectKey,
			salesforceProjectKey, name);

		CorpProject corpProject = new CorpProjectImpl();

		corpProject.setCreateDate(new Date(jsonObject.getLong("createDate")));
		corpProject.setDossieraProjectKey(
			jsonObject.getString("dossieraProjectKey"));
		corpProject.setModifiedDate(
			new Date(jsonObject.getLong("modifiedDate")));
		corpProject.setName(jsonObject.getString("name"));

		JSONObject organizationJSONObject = jsonObject.getJSONObject(
			"organization");

		if (organizationJSONObject != null) {
			corpProject.setOrganizationUuid(
				organizationJSONObject.getString("uuid"));
		}

		corpProject.setSalesforceProjectKey(
			jsonObject.getString("salesforceProjectKey"));

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		if (userJSONObject != null) {
			User user = userLocalService.fetchUserByUuidAndCompanyId(
				userJSONObject.getString("uuid"), OSBConstants.COMPANY_ID);

			if (user != null) {
				corpProject.setUserId(user.getUserId());
				corpProject.setUserName(user.getFullName());
			}
		}

		corpProject.setUuid(jsonObject.getString("uuid"));

		return corpProject;
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		addCorpProjectUsers(corpProject.getUuid(), userIds);
	}

	@Override
	public void addCorpProjectUsers(String corpProjectUuid, long[] userIds)
		throws PortalException {

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putCorpProjectsUser(
				corpProjectUuid, user.getUuid());
		}
	}

	@Override
	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, long roleId)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		addUserCorpProjectRoles(corpProject.getUuid(), userIds, roleId);
	}

	@Override
	public void addUserCorpProjectRoles(
			String corpProjectUuid, long[] userIds, long roleId)
		throws PortalException {

		Role role = roleLocalService.getRole(roleId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putCorpProjectsUserRole(
				corpProjectUuid, user.getUuid(), role.getUuid());
		}
	}

	@Override
	public void deleteCorpProject(long corpProjectId) throws PortalException {
		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		WebRESTWebServiceUtil.deleteCorpProjects(corpProject.getUuid());
	}

	@Override
	public void updateCorpProject(long corpProjectId, String name)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		WebRESTWebServiceUtil.putCorpProjects(corpProject.getUuid(), name);
	}

}