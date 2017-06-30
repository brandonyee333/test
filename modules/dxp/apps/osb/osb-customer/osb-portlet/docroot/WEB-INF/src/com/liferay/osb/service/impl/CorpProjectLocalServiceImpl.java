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
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.AccountEntryCorpProjectException;
import com.liferay.osb.CorpProjectAdminException;
import com.liferay.osb.CorpProjectMembershipException;
import com.liferay.osb.CorpProjectNameException;
import com.liferay.osb.CorpProjectRoleException;
import com.liferay.osb.CorpProjectSalesforceProjectKeyException;
import com.liferay.osb.DuplicateCorpProjectException;
import com.liferay.osb.RequiredCorpProjectException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.base.CorpProjectLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.ModelHintsUtil;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Joan Kim
 */
public class CorpProjectLocalServiceImpl
	extends CorpProjectLocalServiceBaseImpl {

	public CorpProject addCorpProject(
			long creatorUserId, long ownerUserId, String dossieraProjectKey,
			String salesforceProjectKey, String name)
		throws PortalException, SystemException {

		// Corp project

		User creatorUser = userPersistence.findByPrimaryKey(creatorUserId);
		Date now = new Date();

		validate(0, dossieraProjectKey, name);

		long corpProjectId = counterLocalService.increment();

		CorpProject corpProject = corpProjectPersistence.create(corpProjectId);

		corpProject.setUserId(creatorUserId);
		corpProject.setUserName(creatorUser.getFullName());
		corpProject.setCreateDate(now);
		corpProject.setModifiedDate(now);
		corpProject.setDossieraProjectKey(dossieraProjectKey);
		corpProject.setSalesforceProjectKey(salesforceProjectKey);
		corpProject.setName(name);

		String organizationName = getOrganizationName(
			corpProject.getCorpProjectId(), corpProject.getName());

		Organization organization = organizationLocalService.addOrganization(
			OSBConstants.USER_DEFAULT_USER_ID,
			OSBConstants.ORGANIZATION_CORPORATION_PROJECT_PARENT_ID,
			organizationName, OrganizationConstants.TYPE_REGULAR_ORGANIZATION,
			true, 0, 0, OSBConstants.ORGANIZATION_FULL_MEMBER_STATUS_ID,
			StringPool.BLANK, false, new ServiceContext());

		corpProject.setOrganizationId(organization.getOrganizationId());

		corpProjectPersistence.update(corpProject, false);

		if (ownerUserId > 0) {
			userPersistence.findByPrimaryKey(ownerUserId);

			// Group

			Group group = corpProject.getGroup();

			long[] roleIds = new long[] {
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID,
				OSBConstants.ROLE_OSB_CORP_BUYER_ID
			};

			userGroupRoleLocalService.addUserGroupRoles(
				ownerUserId, group.getGroupId(), roleIds);

			// Organization

			userLocalService.addOrganizationUsers(
				organization.getOrganizationId(), new long[] {ownerUserId});
		}

		return corpProject;
	}

	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		validateSalesforceProjectKey(corpProjectId);

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		userLocalService.addOrganizationUsers(
			corpProject.getOrganizationId(), userIds);
	}

	public void addUserCorpProjectRoles(
			long userId, long corpProjectId, long[] userIds, long roleId)
		throws PortalException, SystemException {

		validate(userId, userIds, roleId);

		validateSalesforceProjectKey(corpProjectId);

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		Group group = corpProject.getGroup();

		userGroupRoleLocalService.addUserGroupRoles(
			userIds, group.getGroupId(), roleId);
	}

	@Override
	public CorpProject deleteCorpProject(CorpProject corpProject)
		throws PortalException, SystemException {

		if (accountEntryPersistence.countByCorpProjectId(
				corpProject.getCorpProjectId()) > 0) {

			throw new RequiredCorpProjectException();
		}

		// Corp project

		corpProjectPersistence.remove(corpProject);

		// Organization

		List<User> users = userLocalService.getOrganizationUsers(
			corpProject.getOrganizationId());

		long[] userIds = new long[users.size()];

		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);

			userIds[i] = user.getUserId();
		}

		userLocalService.unsetOrganizationUsers(
			corpProject.getOrganizationId(), userIds);

		organizationLocalService.deleteOrganization(
			corpProject.getOrganizationId());

		return corpProject;
	}

	@Override
	public CorpProject deleteCorpProject(long corpProjectId)
		throws PortalException, SystemException {

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		return deleteCorpProject(corpProject);
	}

	public void deleteUserCorpProjectRoles(
			long userId, long corpProjectId, long[] userIds, long roleId)
		throws PortalException, SystemException {

		validate(userId, userIds, roleId);

		validateSalesforceProjectKey(corpProjectId);

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		Group group = corpProject.getGroup();

		userGroupRoleLocalService.deleteUserGroupRoles(
			userIds, group.getGroupId(), roleId);
	}

	public CorpProject fetchCorpProject(String dossieraProjectKey)
		throws SystemException {

		return corpProjectPersistence.fetchByDossieraProjectKey(
			dossieraProjectKey);
	}

	public List<CorpProject> getCorpProjects(
			String name, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		if (Validator.isNull(name)) {
			return corpProjectPersistence.findAll(start, end, obc);
		}

		return corpProjectPersistence.findByName(name, start, end, obc);
	}

	public int getCorpProjectsCount(String name)
		throws PortalException, SystemException {

		if (Validator.isNull(name)) {
			return corpProjectPersistence.countAll();
		}

		return corpProjectPersistence.countByName(name);
	}

	public CorpProject getOrganizationCorpProject(long organizationId)
		throws PortalException, SystemException {

		return corpProjectPersistence.findByOrganizationId(organizationId);
	}

	public List<CorpProject> getUserCorpProjects(long userId)
		throws PortalException, SystemException {

		return getUserCorpProjects(userId, 0);
	}

	public List<CorpProject> getUserCorpProjects(long userId, long roleId)
		throws PortalException, SystemException {

		List<Organization> organizations =
			organizationLocalService.getUserOrganizations(userId);

		List<CorpProject> corpProjects = new ArrayList<CorpProject>(
			organizations.size());

		for (Organization organization : organizations) {
			CorpProject corpProject =
				corpProjectPersistence.fetchByOrganizationId(
					organization.getOrganizationId());

			if (corpProject == null) {
				continue;
			}

			if ((roleId > 0) &&
				!userGroupRoleLocalService.hasUserGroupRole(
					userId, organization.getGroupId(), roleId)) {

				continue;
			}

			corpProjects.add(corpProject);
		}

		return corpProjects;
	}

	public List<CorpProject> getUserCorpProjects(long userId, String roleName)
		throws PortalException, SystemException {

		User user = userLocalService.getUser(userId);

		Role role = rolePersistence.findByC_N(user.getCompanyId(), roleName);

		return getUserCorpProjects(userId, role.getRoleId());
	}

	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException, SystemException {

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		return organizationLocalService.hasUserOrganization(
			userId, corpProject.getOrganizationId());
	}

	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, long roleId)
		throws PortalException, SystemException {

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		Organization organization = organizationPersistence.findByPrimaryKey(
			corpProject.getOrganizationId());

		return userGroupRoleLocalService.hasUserGroupRole(
			userId, organization.getGroupId(), roleId);
	}

	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		validateSalesforceProjectKey(corpProjectId);

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		userLocalService.unsetOrganizationUsers(
			corpProject.getOrganizationId(), userIds);
	}

	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException, SystemException {

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		return updateCorpProject(
			corpProjectId, corpProject.getDossieraProjectKey(),
			corpProject.getSalesforceProjectKey(), name);
	}

	public CorpProject updateCorpProject(
			long corpProjectId, String dossieraProjectKey,
			String salesforceProjectKey, String name, long accountEntryId)
		throws PortalException, SystemException {

		validate(corpProjectId, accountEntryId, salesforceProjectKey);

		CorpProject corpProject = updateCorpProject(
			corpProjectId, dossieraProjectKey, salesforceProjectKey, name);

		AccountEntry accountEntry =
			accountEntryLocalService.fetchCorpProjectAccountEntry(
				corpProjectId);

		if ((accountEntry != null) &&
			(accountEntry.getAccountEntryId() != accountEntryId)) {

			accountEntryLocalService.updateCorpProject(
				accountEntry.getAccountEntryId(), 0);
		}

		if ((accountEntryId > 0) &&
			((accountEntry == null) ||
			 (accountEntry.getAccountEntryId() != accountEntryId))) {

			accountEntryLocalService.updateCorpProject(
				accountEntryId, corpProjectId);
		}

		return corpProject;
	}

	public void validateMembership(long userId, long corpProjectId, long roleId)
		throws PortalException, SystemException {

		if (!hasUserCorpProject(userId, corpProjectId)) {
			throw new CorpProjectMembershipException();
		}

		if ((roleId > 0) &&
			!hasUserCorpProjectRole(userId, corpProjectId, roleId)) {

			throw new CorpProjectMembershipException();
		}
	}

	public void validateSalesforceProjectKey(long corpProjectId)
		throws PortalException, SystemException {

		AccountEntry accountEntry =
			accountEntryLocalService.fetchCorpProjectAccountEntry(
				corpProjectId);

		if (accountEntry == null) {
			return;
		}

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		if (Validator.isNull(corpProject.getSalesforceProjectKey())) {
			throw new CorpProjectSalesforceProjectKeyException();
		}
	}

	protected String getOrganizationName(long corpProjectId, String name) {
		StringBundler sb = new StringBundler(5);

		sb.append(_ORGANIZATION_NAMESPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(corpProjectId);
		sb.append(StringPool.CLOSE_PARENTHESIS);
		sb.append(name);

		String organizationName = sb.toString();

		int maxLength = ModelHintsUtil.getMaxLength(
			Group.class.getName(), "friendlyURL");

		if (organizationName.length() > (maxLength - 3)) {
			organizationName = organizationName.substring(0, maxLength - 3);
		}

		return organizationName;
	}

	protected CorpProject updateCorpProject(
			long corpProjectId, String dossieraProjectKey,
			String salesforceProjectKey, String name)
		throws PortalException, SystemException {

		validate(corpProjectId, dossieraProjectKey, name);

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		corpProject.setModifiedDate(new Date());
		corpProject.setDossieraProjectKey(dossieraProjectKey);
		corpProject.setSalesforceProjectKey(salesforceProjectKey);
		corpProject.setName(name);

		corpProjectPersistence.update(corpProject, false);

		return corpProject;
	}

	protected void validate(long userId, long userIds[], long roleId)
		throws PortalException {

		if ((roleId == OSBConstants.ROLE_OSB_CORP_ADMIN_ID) &&
			ArrayUtil.contains(userIds, userId)) {

			throw new CorpProjectAdminException();
		}

		if ((roleId != OSBConstants.ROLE_OSB_CORP_ADMIN_ID) &&
			(roleId != OSBConstants.ROLE_OSB_CORP_BUYER_ID) &&
			(roleId != OSBConstants.ROLE_OSB_CORP_LCS_USER_ID)) {

			throw new CorpProjectRoleException();
		}
	}

	protected void validate(
			long corpProjectId, long accountEntryId,
			String salesforceProjectKey)
		throws PortalException, SystemException {

		if (accountEntryId > 0) {
			AccountEntry accountEntry =
				accountEntryPersistence.findByPrimaryKey(accountEntryId);

			if ((accountEntry.getCorpProjectId() > 0) &&
				(accountEntry.getCorpProjectId() != corpProjectId)) {

				throw new AccountEntryCorpProjectException();
			}

			if (Validator.isNull(salesforceProjectKey)) {
				throw new CorpProjectSalesforceProjectKeyException();
			}
		}
	}

	protected void validate(
			long corpProjectId, String dossieraProjectKey, String name)
		throws PortalException, SystemException {

		if (Validator.isNotNull(dossieraProjectKey)) {
			CorpProject corpProject =
				corpProjectPersistence.fetchByDossieraProjectKey(
					dossieraProjectKey);

			if (corpProject != null) {
				if (corpProject.getCorpProjectId() != corpProjectId) {
					throw new DuplicateCorpProjectException();
				}
			}
		}

		if (Validator.isNull(name)) {
			throw new CorpProjectNameException();
		}
	}

	private static final String _ORGANIZATION_NAMESPACE = "CorpProject";

}