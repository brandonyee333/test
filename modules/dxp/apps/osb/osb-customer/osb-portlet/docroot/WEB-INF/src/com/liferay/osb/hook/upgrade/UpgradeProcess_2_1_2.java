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

package com.liferay.osb.hook.upgrade;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.hook.upgrade.v2_1_2.UpgradeAssetAudit;
import com.liferay.osb.hook.upgrade.v2_1_2.UpgradeAssetStatsDay;
import com.liferay.osb.hook.upgrade.v2_1_2.UpgradeAssetStatsMonth;
import com.liferay.osb.hook.upgrade.v2_1_2.UpgradeAssetStatsWeek;

/**
 * @author Ryan Park
 */
public class UpgradeProcess_2_1_2 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 212;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeAssetAudit.class);
		upgrade(UpgradeAssetStatsDay.class);
		upgrade(UpgradeAssetStatsMonth.class);
		upgrade(UpgradeAssetStatsWeek.class);
	}

}