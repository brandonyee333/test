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
public class UpgradeAppEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateAppEntry();
	}

	protected void updateAppEntry() throws Exception {
		if (tableHasColumn("OSB_AppEntry", "supported")) {
			return;
		}

		runSQL("alter table OSB_AppEntry add column supported BOOLEAN");

		runSQL(
			"update OSB_AppEntry " +
				"set supported = (licenseSupportPercent <> -1)");

		runSQL("alter table OSB_AppEntry drop column licenseSupportPercent");
	}

}
*/

}