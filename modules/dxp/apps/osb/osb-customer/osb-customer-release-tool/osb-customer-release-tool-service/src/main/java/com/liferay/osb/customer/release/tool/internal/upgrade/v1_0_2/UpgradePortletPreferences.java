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

package com.liferay.osb.customer.release.tool.internal.upgrade.v1_0_2;

import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Jenny Chen
 */
public class UpgradePortletPreferences extends UpgradeProcess {

	public UpgradePortletPreferences(
		PortletPreferencesLocalService portletPreferencesLocalService) {

		_portletPreferencesLocalService = portletPreferencesLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PortletPreferences portletPreferences =
			_portletPreferencesLocalService.fetchPortletPreferences(118707044);

		if (portletPreferences == null) {
			return;
		}

		String preferences = portletPreferences.getPreferences();

		if (!preferences.contains("productName")) {
			String newPreferences = StringUtil.replace(
				preferences, "</portlet-preferences>",
				"<preference><name>productName</name><value>dxp</value>" +
					"</preference></portlet-preferences>");

			portletPreferences.setPreferences(newPreferences);

			_portletPreferencesLocalService.updatePortletPreferences(
				portletPreferences);
		}
	}

	private final PortletPreferencesLocalService
		_portletPreferencesLocalService;

}