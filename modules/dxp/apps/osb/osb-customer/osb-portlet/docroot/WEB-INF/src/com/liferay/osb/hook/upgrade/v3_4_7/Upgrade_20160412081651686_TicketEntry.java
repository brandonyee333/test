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

package com.liferay.osb.hook.upgrade.v3_4_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketInformationConstants;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;

*/

/**
 * @author Jenny Chen
 */
public class Upgrade_20160412081651686_TicketEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20160412081651686L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateAuditEntry();
		updateTicketEntry();
	}

	protected void updateAuditEntry() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"select * from OSB_AuditEntry where (field = ?) and " +
					"(newValue = ?) and oldValue is not null";

			ps = con.prepareStatement(sql);

			int field = AuditEntryConstants.FIELD_ENV_NAME;

			ps.setInt(1, field);
			ps.setString(2, StringPool.BLANK);

			rs = ps.executeQuery();

			while (rs.next()) {
				long auditEntryId = rs.getLong("auditEntryId");
				long fieldClassNameId = rs.getLong("fieldClassNameId");
				long fieldClassPK = rs.getLong("fieldClassPK");

				AuditEntry auditEntry =
					AuditEntryLocalServiceUtil.getLastAuditEntry(
						fieldClassNameId, fieldClassPK, field);

				if (auditEntryId == auditEntry.getAuditEntryId()) {
					long userId = rs.getLong("userId");
					String userName = rs.getString("userName");
					long classNameId = rs.getLong("classNameId");
					long classPK = rs.getLong("classPK");

					long auditSetId =
						AuditEntryLocalServiceUtil.getNextAuditSetId(
							classNameId, classPK);

					int action = AuditEntryConstants.ACTION_UPDATE;
					int visibility = rs.getInt("visibility");
					String oldLabel = rs.getString("oldLabel");
					String oldValue = rs.getString("oldValue");
					String newLabel = rs.getString("newLabel");
					String newValue = rs.getString("newValue");

					AuditEntryLocalServiceUtil.addAuditEntry(
						userId, userName, new Date(), classNameId, classPK,
						auditSetId, fieldClassNameId, fieldClassPK, action,
						field, visibility, newLabel, newValue, oldLabel,
						oldValue);

					updateTicketInformation(fieldClassPK, oldValue);
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateTicketEntry() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(3);

			sb.append("select ticketEntryId, data_ from ");
			sb.append("OSB_TicketInformation where data_ is not null and ");
			sb.append("fieldId = ?");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, TicketInformationConstants.FIELD_ENV_NAME);

			rs = ps.executeQuery();

			while (rs.next()) {
				long ticketEntryId = rs.getLong("ticketEntryId");
				String data_ = rs.getString("data_");

				updateTicketEntryData(ticketEntryId, data_);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateTicketEntryData(long ticketEntryId, String envName)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"update OSB_TicketEntry set envName = ? where (" +
					"ticketEntryId = ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, envName);
			ps.setLong(2, ticketEntryId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void updateTicketInformation(long ticketEntryId, String data)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"update OSB_TicketInformation set data_ = ? where (" +
					"fieldId = ?) and (ticketEntryId = ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, data);
			ps.setLong(2, TicketInformationConstants.FIELD_ENV_NAME);
			ps.setLong(3, ticketEntryId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

}
*/

}