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

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Alan Zhang
 */
public class UpgradeTicketLink extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTicketLink();
	}

	protected void updateTicketLink() throws Exception {
		if (hasColumn("OSB_TicketLink", "ticketSolutionId")) {
			return;
		}

		runSQL(
			"alter table OSB_TicketLink add column ticketSolutionId integer");

		runSQL(
			"create index IX_90EFDA98 on OSB_TicketLink (ticketEntryId, " +
				"ticketSolutionId)");

		runSQL("update OSB_TicketLink set ticketSolutionId = 0");
	}

}