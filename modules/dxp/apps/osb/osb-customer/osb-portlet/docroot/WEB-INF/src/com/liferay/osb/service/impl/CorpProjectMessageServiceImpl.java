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

import com.liferay.osb.model.CorpProjectMessage;
import com.liferay.osb.service.base.CorpProjectMessageServiceBaseImpl;
import com.liferay.osb.service.permission.OSBCorpProjectPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;

import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService
public class CorpProjectMessageServiceImpl
	extends CorpProjectMessageServiceBaseImpl {

	public List<CorpProjectMessage> getCorpProjectMessages(long corpProjectId)
		throws PortalException, SystemException {

		OSBCorpProjectPermission.check(
			getPermissionChecker(), corpProjectId, OSBActionKeys.VIEW);

		return corpProjectMessageLocalService.getCorpProjectMessages(
			corpProjectId);
	}

}