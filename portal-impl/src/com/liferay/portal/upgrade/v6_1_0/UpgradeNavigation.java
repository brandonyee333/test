/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletPreferences;

/**
 * @author Julio Camarero
 */
public class UpgradeNavigation extends BaseUpgradePortletPreferences {

	@Override
	protected String[] getPortletIds() {
		return new String[] {"71_INSTANCE_%"};
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		String displayStyle = portletPreferences.getValue(
			"display-style", null);

		if (Validator.isNumber(displayStyle)) {
			int index = GetterUtil.getInteger(displayStyle);

			if ((index < 0) || (index > 6)) {
				index = 0;
			}

			portletPreferences.setValue(
				"display-style", _DISPLAY_STYLES[index]);
		}

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	private static final String[] _DISPLAY_STYLES = {
		"", "relative-with-breadcrumb", "from-level-2-with-title",
		"from-level-1-with-title", "from-level-1",
		"from-level-1-to-all-sublevels", "from-level-0"
	};

}