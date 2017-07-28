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

package com.liferay.osb.hook.upgrade.v2_3_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;

*/

/**
 * @author Ryan Park
 */
public class UpgradeAppVersion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	protected void doUpgrade() throws Exception {
		upgradeAppVersion();
	}

	protected void upgradeAppVersion() throws Exception {
		if (tableHasColumn("OSB_AppVersion", "releaseType")) {
			return;
		}

		runSQL("alter table OSB_AppVersion add column releaseType INT");

		runSQL(
			"update OSB_AppVersion set releaseType = 1 " +
				"where newRelease = true");
		runSQL(
			"update OSB_AppVersion set releaseType = 2 " +
				"where newRelease = false");

		runSQL("alter table OSB_AppVersion drop column newRelease");
	}

}

*/

}