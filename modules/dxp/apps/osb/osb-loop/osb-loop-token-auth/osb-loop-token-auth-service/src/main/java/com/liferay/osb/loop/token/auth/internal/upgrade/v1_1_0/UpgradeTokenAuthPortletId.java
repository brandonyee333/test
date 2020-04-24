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