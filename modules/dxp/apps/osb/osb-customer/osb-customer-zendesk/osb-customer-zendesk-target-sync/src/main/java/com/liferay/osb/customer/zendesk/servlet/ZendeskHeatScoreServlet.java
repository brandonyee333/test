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
import com.liferay.osb.customer.zendesk.constants.ZendeskHeatScoreConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

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
		HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_NAME + "=com.liferay.osb.customer.zendesk.servlet.ZendeskHeatScoreServlet",
		HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/zendesk-heat-score-servlet/*"
	},
	service = Servlet.class
)
public class ZendeskHeatScoreServlet extends ZendeskBaseServlet {

	public void postHeatScore(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, PortalException {

		JSONObject jsonObject = getRequestJSONObject(request);

		JSONObject fieldsJSONObject = jsonObject.getJSONObject("fields");

		String createdAt = fieldsJSONObject.getString("createdAt");

		OffsetDateTime offsetDateTime = OffsetDateTime.parse(createdAt);

		Instant created = offsetDateTime.toInstant();

		long days = ChronoUnit.DAYS.between(created, Instant.now());

		int ageScore = ZendeskHeatScoreConstants.getAgeScore(days);

		String businessPriority = fieldsJSONObject.getString(
			"businessPriority");

		int businessPriorityScore =
			ZendeskHeatScoreConstants.getBusinessPriorityScore(
				businessPriority);

		String casMetrics = fieldsJSONObject.getString("casMetrics");

		int casMetricsScore = ZendeskHeatScoreConstants.getCASMetricsScore(
			casMetrics);

		String environment = fieldsJSONObject.getString("environment");

		int environmentScore = ZendeskHeatScoreConstants.getEnvironmentScore(
			environment);

		String priority = fieldsJSONObject.getString("priority");

		int priorityScore = ZendeskHeatScoreConstants.getPriorityScore(
			priority);

		String subStatus = fieldsJSONObject.getString("subStatus");

		int subStatusScore = ZendeskHeatScoreConstants.getSubStatusScore(
			subStatus);

		long zendeskTicketId = fieldsJSONObject.getLong("ticketId");
		long heatScoreFieldId = fieldsJSONObject.getLong("heatScoreFieldId");

		int totalHeatScore =
			ageScore + businessPriorityScore + casMetricsScore +
				environmentScore + priorityScore + subStatusScore;

		updateZendeskTicketHeatScore(
			zendeskTicketId, heatScoreFieldId, totalHeatScore);
	}

	protected JSONObject updateZendeskTicketHeatScore(
			long zendeskTicketId, long heatScoreFieldId, int totalHeatScore)
		throws PortalException {

		JSONObject ticketJSONObject = JSONFactoryUtil.createJSONObject();

		JSONArray customFieldsJSONArray = JSONFactoryUtil.createJSONArray();

		JSONObject customFieldJSONObject = JSONFactoryUtil.createJSONObject();

		customFieldJSONObject.put("id", heatScoreFieldId);
		customFieldJSONObject.put("value", totalHeatScore);

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
			"/api/v2/tickets/" + zendeskTicketId + ".json",
			jsonObject.toString());
	}

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}