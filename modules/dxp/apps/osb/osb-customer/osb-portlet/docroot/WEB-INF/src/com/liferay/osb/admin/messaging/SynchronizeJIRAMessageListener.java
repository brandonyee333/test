/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.admin.messaging;

import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.Format;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* TODO update JIRA integration
import com.liferay.jira.service.JIRATicketLocalServiceUtil;
*/

/**
 * @author Amos Fong
 */
public class SynchronizeJIRAMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String jiraTicketKey = _getJIRATicketKey(message);
		String description = _getDescription(message);
		String assigneeName = message.getString(
			"primaryTicketWorkerScreenName");
		Map<String, Object> customFields = _getCustomFields(message);
		String status = _getStatus(message);

		// TODO remove temporary JIRA integration

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(jiraTicketKey)) {
			/* TODO update JIRA integration
			JSONObject jsonObject = JIRATicketLocalServiceUtil.getJIRATicket(
				jiraTicketKey);

			*/
			String summary = _getSummary(jsonObject, status);
			status = _getStatus(jsonObject, status);

			/* TODO update JIRA integration
			JIRATicketLocalServiceUtil.updateJIRATicket(
				jiraTicketKey, summary, description, assigneeName, customFields,
				status);
			*/
		}
		else {
			String issueType = _getIssueType(message);
			String summary = message.getString("displayId");

			/* TODO update JIRA integration
			JSONObject jsonObject = JIRATicketLocalServiceUtil.createJIRATicket(
				issueType, summary, description, assigneeName, customFields,
				status);
			*/
			long classNameId = PortalUtil.getClassNameId(TicketEntry.class);
			long ticketEntryId = message.getLong("ticketEntryId");
			String externalId = jsonObject.getString("key");

			ExternalIdMapperLocalServiceUtil.addExternalIdMapper(
				classNameId, ticketEntryId, ExternalIdMapperConstants.TYPE_JIRA,
				externalId);
		}
	}

	private JSONArray _getComponents(Message message) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int component = message.getInteger("component");

		if (component == TicketEntryConstants.COMPONENT_AUTHENTICATION) {
			jsonObject.put("id", "12895");
		}
		else if (component == TicketEntryConstants.COMPONENT_CALENDAR) {
			jsonObject.put("id", "17281");
		}
		else if (component == TicketEntryConstants.COMPONENT_CLUSTERING) {
			jsonObject.put("id", "12894");
		}
		else if (component ==
					TicketEntryConstants.COMPONENT_COLLABORATION_SUITE) {

			jsonObject.put("id", "12893");
		}
		else if (component ==
					TicketEntryConstants.COMPONENT_CUSTOM_DEVELOPMENT) {

			jsonObject.put("id", "15880");
		}
		else if (component == TicketEntryConstants.COMPONENT_DOCUMENT_LIBRARY) {
			jsonObject.put("id", "12892");
		}
		else if (component == TicketEntryConstants.COMPONENT_LAR_STAGING) {
			jsonObject.put("id", "12891");
		}
		else if (component == TicketEntryConstants.COMPONENT_LICENSE) {
			jsonObject.put("id", "12898");
		}
		else if (component ==
					TicketEntryConstants.COMPONENT_LICENSE_ACCOUNT_SETUP) {

			jsonObject.put("id", "12898");
		}
		else if (component == TicketEntryConstants.COMPONENT_LIFERAY_API) {
			jsonObject.put("id", "12890");
		}
		else if (component ==
					TicketEntryConstants.COMPONENT_LIFERAY_DEVELOPER_STUDIO) {

			jsonObject.put("id", "12889");
		}
		else if (component == TicketEntryConstants.COMPONENT_LIFERAY_FACES) {
			jsonObject.put("id", "13680");
		}
		else if (component == TicketEntryConstants.COMPONENT_LIFERAY_SYNC) {
			jsonObject.put("id", "12888");
		}
		else if (component == TicketEntryConstants.COMPONENT_PATCH_MANAGEMENT) {
			jsonObject.put("id", "20382");
		}
		else if (component ==
					TicketEntryConstants.COMPONENT_PORTAL_ADMINISTRATION) {

			jsonObject.put("id", "12886");
		}
		else if (component ==
					TicketEntryConstants.COMPONENT_PORTAL_DEPLOYMENT) {

			jsonObject.put("id", "12885");
		}
		else if (component ==
					TicketEntryConstants.COMPONENT_PROJECT_ADMINISTRATION) {

			jsonObject.put("id", "20383");
		}
		else if (component == TicketEntryConstants.COMPONENT_SEARCH_INDEXING) {
			jsonObject.put("id", "12884");
		}
		else if (component == TicketEntryConstants.COMPONENT_SECURITY) {
			jsonObject.put("id", "12883");
		}
		else if (component ==
					TicketEntryConstants.COMPONENT_WEB_CONTENT_MANAGEMENT) {

			jsonObject.put("id", "12880");
		}
		else if (component == TicketEntryConstants.COMPONENT_UI) {
			jsonObject.put("id", "12882");
		}
		else if (component == TicketEntryConstants.COMPONENT_UPGRADE) {
			jsonObject.put("id", "12881");
		}
		else if (component == TicketEntryConstants.COMPONENT_WORKFLOWS_FORMS) {
			jsonObject.put("id", "20381");
		}
		else {
			jsonObject.put("id", "12887");
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(jsonObject);

		return jsonArray;
	}

	private Map<String, Object> _getCustomFields(Message message) {
		Map<String, Object> customFields = new HashMap<>();

		customFields.put("components", _getComponents(message));

		int escalationLevel = message.getInteger("escalationLevel");

		if (escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_P1) {
			customFields.put(
				"customfield_10140", message.getString("partnerEntryCode"));
		}

		customFields.put("customfield_10734", _getLESAAssignees(message));
		customFields.put("customfield_11122", _getEscalationLevel(message));

		Date dueDate = new Date(message.getLong("dueDate"));

		customFields.put("customfield_11125", _dateFormat.format(dueDate));

		Date createDate = new Date(message.getLong("createDate"));

		customFields.put("customfield_11126", _dateFormat.format(createDate));

		customFields.put("customfield_11523", _getSupport(message));
		customFields.put("labels", _getLabels(message));
		customFields.put("priority", _getPriority(message));

		return customFields;
	}

	private String _getDescription(Message message) throws PortalException {
		String displayId = message.getString("displayId");

		String layoutFullURL = PortalUtil.getLayoutFullURL(
			OSBConstants.GROUP_SUPPORT_ID, OSBPortletKeys.OSB_SUPPORT);

		return
			layoutFullURL + Portal.FRIENDLY_URL_SEPARATOR + "support/ticket/" +
				displayId;
	}

	private JSONArray _getEscalationLevel(Message message) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int escalationLevel = message.getInteger("escalationLevel");

		if (escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_1) {
			jsonObject.put("id", "10810");
		}
		else if (escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_2) {
			jsonObject.put("id", "10811");
		}
		else if (escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_P1) {
			jsonObject.put("id", "10819");
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(jsonObject);

		return jsonArray;
	}

	private String _getIssueType(Message message) {
		int escalationLevel = message.getInteger("escalationLevel");
		int supportResponseSupportLevel = message.getInteger(
			"supportResponseSupportLevel");

		if ((escalationLevel == TicketEntryConstants.ESCALATION_LEVEL_P1) &&
			(supportResponseSupportLevel ==
				SupportResponseConstants.SUPPORT_LEVEL_GOLD)) {

			return "Partner - Platinum LESA Ticket";
		}
		else if ((escalationLevel ==
					TicketEntryConstants.ESCALATION_LEVEL_P1) &&
				 (supportResponseSupportLevel ==
					 SupportResponseConstants.SUPPORT_LEVEL_PLATINUM)) {

			return "Partner - Platinum LESA Ticket";
		}
		else if (supportResponseSupportLevel ==
					SupportResponseConstants.SUPPORT_LEVEL_GOLD) {

			return "Gold LESA Ticket";
		}
		else if (supportResponseSupportLevel ==
					SupportResponseConstants.SUPPORT_LEVEL_PLATINUM) {

			return "Platinum LESA Ticket";
		}
		else {
			return "LESA Ticket";
		}
	}

	private String _getJIRATicketKey(Message message) throws PortalException {
		String jiraTicketKey = message.getString("jiraTicketKey");

		if (Validator.isNotNull(jiraTicketKey)) {
			return jiraTicketKey;
		}

		String displayId = message.getString("displayId");

		StringBundler sb = new StringBundler(5);

		sb.append("project = 'SIT' and (summary ~ '");
		sb.append(displayId);
		sb.append("' or summary ~ '");
		sb.append(displayId);
		sb.append(" | REOPENED')");

		// TODO remove temporary JIRA integration

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		/* TODO update JIRA integration
		JSONObject jsonObject = JIRATicketLocalServiceUtil.getJIRATickets(
			sb.toString());

		*/

		JSONArray jsonArray = jsonObject.getJSONArray("issues");

		if ((jsonArray != null) && (jsonArray.length() > 0)) {
			JSONObject issueJSONObject = jsonArray.getJSONObject(0);

			return issueJSONObject.getString("key");
		}

		return StringPool.BLANK;
	}

	private JSONArray _getLabels(Message message) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(message.getString("accountEntryCode"));

		return jsonArray;
	}

	private JSONArray _getLESAAssignees(Message message) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String[] otherTicketWorkerScreenNames = StringUtil.split(
			message.getString("otherTicketWorkerScreenNames"));

		for (String screenName : otherTicketWorkerScreenNames) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", screenName);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private JSONObject _getPriority(Message message) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int severity = message.getInteger("severity");

		if (severity == SupportResponseConstants.SEVERITY_CRITICAL) {
			jsonObject.put("id", "2");
		}
		else if (severity == SupportResponseConstants.SEVERITY_MAJOR) {
			jsonObject.put("id", "3");
		}
		else if (severity == SupportResponseConstants.SEVERITY_MINOR) {
			jsonObject.put("id", "4");
		}

		return jsonObject;
	}

	private String _getStatus(JSONObject jsonObject, String status) {
		JSONObject fieldsJSONObject = jsonObject.getJSONObject("fields");

		JSONObject statusJSONObject = fieldsJSONObject.getJSONObject("status");

		String curStatus = statusJSONObject.getString("name");

		if (curStatus.equals(status)) {
			return StringPool.BLANK;
		}
		else {
			return status;
		}
	}

	private String _getStatus(Message message) {
		int status = message.getInteger("status");

		if (status == TicketEntryConstants.STATUS_BUILDING_PATCH) {
			return "Building Patch";
		}
		else if (status == TicketEntryConstants.STATUS_CLOSED) {
			return "LESA Close";
		}
		else if (status == TicketEntryConstants.STATUS_ENGINEER_ANALYZING) {
			return "Engineer Analyzing";
		}
		else if (status == TicketEntryConstants.STATUS_INACTIVE) {
			return "Inactive";
		}
		else if (status == TicketEntryConstants.STATUS_INCIDENT_REPORTED) {
			return "Incident Reported";
		}
		else if (status == TicketEntryConstants.STATUS_INVESTIGATING) {
			return "Investigating";
		}
		else if (status == TicketEntryConstants.STATUS_ON_HOLD) {
			return "On Hold";
		}
		else if (status == TicketEntryConstants.STATUS_PENDING_WORKER) {
			return "Pending";
		}
		else if (status == TicketEntryConstants.STATUS_REOPENED) {
			return "Reopened";
		}
		else if (status == TicketEntryConstants.STATUS_REPRODUCED) {
			return "Reproduced";
		}
		else if (status == TicketEntryConstants.STATUS_RESOLVED) {
			return "Resolved";
		}
		else if (status == TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) {
			return "Resolved in Production";
		}
		else if (status == TicketEntryConstants.STATUS_SOLUTION_DELIVERED) {
			return "Solution Delivered";
		}
		else if (status == TicketEntryConstants.STATUS_SOLUTION_PROPOSED) {
			return "Solution Proposed";
		}
		else {
			return StringPool.BLANK;
		}
	}

	private String _getSummary(JSONObject jsonObject, String status) {
		JSONObject fieldsJSONObject = jsonObject.getJSONObject("fields");

		JSONObject statusJSONObject = fieldsJSONObject.getJSONObject("status");

		String curStatus = statusJSONObject.getString("name");

		if (!curStatus.equals(status) && status.equals("Reopened")) {
			return fieldsJSONObject.getString("summary") + " | REOPENED";
		}

		return fieldsJSONObject.getString("summary");
	}

	private JSONArray _getSupport(Message message) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		String supportRegionName = message.getString("supportRegionName");

		if (supportRegionName.equals("Australia")) {
			jsonObject.put("id", "11415");
		}
		else if (supportRegionName.equals("Brazil")) {
			jsonObject.put("id", "11418");
		}
		else if (supportRegionName.equals("China")) {
			jsonObject.put("id", "11415");
		}
		else if (supportRegionName.equals("Hungary")) {
			jsonObject.put("id", "11416");
		}
		else if (supportRegionName.equals("India")) {
			jsonObject.put("id", "11440");
		}
		else if (supportRegionName.equals("Japan")) {
			jsonObject.put("id", "16511");
		}
		else if (supportRegionName.equals("Spain")) {
			jsonObject.put("id", "11417");
		}
		else if (supportRegionName.equals("US")) {
			jsonObject.put("id", "11414");
		}
		else {
			jsonObject.put("id", "11452");
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		jsonArray.put(jsonObject);

		return jsonArray;
	}

	private static final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

}