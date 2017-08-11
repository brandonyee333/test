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

package com.liferay.osb.hook.upgrade.v3_0_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20140627134445636_AppEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20140627134445636L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAppEntry();
	}

	protected void upgradeAppEntry() throws Exception {
		runSQL(
			"create index IX_CEB292AF on OSB_AppEntry (developerEntryId)",
			false);
		runSQL(
			"create index IX_E706B295 on OSB_AppEntry " +
				"(developerEntryId, status)",
			false);
		runSQL(
			"create index IX_8DB55D79 on OSB_AppEntry " +
				"(developerEntryId, title)",
			false);

		runSQL("drop index IX_FCC9438B on OSB_AppEntry", false);
		runSQL("drop index IX_163CC771 on OSB_AppEntry", false);
		runSQL("drop index IX_8F3B3D1D on OSB_AppEntry", false);

		if (tableHasColumn("OSB_AppEntry", "ownerClassNameId")) {
			runSQL("alter table OSB_AppEntry drop column ownerClassNameId");
		}

		if (tableHasColumn("OSB_AppEntry", "ownerClassPK")) {
			runSQL("alter table OSB_AppEntry drop column ownerClassPK");
		}

		if (tableHasColumn("OSB_AppEntry", "ownerName")) {
			runSQL(
				"alter table OSB_AppEntry change " +
					"ownerName developerName VARCHAR(75)");
		}
	}

	 */

}