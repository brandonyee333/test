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

package com.liferay.osb.trainingcertificationagreement.action;

import com.liferay.compat.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Douglas Wong
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

		long eulaFileEntryId = ParamUtil.getLong(
			actionRequest, "eulaFileEntryId");

		DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.fetchDLFileEntry(
			eulaFileEntryId);

		if (dlFileEntry == null) {
			SessionErrors.add(actionRequest, "eulaFileEntryId");

			return;
		}

		String eulaVersion = ParamUtil.getString(actionRequest, "eulaVersion");

		DLFileVersion dlFileVersion = null;

		try {
			dlFileVersion = DLFileVersionLocalServiceUtil.getFileVersion(
				eulaFileEntryId, eulaVersion);
		}
		catch (Exception e) {
		}

		if (dlFileVersion == null) {
			SessionErrors.add(actionRequest, "eulaVersion");

			return;
		}

		PortletPreferences portletPreferences = actionRequest.getPreferences();

		portletPreferences.setValue(
			"eulaFileEntryId", String.valueOf(eulaFileEntryId));
		portletPreferences.setValue("eulaVersion", String.valueOf(eulaVersion));

		portletPreferences.store();

		String portletResource = ParamUtil.getString(
			actionRequest, "portletResource");

		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)portletConfig;

		SessionMessages.add(
			actionRequest,
			liferayPortletConfig.getPortletId() +
				SessionMessages.KEY_SUFFIX_REFRESH_PORTLET,
			portletResource);

		SessionMessages.add(
			actionRequest,
			liferayPortletConfig.getPortletId() +
				SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
	}

	@Override
	public String render(
		PortletConfig portletConfig, RenderRequest renderRequest,
		RenderResponse renderResponse) {

		return "/training_certification_agreement/configuration.jsp";
	}

}