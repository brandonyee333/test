/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.web.service.ZendeskAttachmentWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = ZendeskAttachmentWebService.class
)
public class DefaultZendeskAttachmentWebService
	implements ZendeskAttachmentWebService {

	public void deleteZendeskAttachment(
			long zendeskTicketId, long zendeskTicketCommentId,
			long zendeskAttachmentId)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	@Reference
	protected MessagePublisher messagePublisher;

	@Reference
	protected ZendeskConverter zendeskConverter;

}