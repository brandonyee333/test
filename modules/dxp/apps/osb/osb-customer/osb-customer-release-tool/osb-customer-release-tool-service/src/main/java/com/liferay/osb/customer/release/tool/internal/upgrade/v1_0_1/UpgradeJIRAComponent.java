/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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