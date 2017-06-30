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

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.TicketWorkerConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.TicketCommentLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.Date;

/**
 * @author Amos Fong
 * @author Mate Thurzo
 */
public class OSBTicketFeedbackPermission {

	public static void check(
			PermissionChecker permissionChecker, long ticketFeedbackId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, ticketFeedbackId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, TicketFeedback ticketFeedback,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, ticketFeedback, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void checkSubjectLiferay(
			PermissionChecker permissionChecker, long ticketEntryId,
			String actionId)
		throws PortalException, SystemException {

		if (!containsSubjectLiferay(
				permissionChecker, ticketEntryId, actionId)) {

			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long ticketFeedbackId,
			String actionId)
		throws PortalException, SystemException {

		TicketFeedback ticketFeedback =
			TicketFeedbackLocalServiceUtil.getTicketFeedback(ticketFeedbackId);

		return contains(permissionChecker, ticketFeedback, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, TicketFeedback ticketFeedback,
			String actionId)
		throws SystemException {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.UPDATE) &&
			ticketFeedback.isClosed()) {

			return false;
		}

		if (ticketFeedback.getSubject() ==
				TicketFeedbackConstants.SUBJECT_LIFERAY) {

			return containsSubjectLiferay(
				permissionChecker, ticketFeedback.getTicketEntryId(), actionId);
		}
		else if (ticketFeedback.getSubject() ==
					TicketFeedbackConstants.SUBJECT_PARTNER) {

			TicketWorker ticketWorker = null;

			try {
				ticketWorker = TicketWorkerLocalServiceUtil.getTicketWorker(
					permissionChecker.getUserId(),
					ticketFeedback.getTicketEntryId());
			}
			catch (Exception e) {
			}

			if (ticketWorker != null) {
				if (OrganizationLocalServiceUtil.hasUserOrganization(
						permissionChecker.getUserId(),
						OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

					if ((ticketWorker.getRole() ==
							TicketWorkerConstants.ROLE_DEVELOPER) ||
						(ticketWorker.getRole() ==
							TicketWorkerConstants.ROLE_ESCALATED_DEVELOPER) ||
						(ticketWorker.getRole() ==
							TicketWorkerConstants.ROLE_MANAGER)) {

						return true;
					}
				}
			}

			if (actionId.equals(OSBActionKeys.UPDATE)) {
				return false;
			}
		}

		if (OrganizationLocalServiceUtil.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		return false;
	}

	public static boolean containsSubjectLiferay(
			PermissionChecker permissionChecker, long ticketEntryId,
			String actionId)
		throws SystemException {

		TicketEntry ticketEntry = TicketEntryLocalServiceUtil.fetchTicketEntry(
			ticketEntryId);

		if (ticketEntry == null) {
			return false;
		}

		long[] organizationIds = {
			OSBConstants.ORGANIZATION_LIFERAY_INC_ID,
			OSBConstants.ORGANIZATION_PARTNER_ID
		};

		int workerCommentCount =
			TicketCommentLocalServiceUtil.getOrganizationTicketCommentsCount(
				organizationIds, ticketEntryId, VisibilityConstants.PUBLIC);

		if (workerCommentCount == 0) {
			return false;
		}

		boolean liferayIncOrg =
			OrganizationLocalServiceUtil.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID);

		if (ticketEntry.getClosedDate() != null) {
			Date closedDate = ticketEntry.getClosedDate();

			if (liferayIncOrg) {
				if (actionId.equals(OSBActionKeys.VIEW) &&
					(ticketEntry.getEscalationLevel() !=
						TicketEntryConstants.ESCALATION_LEVEL_P1) &&
					(ticketEntry.getStatus() ==
						TicketEntryConstants.STATUS_CLOSED)) {

					return true;
				}
			}
			else {
				if ((System.currentTimeMillis() - closedDate.getTime()) >
						(30 * Time.DAY)) {

					return false;
				}
			}
		}

		AccountCustomer accountCustomer = null;

		try {
			accountCustomer =
				AccountCustomerLocalServiceUtil.getAccountCustomer(
					permissionChecker.getUserId(),
					ticketEntry.getAccountEntryId());
		}
		catch (Exception e) {
		}

		if ((accountCustomer != null) &&
			(ticketEntry.getEscalationLevel() !=
				TicketEntryConstants.ESCALATION_LEVEL_P1) &&
			(ticketEntry.getResolution() !=
				TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER) &&
			(accountCustomer.getRole() ==
				AccountCustomerConstants.ROLE_DEVELOPER) &&
			(ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.UPDATE)) {
			return false;
		}

		return false;
	}

}