/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_6_0;

import com.liferay.osb.testray.internal.upgrade.v1_5_0.BaseUpgradeIndex;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayRun extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayRun", "IX_2ED0971E")) {
			return;
		}

		runSQL("delete from OSB_TestrayRun where testrayBuildId = 0");
		runSQL(
			"update OSB_TestrayRun set externalReferencePK = '' where " +
				"externalReferencePK is null");
		runSQL(
			"update OSB_TestrayRun set externalReferenceType = 1 where " +
				"externalReferenceType = 0 and externalReferencePK != ''");
		runSQL(
			"update OSB_TestrayRun set externalReferencePK = testrayRunId " +
				"where externalReferencePK = '' and externalReferenceType = 0");

		runSQL(
			"create unique index IX_2ED0971E on OSB_TestrayRun " +
				"(testrayBuildId, externalReferencePK[$COLUMN_LENGTH:255$], " +
					"externalReferenceType)");
	}

}