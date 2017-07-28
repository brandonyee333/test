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

package com.liferay.osb.hook.upgrade.v3_2_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20150424161050560_TicketSolution extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20150424161050560L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_TicketSolution", "solution")) {
			runSQL(
				"alter table OSB_TicketSolution change body solution STRING");
		}

		if (!tableHasColumn("OSB_TicketSolution", "summary")) {
			runSQL("alter table OSB_TicketSolution add summary STRING");
		}

		if (!tableHasColumn("OSB_TicketSolution", "customerSpecific")) {
			runSQL(
				"alter table OSB_TicketSolution add customerSpecific BOOLEAN");
		}

		if (!tableHasColumn("OSB_TicketSolution", "environmentSpecific")) {
			runSQL(
				"alter table OSB_TicketSolution add environmentSpecific " +
					"BOOLEAN");
		}

		if (!tableHasColumn("OSB_TicketSolution", "versionSpecific")) {
			runSQL(
				"alter table OSB_TicketSolution add versionSpecific BOOLEAN");
		}
	}

}

*/

}