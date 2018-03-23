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
import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.RemoteCorpProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;

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

		return corpProjectLocalService.addCorpProject(jsonObject);
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putCorpProjectsUser(
				corpProject.getUuid(), user.getUuid());
		}
	}

	@Override
	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, long roleId)
		throws PortalException {

		CorpProject corpProject = corpProjectLocalService.getCorpProject(
			corpProjectId);

		Role role = roleLocalService.getRole(roleId);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putCorpProjectsUserRole(
				corpProject.getUuid(), user.getUuid(), role.getUuid());
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

		JSONObject jsonObject = WebRESTWebServiceUtil.putCorpProjects(
			corpProject.getUuid(), name);

		corpProjectLocalService.updateCorpProject(jsonObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RemoteCorpProjectLocalServiceImpl.class);

}