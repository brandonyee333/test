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

package com.liferay.osb.hook.upgrade.v3_4_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20160225105737649_TicketAttachment
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20160225105737649L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"create index IX_26149242 on OSB_TicketAttachment " +
				"(ticketEntryId, fileName, visibility, status)");
		runSQL(
			"create index IX_CFC835A1 on OSB_TicketAttachment " +
				"(ticketEntryId, status)");
		runSQL(
			"create index IX_36DB14A0 on OSB_TicketAttachment " +
				"(ticketEntryId, type_, status)");
		runSQL(
			"create index IX_B8D980A6 on OSB_TicketAttachment " +
				"(ticketEntryId, type_, visibility, status)");
		runSQL(
			"create index IX_47FFB24C on OSB_TicketAttachment (type_, " +
				"deleteDate)");
		runSQL(
			"create index IX_6EAF2BAD on OSB_TicketAttachment (userId, " +
				"ticketEntryId, visibility, status)");
	}

}
*/

}