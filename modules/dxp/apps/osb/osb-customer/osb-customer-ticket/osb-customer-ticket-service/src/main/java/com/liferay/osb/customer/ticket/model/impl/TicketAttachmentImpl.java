/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.model.impl;

import com.liferay.osb.customer.ticket.repository.FileRepository;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Amos Fong
 */
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