/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_13_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import java.util.Objects;

/**
 * @author William Newbury
 */
public class UpgradeTestrayBuild extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayBuild", "IX_8A80C617")) {
			return;
		}

		runSQL("drop index IX_4ACC8A2C on OSB_TestrayBuild");
		runSQL(
			"create unique index IX_8A80C617 on OSB_TestrayBuild" +
				"(testrayRoutineId, name[$COLUMN_LENGTH:150$])");
	}

	protected boolean tableHasIndex(String tableName, String indexName)
		throws Exception {

		ResultSet rs = null;

		try {
			DatabaseMetaData metadata = connection.getMetaData();

			rs = metadata.getIndexInfo(null, null, tableName, false, false);

			while (rs.next()) {
				String curIndexName = rs.getString("index_name");

				if (Objects.equals(indexName, curIndexName)) {
					return true;
				}
			}
		}
		finally {
			DataAccess.cleanUp(rs);
		}

		return false;
	}

}