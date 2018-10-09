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

package com.liferay.osb.downloads.action;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/downloads/configuration.jsp";
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

		if (tabs1.equals("customer-access")) {
			updateCustomerAccess(actionRequest, preferences);
		}
		else if (tabs1.equals("esa")) {
			updateESA(actionRequest, preferences);
		}
		else if (tabs1.equals("evaluation-eula")) {
			updateEvaluationEULA(actionRequest, preferences);
		}
		else if (tabs1.equals("guest-access")) {
			updateGuestAccess(actionRequest, preferences);
		}
		else if (tabs1.equals("trial")) {
			updateTrial(actionRequest, preferences);
		}
		else {
			updateStudio(actionRequest, preferences);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			preferences.store();

			SessionMessages.add(
				actionRequest,
				portletConfig.getPortletName() +
					SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
		}
	}

	protected void updateCustomerAccess(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String customerAccessPattern = ParamUtil.getString(
			actionRequest, "customerAccessPattern");

		preferences.setValue("customerAccessPattern", customerAccessPattern);
	}

	protected void updateESA(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String esaUrl = ParamUtil.getString(
			actionRequest, "esaUrl_" + languageId);
		double esaVersion = ParamUtil.getDouble(
			actionRequest, "esaVersion_" + languageId);
		double esaVersionRequired = ParamUtil.getDouble(
			actionRequest, "esaVersionRequired_" + languageId);

		if (esaVersion < esaVersionRequired) {
			SessionErrors.add(actionRequest, "esaVersion");
		}
		else {
			preferences.setValue("esaUrl_" + languageId, esaUrl);
			preferences.setValue(
				"esaVersion_" + languageId, String.valueOf(esaVersion));
			preferences.setValue(
				"esaVersionRequired_" + languageId,
				String.valueOf(esaVersionRequired));
		}
	}

	protected void updateEvaluationEULA(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String evaluationEulaUrl = ParamUtil.getString(
			actionRequest, "evaluationEulaUrl_" + languageId);
		double evaluationEulaVersion = ParamUtil.getDouble(
			actionRequest, "evaluationEulaVersion_" + languageId);
		double evaluationEulaVersionRequired = ParamUtil.getDouble(
			actionRequest, "evaluationEulaVersionRequired_" + languageId);

		if (evaluationEulaVersion < evaluationEulaVersionRequired) {
			SessionErrors.add(actionRequest, "evaluationEulaVersion");
		}
		else {
			preferences.setValue(
				"evaluationEulaUrl_" + languageId, evaluationEulaUrl);
			preferences.setValue(
				"evaluationEulaVersion_" + languageId,
				String.valueOf(evaluationEulaVersion));
			preferences.setValue(
				"evaluationEulaVersionRequired_" + languageId,
				String.valueOf(evaluationEulaVersionRequired));
		}
	}

	protected void updateGeneral(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String fileDirectory = ParamUtil.getString(
			actionRequest, "fileDirectory");
		String downloadPage = ParamUtil.getString(
			actionRequest, "downloadPage");

		preferences.setValue("fileDirectory", fileDirectory);
		preferences.setValue("downloadPage", downloadPage);
	}

	protected void updateGuestAccess(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String guestAccessPattern = ParamUtil.getString(
			actionRequest, "guestAccessPattern");

		preferences.setValue("guestAccessPattern", guestAccessPattern);
	}

	protected void updateRequirements(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String studioEulaUrl = ParamUtil.getString(
			actionRequest, "studioEulaUrl_" + languageId);
		double studioEulaVersion = ParamUtil.getDouble(
			actionRequest, "studioEulaVersion_" + languageId);
		double studioEulaVersionRequired = ParamUtil.getDouble(
			actionRequest, "studioEulaVersionRequired_" + languageId);

		if (studioEulaVersion < studioEulaVersionRequired) {
			SessionErrors.add(actionRequest, "studioEulaVersion");
		}
		else {
			preferences.setValue("studioEulaUrl_" + languageId, studioEulaUrl);
			preferences.setValue(
				"studioEulaVersion_" + languageId,
				String.valueOf(studioEulaVersion));
			preferences.setValue(
				"studioEulaVersionRequired_" + languageId,
				String.valueOf(studioEulaVersionRequired));
		}
	}

	protected void updateStudio(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("requirements")) {
			updateRequirements(actionRequest, preferences);
		}
		else {
			updateGeneral(actionRequest, preferences);
		}
	}

	protected void updateTrial(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String trialPattern = ParamUtil.getString(
			actionRequest, "trialPattern");

		preferences.setValue("trialPattern", trialPattern);
	}

}