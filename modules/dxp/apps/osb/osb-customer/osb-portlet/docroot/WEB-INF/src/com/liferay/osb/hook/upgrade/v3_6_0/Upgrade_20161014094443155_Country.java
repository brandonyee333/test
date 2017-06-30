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

package com.liferay.osb.hook.upgrade.v3_6_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Joan H. Kim
 */
public class Upgrade_20161014094443155_Country extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20161014094443155L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("update Country set a2 = 'CR' where countryId = 69");
		runSQL(
			"update Country set a2 = 'NI', a3 = 'NIC' where countryId = 159");
		runSQL(
			"update Country set a2 = 'RS', a3 = 'SRB' where countryId = 189");
	}

}