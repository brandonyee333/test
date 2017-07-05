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

import com.liferay.osb.model.TicketCannedResponse;
import com.liferay.osb.service.base.TicketCannedResponseServiceBaseImpl;
import com.liferay.osb.service.permission.OSBTicketCannedResponsePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketCannedResponseServiceImpl
	extends TicketCannedResponseServiceBaseImpl {

	public void incrementUseCount(long ticketCannedResponseId)
		throws PortalException, SystemException {

		OSBTicketCannedResponsePermission.check(
			getPermissionChecker(), ActionKeys.UPDATE);

		ticketCannedResponseLocalService.incrementUseCount(
			ticketCannedResponseId);
	}

	public List<TicketCannedResponse> search(
			String keywords, int start, int end)
		throws PortalException, SystemException {

		OSBTicketCannedResponsePermission.check(
			getPermissionChecker(), ActionKeys.VIEW);

		return ticketCannedResponseLocalService.search(keywords, start, end);
	}

	public List<TicketCannedResponse> search(
			String name, String content, boolean andSearch, int start, int end)
		throws PortalException, SystemException {

		OSBTicketCannedResponsePermission.check(
			getPermissionChecker(), ActionKeys.VIEW);

		return ticketCannedResponseLocalService.search(
			name, content, andSearch, start, end);
	}

	public int searchCount(String keywords)
		throws PortalException, SystemException {

		OSBTicketCannedResponsePermission.check(
			getPermissionChecker(), ActionKeys.VIEW);

		return ticketCannedResponseLocalService.searchCount(keywords);
	}

	public int searchCount(String name, String content, boolean andSearch)
		throws PortalException, SystemException {

		OSBTicketCannedResponsePermission.check(
			getPermissionChecker(), ActionKeys.VIEW);

		return ticketCannedResponseLocalService.searchCount(
			name, content, andSearch);
	}

}