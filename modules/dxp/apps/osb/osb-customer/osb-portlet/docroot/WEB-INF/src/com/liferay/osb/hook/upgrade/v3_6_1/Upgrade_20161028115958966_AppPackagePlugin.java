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

package com.liferay.osb.hook.upgrade.v3_6_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Yury Butrymovich
 */
public class Upgrade_20161028115958966_AppPackagePlugin
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20161028115958966L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table OSB_AppPackagePlugin modify fileName VARCHAR(255)");

		runSQL(
			"alter table OSB_AppPackagePlugin modify contextName VARCHAR(255)");
	}

}