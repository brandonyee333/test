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

package com.liferay.osb.hook.upgrade.v2_0_9;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Douglas Wong
 */
public class UpgradeAssetReceiptLicense extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAssetReceiptLicense();
	}

	protected void upgradeAssetReceiptLicense() throws Exception {
		if (tableHasColumn("OSB_AssetReceiptLicense", "endDate")) {
			return;
		}

		runSQL("alter table OSB_AssetReceiptLicense add column endDate DATE");

		runSQL(
			"update OSB_AssetReceiptLicense set endDate = DATE_ADD(" +
				"startDate, INTERVAL (licenseLifetime * 1000) MICROSECOND)");

		runSQL(
			"create index IX_1083E5E4 on OSB_AssetReceiptLicense " +
				"(assetReceiptId, endDate)");
	}

}