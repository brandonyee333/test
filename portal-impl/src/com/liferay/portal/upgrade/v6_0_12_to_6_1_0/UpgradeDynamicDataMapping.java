/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_0_12_to_6_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;

/**
 * @author Alberto Chaparro
 */
public class UpgradeDynamicDataMapping extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"insert into ClassName_ (mvccVersion, classNameId, value) " +
					"values (?, ?, ?)")) {

			ps.setLong(1, 0);
			ps.setLong(2, increment());
			ps.setString(
				3, "com.liferay.portlet.dynamicdatamapping.model.DDMStructure");

			ps.executeUpdate();
		}
	}

}