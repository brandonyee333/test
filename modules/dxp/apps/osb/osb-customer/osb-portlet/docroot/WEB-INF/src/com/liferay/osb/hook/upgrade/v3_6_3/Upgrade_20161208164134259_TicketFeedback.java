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

package com.liferay.osb.hook.upgrade.v3_6_3;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20161208164134259_TicketFeedback
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20161208164134259L;
	}

	protected void deleteDuplicateTicketFeedbacks(long ticketEntryId)
		throws SystemException {

		List<TicketFeedback> ticketFeedbacks =
			ListUtil.copy(
				TicketFeedbackLocalServiceUtil.getTicketFeedbacks(
					ticketEntryId, TicketFeedbackConstants.SUBJECT_LIFERAY,
					TicketFeedbackConstants.STATUS_ANSWERED));

		if (!ticketFeedbacks.isEmpty()) {
			ticketFeedbacks.remove(0);

			for (TicketFeedback ticketFeedback : ticketFeedbacks) {
				TicketFeedbackLocalServiceUtil.deleteTicketFeedback(
					ticketFeedback);
			}
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		getFeedbackTicketEntryIds();
	}

	protected void getFeedbackTicketEntryIds() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb = new StringBundler(5);

		sb.append("select ticketEntryId from OSB_TicketFeedback where (");
		sb.append("subject = ");
		sb.append(TicketFeedbackConstants.SUBJECT_LIFERAY);
		sb.append(") group by 1 having count(distinct(ticketFeedbackId)) > 1 ");
		sb.append("and datediff(max(createDate), min(createDate)) = 0");

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long ticketEntryId = rs.getLong("ticketEntryId");

				deleteDuplicateTicketFeedbacks(ticketEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}