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

package com.liferay.osb.customer.ticket.attachment.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.ticket.attachment.repository.FileRepository;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Amos Fong
 */
@ProviderType
public class TicketAttachmentImpl extends TicketAttachmentBaseImpl {

	public TicketAttachmentImpl() {
	}

	public String getFilePath() {
		StringBundler sb = new StringBundler(6);

		sb.append(FileRepository.DIR_ZENDESK_TICKET);
		sb.append(getZendeskTicketId());
		sb.append(StringPool.SLASH);
		sb.append(getTicketAttachmentId());
		sb.append(StringPool.SLASH);
		sb.append(getFileName());

		return sb.toString();
	}

}