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

import com.liferay.osb.model.TicketCall;
import com.liferay.osb.service.base.TicketCallServiceBaseImpl;
import com.liferay.osb.service.permission.OSBTicketEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;

/**
 * @author Alan Zhang
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketCallServiceImpl extends TicketCallServiceBaseImpl {

	public TicketCall addTicketCall(
			long ticketEntryId, int type, int callDateMonth, int callDateDay,
			int callDateYear, int callDateHour, int callDateMinute,
			long callLength, String customerName, String customerContact,
			String confirmation, String instructions)
		throws PortalException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId,
			OSBActionKeys.UPDATE_TICKET_CALL);

		return ticketCallLocalService.addTicketCall(
			getUserId(), ticketEntryId, type, callDateMonth, callDateDay,
			callDateYear, callDateHour, callDateMinute, callLength,
			customerName, customerContact, confirmation, instructions);
	}

}