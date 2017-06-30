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
import com.liferay.osb.service.permission.OSBCorpPermission;
import com.liferay.osb.service.permission.OSBCorpProjectPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionCheckerFactoryUtil;

import java.util.List;

/**
 * @author Joan Kim
 * @author Igor Beslic
 */
@JSONWebService
public class CorpProjectServiceImpl extends CorpProjectServiceBaseImpl {

	public CorpProject addCorpProject(String name)
		throws PortalException, SystemException {

		OSBCorpPermission.check(
			getPermissionChecker(), OSBActionKeys.ADD_CORP_PROJECT);

		return corpProjectLocalService.addCorpProject(
			getUserId(), getUserId(), StringPool.BLANK, StringPool.BLANK, name);
	}

	public CorpProject addCorpProject(String userUuid, String name)
		throws PortalException, SystemException {

		OSBCorpPermission.check(getPermissionChecker(), OSBActionKeys.MANAGE);

		User user = userLocalService.getUserByUuid(userUuid);

		PermissionChecker permissionChecker = null;

		try {
			permissionChecker = PermissionCheckerFactoryUtil.create(user);
		}
		catch (Exception e) {
		}

		OSBCorpPermission.check(
			permissionChecker, OSBActionKeys.ADD_CORP_PROJECT);

		return corpProjectLocalService.addCorpProject(
			user.getUserId(), user.getUserId(), StringPool.BLANK,
			StringPool.BLANK, name);
	}

	public CorpProject addCorpProject(
			String dossieraProjectKey, String salesforceProjectKey, String name)
		throws PortalException, SystemException {

		OSBCorpPermission.check(
			getPermissionChecker(), OSBActionKeys.ADD_CORP_PROJECT);

		return corpProjectLocalService.addCorpProject(
			getUserId(), 0, dossieraProjectKey, salesforceProjectKey, name);
	}

	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId,
			OSBActionKeys.ASSIGN_MEMBERS);

		corpProjectLocalService.addCorpProjectUsers(corpProjectId, userIds);
	}

	public void addCorpProjectUsers(long corpProjectId, String[] userUuids)
		throws PortalException, SystemException {

		addCorpProjectUsers(corpProjectId, getUserIds(userUuids));
	}

	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, long roleId)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId,
			OSBActionKeys.ASSIGN_CORP_PROJECT_ROLES);

		corpProjectLocalService.addUserCorpProjectRoles(
			getUserId(), corpProjectId, userIds, roleId);
	}

	public void addUserCorpProjectRoles(
			long corpProjectId, String[] userUuids, String roleName)
		throws PortalException, SystemException {

		Role role = roleLocalService.getRole(OSBConstants.COMPANY_ID, roleName);

		addUserCorpProjectRoles(
			corpProjectId, getUserIds(userUuids), role.getRoleId());
	}

	public CorpProject deleteCorpProject(long corpProjectId)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId,
			OSBActionKeys.DELETE_CORP_PROJECT);

		return corpProjectLocalService.deleteCorpProject(corpProjectId);
	}

	public void deleteUserCorpProjectRoles(
			long corpProjectId, long[] userIds, long roleId)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId,
			OSBActionKeys.ASSIGN_CORP_PROJECT_ROLES);

		corpProjectLocalService.deleteUserCorpProjectRoles(
			getUserId(), corpProjectId, userIds, roleId);
	}

	public void deleteUserCorpProjectRoles(
			long corpProjectId, String[] userUuids, long roleId)
		throws PortalException, SystemException {

		deleteUserCorpProjectRoles(
			corpProjectId, getUserIds(userUuids), roleId);
	}

	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException, SystemException {

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProject, OSBActionKeys.VIEW);

		return corpProject;
	}

	public List<CorpProject> getUserCorpProjects(
			String userUuid, String roleName)
		throws PortalException, SystemException {

		User user = userLocalService.getUserByUuid(userUuid);

		return corpProjectLocalService.getUserCorpProjects(
			user.getUserId(), roleName);
	}

	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId, OSBActionKeys.VIEW);

		return corpProjectLocalService.hasUserCorpProject(
			userId, corpProjectId);
	}

	public boolean hasUserCorpProject(String userUuid, long corpProjectId)
		throws PortalException, SystemException {

		User user = userLocalService.getUserByUuid(userUuid);

		return corpProjectLocalService.hasUserCorpProject(
			user.getUserId(), corpProjectId);
	}

	public boolean hasUserCorpProjectRole(
			String userUuid, long corpProjectId, String roleName)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId, OSBActionKeys.VIEW);

		User user = userLocalService.getUserByUuid(userUuid);
		Role role = roleLocalService.getRole(OSBConstants.COMPANY_ID, roleName);

		return corpProjectLocalService.hasUserCorpProjectRole(
			user.getUserId(), corpProjectId, role.getRoleId());
	}

	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProject, userIds,
			OSBActionKeys.ASSIGN_MEMBERS);

		corpProjectLocalService.unsetCorpProjectUsers(corpProjectId, userIds);
	}

	public void unsetCorpProjectUsers(long corpProjectId, String[] userUuids)
		throws PortalException, SystemException {

		unsetCorpProjectUsers(corpProjectId, getUserIds(userUuids));
	}

	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId, OSBActionKeys.UPDATE);

		return corpProjectLocalService.updateCorpProject(corpProjectId, name);
	}

	public CorpProject updateCorpProject(
			long corpProjectId, String dossieraProjectKey,
			String salesforceProjectKey, String name, long accountEntryId)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId, OSBActionKeys.UPDATE);

		return corpProjectLocalService.updateCorpProject(
			corpProjectId, dossieraProjectKey, salesforceProjectKey, name,
			accountEntryId);
	}

	protected long[] getUserIds(String[] userUuids)
		throws PortalException, SystemException {

		long[] userIds = new long[userUuids.length];

		for (int i = 0; i < userUuids.length; i++) {
			User user = userLocalService.getUserByUuid(userUuids[i]);

			userIds[i] = user.getUserId();
		}

		return userIds;
	}

}