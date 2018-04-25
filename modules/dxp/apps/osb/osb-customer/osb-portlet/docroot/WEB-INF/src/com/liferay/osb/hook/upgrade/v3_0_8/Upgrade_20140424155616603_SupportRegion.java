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

package com.liferay.osb.hook.upgrade.v3_0_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.SupportRegionModelImpl;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20140424155616603_SupportRegion
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140424155616603L;
	}

	protected void createSupportRegion() throws Exception {
		if (!hasTable(SupportRegionModelImpl.TABLE_NAME)) {
			runSQL(SupportRegionModelImpl.TABLE_SQL_CREATE);

			runSQL(
				"create unique index IX_DC67F8E7 on OSB_SupportRegion (name)");
		}

		if (!hasTable(
				SupportRegionModelImpl.
					MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_NAME)) {

			runSQL(
				SupportRegionModelImpl.
					MAPPING_TABLE_OSB_ACCOUNTENTRIES_SUPPORTREGIONS_SQL_CREATE);

			runSQL(
				"create index IX_E9054065 on " +
					"OSB_AccountEntries_SupportRegions (accountEntryId)");
			runSQL(
				"create index IX_B9459F3D on " +
					"OSB_AccountEntries_SupportRegions (supportRegionId)");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		createSupportRegion();
	}

}