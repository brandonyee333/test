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

import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.service.TicketCommentLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.TicketCommentCreateDateComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class OSBTicketCommentPermission {

	public static void check(
			PermissionChecker permissionChecker, long ticketCommentId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ticketCommentId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, TicketComment ticketComment,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ticketComment, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long ticketCommentId,
			String actionId)
		throws PortalException {

		TicketComment ticketComment =
			TicketCommentLocalServiceUtil.getTicketComment(ticketCommentId);

		return contains(permissionChecker, ticketComment, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, TicketComment ticketComment,
		String actionId) {

		List<SupportWorker> supportWorkers = new ArrayList<>();

		try {
			supportWorkers =
				SupportWorkerLocalServiceUtil.getUserSupportWorkers(
					permissionChecker.getUserId());

			if (!supportWorkers.isEmpty()) {
				SupportWorker supportWorker = supportWorkers.get(0);

				if (!supportWorker.isClockedIn()) {
					return false;
				}
			}
		}
		catch (Exception e) {
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		if (actionId.equals(OSBActionKeys.MARK_AS_SOLUTION)) {
			for (SupportWorker supportWorker : supportWorkers) {
				if (supportWorker.getRole() !=
						SupportWorkerConstants.ROLE_WATCHER) {

					return true;
				}
			}
		}

		if ((ticketComment == null) ||
			(ticketComment.getUserId() != permissionChecker.getUserId())) {

			return false;
		}

		if (actionId.equals(OSBActionKeys.UPDATE)) {
			if (ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) {
				return true;
			}

			List<PartnerWorker> partnerWorkers = new ArrayList<>();

			try {
				partnerWorkers =
					PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(
						permissionChecker.getUserId());
			}
			catch (Exception e) {
			}

			if (!supportWorkers.isEmpty() || !partnerWorkers.isEmpty()) {
				TicketComment lastTicketComment =
					TicketCommentLocalServiceUtil.fetchLastTicketComment(
						permissionChecker.getUserId(),
						ticketComment.getTicketEntryId(),
						ticketComment.getVisibility(),
						WorkflowConstants.STATUS_APPROVED,
						new TicketCommentCreateDateComparator(true));

				if ((lastTicketComment != null) &&
					(lastTicketComment.getType() !=
						TicketCommentConstants.TYPE_SOLUTION) &&
					(lastTicketComment.getTicketCommentId() ==
						ticketComment.getTicketCommentId())) {

					return true;
				}
			}

			return false;
		}

		if (actionId.equals(OSBActionKeys.DELETE)) {
			if (ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) {
				return true;
			}
		}

		return false;
	}

}