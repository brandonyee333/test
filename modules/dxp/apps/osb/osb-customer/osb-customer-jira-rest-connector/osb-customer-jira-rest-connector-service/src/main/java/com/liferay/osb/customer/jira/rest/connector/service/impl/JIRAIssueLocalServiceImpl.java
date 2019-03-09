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

package com.liferay.osb.customer.jira.rest.connector.service.impl;

import com.liferay.osb.customer.jira.rest.connector.exception.JIRAIssueIssueKeyException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRAIssueIssueTypeException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRAIssueSummaryException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRAResponseException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRAResponseIssueNotFoundException;
import com.liferay.osb.customer.jira.rest.connector.model.JIRAIssue;
import com.liferay.osb.customer.jira.rest.connector.service.base.JIRAIssueLocalServiceBaseImpl;
import com.liferay.osb.customer.jira.rest.connector.util.JIRAHttpUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Noah Sherrill
 */
public class JIRAIssueLocalServiceImpl extends JIRAIssueLocalServiceBaseImpl {

	public JSONObject createJIRAIssue(
			String projectKey, String issueType, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFields, String status)
		throws PortalException {

		validate(issueType, summary);

		JIRAIssue jiraIssue = new JIRAIssue(
			projectKey, issueType, summary, description, assigneeName, labels,
			customFields);

		JSONObject responseJSONObject = JIRAHttpUtil.post(
			"issue", jiraIssue.toJSONObject());

		handleResponseErrors(responseJSONObject);

		if (Validator.isNotNull(status)) {
			_updateJIRAIssueStatus(
				responseJSONObject.getString("key"), status);
		}

		return responseJSONObject;
	}

	public JSONObject getJIRAIssue(String ticketKey) throws PortalException {
		JSONObject responseJSONObject = JIRAHttpUtil.get(
			"issue/" + ticketKey, JSONFactoryUtil.createJSONObject());

		handleResponseErrors(responseJSONObject);

		return responseJSONObject;
	}

	public JSONObject getJIRAIssues(String jql, int startAt, int maxResults)
		throws PortalException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("expand", "renderedFields");
		parameters.put("jql", jql);
		parameters.put("maxResults", String.valueOf(maxResults));
		parameters.put("startAt", String.valueOf(startAt));

		JSONObject responseJSONObject = JIRAHttpUtil.get("search", parameters);

		handleResponseErrors(responseJSONObject);

		return responseJSONObject;
	}

	public JSONObject updateJIRAIssue(
			String projectKey, String ticketKey, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFields, String status)
		throws PortalException {

		validate(ticketKey);

		JIRAIssue jiraIssue = new JIRAIssue(
			projectKey, StringPool.BLANK, summary, description, assigneeName,
			labels, customFields);

		JSONObject responseJSONObject = JIRAHttpUtil.put(
			"issue/" + ticketKey, jiraIssue.toJSONObject());

		handleResponseErrors(responseJSONObject);

		if (Validator.isNotNull(status)) {
			_updateJIRAIssueStatus(ticketKey, status);
		}

		return responseJSONObject;
	}

	protected void handleResponseErrors(JSONObject responseJSONObject)
		throws PortalException {

		JSONArray errorMessagesJSONArray = responseJSONObject.getJSONArray(
			"errorMessages");

		if (errorMessagesJSONArray == null) {
			return;
		}

		if (errorMessagesJSONArray.length() == 0) {
			_log.error(responseJSONObject.toString());

			throw new JIRAResponseException();
		}

		String errorMessage = errorMessagesJSONArray.getString(0);

		if (errorMessage.equals("Issue Does Not Exist")) {
			throw new JIRAResponseIssueNotFoundException(errorMessage);
		}
		else {
			throw new JIRAResponseException(errorMessage);
		}
	}

	protected void validate(String ticketKey) throws PortalException {
		if (Validator.isNull(ticketKey)) {
			throw new JIRAIssueIssueKeyException();
		}
	}

	protected void validate(String issueType, String summary)
		throws PortalException {

		if (Validator.isNull(issueType)) {
			throw new JIRAIssueIssueTypeException();
		}

		if (Validator.isNull(summary)) {
			throw new JIRAIssueSummaryException();
		}
	}

	private Integer _getTransitionId(String ticketKey, String status)
		throws PortalException {

		Integer transitionId = _transitionIds.get(status);

		if (transitionId != null) {
			return transitionId;
		}

		JSONObject responseJSONObject = JIRAHttpUtil.get(
			_getTransitionsEndpoint(ticketKey),
			JSONFactoryUtil.createJSONObject());

		JSONArray transitionsJSONArray = responseJSONObject.getJSONArray(
			"transitions");

		for (int i = 0; i < transitionsJSONArray.length(); ++i) {
			JSONObject transitionJSONObject =
				transitionsJSONArray.getJSONObject(i);

			_transitionIds.put(
				transitionJSONObject.getString("name"),
				transitionJSONObject.getInt("id"));
		}

		return _transitionIds.get(status);
	}

	private String _getTransitionsEndpoint(String ticketKey) {
		return "issue/" + ticketKey + "/transitions";
	}

	private void _updateJIRAIssueStatus(String ticketKey, String status)
		throws PortalException {

		JSONObject updateStatusJSONObject = JSONFactoryUtil.createJSONObject();

		JSONObject transitionJSONObject = JSONFactoryUtil.createJSONObject();

		transitionJSONObject.put("id", _getTransitionId(ticketKey, status));

		updateStatusJSONObject.put("transition", transitionJSONObject);

		JSONObject responseJSONObject = JIRAHttpUtil.post(
			_getTransitionsEndpoint(ticketKey), updateStatusJSONObject);

		handleResponseErrors(responseJSONObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JIRAIssueLocalServiceImpl.class);

	private final Map<String, Integer> _transitionIds = new HashMap<>();

}