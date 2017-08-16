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

package com.liferay.osb.hook.upgrade.v3_6_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170131150512687_SupportResponse
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170131150512687L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_SupportResponse", "accessType")) {
			return;
		}

		runSQL("alter table OSB_SupportResponse drop column accessType");
		runSQL("alter table OSB_SupportResponse drop column timeZoneType");
	}

}