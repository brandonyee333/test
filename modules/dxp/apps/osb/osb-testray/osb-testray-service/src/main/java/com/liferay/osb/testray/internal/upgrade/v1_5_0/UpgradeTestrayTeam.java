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