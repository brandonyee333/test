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

import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketSolution;
import com.liferay.osb.service.base.TicketSolutionServiceBaseImpl;
import com.liferay.osb.service.permission.OSBTicketEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

import java.util.List;

/**
 * @author Alan Zhang
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketSolutionServiceImpl extends TicketSolutionServiceBaseImpl {

	public TicketSolution addTicketSolution(
			long userId, long ticketEntryId, String summary,
			boolean useCustomerSummary, int issueType, String solution,
			int type, boolean customerSpecific, boolean environmentSpecific,
			boolean versionSpecific, boolean reviewForKB, int status,
			int ticketEntrySubcomponent, String ticketEntrySubcomponentCustom,
			List<String> ticketLinkURLs, List<Integer> ticketLinkTypes,
			List<TicketAttachment> ticketAttachments)
		throws Exception {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId,
			OSBActionKeys.UPDATE_ADVANCED);

		return ticketSolutionLocalService.addTicketSolution(
			userId, ticketEntryId, summary, useCustomerSummary, issueType,
			solution, type, customerSpecific, environmentSpecific,
			versionSpecific, reviewForKB, status, ticketEntrySubcomponent,
			ticketEntrySubcomponentCustom, ticketLinkURLs, ticketLinkTypes,
			ticketAttachments);
	}

	public TicketSolution updateTicketSolution(
			long ticketSolutionId, long ticketEntryId, int status,
			long statusByUserId, String statusMessage, int statusReason)
		throws Exception {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId, OSBActionKeys.UPDATE_BASIC);

		return ticketSolutionLocalService.updateTicketSolution(
			ticketSolutionId, ticketEntryId, status, statusByUserId,
			statusMessage, statusReason);
	}

}