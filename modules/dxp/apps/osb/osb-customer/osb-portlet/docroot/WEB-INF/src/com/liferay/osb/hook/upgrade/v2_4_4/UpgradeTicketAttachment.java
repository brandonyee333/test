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

package com.liferay.osb.hook.upgrade.v2_4_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketAttachmentConstants;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Brent Krone-Schmidt
 * @author Alan Zhang
 */
public class UpgradeTicketAttachment extends UpgradeProcess {

	protected void deleteTicketAttachments(long ticketEntryId)
		throws PortalException, SystemException {

		TicketAttachment patchLevelTicketAttachment =
			TicketAttachmentLocalServiceUtil.fetchTicketAttachment(
				ticketEntryId, TicketAttachmentConstants.TYPE_PATCH_LEVEL);
		TicketAttachment portalExtTicketAttachment =
			TicketAttachmentLocalServiceUtil.fetchTicketAttachment(
				ticketEntryId, TicketAttachmentConstants.TYPE_PORTAL_EXT);

		List<TicketAttachment> ticketAttachments =
			TicketAttachmentLocalServiceUtil.getTicketAttachments(
				ticketEntryId);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			if ((ticketAttachment.getType() !=
					TicketAttachmentConstants.TYPE_PATCH_LEVEL) &&
				(ticketAttachment.getType() !=
					TicketAttachmentConstants.TYPE_PORTAL_EXT)) {

				continue;
			}

			if (ticketAttachment.getTicketAttachmentId() ==
					patchLevelTicketAttachment.getTicketAttachmentId()) {

				continue;
			}

			if (ticketAttachment.getTicketAttachmentId() ==
					portalExtTicketAttachment.getTicketAttachmentId()) {

				continue;
			}

			TicketAttachmentLocalServiceUtil.deleteTicketAttachment(
				OSBConstants.USER_DEFAULT_USER_ID,
				ticketAttachment.getTicketAttachmentId());
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeTicketAttachments();
	}

	protected void updateStatus() throws Exception {
		if (tableHasColumn("OSB_TicketAttachment", "status")) {
			return;
		}

		runSQL("alter table OSB_TicketAttachment add column status INTEGER");

		runSQL("update OSB_TicketAttachment set status = 0");
	}

	protected void upgradeTicketAttachments() throws Exception {
		updateStatus();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb = new StringBundler(5);

		sb.append("select ticketEntryId from OSB_TicketEntry where ((select ");
		sb.append("count(*) from OSB_TicketAttachment where ");
		sb.append("(OSB_TicketAttachment.ticketEntryId = ");
		sb.append("OSB_TicketEntry.ticketEntryId) && (type_ = 3)) > 1) or ");
		sb.append("((select count(*) from OSB_TicketAttachment where ");
		sb.append("(OSB_TicketAttachment.ticketEntryId = ");
		sb.append("OSB_TicketEntry.ticketEntryId) && (type_ = 4)) > 1);");

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long ticketEntryId = rs.getLong("ticketEntryId");

				deleteTicketAttachments(ticketEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}