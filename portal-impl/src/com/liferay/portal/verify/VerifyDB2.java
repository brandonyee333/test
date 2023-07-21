/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Igor Beslic
 */
public class VerifyDB2 extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		DB db = DBManagerUtil.getDB();

		if (db.getDBType() != DBType.DB2) {
			return;
		}

		verifyDB2();
	}

	protected void verifyDB2() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(4);

			sb.append("select tbname, name, coltype, length from ");
			sb.append("sysibm.syscolumns where tbcreator = (select distinct ");
			sb.append("current schema from sysibm.sysschemata) AND coltype = ");
			sb.append("'VARCHAR' and length = 500");

			try (PreparedStatement ps = connection.prepareStatement(
					sb.toString());
				ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					String tableName = rs.getString(1);

					if (!isPortalTableName(tableName)) {
						continue;
					}

					String columnName = rs.getString(2);

					runSQL(
						StringBundler.concat(
							"alter table ", tableName, " alter column ",
							columnName, " set data type varchar(600)"));
				}
			}
		}
	}

}