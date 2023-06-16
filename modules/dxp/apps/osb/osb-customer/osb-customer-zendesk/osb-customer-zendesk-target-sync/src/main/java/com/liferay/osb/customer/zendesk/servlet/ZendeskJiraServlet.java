/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.servlet;

import com.liferay.osb.customer.jira.rest.connector.service.JIRAIssueRESTService;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = {
		HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_NAME + "=com.liferay.osb.customer.zendesk.servlet.ZendeskJiraServlet",
		HttpWhiteboardConstants.HTTP_WHITEBOARD_SERVLET_PATTERN + "=/zendesk-jira-servlet/*"
	},
	service = Servlet.class
)
public class ZendeskJiraServlet extends ZendeskBaseServlet {

	public void postJiraIssue(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		postJiraTicket(request, response);
	}

	public void postJiraTicket(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		JSONObject jsonObject = getRequestJSONObject(request);

		JSONObject fieldsJSONObject = jsonObject.getJSONObject("fields");

		JSONObject projectJSONObject = fieldsJSONObject.getJSONObject(
			"project");

		String projectKey = projectJSONObject.getString("key");

		JSONObject issueTypeJSONObject = fieldsJSONObject.getJSONObject(
			"issuetype");

		String issueType = issueTypeJSONObject.getString("name");

		String summary = fieldsJSONObject.getString("summary");
		String description = fieldsJSONObject.getString("description");

		Set<String> labels = new HashSet<>();

		JSONArray labelsJSONArray = fieldsJSONObject.getJSONArray("labels");

		for (int i = 0; i < labelsJSONArray.length(); i++) {
			labels.add(labelsJSONArray.getString(i));
		}

		long zendeskTicketId = 0;

		Map<String, Object> customFields = new HashMap<>();

		Iterator<String> iterator = fieldsJSONObject.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			if (key.contains("customfield")) {
				Object customField = fieldsJSONObject.get(key);

				if (customField instanceof String) {
					customFields.put(key, customField);

					if (Validator.isDigit((String)customField)) {
						zendeskTicketId = fieldsJSONObject.getLong(key);
					}
				}
				else {
					JSONObject valueJSONObject = fieldsJSONObject.getJSONObject(
						key);

					String value = valueJSONObject.getString("value");

					if (Validator.isNotNull(value) &&
						!value.equals(StringPool.DASH)) {

						customFields.put(key, valueJSONObject);
					}
				}
			}
		}

		JSONObject jiraResponse = _jiraIssueRESTService.createJIRAIssue(
			projectKey, issueType, summary, description, StringPool.BLANK,
			labels, customFields, StringPool.BLANK);

		createZendeskJiraLink(zendeskTicketId, jiraResponse);

		if (projectKey.contains("FLS")) {
			updateZendeskFLSTicketTags(zendeskTicketId);
		}
	}

	protected JSONObject createZendeskJiraLink(
			long zendeskTicketId, JSONObject jiraJSONObject)
		throws PortalException {

		long jiraIssueId = jiraJSONObject.getLong("id");
		String jiraIssueKey = jiraJSONObject.getString("key");

		JSONObject linkJSONObject = JSONFactoryUtil.createJSONObject();

		linkJSONObject.put("issue_id", jiraIssueId);
		linkJSONObject.put("issue_key", jiraIssueKey);
		linkJSONObject.put("ticket_id", zendeskTicketId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("link", linkJSONObject);

		return _zendeskBaseWebService.post(
			"/api/services/jira/links", jsonObject.toString());
	}

	protected JSONObject updateZendeskFLSTicketTags(long zendeskTicketId)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("safe_update", true);

		JSONArray tagsJSONArray = JSONFactoryUtil.createJSONArray();

		tagsJSONArray.put("jira_escalated");

		jsonObject.put("tags", tagsJSONArray);

		SimpleDateFormat updateStampFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		jsonObject.put("updated_stamp", updateStampFormat.format(new Date()));

		return _zendeskBaseWebService.put(
			"/api/v2/tickets/" + zendeskTicketId + "/tags.json",
			jsonObject.toString());
	}

	@Reference
	private JIRAIssueRESTService _jiraIssueRESTService;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}