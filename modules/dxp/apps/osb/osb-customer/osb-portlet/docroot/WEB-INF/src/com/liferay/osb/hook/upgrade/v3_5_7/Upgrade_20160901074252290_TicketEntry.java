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

package com.liferay.osb.hook.upgrade.v3_5_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;

*/

/**
 * @author Jenny Chen
 */
public class Upgrade_20160901074252290_TicketEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20160901074252290L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateAuditEntry();
	}

	protected void updateAuditEntry() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"select * from OSB_AuditEntry where (field = ?) and " +
					"(newValue = ?) and (oldValue is not null)";

			ps = con.prepareStatement(sql);

			ps.setInt(1, AuditEntryConstants.FIELD_REPRODUCTION_STEPS);
			ps.setString(2, StringPool.BLANK);

			rs = ps.executeQuery();

			while (rs.next()) {
				long auditEntryId = rs.getLong("auditEntryId");
				long fieldClassNameId = rs.getLong("fieldClassNameId");
				long fieldClassPK = rs.getLong("fieldClassPK");

				AuditEntry auditEntry =
					AuditEntryLocalServiceUtil.getLastAuditEntry(
						fieldClassNameId, fieldClassPK,
						AuditEntryConstants.FIELD_REPRODUCTION_STEPS);

				if (auditEntryId == auditEntry.getAuditEntryId()) {
					long userId = rs.getLong("userId");
					String userName = rs.getString("userName");
					long classNameId = rs.getLong("classNameId");
					long classPK = rs.getLong("classPK");
					int visibility = rs.getInt("visibility");
					String oldLabel = rs.getString("oldLabel");
					String oldValue = rs.getString("oldValue");
					String newLabel = rs.getString("newLabel");
					String newValue = rs.getString("newValue");

					long auditSetId =
						AuditEntryLocalServiceUtil.getNextAuditSetId(
							classNameId, classPK);

					AuditEntryLocalServiceUtil.addAuditEntry(
						userId, userName, new Date(), classNameId, classPK,
						auditSetId, fieldClassNameId, fieldClassPK,
						AuditEntryConstants.ACTION_UPDATE,
						AuditEntryConstants.FIELD_REPRODUCTION_STEPS,
						visibility, newLabel, newValue, oldLabel, oldValue);

					updateTicketEntry(fieldClassPK, oldValue);
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateTicketEntry(
			long ticketEntryId, String reproductionSteps)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"update OSB_TicketEntry set reproductionSteps = ? where (" +
					"ticketEntryId = ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, reproductionSteps);
			ps.setLong(2, ticketEntryId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	 */

}