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

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.web.service.ZendeskAttachmentWebService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
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
		throws PortalException {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "tickets/", zendeskTicketId,
			"/comments/", zendeskTicketCommentId, "/attachments/",
			zendeskAttachmentId, "/redact.json");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "put", null, jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

}