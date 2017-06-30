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

package com.liferay.osb.hook.upgrade.v3_2_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20150311145345752_TicketWorker extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150311145345752L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeTicketWorker();

		upgradeTicketWorkers();
	}

	protected void upgradeTicketWorker() throws Exception {
		if (tableHasColumn("OSB_TicketWorker", "primary_")) {
			return;
		}

		runSQL("alter table OSB_TicketWorker add column primary_ BOOLEAN");

		runSQL(
			"create index IX_26AF5491 on OSB_TicketWorker (ticketEntryId, " +
				"primary_)");

		runSQL("update OSB_TicketWorker set primary_ = 0;");
	}

	protected void upgradeTicketWorkers() throws Exception {
		List<TicketEntry> ticketEntries =
			TicketEntryLocalServiceUtil.getTicketEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (TicketEntry ticketEntry : ticketEntries) {
			TicketWorker ticketWorker =
				TicketWorkerLocalServiceUtil.fetchLatestTicketWorker(
					ticketEntry.getTicketEntryId());

			if (ticketWorker != null) {
				ticketWorker.setPrimary(true);

				TicketWorkerLocalServiceUtil.updateTicketWorker(ticketWorker);
			}
		}
	}

}