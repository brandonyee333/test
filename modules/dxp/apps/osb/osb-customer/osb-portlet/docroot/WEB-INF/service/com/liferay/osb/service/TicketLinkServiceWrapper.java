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
 * Provides a wrapper for {@link TicketLinkService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketLinkService
 * @generated
 */
@ProviderType
public class TicketLinkServiceWrapper implements TicketLinkService,
	ServiceWrapper<TicketLinkService> {
	public TicketLinkServiceWrapper(TicketLinkService ticketLinkService) {
		_ticketLinkService = ticketLinkService;
	}

	@Override
	public com.liferay.osb.model.TicketLink addTicketLink(long userId,
		long ticketEntryId, long ticketSolutionId, java.lang.String[] urls,
		java.lang.Integer[] types, int visibility,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketLinkService.addTicketLink(userId, ticketEntryId,
			ticketSolutionId, urls, types, visibility, serviceContext);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketLinkService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketLinkService.getOSGiServiceIdentifier();
	}

	@Override
	public void deleteTicketLink(long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketLinkService.deleteTicketLink(ticketLinkId);
	}

	@Override
	public TicketLinkService getWrappedService() {
		return _ticketLinkService;
	}

	@Override
	public void setWrappedService(TicketLinkService ticketLinkService) {
		_ticketLinkService = ticketLinkService;
	}

	private TicketLinkService _ticketLinkService;
}