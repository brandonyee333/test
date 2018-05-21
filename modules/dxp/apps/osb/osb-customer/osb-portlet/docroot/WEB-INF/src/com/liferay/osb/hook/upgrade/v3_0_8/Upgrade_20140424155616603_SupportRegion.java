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