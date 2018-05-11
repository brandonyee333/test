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
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Amos Fong
 */
@JSONWebService
public class CorpProjectMessageServiceImpl
	extends CorpProjectMessageServiceBaseImpl {

	public CorpProjectMessage addCorpProjectMessage(
			String userUuid, long corpProjectId, int type, int severityLevel,
			String title, String content, boolean displayCP, boolean displayLCS,
			ServiceContext serviceContext)
		throws PortalException {

		validateJSONWebServicePermissions();

		User user = userLocalService.getUserByUuidAndCompanyId(
			userUuid, OSBConstants.COMPANY_ID);

		return corpProjectMessageLocalService.addCorpProjectMessage(
			user.getUserId(), corpProjectId, type, severityLevel, title,
			content, displayCP, displayLCS, serviceContext);
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

}