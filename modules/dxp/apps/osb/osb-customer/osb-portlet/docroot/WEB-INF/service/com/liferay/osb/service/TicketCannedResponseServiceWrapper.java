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
 * Provides a wrapper for {@link TicketCannedResponseService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCannedResponseService
 * @generated
 */
@ProviderType
public class TicketCannedResponseServiceWrapper
	implements TicketCannedResponseService,
		ServiceWrapper<TicketCannedResponseService> {
	public TicketCannedResponseServiceWrapper(
		TicketCannedResponseService ticketCannedResponseService) {
		_ticketCannedResponseService = ticketCannedResponseService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketCannedResponseService.getOSGiServiceIdentifier();
	}

	@Override
	public void incrementUseCount(long ticketCannedResponseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketCannedResponseService.incrementUseCount(ticketCannedResponseId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseService.search(keywords, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketCannedResponse> search(
		java.lang.String name, java.lang.String content, boolean andSearch,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseService.search(name, content, andSearch,
			start, end);
	}

	@Override
	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseService.searchCount(keywords);
	}

	@Override
	public int searchCount(java.lang.String name, java.lang.String content,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketCannedResponseService.searchCount(name, content, andSearch);
	}

	@Override
	public TicketCannedResponseService getWrappedService() {
		return _ticketCannedResponseService;
	}

	@Override
	public void setWrappedService(
		TicketCannedResponseService ticketCannedResponseService) {
		_ticketCannedResponseService = ticketCannedResponseService;
	}

	private TicketCannedResponseService _ticketCannedResponseService;
}