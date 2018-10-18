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

import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.repository.FileRepositoryWebService;
import com.liferay.osb.customer.ticket.service.base.TicketAttachmentLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class TicketAttachmentLocalServiceImpl
	extends TicketAttachmentLocalServiceBaseImpl {

	public TicketAttachment addTicketAttachment(
			long userId, long accountEntryId, long zendeskTicketId,
			String fileRepositoryId, String fileName, long fileSize, int type)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long ticketAttachmentId = counterLocalService.increment();

		TicketAttachment ticketAttachment = ticketAttachmentPersistence.create(
			ticketAttachmentId);

		ticketAttachment.setUserId(user.getUserId());
		ticketAttachment.setUserName(user.getFullName());
		ticketAttachment.setCreateDate(new Date());
		ticketAttachment.setAccountEntryId(accountEntryId);
		ticketAttachment.setZendeskTicketId(zendeskTicketId);
		ticketAttachment.setFileRepositoryId(fileRepositoryId);
		ticketAttachment.setFileName(fileName);
		ticketAttachment.setFileSize(fileSize);
		ticketAttachment.setType(type);

		ticketAttachmentPersistence.update(ticketAttachment);

		_fileRepositoryWebService.updateFile(
			fileRepositoryId, zendeskTicketId, fileName,
			ticketAttachment.getFilePath());

		return ticketAttachment;
	}

	public void deleteTicketAttachments(long zendeskTicketId, int[] types)
		throws PortalException {

		List<TicketAttachment> ticketAttachments =
			ticketAttachmentPersistence.findByZTI_T(zendeskTicketId, types);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			_fileRepositoryWebService.deleteFile(
				ticketAttachment.getFileRepositoryId(),
				ticketAttachment.getFilePath());

			ticketAttachmentPersistence.remove(ticketAttachment);
		}
	}

	@ServiceReference(type = FileRepositoryWebService.class)
	private FileRepositoryWebService _fileRepositoryWebService;

}