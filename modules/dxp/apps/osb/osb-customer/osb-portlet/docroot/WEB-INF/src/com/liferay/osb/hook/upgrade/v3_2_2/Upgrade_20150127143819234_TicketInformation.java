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

package com.liferay.osb.hook.upgrade.v3_2_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketInformationConstants;
import com.liferay.osb.model.impl.TicketInformationModelImpl;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20150127143819234_TicketInformation
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150127143819234L;
	}

	protected void createTicketInformation() throws Exception {
		if (hasTable(TicketInformationModelImpl.TABLE_NAME)) {
			return;
		}

		runSQL(TicketInformationModelImpl.TABLE_SQL_CREATE);

		createTicketInformationIndexes();
	}

	protected void createTicketInformationIndexes() throws Exception {
		if (!hasIndex(TicketInformationModelImpl.TABLE_NAME, "IX_539CB0C")) {
			runSQL(
				"create index IX_539CB0C on OSB_TicketInformation " +
					"(ticketEntryId)");
		}

		if (!hasIndex(TicketInformationModelImpl.TABLE_NAME, "IX_C15F4C19")) {
			runSQL(
				"create index IX_C15F4C19 on OSB_TicketInformation " +
					"(ticketEntryId, fieldId)");
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		createTicketInformation();

		migrateTicketInformation();
	}

	protected void migrateTicketInformation() throws Exception {
		if (hasRows(TicketInformationModelImpl.TABLE_NAME)) {
			return;
		}

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("select * from OSB_TicketEntry");

			rs = ps.executeQuery();

			while (rs.next()) {
				long ticketEntryId = rs.getLong("ticketEntryId");

				if (rs.getInt("envAS") > 0) {
					updateTicketInformation(
						ticketEntryId, TicketInformationConstants.FIELD_ENV_AS,
						String.valueOf(rs.getInt("envAS")));
				}

				if (rs.getInt("envBrowser") > 0) {
					updateTicketInformation(
						ticketEntryId,
						TicketInformationConstants.FIELD_ENV_BROWSER,
						String.valueOf(rs.getInt("envBrowser")));
				}

				if (rs.getInt("envDB")> 0) {
					updateTicketInformation(
						ticketEntryId, TicketInformationConstants.FIELD_ENV_DB,
						String.valueOf(rs.getInt("envDB")));
				}

				if (rs.getInt("envJVM") > 0) {
					updateTicketInformation(
						ticketEntryId, TicketInformationConstants.FIELD_ENV_JVM,
						String.valueOf(rs.getInt("envJVM")));
				}

				if (rs.getInt("envLFR") > 0) {
					updateTicketInformation(
						ticketEntryId, TicketInformationConstants.FIELD_ENV_LFR,
						String.valueOf(rs.getInt("envLFR")));
				}

				if (rs.getInt("envOS") > 0) {
					updateTicketInformation(
						ticketEntryId, TicketInformationConstants.FIELD_ENV_OS,
						String.valueOf(rs.getInt("envOS")));
				}

				if (Validator.isNotNull(rs.getString("envName"))) {
					updateTicketInformation(
						ticketEntryId,
						TicketInformationConstants.FIELD_ENV_NAME,
						rs.getString("envName"));
				}

				if (Validator.isNotNull(rs.getString("envBrowserCustom"))) {
					updateTicketInformation(
						ticketEntryId,
						TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM,
						rs.getString("envBrowserCustom"));
				}

				if (Validator.isNotNull(rs.getString("envOSCustom"))) {
					updateTicketInformation(
						ticketEntryId,
						TicketInformationConstants.FIELD_ENV_OS_CUSTOM,
						rs.getString("envOSCustom"));
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateTicketInformation(
			long ticketEntryId, long fieldId, String data)
		throws Exception {

		PreparedStatement ps = null;

		try {
			StringBundler sb = new StringBundler(3);

			sb.append("insert into OSB_TicketInformation ");
			sb.append("(ticketInformationId, ticketEntryId, fieldId, data_) ");
			sb.append("values (?, ?, ?, ?)");

			ps = connection.prepareStatement(sb.toString());

			ps.setLong(1, increment());
			ps.setLong(2, ticketEntryId);
			ps.setLong(3, fieldId);
			ps.setString(4, data);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

}