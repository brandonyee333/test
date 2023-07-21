/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_6_0;

import com.liferay.osb.testray.internal.upgrade.v1_5_0.BaseUpgradeIndex;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayFactorOption extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayFactorOption", "IX_4D2F0F8E")) {
			return;
		}

		runSQL(
			"create unique index IX_4D2F0F8E on OSB_TestrayFactorOption " +
				"(testrayFactorCategoryId, name[$COLUMN_LENGTH:75$])");
	}

}