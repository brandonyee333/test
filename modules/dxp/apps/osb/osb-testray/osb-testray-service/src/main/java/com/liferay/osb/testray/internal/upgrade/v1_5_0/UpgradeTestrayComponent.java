/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_5_0;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayComponent extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayComponent", "IX_30D6FF7B")) {
			return;
		}

		runSQL(
			"create unique index IX_30D6FF7B on OSB_TestrayComponent " +
				"(testrayProjectId, name[$COLUMN_LENGTH:75$])");
	}

}