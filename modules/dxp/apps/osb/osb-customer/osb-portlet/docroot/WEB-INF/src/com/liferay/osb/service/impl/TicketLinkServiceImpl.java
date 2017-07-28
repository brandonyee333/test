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

import com.liferay.osb.model.TicketLink;
import com.liferay.osb.service.base.TicketLinkServiceBaseImpl;
import com.liferay.osb.service.permission.OSBTicketEntryPermission;
import com.liferay.osb.service.permission.OSBTicketLinkPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketLinkServiceImpl extends TicketLinkServiceBaseImpl {

	public TicketLink addTicketLink(
			long userId, long ticketEntryId, long ticketSolutionId,
			String[] urls, Integer[] types, int visibility,
			ServiceContext serviceContext)
		throws PortalException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId, OSBActionKeys.ADD_LINK);

		checkVisibility(getUserId(), ticketEntryId, visibility);

		return ticketLinkLocalService.addTicketLink(
			userId, ticketEntryId, ticketSolutionId, urls, types, visibility,
			serviceContext);
	}

	public void deleteTicketLink(long ticketLinkId) throws PortalException {
		TicketLink ticketLink = ticketLinkPersistence.findByPrimaryKey(
			ticketLinkId);

		OSBTicketLinkPermission.check(
			getPermissionChecker(), ticketLink, OSBActionKeys.DELETE);

		ticketLinkLocalService.deleteTicketLink(getUserId(), ticketLink);
	}

	protected void checkVisibility(
			long userId, long ticketEntryId, int visibility)
		throws PortalException {

		if (!ticketEntryLocalService.hasVisibility(
				userId, ticketEntryId, visibility)) {

			throw new PrincipalException();
		}
	}

}