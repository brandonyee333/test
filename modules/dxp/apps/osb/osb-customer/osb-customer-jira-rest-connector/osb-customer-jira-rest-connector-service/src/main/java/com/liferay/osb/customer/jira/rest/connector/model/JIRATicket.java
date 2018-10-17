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

package com.liferay.osb.customer.jira.rest.connector.model;

import com.liferay.osb.customer.jira.rest.connector.exception.JIRATicketCustomFieldNameException;
import com.liferay.osb.customer.jira.rest.connector.exception.JIRATicketCustomFieldValueException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Noah Sherrill
 */
public class JIRATicket {

	public JIRATicket() {
	}

	public JIRATicket(
		String projectKey, String issueType, String summary, String description,
		Set<String> labels) {

		_projectKey = projectKey;
		_issueType = issueType;
		_summary = summary;
		_description = description;
		_labels = labels;
	}

	public JIRATicket(
			String projectKey, String issueType, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFieldValues)
		throws PortalException {

		this(projectKey, issueType, summary, description, labels);

		_assigneeName = assigneeName;

		for (Map.Entry<String, Object> entry : customFieldValues.entrySet()) {
			addCustomField(entry.getKey(), entry.getValue());
		}
	}

	public void addCustomField(String customFieldName, Object customFieldValue)
		throws PortalException {

		if (Validator.isNull(customFieldName)) {
			throw new JIRATicketCustomFieldNameException();
		}

		if (customFieldValue == null) {
			throw new JIRATicketCustomFieldValueException();
		}

		_customFieldValues.put(customFieldName, customFieldValue);
	}

	public String getAssigneeName() {
		return _assigneeName;
	}

	public Object getCustomFieldValue(String customFieldName)
		throws PortalException {

		if (!_customFieldValues.containsKey(customFieldName)) {
			throw new JIRATicketCustomFieldNameException();
		}

		return _customFieldValues.get(customFieldName);
	}

	public String getDescription() {
		return _description;
	}

	public String getIssueType() {
		return _issueType;
	}

	public Set<String> getLabels() {
		return _labels;
	}

	public String getProjectKey() {
		return _projectKey;
	}

	public String getStatus() {
		return _status;
	}

	public String getSummary() {
		return _summary;
	}

	public String getTicketKey() {
		return _ticketKey;
	}

	public void removeCustomField(String customFieldName)
		throws PortalException {

		if (!_customFieldValues.containsKey(customFieldName)) {
			throw new JIRATicketCustomFieldNameException();
		}

		_customFieldValues.remove(customFieldName);
	}

	public void setAssigneeName(String assigneeName) {
		_assigneeName = assigneeName;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setIssueType(String issueType) {
		_issueType = issueType;
	}

	public void setLabels(Set<String> labels) {
		_labels = labels;
	}

	public void setProjectKey(String projectKey) {
		_projectKey = projectKey;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setSummary(String summary) {
		_summary = summary;
	}

	public void setTicketKey(String ticketKey) {
		_ticketKey = ticketKey;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject fieldsJSONObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(_assigneeName)) {
			JSONObject assigneeJSONObject = JSONFactoryUtil.createJSONObject();

			assigneeJSONObject.put("name", _assigneeName);

			fieldsJSONObject.put("assignee", assigneeJSONObject);
		}

		if (Validator.isNotNull(_description)) {
			fieldsJSONObject.put("description", _description);
		}

		if (Validator.isNotNull(_issueType)) {
			JSONObject issueTypeJSONObject = JSONFactoryUtil.createJSONObject();

			issueTypeJSONObject.put("name", _issueType);

			fieldsJSONObject.put("issuetype", issueTypeJSONObject);
		}

		if (Validator.isNotNull(_labels)) {
			JSONArray labelsJSONArray = JSONFactoryUtil.createJSONArray();

			for (String label : _labels) {
				labelsJSONArray.put(label);
			}

			fieldsJSONObject.put("labels", labelsJSONArray);
		}

		if (Validator.isNotNull(_ticketKey)) {
			fieldsJSONObject.put("key", _ticketKey);
		}

		if (Validator.isNotNull(_projectKey)) {
			JSONObject projectJSONObject = JSONFactoryUtil.createJSONObject();

			projectJSONObject.put("key", _projectKey);

			fieldsJSONObject.put("project", projectJSONObject);
		}

		if (Validator.isNotNull(_status)) {
			JSONObject statusJSONObject = JSONFactoryUtil.createJSONObject();

			statusJSONObject.put("name", _status);

			fieldsJSONObject.put("status", statusJSONObject);
		}

		if (Validator.isNotNull(_summary)) {
			fieldsJSONObject.put("summary", _summary);
		}

		for (Map.Entry<String, Object> entry : _customFieldValues.entrySet()) {
			_putCustomFieldValue(
				fieldsJSONObject, entry.getKey(), entry.getValue());
		}

		jsonObject.put("fields", fieldsJSONObject);

		return jsonObject;
	}

	private void _putCustomFieldValue(
		JSONObject jsonObject, String customFieldName,
		Object customFieldValue) {

		if (customFieldValue instanceof Boolean) {
			jsonObject.put(customFieldName, (Boolean)customFieldValue);
		}
		else if (customFieldValue instanceof Date) {
			jsonObject.put(customFieldName, (Date)customFieldValue);
		}
		else if (customFieldValue instanceof Double) {
			jsonObject.put(customFieldName, (Double)customFieldValue);
		}
		else if (customFieldValue instanceof Integer) {
			jsonObject.put(customFieldName, (Integer)customFieldValue);
		}
		else if (customFieldValue instanceof JSONArray) {
			jsonObject.put(customFieldName, (JSONArray)customFieldValue);
		}
		else if (customFieldValue instanceof JSONObject) {
			jsonObject.put(customFieldName, (JSONObject)customFieldValue);
		}
		else if (customFieldValue instanceof Long) {
			jsonObject.put(customFieldName, (Long)customFieldValue);
		}
		else {
			jsonObject.put(customFieldName, String.valueOf(customFieldValue));
		}
	}

	private String _assigneeName;
	private Map<String, Object> _customFieldValues = new HashMap<>();
	private String _description;
	private String _issueType;
	private Set<String> _labels;
	private String _projectKey;
	private String _status;
	private String _summary;
	private String _ticketKey;

}