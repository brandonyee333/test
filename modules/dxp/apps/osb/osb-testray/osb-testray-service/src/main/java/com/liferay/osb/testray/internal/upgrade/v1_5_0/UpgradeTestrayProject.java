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
public class UpgradeTestrayProject extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayProject", "IX_4F5C5361")) {
			return;
		}

		runSQL(
			"create unique index IX_4F5C5361 on OSB_TestrayProject (groupId, " +
				"name[$COLUMN_LENGTH:150$])");
	}

}