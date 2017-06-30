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
 * This class is a wrapper for {@link TicketCannedResponseService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketCannedResponseService
 * @generated
 */
public class TicketCannedResponseServiceWrapper
	implements TicketCannedResponseService,
		ServiceWrapper<TicketCannedResponseService> {
	public TicketCannedResponseServiceWrapper(
		TicketCannedResponseService ticketCannedResponseService) {
		_ticketCannedResponseService = ticketCannedResponseService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _ticketCannedResponseService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ticketCannedResponseService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketCannedResponseService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void incrementUseCount(long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_ticketCannedResponseService.incrementUseCount(ticketCannedResponseId);
	}

	public java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseService.search(keywords, start, end);
	}

	public java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String name, java.lang.String content, boolean andSearch,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseService.search(name, content, andSearch,
			start, end);
	}

	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseService.searchCount(keywords);
	}

	public int searchCount(java.lang.String name, java.lang.String content,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _ticketCannedResponseService.searchCount(name, content, andSearch);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public TicketCannedResponseService getWrappedTicketCannedResponseService() {
		return _ticketCannedResponseService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedTicketCannedResponseService(
		TicketCannedResponseService ticketCannedResponseService) {
		_ticketCannedResponseService = ticketCannedResponseService;
	}

	public TicketCannedResponseService getWrappedService() {
		return _ticketCannedResponseService;
	}

	public void setWrappedService(
		TicketCannedResponseService ticketCannedResponseService) {
		_ticketCannedResponseService = ticketCannedResponseService;
	}

	private TicketCannedResponseService _ticketCannedResponseService;
}