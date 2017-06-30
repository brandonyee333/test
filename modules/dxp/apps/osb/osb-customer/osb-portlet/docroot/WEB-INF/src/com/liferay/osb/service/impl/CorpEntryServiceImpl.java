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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.osb.CorpEntryAdminException;
import com.liferay.osb.CorpEntryRoleException;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.base.CorpEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBCorpEntryPermission;
import com.liferay.osb.service.permission.OSBCorpPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.io.File;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
@JSONWebService
public class CorpEntryServiceImpl extends CorpEntryServiceBaseImpl {

	public CorpEntry addCorpEntry(
			String name, Map<Locale, String> descriptionMap, File logoFile,
			String street1, String street2, String street3, String city,
			String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
			String dossieraAccountKey, ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBCorpPermission.check(
			getPermissionChecker(), OSBActionKeys.ADD_CORP_ENTRY);

		return corpEntryLocalService.addCorpEntry(
			getUserId(), name, descriptionMap, logoFile, street1, street2,
			street3, city, zip, regionId, countryId, contactEmailAddress,
			profileEmailAddress, phoneNumber, faxNumber, website,
			dossieraAccountKey, serviceContext);
	}

	public void addCorpEntryUsers(long corpEntryId, long[] userIds)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntry, OSBActionKeys.ASSIGN_MEMBERS);

		userLocalService.addOrganizationUsers(
			corpEntry.getOrganizationId(), userIds);
	}

	public void addUserCorpEntryRoles(
			long corpEntryId, long[] userIds, long roleId)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntry,
			OSBActionKeys.ASSIGN_CORP_ENTRY_ROLES);

		validate(corpEntry, userIds, roleId);

		Group group = corpEntry.getGroup();

		userGroupRoleLocalService.addUserGroupRoles(
			userIds, group.getGroupId(), roleId);

		if (!corpEntry.isApproved()) {
			return;
		}

		DeveloperEntry developerEntry =
			developerEntryLocalService.fetchDeveloperEntry(
				corpEntry.getDossieraAccountKey());

		if ((developerEntry == null) || !developerEntry.isApproved()) {
			return;
		}

		if ((roleId == OSBConstants.ROLE_OSB_CORP_ADMIN_ID) ||
			(roleId == OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID)) {

			userLocalService.addGroupUsers(
				OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID, userIds);
		}
	}

	public void addUserCorpEntryRoles(
			long corpEntryId, String[] userUuids, String roleName)
		throws PortalException, SystemException {

		long[] userIds = new long[userUuids.length];

		for (int i = 0; i < userUuids.length; i++) {
			User user = userLocalService.getUserByUuid(userUuids[i]);

			userIds[i] = user.getUserId();
		}

		Role role = roleLocalService.getRole(OSBConstants.COMPANY_ID, roleName);

		addUserCorpEntryRoles(corpEntryId, userIds, role.getRoleId());
	}

	public CorpEntry deleteCorpEntry(long corpEntryId)
		throws PortalException, SystemException {

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntryId, OSBActionKeys.UPDATE);

		return corpEntryLocalService.deleteCorpEntry(corpEntryId);
	}

	public void deleteUserCorpEntryRoles(
			long corpEntryId, long[] userIds, long roleId)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntry,
			OSBActionKeys.ASSIGN_CORP_ENTRY_ROLES);

		validate(corpEntry, userIds, roleId);

		Group group = corpEntry.getGroup();

		userGroupRoleLocalService.deleteUserGroupRoles(
			userIds, group.getGroupId(), roleId);
	}

	public CorpEntry getCorpEntry(long corpEntryId)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryLocalService.getCorpEntry(corpEntryId);

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntry, OSBActionKeys.VIEW);

		return corpEntry;
	}

	public boolean hasUserCorpEntry(long userId, long corpEntryId)
		throws PortalException, SystemException {

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntryId, OSBActionKeys.VIEW);

		return corpEntryLocalService.hasUserCorpEntry(userId, corpEntryId);
	}

	public CorpEntry mergeCorpEntry(
			long corpEntryId, long mergeCorpEntryId, String name,
			Map<Locale, String> descriptionMap, long logoId, String street1,
			String street2, String street3, String city, String zip,
			long regionId, long countryId, String contactEmailAddress,
			String profileEmailAddress, String phoneNumber, String faxNumber,
			String website, String dossieraAccountKey,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntryId, OSBActionKeys.UPDATE);

		return corpEntryLocalService.mergeCorpEntry(
			getUserId(), corpEntryId, mergeCorpEntryId, name, descriptionMap,
			logoId, street1, street2, street3, city, zip, regionId, countryId,
			contactEmailAddress, profileEmailAddress, phoneNumber, faxNumber,
			website, dossieraAccountKey, serviceContext);
	}

	public void unsetCorpEntryUsers(long corpEntryId, long[] userIds)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		validate(corpEntry, userIds);

		if ((userIds.length != 1) || (userIds[0] != getUserId())) {
			OSBCorpEntryPermission.check(
				getPermissionChecker(), corpEntry,
				OSBActionKeys.ASSIGN_MEMBERS);
		}

		userLocalService.unsetOrganizationUsers(
			corpEntry.getOrganizationId(), userIds);
	}

	public CorpEntry updateCorpEntry(
			long corpEntryId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String street1, String street2, String street3,
			String city, String zip, long regionId, long countryId,
			String contactEmailAddress, String profileEmailAddress,
			String phoneNumber, String faxNumber, String website,
			String dossieraAccountKey, ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntryId, OSBActionKeys.UPDATE);

		return corpEntryLocalService.updateCorpEntry(
			corpEntryId, name, descriptionMap, logoFile, street1, street2,
			street3, city, zip, regionId, countryId, contactEmailAddress,
			profileEmailAddress, phoneNumber, faxNumber, website,
			dossieraAccountKey, serviceContext);
	}

	public CorpEntry updateStatus(
			long corpEntryId, int status, String statusMessage)
		throws PortalException, SystemException {

		CorpEntry corpEntry = corpEntryPersistence.findByPrimaryKey(
			corpEntryId);

		OSBCorpEntryPermission.check(
			getPermissionChecker(), corpEntry, OSBActionKeys.MANAGE);

		return corpEntryLocalService.updateStatus(
			getUserId(), corpEntryId, status, statusMessage);
	}

	protected void validate(CorpEntry corpEntry, long[] userIds)
		throws PortalException, SystemException {

		Group group = corpEntry.getGroup();

		boolean admin = false;

		for (long userId : userIds) {
			if (userGroupRoleLocalService.hasUserGroupRole(
					userId, group.getGroupId(),
					OSBConstants.ROLE_OSB_CORP_ADMIN_ID)) {

				admin = true;
			}
		}

		if (!admin) {
			return;
		}

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("inherit", true);
		params.put("usersGroups", new Long(group.getGroupId()));

		Long[] userGroupRoleParam = new Long[] {
			new Long(group.getGroupId()),
			new Long(OSBConstants.ROLE_OSB_CORP_ADMIN_ID)};

		params.put("userGroupRole", userGroupRoleParam);

		int count = userLocalService.searchCount(
			group.getCompanyId(), StringPool.BLANK,
			WorkflowConstants.STATUS_APPROVED, params);

		if (count <= 1) {
			throw new CorpEntryAdminException();
		}
	}

	protected void validate(CorpEntry corpEntry, long[] userIds, long roleId)
		throws PortalException {

		if ((roleId == OSBConstants.ROLE_OSB_CORP_ADMIN_ID) &&
			ArrayUtil.contains(userIds, getUserId())) {

			throw new CorpEntryAdminException();
		}

		if ((roleId != OSBConstants.ROLE_OSB_CORP_ADMIN_ID) &&
			(roleId != OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID)) {

			throw new CorpEntryRoleException();
		}
	}

}