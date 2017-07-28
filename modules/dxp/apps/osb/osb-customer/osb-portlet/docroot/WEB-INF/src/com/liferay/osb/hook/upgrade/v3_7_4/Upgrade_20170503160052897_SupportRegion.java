/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v3_7_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170503160052897_SupportRegion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20170503160052897L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn("OSB_SupportRegion", "managerUserId")) {
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
			"update OSB_SupportRegion set managerUserId = 390865 where name =" +
				" 'Spain'");
		runSQL(
			"update OSB_SupportRegion set managerUserId = 4260579 where name " +
				"= 'US'");
	}

}

*/

}