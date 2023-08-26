/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.servlet;

import com.liferay.osb.customer.zendesk.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskFeedbackConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketCommentWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;

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

	public void getTicketInfo(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, PortalException {

		doOptions(request, response);

		String ticketToken = request.getHeader("Ticket-Token");

		long ticketId = GetterUtil.getLong(
			ticketToken.substring(
				0, ticketToken.indexOf(StringPool.UNDERLINE)));

		ZendeskTicket zendeskTicket = _zendeskTicketWebService.getZendeskTicket(
			ticketId);

		String status = zendeskTicket.getStatus();

		if (status.equalsIgnoreCase("closed")) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);

			return;
		}

		ZendeskUser zendeskUser = _zendeskUserWebService.getZendeskUser(
			zendeskTicket.getAssigneeId());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("assigneeName", zendeskUser.getName());

		jsonObject.put(
			"ticketTitle",
			StringBundler.concat(
				zendeskTicket.getSubject(), " (#", String.valueOf(ticketId),
				")"));

		PrintWriter printWriter = response.getWriter();

		printWriter.write(jsonObject.toString());
		printWriter.flush();

		printWriter.close();
	}

	public void postSurveyForm(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		doOptions(request, response);

		JSONObject jsonObject = getRequestJSONObject(request);

		String ces1Rating = jsonObject.getString("ces1_rating");
		String csat1Rating = jsonObject.getString("csat1_rating");
		String satisfactionComment = jsonObject.getString(
			"ticket_satisfaction_comment");

		_validateResponses(ces1Rating, csat1Rating);

		String ticketToken = request.getHeader("Ticket-Token");

		long ticketId = GetterUtil.getLong(
			ticketToken.substring(
				0, ticketToken.indexOf(StringPool.UNDERLINE)));

		ZendeskUser zendeskUser = _zendeskUserWebService.getZendeskUserByEmail(
			ZendeskConnectorConfigurationValues.ZENDESK_ADMIN_USER_EMAIL);

		_zendeskTicketCommentWebService.addAgentZendeskTicketComment(
			ticketId, zendeskUser.getZendeskUserId(),
			buildTicketCommentBody(
				ces1Rating, csat1Rating, satisfactionComment),
			false);

		JSONObject tagsJSONObject = JSONFactoryUtil.createJSONObject();

		JSONArray tagsJSONArray = JSONFactoryUtil.createJSONArray();

		tagsJSONArray.put(ces1Rating);
		tagsJSONArray.put(csat1Rating);

		if (Validator.isNotNull(satisfactionComment)) {
			tagsJSONArray.put(ZendeskFeedbackConstants.SURVEY_COMMENT);
		}

		tagsJSONObject.put("tags", tagsJSONArray);

		tagsJSONObject.put("safe_update", true);

		SimpleDateFormat updateStampFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		tagsJSONObject.put(
			"updated_stamp", updateStampFormat.format(new Date()));

		_zendeskBaseWebService.put(
			"/api/v2/tickets/" + ticketId + "/tags.json",
			tagsJSONObject.toString());
	}

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

	protected String buildTicketCommentBody(
		String ces1Rating, String csat1Rating, String satisfactionComment) {

		StringBundler sb = new StringBundler(6);

		sb.append("CES 1 Rating: ");
		sb.append(ZendeskFeedbackConstants.getRatingLabel(ces1Rating));
		sb.append("<br />CSAT 1 Rating: ");
		sb.append(ZendeskFeedbackConstants.getRatingLabel(csat1Rating));

		if (Validator.isNotNull(satisfactionComment)) {
			sb.append("<br /><br />Ticket Satisfaction Comment: <br />");
			sb.append(HtmlUtil.escape(satisfactionComment));
		}

		return sb.toString();
	}

	@Override
	protected void doOptions(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		httpServletResponse.setHeader(
			"Access-Control-Allow-Origin",
			ZendeskConnectorConfigurationValues.ZENDESK_SUBDOMAIN);
		httpServletResponse.setHeader(
			"Access-Control-Allow-Methods", "GET, POST");
		httpServletResponse.setHeader(
			"Access-Control-Allow-Headers", "Content-Type, Ticket-Token");

		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	protected boolean isAuthorized(HttpServletRequest request) {
		if (!request.isSecure()) {
			return false;
		}

		String method = request.getMethod();
		String pathInfo = request.getPathInfo();

		if (method.equals("POST") && pathInfo.equals("/ticket-token/")) {
			return super.isAuthorized(request);
		}

		try {
			String ticketToken = request.getHeader("Ticket-Token");

			long ticketId = GetterUtil.getLong(
				ticketToken.substring(
					0, ticketToken.indexOf(StringPool.UNDERLINE)));

			ZendeskTicket zendeskTicket =
				_zendeskTicketWebService.getZendeskTicket(ticketId);

			Map<Long, String> customFields = zendeskTicket.getCustomFields();

			if (ticketToken.equals(
					customFields.get(
						ZendeskConnectorConfigurationValues.
							ZENDESK_TICKET_TOKEN_CUSTOM_FIELD_ID))) {

				return true;
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}

			return false;
		}

		return false;
	}

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String requestMethod = request.getMethod();

		if (requestMethod.equals("OPTIONS")) {
			doOptions(request, response);
		}
		else {
			super.service(request, response);
		}
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

	private void _validateResponses(String ces1Rating, String csat1Rating) {
		if (!ces1Rating.matches("ces1_[1-5]")) {
			throw new IllegalArgumentException(
				"Invalid ces1 value: " + ces1Rating);
		}

		if (!csat1Rating.matches("csat1_[1-5]")) {
			throw new IllegalArgumentException(
				"Invalid csat value: " + csat1Rating);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskFeedbackServlet.class);

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

	@Reference
	private ZendeskTicketCommentWebService _zendeskTicketCommentWebService;

	@Reference
	private ZendeskTicketWebService _zendeskTicketWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}