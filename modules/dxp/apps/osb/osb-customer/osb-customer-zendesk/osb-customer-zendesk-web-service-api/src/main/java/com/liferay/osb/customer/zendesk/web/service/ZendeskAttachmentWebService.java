/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Jenny Chen
 */
@ProviderType
public interface ZendeskAttachmentWebService {

	public void deleteZendeskAttachment(
			long zendeskTicketId, long zendeskTicketCommentId,
			long zendeskAttachmentId)
		throws Exception;

}