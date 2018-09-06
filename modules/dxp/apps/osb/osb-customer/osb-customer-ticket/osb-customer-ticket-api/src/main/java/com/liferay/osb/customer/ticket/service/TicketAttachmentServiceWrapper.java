/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
		long accountEntryId, long zendeskTicketId, String fileRepositoryId,
		String fileName, long fileSize, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.addTicketAttachment(accountEntryId,
			zendeskTicketId, fileRepositoryId, fileName, fileSize, type);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _ticketAttachmentService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment getTicketAttachment(
		long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketAttachmentService.getTicketAttachment(ticketAttachmentId);
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