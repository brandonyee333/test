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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TicketCallService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCallService
 * @generated
 */
@ProviderType
public class TicketCallServiceWrapper implements TicketCallService,
	ServiceWrapper<TicketCallService> {
	public TicketCallServiceWrapper(TicketCallService ticketCallService) {
		_ticketCallService = ticketCallService;
	}

	@Override
	public com.liferay.osb.model.TicketCall addTicketCall(long ticketEntryId,
		int type, int callDateMonth, int callDateDay, int callDateYear,
		int callDateHour, int callDateMinute, long callLength,
		java.lang.String customerName, java.lang.String customerContact,
		java.lang.String confirmation, java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCallService.addTicketCall(ticketEntryId, type,
			callDateMonth, callDateDay, callDateYear, callDateHour,
			callDateMinute, callLength, customerName, customerContact,
			confirmation, instructions);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketCallService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketCallService.getOSGiServiceIdentifier();
	}

	@Override
	public TicketCallService getWrappedService() {
		return _ticketCallService;
	}

	@Override
	public void setWrappedService(TicketCallService ticketCallService) {
		_ticketCallService = ticketCallService;
	}

	private TicketCallService _ticketCallService;
}