/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.upgrade.v1_0_0;

import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletPreferences;
import com.liferay.shopping.constants.ShoppingPortletKeys;

import javax.portlet.PortletPreferences;

/**
 * @author Iván Zaera
 */
public class UpgradeShoppingPreferences extends BaseUpgradePortletPreferences {

	@Override
	protected String[] getPortletIds() {
		return new String[] {ShoppingPortletKeys.SHOPPING};
	}

	@Override
	protected String upgradePreferences(
			long companyId, long ownerId, int ownerType, long plid,
			String portletId, String xml)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromXML(
				companyId, ownerId, ownerType, plid, portletId, xml);

		upgradeMultiValuePreference(portletPreferences, "ccTypes");
		upgradeMultiValuePreference(portletPreferences, "insurance");
		upgradeMultiValuePreference(portletPreferences, "shipping");

		return PortletPreferencesFactoryUtil.toXML(portletPreferences);
	}

}