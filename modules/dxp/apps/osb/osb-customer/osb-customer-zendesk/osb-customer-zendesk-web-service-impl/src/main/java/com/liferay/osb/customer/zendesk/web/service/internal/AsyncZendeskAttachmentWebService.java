/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.web.service.ZendeskAttachmentWebService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = {"async=true", "service.ranking:Integer=1"},
	service = ZendeskAttachmentWebService.class
)
public class AsyncZendeskAttachmentWebService
	extends DefaultZendeskAttachmentWebService
	implements ZendeskAttachmentWebService {

	@Override
	public void deleteZendeskAttachment(
			long zendeskTicketId, long zendeskTicketCommentId,
			long zendeskAttachmentId)
		throws Exception {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "tickets/",
			String.valueOf(zendeskTicketId), "/comments/",
			String.valueOf(zendeskTicketCommentId), "/attachments/",
			String.valueOf(zendeskAttachmentId), "/redact.json");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "put", null, jsonObject, null);

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

}