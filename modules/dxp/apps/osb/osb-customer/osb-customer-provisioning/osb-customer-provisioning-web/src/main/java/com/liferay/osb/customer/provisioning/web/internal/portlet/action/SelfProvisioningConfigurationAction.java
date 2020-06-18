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

package com.liferay.osb.customer.provisioning.web.internal.portlet.action;

import com.liferay.osb.customer.provisioning.web.internal.constants.CustomerProvisioningPortletKeys;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CustomerProvisioningPortletKeys.SELF_PROVISIONING,
	service = ConfigurationAction.class
)
public class SelfProvisioningConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/self_provisioning/configuration.jsp";
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

		updatePreferences(actionRequest, preferences);

		SessionMessages.add(
			actionRequest,
			portletConfig.getPortletName() +
				SessionMessages.KEY_SUFFIX_UPDATED_CONFIGURATION);
	}

	protected void updatePreferences(
			ActionRequest actionRequest, PortletPreferences preferences)
		throws Exception {

		long[] digitalEnterpriseProductMinorVersions = ParamUtil.getLongValues(
			actionRequest, "digitalEnterprise_productMinorVersions");
		long[] portalProductMinorVersions = ParamUtil.getLongValues(
			actionRequest, "portal_productMinorVersions");

		preferences.setValue(
			"digitalEnterprise_productMinorVersions",
			StringUtil.merge(digitalEnterpriseProductMinorVersions));
		preferences.setValue(
			"portal_productMinorVersions",
			StringUtil.merge(portalProductMinorVersions));

		preferences.store();
	}

}