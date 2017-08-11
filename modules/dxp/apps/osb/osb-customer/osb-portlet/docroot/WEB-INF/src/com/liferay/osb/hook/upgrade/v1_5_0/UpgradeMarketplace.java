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

package com.liferay.osb.hook.upgrade.v1_5_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;

*/

/**
 * @author Douglas Wong
 */
public class UpgradeMarketplace extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		updateAppEntry();
		updateAppPricing();
		updateAppPricingItem();
		updateAppVersion();
	}

	protected void updateAppEntry() throws Exception {
		if (tableHasColumn("OSB_AppEntry", "licenseSupportPercent")) {
			return;
		}

		runSQL(
			"alter table OSB_AppEntry add column " +
				"licenseSupportPercent INTEGER");

		runSQL("update OSB_AppEntry set licenseSupportPercent = -1");
	}

	protected void updateAppPricing() throws Exception {
		runSQL("create index IX_575D00CB on OSB_AppPricing (appEntryId, name)");
	}

	protected void updateAppPricingItem() throws Exception {
		runSQL(
			"create index IX_EE718AF3 on OSB_AppPricingItem " +
				"(appPricingId, assetLicenseId)");
	}

	protected void updateAppVersion() throws Exception {
		if (tableHasColumn("OSB_AppVersion", "licenseSupportPercent")) {
			return;
		}

		runSQL(
			"alter table OSB_AppVersion add column " +
				"licenseSupportPercent INTEGER");

		runSQL("update OSB_AppVersion set licenseSupportPercent = -1");
	}

	 */

}