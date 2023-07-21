/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_6_0;

import com.liferay.osb.testray.internal.upgrade.v1_5_0.BaseUpgradeIndex;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayFactor extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayFactor", "IX_B5A8AD86")) {
			return;
		}

		runSQL("drop index IX_5E2C79A1 on OSB_TestrayFactor");
		runSQL(
			"create unique index IX_B5A8AD86 on OSB_TestrayFactor " +
				"(classNameId, classPK, testrayFactorOptionId)");
	}

}