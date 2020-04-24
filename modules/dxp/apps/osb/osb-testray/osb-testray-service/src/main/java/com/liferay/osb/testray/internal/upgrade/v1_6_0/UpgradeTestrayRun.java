/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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