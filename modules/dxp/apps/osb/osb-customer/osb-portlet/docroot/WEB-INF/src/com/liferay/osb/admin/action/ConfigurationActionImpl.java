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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

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

	protected void updateEmailNotifications(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("provisioning-create-account")) {
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

	protected void updateSupport(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		updateFileRepositories(actionRequest, preferences);
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