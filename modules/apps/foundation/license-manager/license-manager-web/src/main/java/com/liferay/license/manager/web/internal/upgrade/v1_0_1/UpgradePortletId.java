/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.license.manager.web.internal.upgrade.v1_0_1;

import com.liferay.license.manager.web.internal.constants.LicenseManagerPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author David Zhang
 * @author Alberto Chaparro
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select id_ from Portlet where portletId = '176'");
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				runSQL(
					"delete from Portlet where portletId = '" +
						LicenseManagerPortletKeys.LICENSE_MANAGER + "'");

				super.doUpgrade();
			}
		}
	}

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{"176", LicenseManagerPortletKeys.LICENSE_MANAGER}
		};
	}

}