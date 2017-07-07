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

package com.liferay.osb.hook.upgrade.v2_5_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.upgrade.UpgradeProcess;

*/

/**
 * @author Alan Zhang
 */
public class UpgradeTicketAttachment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateTicketAttachment();
	}

	protected void updateTicketAttachment() throws Exception {
		if (tableHasColumn("OSB_TicketAttachment", "ticketSolutionId")) {
			return;
		}

		runSQL(
			"alter table OSB_TicketAttachment add column ticketSolutionId " +
				"integer");

		runSQL(
			"create index IX_46E45E8F on OSB_TicketAttachment " +
				"(ticketEntryId, ticketSolutionId)");

		runSQL("update OSB_TicketAttachment set ticketSolutionId = 0");
	}

}
*/

}