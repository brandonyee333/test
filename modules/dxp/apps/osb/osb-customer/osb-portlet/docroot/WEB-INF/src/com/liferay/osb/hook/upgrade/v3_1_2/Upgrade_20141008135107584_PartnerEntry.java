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

package com.liferay.osb.hook.upgrade.v3_1_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Sharon Li
 */
public class Upgrade_20141008135107584_PartnerEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141008135107584L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradePartnerEntry();
	}

	protected void upgradePartnerEntry() throws Exception {
		if (hasColumn("OSB_PartnerEntry", "status")) {
			return;
		}

		runSQL("alter table OSB_PartnerEntry add column status INTEGER");

		runSQL("update OSB_PartnerEntry set status = 0");
	}

}