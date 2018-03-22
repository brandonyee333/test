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
 * Provides a wrapper for {@link TicketInformationService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformationService
 * @generated
 */
@ProviderType
public class TicketInformationServiceWrapper implements TicketInformationService,
	ServiceWrapper<TicketInformationService> {
	public TicketInformationServiceWrapper(
		TicketInformationService ticketInformationService) {
		_ticketInformationService = ticketInformationService;
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketInformationService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketInformationService.getOSGiServiceIdentifier();
	}

	@Override
	public TicketInformationService getWrappedService() {
		return _ticketInformationService;
	}

	@Override
	public void setWrappedService(
		TicketInformationService ticketInformationService) {
		_ticketInformationService = ticketInformationService;
	}

	private TicketInformationService _ticketInformationService;
}