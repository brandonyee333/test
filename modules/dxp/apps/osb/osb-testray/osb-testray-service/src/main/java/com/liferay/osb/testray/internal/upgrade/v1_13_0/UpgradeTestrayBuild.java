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