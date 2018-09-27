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

import com.liferay.osb.exception.NoSuchCorpProjectException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.base.CorpProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class CorpProjectLocalServiceImpl
	extends CorpProjectLocalServiceBaseImpl {

	public CorpProject addCorpProject(
			long userId, String dossieraProjectKey, String salesforceProjectKey,
			String name, long organizationId, ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date createDate = serviceContext.getCreateDate(new Date());
		Date modifiedDate = serviceContext.getModifiedDate(new Date());

		Long corpProjectId = GetterUtil.getLong(
			serviceContext.getAttribute("corpProjectId"));

		CorpProject corpProject = corpProjectPersistence.create(corpProjectId);

		corpProject.setUuid(serviceContext.getUuid());
		corpProject.setUserId(userId);
		corpProject.setUserName(user.getFullName());
		corpProject.setCreateDate(createDate);
		corpProject.setModifiedDate(modifiedDate);
		corpProject.setDossieraProjectKey(dossieraProjectKey);
		corpProject.setSalesforceProjectKey(salesforceProjectKey);
		corpProject.setName(name);
		corpProject.setOrganizationId(organizationId);

		return corpProjectPersistence.update(corpProject);
	}

	@Override
	public CorpProject deleteCorpProject(CorpProject corpProject)
		throws PortalException {

		AccountEntry accountEntry =
			accountEntryPersistence.fetchByCorpProjectId(
				corpProject.getCorpProjectId());

		if (accountEntry != null) {
			accountEntry.setCorpProjectId(0);

			accountEntryPersistence.update(accountEntry);
		}

		return corpProjectPersistence.remove(corpProject);
	}

	public CorpProject deleteCorpProject(long corpProjectId)
		throws PortalException {

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		return deleteCorpProject(corpProject);
	}

	public CorpProject fetchCorpProject(String dossieraProjectKey) {
		return corpProjectPersistence.fetchByDossieraProjectKey(
			dossieraProjectKey);
	}

	public CorpProject fetchCorpProjectByUuid(String uuid)
		throws PortalException {

		List<CorpProject> corpProjects = corpProjectPersistence.findByUuid(
			uuid);

		if (corpProjects.isEmpty()) {
			return null;
		}
		else {
			return corpProjects.get(0);
		}
	}

	public CorpProject getCorpProjectByUuid(String uuid)
		throws PortalException {

		CorpProject corpProject = fetchCorpProjectByUuid(uuid);

		if (corpProject == null) {
			throw new NoSuchCorpProjectException("{uuid=" + uuid + "}");
		}

		return corpProject;
	}

	public List<CorpProject> getCorpProjects(
			String name, int start, int end, OrderByComparator obc)
		throws PortalException {

		if (Validator.isNull(name)) {
			return corpProjectPersistence.findAll(start, end, obc);
		}

		name = StringUtil.quote(name, StringPool.PERCENT);

		return corpProjectPersistence.findByName(name, start, end, obc);
	}

	public int getCorpProjectsCount(String name) throws PortalException {
		if (Validator.isNull(name)) {
			return corpProjectPersistence.countAll();
		}

		name = StringUtil.quote(name, StringPool.PERCENT);

		return corpProjectPersistence.countByName(name);
	}

	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, long roleId)
		throws PortalException {

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		Organization organization = organizationLocalService.findByPrimaryKey(
			corpProject.getOrganizationId());

		return userGroupRoleLocalService.hasUserGroupRole(
			userId, organization.getGroupId(), roleId);
	}

	public CorpProject updateCorpProject(
			long corpProjectId, String name, ServiceContext serviceContext)
		throws PortalException {

		Date modifiedDate = serviceContext.getModifiedDate(new Date());

		CorpProject corpProject = corpProjectPersistence.findByPrimaryKey(
			corpProjectId);

		corpProject.setModifiedDate(modifiedDate);
		corpProject.setName(name);

		return corpProjectPersistence.update(corpProject);
	}

}