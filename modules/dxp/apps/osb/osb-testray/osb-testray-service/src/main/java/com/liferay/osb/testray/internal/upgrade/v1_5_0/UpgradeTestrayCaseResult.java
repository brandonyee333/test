/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_5_0;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayCaseResult extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayCaseResult", "IX_26BEC6B")) {
			return;
		}

		runSQL("drop index IX_21E5E829 on OSB_TestrayCaseResult");
		runSQL(
			"create unique index IX_26BEC6B on OSB_TestrayCaseResult " +
				"(testrayCaseId, testrayRunId)");
	}

}