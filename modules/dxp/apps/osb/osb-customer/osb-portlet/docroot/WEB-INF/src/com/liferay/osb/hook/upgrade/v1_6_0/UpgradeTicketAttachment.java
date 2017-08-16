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

package com.liferay.osb.hook.upgrade.v1_6_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeTicketAttachment extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTicketAttachment();
	}

	protected void updateTicketAttachment() throws Exception {
		if (hasColumn("OSB_TicketAttachment", "visibility")) {
			return;
		}

		runSQL("drop index IX_51AA2756 on OSB_TicketAttachment");

		runSQL(
			"alter table OSB_TicketAttachment add column visibility integer");

		runSQL("update OSB_TicketAttachment set visibility = 1");

		runSQL(
			"create index IX_CD5BBA41 on OSB_TicketAttachment " +
				"(ticketEntryId, visibility)");
		runSQL(
			"create unique index IX_161D6F5C on OSB_TicketAttachment " +
				"(ticketEntryId, fileName, visibility)");
	}

}