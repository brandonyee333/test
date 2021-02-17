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

	public TicketAttachment getTicketAttachment(long ticketAttachmentId)
		throws PortalException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		TicketAttachmentPermissionChecker.check(
			getPermissionChecker(), ticketAttachment.getAccountEntryId(),
			TicketActionKeys.VIEW);

		return ticketAttachment;
	}

}