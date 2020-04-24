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