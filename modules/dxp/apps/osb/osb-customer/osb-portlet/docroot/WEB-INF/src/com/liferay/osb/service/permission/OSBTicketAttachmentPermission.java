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

import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketSolutionConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class OSBTicketAttachmentPermission {

	public static void check(
			PermissionChecker permissionChecker,
			TicketAttachment ticketAttachment, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ticketAttachment, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			TicketAttachment ticketAttachment, String actionId)
		throws PortalException {

		if (ticketAttachment.getTicketEntryId() <= 0) {
			return isOwner(permissionChecker, ticketAttachment);
		}

		if (ArrayUtil.contains(
				TicketAttachmentConstants.TYPES_LARGE,
				ticketAttachment.getType())) {

			return containsLargeFile(
				permissionChecker, ticketAttachment, actionId);
		}
		else {
			return containsRegular(
				permissionChecker, ticketAttachment, actionId);
		}
	}

	public static boolean containsLargeFile(
			PermissionChecker permissionChecker,
			TicketAttachment ticketAttachment, String actionId)
		throws PortalException {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		List<SupportWorker> supportWorkers = new ArrayList<>();

		try {
			supportWorkers =
				SupportWorkerLocalServiceUtil.getUserSupportWorkers(
					permissionChecker.getUserId());
		}
		catch (Exception e) {
		}

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.getRole() ==
					SupportWorkerConstants.ROLE_MANAGER) {

				return true;
			}
		}

		if (actionId.equals(OSBActionKeys.DELETE)) {
			return false;
		}

		TicketWorker ticketWorker = null;

		try {
			ticketWorker = TicketWorkerLocalServiceUtil.getTicketWorker(
				permissionChecker.getUserId(),
				ticketAttachment.getTicketEntryId());
		}
		catch (Exception e) {
		}

		if (ticketWorker != null) {
			return true;
		}

		if (actionId.equals(OSBActionKeys.VIEW)) {
			TicketEntry ticketEntry = ticketAttachment.getTicketEntry();

			if ((permissionChecker.getUserId() == ticketEntry.getUserId()) &&
				OrganizationLocalServiceUtil.hasUserOrganization(
					ticketAttachment.getUserId(),
					OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				return true;
			}

			AccountCustomer accountCustomer =
				AccountCustomerLocalServiceUtil.fetchAccountCustomer(
					permissionChecker.getUserId(),
					ticketEntry.getAccountEntryId());

			if ((accountCustomer != null) &&
				ArrayUtil.contains(
					TicketAttachmentConstants.TYPES_HOTFIX,
					ticketAttachment.getType())) {

				return true;
			}
		}

		return false;
	}

	public static boolean containsRegular(
			PermissionChecker permissionChecker,
			TicketAttachment ticketAttachment, String actionId)
		throws PortalException {

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		if (ticketAttachment.getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return isOwner(permissionChecker, ticketAttachment);
		}

		if (actionId.equals(OSBActionKeys.VIEW)) {
			if (ticketAttachment.getTicketEntryId() > 0) {
				return OSBTicketEntryPermission.contains(
					permissionChecker, ticketAttachment.getTicketEntryId(),
					OSBActionKeys.VIEW);
			}
			else {
				return false;
			}
		}

		if (actionId.equals(OSBActionKeys.DELETE)) {
			if ((ticketAttachment.getType() !=
					TicketAttachmentConstants.TYPE_HOTFIX) &&
				(ticketAttachment.getType() !=
					TicketAttachmentConstants.TYPE_NONE)) {

				return false;
			}
		}

		TicketEntry ticketEntry = ticketAttachment.getTicketEntry();

		if (ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) {
			Date createDate = ticketAttachment.getCreateDate();

			if (createDate.before(ticketEntry.getClosedDate())) {
				return false;
			}
		}

		if (!OSBTicketEntryPermission.contains(
				permissionChecker, ticketEntry,
				OSBActionKeys.DELETE_ATTACHMENT)) {

			return false;
		}

		if (ticketAttachment.getTicketSolutionId() !=
				TicketSolutionConstants.DEFAULT_SOLUTION_ID) {

			if (!OSBTicketEntryPermission.contains(
					permissionChecker, ticketEntry,
					OSBActionKeys.UPDATE_SOLUTION)) {

				return false;
			}
		}

		return true;
	}

	protected static boolean isOwner(
		PermissionChecker permissionChecker,
		TicketAttachment ticketAttachment) {

		if (ticketAttachment.getUserId() == permissionChecker.getUserId()) {
			return true;
		}
		else {
			return false;
		}
	}

}