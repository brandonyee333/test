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

package com.liferay.osb.hook.upgrade.v3_2_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Alan Zhang
 */
public class Upgrade_20150414105351569_TicketAttachment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20150414105351569L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_TicketAttachment", "availableServers")) {
			runSQL(
				"alter table OSB_TicketAttachment add column " +
					"availableServers STRING");
		}

		if (!tableHasColumn("OSB_TicketAttachment", "replicate")) {
			runSQL(
				"alter table OSB_TicketAttachment add column replicate " +
					"BOOLEAN");
		}

		if (!tableHasColumn("OSB_TicketAttachment", "deleteDate")) {
			runSQL(
				"alter table OSB_TicketAttachment add column deleteDate DATE");
		}
	}

	 */

}