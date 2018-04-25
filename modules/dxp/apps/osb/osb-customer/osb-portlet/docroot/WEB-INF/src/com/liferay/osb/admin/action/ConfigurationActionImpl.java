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

package com.liferay.osb.admin.action;

import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 * @author Joan Kim
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/admin/configuration.jsp";
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		PortletPreferences preferences = actionRequest.getPreferences();

		String tabs1 = ParamUtil.getString(actionRequest, "tabs1");

		if (tabs1.equals("email-notifications")) {
			updateEmailNotifications(actionRequest, preferences);
		}
		else if (tabs1.equals("rabbitmq")) {
			updateRabbitMQ(actionRequest, preferences);
		}
		else if (tabs1.equals("support")) {
			updateSupport(actionRequest, preferences);
		}
		else {
			updateTrial(actionRequest, preferences);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			SessionMessages.add(
				actionRequest,
				portletConfig.getPortletName() +
					SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
		}
	}

	protected void updateAssignmentRatio(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		long supportRegionId = ParamUtil.getLong(
			actionRequest, "supportRegionId");

		double ratio = ParamUtil.getDouble(actionRequest, "ratio");

		preferences.setValue(
			supportRegionId + "_assignmentRatio", StringUtil.valueOf(ratio));

		preferences.store();
	}

	protected void updateAttachmentKeywords(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String attachmentKeywords = ParamUtil.getString(
			actionRequest, "attachmentKeywords");

		preferences.setValue("attachmentKeywords", attachmentKeywords);

		preferences.store();
	}

	protected void updateBanner(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String announcementTitle = ParamUtil.getString(
			actionRequest, "announcementTitle");
		String announcementContent = ParamUtil.getString(
			actionRequest, "announcementContent");

		int announcementDisplayDateDay = ParamUtil.getInteger(
			actionRequest, "announcementDisplayDateDay");
		int announcementDisplayDateMonth = ParamUtil.getInteger(
			actionRequest, "announcementDisplayDateMonth");
		int announcementDisplayDateYear = ParamUtil.getInteger(
			actionRequest, "announcementDisplayDateYear");
		int announcementDisplayDateHour = ParamUtil.getInteger(
			actionRequest, "announcementDisplayDateHour");
		int announcementDisplayDateMinute = ParamUtil.getInteger(
			actionRequest, "announcementDisplayDateMinute");
		int announcementDisplayDateAmPm = ParamUtil.getInteger(
			actionRequest, "announcementDisplayDateAmPm");

		if (announcementDisplayDateAmPm == Calendar.PM) {
			announcementDisplayDateHour += 12;
		}

		Date announcementDisplayDate = PortalUtil.getDate(
			announcementDisplayDateMonth, announcementDisplayDateDay,
			announcementDisplayDateYear, announcementDisplayDateHour,
			announcementDisplayDateMinute,
			(Class<? extends PortalException>)null);

		int announcementExpirationDateDay = ParamUtil.getInteger(
			actionRequest, "announcementExpirationDateDay");
		int announcementExpirationDateMonth = ParamUtil.getInteger(
			actionRequest, "announcementExpirationDateMonth");
		int announcementExpirationDateYear = ParamUtil.getInteger(
			actionRequest, "announcementExpirationDateYear");
		int announcementExpirationDateHour = ParamUtil.getInteger(
			actionRequest, "announcementExpirationDateHour");
		int announcementExpirationDateMinute = ParamUtil.getInteger(
			actionRequest, "announcementExpirationDateMinute");
		int announcementExpirationDateAmPm = ParamUtil.getInteger(
			actionRequest, "announcementExpirationDateAmPm");

		if (announcementExpirationDateAmPm == Calendar.PM) {
			announcementExpirationDateHour += 12;
		}

		Date announcementExpirationDate = PortalUtil.getDate(
			announcementExpirationDateMonth, announcementExpirationDateDay,
			announcementExpirationDateYear, announcementExpirationDateHour,
			announcementExpirationDateMinute,
			(Class<? extends PortalException>)null);

		if (announcementDisplayDate.after(announcementExpirationDate)) {
			SessionErrors.add(actionRequest, "announcementDateInvalid");

			return;
		}

		preferences.setValue(
			"announcementTitle_" + languageId, announcementTitle);
		preferences.setValue(
			"announcementContent_" + languageId, announcementContent);
		preferences.setValue(
			"announcementExpirationDate",
			String.valueOf(announcementExpirationDate.getTime()));
		preferences.setValue(
			"announcementDisplayDate",
			String.valueOf(announcementDisplayDate.getTime()));

		preferences.store();
	}

	protected void updateComments(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String tabs3 = ParamUtil.getString(actionRequest, "tabs3");
		String commentValue = ParamUtil.getString(
			actionRequest, "commentValue");

		String commentKey = AdminUtil.getCommentPreferenceKey(
			tabs3, languageId);

		preferences.setValue(commentKey, commentValue);

		preferences.store();
	}

	protected void updateComponentMessage(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String productDisplayName = ParamUtil.getString(
			actionRequest, "productDisplayName");
		String component = ParamUtil.getString(actionRequest, "component");
		String componentMessage = ParamUtil.getString(
			actionRequest, "componentMessage");
		String componentMessageLink = ParamUtil.getString(
			actionRequest, "componentMessageLink");
		String componentLink = ParamUtil.getString(
			actionRequest, "componentLink");
		boolean showInTicketView = ParamUtil.getBoolean(
			actionRequest, "showInTicketView");

		if (Validator.isNotNull(componentLink) &&
			!Validator.isUrl(componentLink)) {

			SessionErrors.add(actionRequest, "componentLinkInvalid");
		}

		if (Validator.isNotNull(componentMessageLink) &&
			!Validator.isUrl(componentMessageLink)) {

			SessionErrors.add(actionRequest, "componentMessageLinkInvalid");
		}

		if (!SessionErrors.isEmpty(actionRequest)) {
			return;
		}

		String componentLinkKey = AdminUtil.getPreferenceKey(
			"componentLink_", productDisplayName, component);

		preferences.setValue(componentLinkKey, componentLink);

		String componentMessageKey = AdminUtil.getComponentPreferenceKey(
			"componentMessage_", productDisplayName, component, languageId);

		preferences.setValue(componentMessageKey, componentMessage);

		String componentMessageLinkKey = AdminUtil.getPreferenceKey(
			"componentMessageLink_", productDisplayName, component);

		preferences.setValue(componentMessageLinkKey, componentMessageLink);

		String showInTicketViewKey = AdminUtil.getPreferenceKey(
			"showInTicketView_", productDisplayName, component);

		preferences.setValue(
			showInTicketViewKey, String.valueOf(showInTicketView));

		preferences.store();
	}

	protected void updateDXPMessage(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String dxpTitle = ParamUtil.getString(actionRequest, "dxpTitle");
		String dxpMessage = ParamUtil.getString(actionRequest, "dxpMessage");

		preferences.setValue("dxpTitle", dxpTitle);
		preferences.setValue("dxpMessage", dxpMessage);

		preferences.store();
	}

	protected void updateEmailNotifications(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("liferay-feedback")) {
			String languageId = LanguageUtil.getLanguageId(actionRequest);

			String emailCustomerFeedbackSubject = ParamUtil.getString(
				actionRequest, "emailCustomerFeedbackSubject_" + languageId);

			preferences.setValue(
				"emailCustomerFeedbackSubject_" + languageId,
				emailCustomerFeedbackSubject);

			String emailCustomerFeedbackBody = ParamUtil.getString(
				actionRequest, "emailCustomerFeedbackBody_" + languageId);

			preferences.setValue(
				"emailCustomerFeedbackBody_" + languageId,
				emailCustomerFeedbackBody);

			preferences.store();
		}
		else if (tabs2.equals("provisioning-create-account")) {
			String languageId = LanguageUtil.getLanguageId(actionRequest);

			String emailProvisioningCreateAccountSubject = ParamUtil.getString(
				actionRequest,
				"emailProvisioningCreateAccountSubject_" + languageId);

			preferences.setValue(
				"emailProvisioningCreateAccountSubject_" + languageId,
				emailProvisioningCreateAccountSubject);

			String emailProvisioningCreateAccountBody = ParamUtil.getString(
				actionRequest,
				"emailProvisioningCreateAccountBody_" + languageId);

			preferences.setValue(
				"emailProvisioningCreateAccountBody_" + languageId,
				emailProvisioningCreateAccountBody);

			preferences.store();
		}
		else if (tabs2.equals("ticket-notification")) {
			String languageId = LanguageUtil.getLanguageId(actionRequest);

			String emailTicketEntrySubject = ParamUtil.getString(
				actionRequest, "emailTicketEntrySubject_" + languageId);

			preferences.setValue(
				"emailTicketEntrySubject_" + languageId,
				emailTicketEntrySubject);

			String emailTicketEntryBody = ParamUtil.getString(
				actionRequest, "emailTicketEntryBody_" + languageId);

			preferences.setValue(
				"emailTicketEntryBody_" + languageId, emailTicketEntryBody);

			String emailTicketEntryCommentTemplate = ParamUtil.getString(
				actionRequest, "emailTicketEntryCommentTemplate_" + languageId);

			preferences.setValue(
				"emailTicketEntryCommentTemplate_" + languageId,
				emailTicketEntryCommentTemplate);

			String emailTicketEntryDueDateTemplate = ParamUtil.getString(
				actionRequest, "emailTicketEntryDueDateTemplate_" + languageId);

			preferences.setValue(
				"emailTicketEntryDueDateTemplate_" + languageId,
				emailTicketEntryDueDateTemplate);

			preferences.store();
		}
		else {
			String emailFromAddress = ParamUtil.getString(
				actionRequest, "emailFromAddress");

			if (Validator.isNotNull(emailFromAddress) &&
				!Validator.isEmailAddress(emailFromAddress)) {

				SessionErrors.add(actionRequest, "emailFromAddress");
			}
			else {
				String emailFromName = ParamUtil.getString(
					actionRequest, "emailFromName");

				preferences.setValue("emailFromName", emailFromName);

				preferences.setValue("emailFromAddress", emailFromAddress);

				preferences.store();
			}
		}
	}

	protected void updateEscalationDetails(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String escalationDetails = ParamUtil.getString(
			actionRequest, "escalationDetails_" + languageId);

		preferences.setValue(
			"escalationDetails_" + languageId, escalationDetails);

		preferences.store();
	}

	protected void updateFileRepositories(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String fileRepositoryId = ParamUtil.getString(
			actionRequest, "fileRepositoryId");

		String name = ParamUtil.getString(actionRequest, "name");
		String host = ParamUtil.getString(actionRequest, "host");
		String status = ParamUtil.getString(actionRequest, "status");
		String[] supportRegions = ParamUtil.getParameterValues(
			actionRequest, "supportRegions");

		String fileRepositories = preferences.getValue(
			"fileRepositories", StringPool.BLANK);

		UnicodeProperties fileRepositoriesProperties = new UnicodeProperties(
			true);

		fileRepositoriesProperties.fastLoad(fileRepositories);

		UnicodeProperties serverProperties = new UnicodeProperties(true);

		serverProperties.setProperty("fileRepositoryId", fileRepositoryId);
		serverProperties.setProperty("host", host);
		serverProperties.setProperty("name", name);
		serverProperties.setProperty(
			"supportRegionIds", StringUtil.merge(supportRegions));
		serverProperties.setProperty("status", status);

		fileRepositoriesProperties.setProperty(
			fileRepositoryId, serverProperties.toString());

		preferences.setValue(
			"fileRepositories", fileRepositoriesProperties.toString());

		preferences.store();
	}

	protected void updateProductLink(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String productEntryDisplayName = ParamUtil.getString(
			actionRequest, "productEntryDisplayName");
		String productLink = ParamUtil.getString(actionRequest, "productLink");

		if (Validator.isNotNull(productLink) && !Validator.isUrl(productLink)) {
			SessionErrors.add(actionRequest, "productLinkInvalid");
		}

		if (!SessionErrors.isEmpty(actionRequest)) {
			return;
		}

		preferences.setValue(
			"productLink_" + productEntryDisplayName, productLink);

		preferences.store();
	}

	protected void updateRabbitMQ(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isOmniadmin()) {
			throw new PrincipalException();
		}

		String deadLetterFilterScript = ParamUtil.getString(
			actionRequest, "deadLetterFilterScript");

		try {
			if (Validator.isNotNull(deadLetterFilterScript)) {
				Map<String, Object> inputObjects = new HashMap<>();

				inputObjects.put(
					"messageJSONObject", JSONFactoryUtil.createJSONObject());
				inputObjects.put("properties", new HashMap<String, Object>());
				inputObjects.put("routingKey", StringPool.BLANK);

				Set<String> outputNames = new HashSet<>();

				outputNames.add("response");

				ScriptingUtil.eval(
					null, inputObjects, outputNames, "groovy",
					deadLetterFilterScript);
			}

			preferences.setValue(
				"deadLetterFilterScript", deadLetterFilterScript);

			preferences.store();
		}
		catch (Exception e) {
			SessionErrors.add(
				actionRequest, "deadLetterFilterScriptCompile", e);
		}
	}

	protected void updateStatusMessage(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		int status = ParamUtil.getInteger(actionRequest, "status");
		String statusMessage = ParamUtil.getString(
			actionRequest, "statusMessage");

		String statusMessageKey = AdminUtil.getPreferenceKey(
			"statusMessage_", String.valueOf(status), languageId);

		preferences.setValue(statusMessageKey, statusMessage);

		preferences.store();
	}

	protected void updateSupport(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("assignment-ratio")) {
			updateAssignmentRatio(actionRequest, preferences);
		}
		else if (tabs2.equals("attachment-keywords")) {
			updateAttachmentKeywords(actionRequest, preferences);
		}
		else if (tabs2.equals("banner")) {
			updateBanner(actionRequest, preferences);
		}
		else if (tabs2.equals("component-messages")) {
			updateComponentMessage(actionRequest, preferences);
		}
		else if (tabs2.equals("dxp-message")) {
			updateDXPMessage(actionRequest, preferences);
		}
		else if (tabs2.equals("escalation-details")) {
			updateEscalationDetails(actionRequest, preferences);
		}
		else if (tabs2.equals("file-repositories")) {
			updateFileRepositories(actionRequest, preferences);
		}
		else if (tabs2.equals("product-messages")) {
			updateProductLink(actionRequest, preferences);
		}
		else if (tabs2.equals("status-messages")) {
			updateStatusMessage(actionRequest, preferences);
		}
		else if (tabs2.equals("tier-messages")) {
			updateTierMessage(actionRequest, preferences);
		}
		else {
			updateComments(actionRequest, preferences);
		}
	}

	protected void updateTierMessage(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		int tier = ParamUtil.getInteger(actionRequest, "tier");
		String tierMessage = ParamUtil.getString(actionRequest, "tierMessage");

		String tierMessageKey = AdminUtil.getPreferenceKey(
			"tierMessage_", String.valueOf(tier), languageId);

		preferences.setValue(tierMessageKey, tierMessage);

		preferences.store();
	}

	protected void updateTrial(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String maxTrialKeys = ParamUtil.getString(
			actionRequest, "maxTrialKeys");
		String trialLiferayVersion = ParamUtil.getString(
			actionRequest, "trialLiferayVersion");
		String trialProductEntryId = ParamUtil.getString(
			actionRequest, "trialProductEntryId");
		String trialSupportResponseId = ParamUtil.getString(
			actionRequest, "trialSupportResponseId");

		preferences.setValue("maxTrialKeys", maxTrialKeys);
		preferences.setValue("trialLiferayVersion", trialLiferayVersion);
		preferences.setValue("trialProductEntryId", trialProductEntryId);
		preferences.setValue("trialSupportResponseId", trialSupportResponseId);

		preferences.store();
	}

}