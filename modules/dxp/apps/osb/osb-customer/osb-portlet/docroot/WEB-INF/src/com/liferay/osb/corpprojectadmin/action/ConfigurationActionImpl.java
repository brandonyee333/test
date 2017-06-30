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

package com.liferay.osb.corpprojectadmin.action;

import com.liferay.compat.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.corpprojectadmin.util.CorpProjectAdminUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Kyle Bischof
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

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

		if (tabs1.equals("project-messages")) {
			updateProjectMessages(actionRequest, preferences);
		}

		if (SessionErrors.isEmpty(actionRequest)) {
			SessionMessages.add(
				actionRequest,
				portletConfig.getPortletName() +
					SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
		}
	}

	@Override
	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return "/corp_project_admin/configuration.jsp";
	}

	protected void updateProjectMessages(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String languageId = LanguageUtil.getLanguageId(actionRequest);

		String template = ParamUtil.getString(actionRequest, "template");
		String projectTitle = ParamUtil.getString(
			actionRequest, "projectTitle");
		String projectMessage = ParamUtil.getString(
			actionRequest, "projectMessage");

		if (Validator.isNull(projectTitle) &&
			Validator.isNotNull(projectMessage)) {

			SessionErrors.add(actionRequest, "titleInvalid");

			return;
		}

		String projectTitleKey = CorpProjectAdminUtil.getPreferenceKey(
			"projectTitle_", template, languageId);
		String projectMessageKey = CorpProjectAdminUtil.getPreferenceKey(
			"projectMessage_", template, languageId);

		preferences.setValue(projectTitleKey, projectTitle);
		preferences.setValue(projectMessageKey, projectMessage);

		preferences.store();
	}

}