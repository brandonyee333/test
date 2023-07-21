/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TicketAttachmentService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentService
 * @generated
 */
public class TicketAttachmentServiceWrapper
	implements ServiceWrapper<TicketAttachmentService>,
			   TicketAttachmentService {

	public TicketAttachmentServiceWrapper(
		TicketAttachmentService ticketAttachmentService) {

		_ticketAttachmentService = ticketAttachmentService;
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
			addTicketAttachment(
				long accountEntryId, long zendeskTicketId,
				String fileRepositoryId, String fileName, long fileSize,
				int type, boolean regionRestricted,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentService.addTicketAttachment(
			accountEntryId, zendeskTicketId, fileRepositoryId, fileName,
			fileSize, type, regionRestricted, serviceContext);
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
			deleteTicketAttachment(long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentService.deleteTicketAttachment(
			ticketAttachmentId);
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
	public com.liferay.osb.customer.ticket.model.TicketAttachment
			getTicketAttachment(long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentService.getTicketAttachment(ticketAttachmentId);
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
			removeRegionRestriction(long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentService.removeRegionRestriction(
			ticketAttachmentId);
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