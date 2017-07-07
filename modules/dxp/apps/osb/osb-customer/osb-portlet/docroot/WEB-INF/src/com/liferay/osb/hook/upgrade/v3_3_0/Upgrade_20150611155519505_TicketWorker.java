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

package com.liferay.osb.hook.upgrade.v3_3_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.impl.TicketWorkerImpl;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Jeremy Fu
 */
public class Upgrade_20150611155519505_TicketWorker extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20150611155519505L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateTicketWorker();
	}

	protected void updateTicketWorker() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select * from " + TicketWorkerImpl.TABLE_NAME +
					" where role = 4");

			rs = ps.executeQuery();

			while (rs.next()) {
				long ticketEntryId = rs.getLong("ticketEntryId");
				long ticketWorkerId = rs.getLong("ticketWorkerId");
				long userId = rs.getLong("userId");

				if (userId > 0) {
					SubscriptionLocalServiceUtil.addSubscription(
						userId, 0, TicketEntry.class.getName(), ticketEntryId);

					TicketWorkerLocalServiceUtil.deleteTicketWorker(
						ticketWorkerId);
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}
*/

}