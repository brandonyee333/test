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

package com.liferay.osb.hook.upgrade.v3_5_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Haote Chou
 */
public class Upgrade_20160607171721488_AppVersion extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160607171721488L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn("OSB_AppVersion", "orderURL")) {
			return;
		}

		runSQL("alter table OSB_AppVersion add column orderURL longtext");
	}

}