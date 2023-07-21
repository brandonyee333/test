/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.menu.web.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portletdisplaytemplate.PortletDisplayTemplateManager;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.navigation.menu.web.internal.constants.SiteNavigationMenuPortletKeys;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

/**
 * @author Eduardo García
 */
public class UpgradePortletPreferences extends BaseUpgradePortletPreferences {

	@Override
	protected String[] getPortletIds() {
		return new String[] {
			SiteNavigationMenuPortletKeys.SITE_NAVIGATION_MENU + "%"
		};
	}

	protected void upgradeDisplayStyle(PortletPreferences portletPreferences)
		throws ReadOnlyException {

		String displayStyle = GetterUtil.getString(
			portletPreferences.getValue("displayStyle", null));

		if (Validator.isNull(displayStyle) ||
			displayStyle.startsWith(
				PortletDisplayTemplateManager.DISPLAY_STYLE_PREFIX)) {

			return;
		}

		portletPreferences.setValue(
			"displayStyle",
			PortletDisplayTemplateManager.DISPLAY_STYLE_PREFIX +
				"list-menu-ftl");

		// Remove unsupported preferences

		portletPreferences.reset("bulletStyle");
		portletPreferences.reset("headerType");
		portletPreferences.reset("nestedChildren");
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		upgradeDisplayStyle(portletPreferences);

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}