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

package com.liferay.osb.hook.upgrade.v3_7_4;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170503160052897_SupportRegion
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170503160052897L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("OSB_SupportRegion", "managerUserId")) {
			return;
		}

		runSQL("alter table OSB_SupportRegion add column managerUserId LONG");

		runSQL(
			"update OSB_SupportRegion set managerUserId = 9896665 where name " +
				"= 'Australia'");
		runSQL(
			"update OSB_SupportRegion set managerUserId = 6054312 where name " +
				"= 'Brazil'");
		runSQL(
			"update OSB_SupportRegion set managerUserId = 9896665 where name " +
				"= 'China'");
		runSQL(
			"update OSB_SupportRegion set managerUserId = 69511362 where " +
				"name = 'Hungary'");
		runSQL(
			"update OSB_SupportRegion set managerUserId = 28919080 where " +
				"name = 'India'");
		runSQL(
			"update OSB_SupportRegion set managerUserId = 12791771 where " +
				"name = 'Japan'");
		runSQL(
			"update OSB_SupportRegion set managerUserId = 390865 where name" +
				"= 'Spain'");
		runSQL(
			"update OSB_SupportRegion set managerUserId = 4260579 where name " +
				"= 'US'");
	}

}