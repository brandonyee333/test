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
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alan Zhang
 * @author Sharon Li
 */
public class UpgradeTicketStatus extends BaseUpgradeProcess {

	public static final int STATUS_CUSTOMER_TESTING = 33015;

	public static final int STATUS_INVESTIGATING = 33003;

	public static final int STATUS_PATCH_IN_QA = 33011;

	public static final int STATUS_PATCH_NOT_WORKING = 33012;

	public static final int STATUS_REVIEWING_PATCH = 33013;

	@Override
	protected void doUpgrade() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

		String now = dateFormat.format(new Date());

		updateTicketStatuses(
			STATUS_CUSTOMER_TESTING, STATUS_INVESTIGATING, now);
		updateTicketStatuses(STATUS_PATCH_IN_QA, STATUS_INVESTIGATING, now);
		updateTicketStatuses(
			STATUS_PATCH_NOT_WORKING, STATUS_INVESTIGATING, now);
		updateTicketStatuses(STATUS_REVIEWING_PATCH, STATUS_INVESTIGATING, now);
	}

	protected List<long[]> getTicketEntryData(int status) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select ticketEntryId, accountEntryId, status, " +
					"escalationLevel from OSB_TicketEntry where status = ?");

			ps.setInt(1, status);

			rs = ps.executeQuery();

			List<long[]> results = new ArrayList<>();

			while (rs.next()) {
				long[] ticketEntryData = new long[4];

				ticketEntryData[0] = rs.getLong("ticketEntryId");
				ticketEntryData[1] = rs.getLong("accountEntryId");
				ticketEntryData[2] = rs.getInt("status");
				ticketEntryData[3] = rs.getInt("escalationLevel");

				results.add(ticketEntryData);
			}

			return results;
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateTicketPendingType(
			int status, int escalation, String modifiedDate,
			long accountEntryId, long ticketEntryId)
		throws Exception {

		int type = TicketFlagConstants.TYPE_PENDING_LIFERAY;

		if (status == STATUS_CUSTOMER_TESTING) {
			type = TicketFlagConstants.TYPE_PENDING_CUSTOMER;
		}
		else if (escalation == TicketEntryConstants.ESCALATION_LEVEL_P1) {
			type = TicketFlagConstants.TYPE_PENDING_PARTNER;
		}

		StringBundler sb = new StringBundler(17);

		sb.append("insert into OSB_TicketFlag (ticketFlagId, userId, ");
		sb.append("modifiedDate, accountEntryId, ticketEntryId, type_, flag) ");
		sb.append("values (");
		sb.append(increment());
		sb.append(", ");
		sb.append(OSBConstants.USER_DEFAULT_USER_ID);
		sb.append(", '");
		sb.append(modifiedDate);
		sb.append("', ");
		sb.append(accountEntryId);
		sb.append(", ");
		sb.append(ticketEntryId);
		sb.append(", ");
		sb.append(type);
		sb.append(", ");
		sb.append(TicketFlagConstants.FLAG_TRUE);
		sb.append(")");

		runSQL(sb.toString());
	}

	protected void updateTicketStatuses(int oldStatus, int status, String now)
		throws Exception {

		List<long[]> ticketEntryData = getTicketEntryData(oldStatus);

		if (ticketEntryData.isEmpty()) {
			return;
		}

		StringBundler sb = new StringBundler(6);

		sb.append("update OSB_TicketEntry set status = ");
		sb.append(status);
		sb.append(", workerModifiedDate = '");
		sb.append(now);
		sb.append("' where status = ");
		sb.append(oldStatus);

		runSQL(sb.toString());

		for (long[] curTicketEntryData : ticketEntryData) {
			updateTicketPendingType(
				(int)curTicketEntryData[2], (int)curTicketEntryData[3], now,
				curTicketEntryData[1], curTicketEntryData[0]);
		}
	}

}