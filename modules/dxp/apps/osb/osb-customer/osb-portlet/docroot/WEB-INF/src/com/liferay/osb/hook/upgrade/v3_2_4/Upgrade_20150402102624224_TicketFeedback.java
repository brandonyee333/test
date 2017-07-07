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

package com.liferay.osb.hook.upgrade.v3_2_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Jeremy Fu
 */
public class Upgrade_20150402102624224_TicketFeedback
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20150402102624224L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_TicketFeedback", "subject")) {
			runSQL("alter table OSB_TicketFeedback add subject INTEGER");

			runSQL("update OSB_TicketFeedback set subject = 1");
		}

		if (!tableHasColumn("OSB_TicketFeedback", "answer1")) {
			runSQL("alter table OSB_TicketFeedback add answer1 INTEGER");
		}

		if (!tableHasColumn("OSB_TicketFeedback", "answer2")) {
			runSQL("alter table OSB_TicketFeedback add answer2 INTEGER");
		}

		if (!tableHasColumn("OSB_TicketFeedback", "answer3")) {
			runSQL("alter table OSB_TicketFeedback add answer3 INTEGER");
		}

		if (hasIndex("OSB_TicketFeedback", "IX_5BB30859")) {
			runSQL("drop index IX_5BB30859 on OSB_TicketFeedback");

			runSQL(
				"create index IX_5BB30859 on OSB_TicketFeedback " +
					"(ticketEntryId)");
			runSQL(
				"create index IX_61173523 on OSB_TicketFeedback " +
					"(ticketEntryId, subject)");
		}
	}

}
*/

}