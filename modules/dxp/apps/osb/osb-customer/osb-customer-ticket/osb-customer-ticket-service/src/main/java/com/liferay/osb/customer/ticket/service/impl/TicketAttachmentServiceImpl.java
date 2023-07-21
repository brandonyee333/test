/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.service.impl;

import com.liferay.osb.customer.ticket.constants.TicketActionKeys;
import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.service.base.TicketAttachmentServiceBaseImpl;
import com.liferay.osb.customer.ticket.service.permission.TicketAttachmentPermissionChecker;
import com.liferay.osb.customer.ticket.service.permission.TicketEntryPermissionChecker;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

/**
 * @author Amos Fong
 */
public class TicketAttachmentServiceImpl
	extends TicketAttachmentServiceBaseImpl {

	public TicketAttachment addTicketAttachment(
			long accountEntryId, long zendeskTicketId, String fileRepositoryId,
			String fileName, long fileSize, int type, boolean regionRestricted,
			ServiceContext serviceContext)
		throws PortalException {

		TicketEntryPermissionChecker.check(
			getPermissionChecker(), accountEntryId,
			TicketActionKeys.ADD_TICKET_ATTACHMENT);

		return ticketAttachmentLocalService.addTicketAttachment(
			getUserId(), accountEntryId, zendeskTicketId, fileRepositoryId,
			fileName, fileSize, type, regionRestricted, serviceContext);
	}

	public TicketAttachment deleteTicketAttachment(long ticketAttachmentId)
		throws PortalException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		TicketAttachmentPermissionChecker.check(
			getPermissionChecker(), ticketAttachment, TicketActionKeys.DELETE);

		return ticketAttachmentLocalService.deleteTicketAttachment(
			ticketAttachmentId);
	}

	public TicketAttachment getTicketAttachment(long ticketAttachmentId)
		throws PortalException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		TicketAttachmentPermissionChecker.check(
			getPermissionChecker(), ticketAttachment, TicketActionKeys.VIEW);

		return ticketAttachment;
	}

	public TicketAttachment removeRegionRestriction(long ticketAttachmentId)
		throws PortalException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		TicketAttachmentPermissionChecker.check(
			getPermissionChecker(), ticketAttachment, TicketActionKeys.UPDATE);

		return ticketAttachmentLocalService.removeRegionRestriction(
			ticketAttachmentId);
	}

}