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

package com.liferay.osb.licenseform.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
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
 * @author Amos Fong
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

		updateGeneral(actionRequest, preferences);

		SessionMessages.add(
			actionRequest,
			portletConfig.getPortletName() +
				SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
	}

	public String render(
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		return "/license_form/configuration.jsp";
	}

	protected void updateGeneral(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		String orderUuid = ParamUtil.getString(actionRequest, "orderUuid");
		int productVersion = ParamUtil.getInteger(
			actionRequest, "productVersion");

		preferences.setValue("orderUuid", orderUuid);
		preferences.setValue("productVersion", String.valueOf(productVersion));

		preferences.store();
	}

}