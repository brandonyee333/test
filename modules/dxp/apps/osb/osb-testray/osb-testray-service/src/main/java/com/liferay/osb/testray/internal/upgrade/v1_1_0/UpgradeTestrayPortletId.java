/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayPortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{
				"1_WAR_osbtestrayportlet",
				"com_liferay_osb_testray_portlet_TestrayPortlet"
			}
		};
	}

}