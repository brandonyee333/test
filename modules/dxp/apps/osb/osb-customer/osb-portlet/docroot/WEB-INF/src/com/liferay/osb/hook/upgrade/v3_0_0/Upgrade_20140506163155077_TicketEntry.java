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

package com.liferay.osb.hook.upgrade.v3_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Sharon Li
 */
public class Upgrade_20140506163155077_TicketEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20140506163155077L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeTicketEntry();
	}

	protected void upgradeTicketEntry() throws Exception {
		if (!tableHasColumn("OSB_TicketEntry", "envBrowser")) {
			runSQL("alter table OSB_TicketEntry add column envBrowser integer");
		}

		if (!tableHasColumn("OSB_TicketEntry", "envBrowserCustom")) {
			runSQL(
				"alter table OSB_TicketEntry add column envBrowserCustom " +
					"VARCHAR(150)");
		}
	}

}
*/

}