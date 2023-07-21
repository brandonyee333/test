/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_5_0;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayTeam extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayTeam", "IX_7077F1E9")) {
			return;
		}

		runSQL(
			"create unique index IX_7077F1E9 on OSB_TestrayTeam " +
				"(testrayProjectId, name[$COLUMN_LENGTH:75$])");
	}

}