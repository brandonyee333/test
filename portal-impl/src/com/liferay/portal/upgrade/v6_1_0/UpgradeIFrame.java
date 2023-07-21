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
 * @author Tamas Molnar
 */
public class UpgradeIFrame extends BaseUpgradePortletPreferences {

	@Override
	protected String[] getPortletIds() {
		return new String[] {"48_INSTANCE_%"};
	}

	protected void upgradePassword(PortletPreferences portletPreferences)
		throws Exception {

		String password = GetterUtil.getString(
			portletPreferences.getValue("password", null));

		if (Validator.isNotNull(password)) {
			portletPreferences.setValue("formPassword", password);
		}

		portletPreferences.reset("password");
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		upgradePassword(portletPreferences);
		upgradeUserName(portletPreferences);

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

	protected void upgradeUserName(PortletPreferences portletPreferences)
		throws Exception {

		String userName = GetterUtil.getString(
			portletPreferences.getValue("userName", null));

		if (Validator.isNull(userName)) {
			userName = GetterUtil.getString(
				portletPreferences.getValue("user-name", null));
		}

		if (Validator.isNotNull(userName)) {
			portletPreferences.setValue("formUserName", userName);
		}

		portletPreferences.reset("user-name");
		portletPreferences.reset("userName");
	}

}