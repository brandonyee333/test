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

package com.liferay.osb.customer.ticket.attachment.repository;

import com.liferay.osb.customer.ticket.attachment.model.TicketAttachment;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Alan Zhang
 * @author Amos Fong
 */
public interface FileRepositoryWebService {

	public String getDownloadURL(TicketAttachment ticketAttachment)
		throws PortalException;

	public String getToken(String fileRepositoryId, long zendeskTicketId)
		throws PortalException;

	public String getUploadURL(String fileRepositoryId);

	public String updateFile(
			String fileRepositoryId, long zendeskTicketId, String fileName,
			String filePath)
		throws PortalException;

}