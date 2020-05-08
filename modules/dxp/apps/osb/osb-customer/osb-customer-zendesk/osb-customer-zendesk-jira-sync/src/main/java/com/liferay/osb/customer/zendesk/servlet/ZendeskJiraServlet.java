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

import com.liferay.osb.common.restful.servlet.SimpleRestfulServlet;
import com.liferay.osb.common.restful.servlet.exception.NoResourceException;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.jira.rest.connector.service.JIRAIssueRESTService;
import com.liferay.osb.customer.zendesk.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.http.HttpAuthManager;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;

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
public class ZendeskJiraServlet extends SimpleRestfulServlet {

	public void postJiraIssue(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, PortalException {

		postJiraTicket(request, response);
	}

	public void postJiraTicket(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, PortalException {

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

	protected JSONObject getRequestJSONObject(HttpServletRequest request)
		throws PortalException {

		StringBundler sb = new StringBundler();

		String line = null;

		try {
			BufferedReader bufferedReader = request.getReader();

			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}

			bufferedReader.close();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		return JSONFactoryUtil.createJSONObject(sb.toString());
	}

	@Override
	protected String getResourceKey(HttpServletRequest request) {
		return null;
	}

	@Override
	protected String getResourceName(HttpServletRequest request)
		throws NoResourceException {

		String pathInfo = _http.fixPath(request.getPathInfo());

		if (Validator.isNull(pathInfo)) {
			throw new NoResourceException();
		}

		return pathInfo;
	}

	@Override
	protected boolean isAuthorized(HttpServletRequest request) {
		if (!request.isSecure()) {
			return false;
		}

		try {
			long basicUserId = _httpAuthManager.getBasicUserId(request);

			long userId = _userLocalService.getUserIdByEmailAddress(
				OSBCustomerConstants.COMPANY_ID,
				ZendeskConnectorConfigurationValues.ZENDESK_AUTH_USER_EMAIL);

			if (basicUserId != userId) {
				return false;
			}
		}
		catch (PortalException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(pe, pe);
			}

			return false;
		}

		return true;
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

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskJiraServlet.class);

	@Reference
	private Http _http;

	@Reference
	private HttpAuthManager _httpAuthManager;

	@Reference
	private JIRAIssueRESTService _jiraIssueRESTService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}