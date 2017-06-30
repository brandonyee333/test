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

import com.liferay.osb.model.CorpGroup;
import com.liferay.osb.service.base.CorpGroupServiceBaseImpl;
import com.liferay.osb.service.permission.OSBCorpGroupPermission;
import com.liferay.osb.service.permission.OSBCorpPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.service.ServiceContext;

import java.io.File;

import java.util.Locale;
import java.util.Map;

/**
 * @author Ryan Park
 * @author Rachael Koestartyo
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class CorpGroupServiceImpl extends CorpGroupServiceBaseImpl {

	public CorpGroup addCorpGroup(
			String name, Map<Locale, String> descriptionMap, File logoFile,
			String emailAddress, String website, ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBCorpPermission.check(
			getPermissionChecker(), OSBActionKeys.ADD_CORP_GROUP);

		return corpGroupLocalService.addCorpGroup(
			getUserId(), name, descriptionMap, logoFile, emailAddress, website,
			serviceContext);
	}

	public CorpGroup deleteCorpGroup(long corpGroupId)
		throws PortalException, SystemException {

		OSBCorpGroupPermission.check(
			getPermissionChecker(), corpGroupId, OSBActionKeys.DELETE);

		return corpGroupLocalService.deleteCorpGroup(corpGroupId);
	}

	public CorpGroup updateCorpGroup(
			long corpGroupId, String name, Map<Locale, String> descriptionMap,
			File logoFile, String emailAddress, String website,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBCorpGroupPermission.check(
			getPermissionChecker(), corpGroupId, OSBActionKeys.UPDATE);

		return corpGroupLocalService.updateCorpGroup(
			corpGroupId, name, descriptionMap, logoFile, emailAddress, website,
			serviceContext);
	}

}