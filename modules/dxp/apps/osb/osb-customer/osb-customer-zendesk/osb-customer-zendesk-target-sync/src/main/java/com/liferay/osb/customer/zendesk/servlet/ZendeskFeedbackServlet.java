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

package com.liferay.osb.customer.zendesk.servlet;

import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = {
		HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_NAME + "=com.liferay.osb.customer.zendesk.servlet.ZendeskFeedbackServlet",
		HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/zendesk-feedback-servlet/*"
	},
	service = Servlet.class
)
public class ZendeskFeedbackServlet extends ZendeskBaseServlet {

	public void postTicketToken(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		JSONObject jsonObject = getRequestJSONObject(request);

		JSONObject fieldsJSONObject = jsonObject.getJSONObject("fields");

		long ticketId = fieldsJSONObject.getLong("ticketId");
		long ticketTokenFieldId = fieldsJSONObject.getLong(
			"ticketTokenFieldId");

		if ((ticketId <= 0) && (ticketTokenFieldId <= 0)) {
			return;
		}

		String token =
			ticketId + StringPool.UNDERLINE + PortalUUIDUtil.generate();

		updateZendeskTicketToken(ticketId, ticketTokenFieldId, token);
	}

	protected JSONObject updateZendeskTicketToken(
			long ticketId, long ticketTokenFieldId, String token)
		throws PortalException {

		JSONObject ticketJSONObject = JSONFactoryUtil.createJSONObject();

		JSONArray customFieldsJSONArray = JSONFactoryUtil.createJSONArray();

		JSONObject customFieldJSONObject = JSONFactoryUtil.createJSONObject();

		customFieldJSONObject.put("id", ticketTokenFieldId);
		customFieldJSONObject.put("value", token);

		customFieldsJSONArray.put(customFieldJSONObject);

		ticketJSONObject.put("custom_fields", customFieldsJSONArray);

		ticketJSONObject.put("safe_update", true);

		SimpleDateFormat updateStampFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		ticketJSONObject.put(
			"updated_stamp", updateStampFormat.format(new Date()));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("ticket", ticketJSONObject);

		return _zendeskBaseWebService.put(
			"/api/v2/tickets/" + ticketId + ".json", jsonObject.toString());
	}

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}