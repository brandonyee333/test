/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_5_0;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayCaseType extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayCaseType", "IX_8925D02")) {
			return;
		}

		runSQL(
			"create unique index IX_8925D02 on OSB_TestrayCaseType (groupId, " +
				"name[$COLUMN_LENGTH:75$])");
	}

}