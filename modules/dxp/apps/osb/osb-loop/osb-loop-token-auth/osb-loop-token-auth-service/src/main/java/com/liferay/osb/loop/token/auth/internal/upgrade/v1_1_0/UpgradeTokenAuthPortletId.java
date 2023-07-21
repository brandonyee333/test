/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

/**
 * @author  Timothy Bell
 */
public class UpgradeTokenAuthPortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{
				"1_WAR_tokenauthportlet",
				"com_liferay_osb_loop_token_auth_admin_web_internal_portlet_" +
					"TokenAuthAdminPortlet"
			},
			{
				"2_WAR_tokenauthportlet",
				"com_liferay_osb_loop_token_auth_admin_web_internal_portlet_" +
					"TokenAuthManagerPortlet"
			}
		};
	}

}