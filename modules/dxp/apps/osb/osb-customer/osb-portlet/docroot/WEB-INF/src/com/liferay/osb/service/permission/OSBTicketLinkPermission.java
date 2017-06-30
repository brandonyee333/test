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

package com.liferay.osb.service.permission;

import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketLink;
import com.liferay.osb.model.TicketSolutionConstants;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class OSBTicketLinkPermission {

	public static void check(
			PermissionChecker permissionChecker, TicketLink ticketLink,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, ticketLink, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, TicketLink ticketLink,
			String actionId)
		throws PortalException, SystemException {

		TicketEntry ticketEntry = ticketLink.getTicketEntry();

		if (ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) {
			Date createDate = ticketLink.getCreateDate();

			if (createDate.before(ticketEntry.getClosedDate())) {
				return false;
			}
		}

		if (!OSBTicketEntryPermission.contains(
				permissionChecker, ticketEntry, OSBActionKeys.DELETE_LINK)) {

			return false;
		}

		if (ticketLink.getTicketSolutionId() !=
				TicketSolutionConstants.DEFAULT_SOLUTION_ID) {

			if (!OSBTicketEntryPermission.contains(
					permissionChecker, ticketEntry,
					OSBActionKeys.UPDATE_SOLUTION)) {

				return false;
			}
		}

		return true;
	}

}