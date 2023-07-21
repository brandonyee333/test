/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.internal.upgrade.v1_0_1;

import com.liferay.osb.customer.release.tool.service.JIRAComponentLocalService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jenny Chen
 */
public class UpgradeJIRAComponent extends UpgradeProcess {

	public UpgradeJIRAComponent(
		JIRAComponentLocalService jiraComponentLocalService) {

		_jiraComponentLocalService = jiraComponentLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSBCustomer_JIRAComponent", "remoteProject")) {
			runSQL(
				"alter table OSBCustomer_JIRAComponent add column " +
					"remoteProject varchar(75)");
		}

		if (hasIndex("OSBCustomer_JIRAComponent", "IX_C6B593C7")) {
			runSQL("drop index IX_C6B593C7 on OSBCustomer_JIRAComponent");
		}

		if (!hasIndex("OSBCustomer_JIRAComponent", "IX_B9CDBD3C")) {
			runSQL(
				"create index IX_B9CDBD3C on OSBCustomer_JIRAComponent " +
					"(remoteId, remoteProject[$COLUMN_LENGTH:75$])");
		}

		if (!hasIndex("OSBCustomer_JIRAComponent", "IX_D7782611")) {
			runSQL(
				"create index IX_D7782611 on OSBCustomer_JIRAComponent " +
					"(remoteProject[$COLUMN_LENGTH:75$])");
		}

		updateLPSComponents();
	}

	protected boolean hasIndex(String tableName, String indexName)
		throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			DatabaseMetaData metadata = connection.getMetaData();

			rs = metadata.getIndexInfo(null, null, tableName, false, false);

			while (rs.next()) {
				String curIndexName = rs.getString("index_name");

				if (indexName.equals(curIndexName)) {
					return true;
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}

		return false;
	}

	protected void updateLPSComponents() throws Exception {
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(
				"update OSBCustomer_JIRAComponent set remoteProject = ? " +
					"where remoteProject is null");

			ps.setString(1, "LPS");

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	private final JIRAComponentLocalService _jiraComponentLocalService;

}