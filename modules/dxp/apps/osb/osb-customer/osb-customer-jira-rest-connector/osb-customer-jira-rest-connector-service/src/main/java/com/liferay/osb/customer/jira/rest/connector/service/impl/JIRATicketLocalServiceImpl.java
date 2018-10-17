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

import com.liferay.osb.customer.jira.rest.connector.exception.JIRAResponseException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRAResponseTicketNotFoundException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRATicketIssueTypeException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRATicketSummaryException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRATicketTicketKeyException;
import com.liferay.osb.customer.jira.rest.connector.model.JIRATicket;
import com.liferay.osb.customer.jira.rest.connector.service.base.JIRATicketLocalServiceBaseImpl;
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
public class JIRATicketLocalServiceImpl extends JIRATicketLocalServiceBaseImpl {

	public JSONObject createJIRATicket(
			String projectKey, String issueType, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFields, String status)
		throws PortalException {

		validate(issueType, summary);

		JIRATicket jiraTicket = new JIRATicket(
			projectKey, issueType, summary, description, assigneeName, labels,
			customFields);

		JSONObject responseJSONObject = JIRAHttpUtil.post(
			"issue", jiraTicket.toJSONObject());

		handleResponseErrors(responseJSONObject);

		if (Validator.isNotNull(status)) {
			_updateJIRATicketStatus(
				responseJSONObject.getString("key"), status);
		}

		return responseJSONObject;
	}

	public JSONObject getJIRATicket(String ticketKey) throws PortalException {
		JSONObject responseJSONObject = JIRAHttpUtil.get(
			"issue/" + ticketKey, JSONFactoryUtil.createJSONObject());

		handleResponseErrors(responseJSONObject);

		return responseJSONObject;
	}

	public JSONObject getJIRATickets(String jql) throws PortalException {
		JSONObject responseJSONObject = JIRAHttpUtil.get("search", jql);

		handleResponseErrors(responseJSONObject);

		return responseJSONObject;
	}

	public JSONObject updateJIRATicket(
			String projectKey, String ticketKey, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFields, String status)
		throws PortalException {

		validate(ticketKey);

		JIRATicket jiraTicket = new JIRATicket(
			projectKey, StringPool.BLANK, summary, description, assigneeName,
			labels, customFields);

		JSONObject responseJSONObject = JIRAHttpUtil.put(
			"issue/" + ticketKey, jiraTicket.toJSONObject());

		handleResponseErrors(responseJSONObject);

		if (Validator.isNotNull(status)) {
			_updateJIRATicketStatus(ticketKey, status);
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
			throw new JIRAResponseTicketNotFoundException(errorMessage);
		}
		else {
			throw new JIRAResponseException(errorMessage);
		}
	}

	protected void validate(String ticketKey) throws PortalException {
		if (Validator.isNull(ticketKey)) {
			throw new JIRATicketTicketKeyException();
		}
	}

	protected void validate(String issueType, String summary)
		throws PortalException {

		if (Validator.isNull(issueType)) {
			throw new JIRATicketIssueTypeException();
		}

		if (Validator.isNull(summary)) {
			throw new JIRATicketSummaryException();
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

	private void _updateJIRATicketStatus(String ticketKey, String status)
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
		JIRATicketLocalServiceImpl.class);

	private final Map<String, Integer> _transitionIds = new HashMap<>();

}