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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link TicketLinkService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketLinkService
 * @generated
 */
public class TicketLinkServiceWrapper implements TicketLinkService,
	ServiceWrapper<TicketLinkService> {
	public TicketLinkServiceWrapper(TicketLinkService ticketLinkService) {
		_ticketLinkService = ticketLinkService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketLinkService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketLinkService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketLinkService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.TicketLink addTicketLink(long userId,
		long ticketEntryId, long ticketSolutionId, java.lang.String[] urls,
		java.lang.Integer[] types, int visibility,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketLinkService.addTicketLink(userId, ticketEntryId,
			ticketSolutionId, urls, types, visibility, serviceContext);
	}

	public void deleteTicketLink(long ticketLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketLinkService.deleteTicketLink(ticketLinkId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketLinkService getWrappedTicketLinkService() {
		return _ticketLinkService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketLinkService(TicketLinkService ticketLinkService) {
		_ticketLinkService = ticketLinkService;
	}

	public TicketLinkService getWrappedService() {
		return _ticketLinkService;
	}

	public void setWrappedService(TicketLinkService ticketLinkService) {
		_ticketLinkService = ticketLinkService;
	}

	private TicketLinkService _ticketLinkService;
}