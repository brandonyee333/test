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

import com.liferay.osb.exception.NoSuchCorpProjectException;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.base.CorpProjectLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
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

		long corpProjectId = counterLocalService.increment();

		CorpProject corpProject = corpProjectPersistence.create(corpProjectId);

		corpProject.setUuid(serviceContext.getUuid());
		corpProject.setUserId(userId);

		if (user != null) {
			corpProject.setUserName(user.getFullName());
		}

		corpProject.setCreateDate(createDate);
		corpProject.setModifiedDate(modifiedDate);
		corpProject.setDossieraProjectKey(dossieraProjectKey);
		corpProject.setSalesforceProjectKey(salesforceProjectKey);
		corpProject.setName(name);
		corpProject.setOrganizationId(organizationId);

		return corpProjectPersistence.update(corpProject);
	}

	public CorpProject fetchCorpProject(String dossieraProjectKey) {
		return corpProjectPersistence.fetchByDossieraProjectKey(
			dossieraProjectKey);
	}

	public CorpProject getCorpProjectByUuid(String uuid)
		throws PortalException {

		List<CorpProject> corpProjects = corpProjectPersistence.findByUuid(
			uuid);

		if (corpProjects.isEmpty()) {
			throw new NoSuchCorpProjectException("{uuid=" + uuid + "}");
		}
		else {
			return corpProjects.get(0);
		}
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