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

package com.liferay.osb.hook.upgrade.v1_9_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.upgrade.UpgradeProcess;

*/

/**
 * @author Douglas Wong
 */
public class UpgradeAppVersion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateAppVersion();
	}

	protected void updateAppVersion() throws Exception {
		if (tableHasColumn("OSB_AppVersion", "supported")) {
			return;
		}

		runSQL("alter table OSB_AppVersion add column supported BOOLEAN");

		runSQL(
			"update OSB_AppVersion " +
				"set supported = (licenseSupportPercent <> -1)");

		runSQL("alter table OSB_AppVersion drop column licenseSupportPercent");
	}

}
*/

}