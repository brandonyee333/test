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

package com.liferay.osb.customer.ticket.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TicketAttachmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentService
 * @generated
 */
@ProviderType
public class TicketAttachmentServiceWrapper implements TicketAttachmentService,
	ServiceWrapper<TicketAttachmentService> {
	public TicketAttachmentServiceWrapper(
		TicketAttachmentService ticketAttachmentService) {
		_ticketAttachmentService = ticketAttachmentService;
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment addTicketAttachment(
		long accountEntryId, long zendeskTicketId,
		java.lang.String fileRepositoryId, java.lang.String fileName,
		long fileSize, int type,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.addTicketAttachment(accountEntryId,
			zendeskTicketId, fileRepositoryId, fileName, fileSize, type,
			serviceContext);
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.getTicketAttachment(ticketAttachmentId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketAttachmentService.getOSGiServiceIdentifier();
	}

	@Override
	public TicketAttachmentService getWrappedService() {
		return _ticketAttachmentService;
	}

	@Override
	public void setWrappedService(
		TicketAttachmentService ticketAttachmentService) {
		_ticketAttachmentService = ticketAttachmentService;
	}

	private TicketAttachmentService _ticketAttachmentService;
}