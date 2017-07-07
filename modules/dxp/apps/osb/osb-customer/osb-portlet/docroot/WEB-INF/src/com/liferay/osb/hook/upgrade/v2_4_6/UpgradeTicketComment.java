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

package com.liferay.osb.hook.upgrade.v2_4_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.upgrade.UpgradeProcess;

*/

/**
 * @author Alan Zhang
 */
public class UpgradeTicketComment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateTicketComment();
	}

	protected void updateTicketComment() throws Exception {
		if (!tableHasColumn("OSB_TicketComment", "status")) {
			runSQL("alter table OSB_TicketComment add column status INTEGER");

			runSQL("update OSB_TicketComment set status = 0");

			runSQL(
				"create index IX_3225AAFC on OSB_TicketComment " +
					"(ticketEntryId, type_)");
			runSQL(
				"create index IX_304B8825 on OSB_TicketComment " +
					"(ticketEntryId, visibility, status)");
			runSQL(
				"create index IX_1C71ADAB on OSB_TicketComment (userId, " +
					"ticketEntryId, visibility, status)");
		}

		if (!tableHasColumn("OSB_TicketComment", "settings_")) {
			runSQL("alter table OSB_TicketComment add column settings_ STRING");
		}
	}

}
*/

}