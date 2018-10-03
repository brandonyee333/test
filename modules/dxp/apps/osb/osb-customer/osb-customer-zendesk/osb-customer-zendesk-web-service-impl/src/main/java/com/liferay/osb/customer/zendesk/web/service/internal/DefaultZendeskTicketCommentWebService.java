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
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskTicketComment;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketCommentWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskTicketCommentWebService.class)
public class DefaultZendeskTicketCommentWebService
	implements ZendeskTicketCommentWebService {

	public ZendeskTicketComment addZendeskTicketComment(
			long zendeskTicketId, long zendeskUserId, String htmlBody)
		throws PortalException {

		JSONObject jsonObject = getZendeskTicketCommentJSONObject(
			zendeskUserId, htmlBody);

		JSONObject responseJSONObject = _zendeskBaseWebService.put(
			ZendeskRESTEndpoints.URL_API_V2 + "tickets/" + zendeskTicketId +
				".json",
			jsonObject.toString());

		JSONObject auditJSONObject = responseJSONObject.getJSONObject("audit");

		JSONArray eventsJSONArray = auditJSONObject.getJSONArray("events");

		for (int i = 0; i < eventsJSONArray.length(); i++) {
			JSONObject eventJSONObject = eventsJSONArray.getJSONObject(i);

			String type = eventJSONObject.getString("type");

			if (type.equals("Comment")) {
				return toZendeskTicketComment(eventJSONObject);
			}
		}

		return null;
	}

	protected JSONObject getZendeskTicketCommentJSONObject(
		long zendeskUserId, String htmlBody) {

		JSONObject commentJSONObject = JSONFactoryUtil.createJSONObject();

		commentJSONObject.put("author_id", zendeskUserId);
		commentJSONObject.put("html_body", htmlBody);

		JSONObject ticketJSONObject = JSONFactoryUtil.createJSONObject();

		ticketJSONObject.put("comment", commentJSONObject);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("ticket", ticketJSONObject);

		return jsonObject;
	}

	protected ZendeskTicketComment toZendeskTicketComment(
		JSONObject jsonObject) {

		ZendeskTicketComment zendeskTicketComment = new ZendeskTicketComment();

		zendeskTicketComment.setZendeskTicketCommentId(
			jsonObject.getLong("id"));

		return zendeskTicketComment;
	}

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}