/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_5_0;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayBuild extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayBuild", "IX_4ACC8A2C")) {
			return;
		}

		runSQL(
			"delete from OSB_TestrayBuild where testrayBuildId in (45634004, " +
				"53404709, 68868020)");
		runSQL(
			"delete from OSB_TestrayArchive where classPK in (45634004, " +
				"53404709, 74815817)");

		runSQL(
			"create unique index IX_4ACC8A2C on OSB_TestrayBuild " +
				"(testrayProjectId, name[$COLUMN_LENGTH:150$])");
	}

}