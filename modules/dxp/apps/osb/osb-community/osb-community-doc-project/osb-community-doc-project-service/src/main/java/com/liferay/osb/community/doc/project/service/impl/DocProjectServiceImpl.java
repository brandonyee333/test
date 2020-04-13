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

package com.liferay.osb.community.doc.project.service.impl;

import com.liferay.osb.community.doc.project.constants.DocProjectActionKeys;
import com.liferay.osb.community.doc.project.internal.service.permission.DocProjectPermission;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.service.base.DocProjectServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.File;

/**
 * @author Haote Chou
 */
public class DocProjectServiceImpl extends DocProjectServiceBaseImpl {

	@Override
	public DocProject addDocProject(
			String name, String description, String iconFileName, File iconFile,
			boolean unlisted, String type, String typeSettings, int status,
			ServiceContext serviceContext)
		throws PortalException {

		DocProjectPermission.check(
			getPermissionChecker(), DocProjectActionKeys.ADD_DOC_PROJECT);

		return docProjectLocalService.addDocProject(
			getUserId(), name, description, iconFileName, iconFile, unlisted,
			type, typeSettings, status, serviceContext);
	}

	@Override
	public DocProject updateDocProject(
			long docProjectId, String name, String description,
			String iconFileName, File iconFile, boolean unlisted, String type,
			String typeSettings, int status, ServiceContext serviceContext)
		throws PortalException {

		DocProjectPermission.check(
			getPermissionChecker(), DocProjectActionKeys.UPDATE_DOC_PROJECT);

		return docProjectLocalService.updateDocProject(
			docProjectId, name, description, iconFileName, iconFile, unlisted,
			type, typeSettings, status, serviceContext);
	}

}