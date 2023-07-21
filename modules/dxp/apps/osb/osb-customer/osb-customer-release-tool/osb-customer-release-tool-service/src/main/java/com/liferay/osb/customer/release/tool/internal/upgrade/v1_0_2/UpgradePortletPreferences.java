/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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