/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsValues;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class VerifyMySQL extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		DB db = DBManagerUtil.getDB();

		if ((db.getDBType() == DBType.MARIADB) ||
			(db.getDBType() == DBType.MYSQL)) {

			verifyTableEngine();
		}
	}

	protected void verifyTableEngine() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("show table status")) {

			while (rs.next()) {
				String tableName = rs.getString("Name");

				if (!isPortalTableName(tableName)) {
					continue;
				}

				String engine = GetterUtil.getString(rs.getString("Engine"));

				String comment = GetterUtil.getString(rs.getString("Comment"));

				if (StringUtil.equalsIgnoreCase(comment, "VIEW")) {
					continue;
				}

				if (StringUtil.equalsIgnoreCase(
						engine, PropsValues.DATABASE_MYSQL_ENGINE)) {

					continue;
				}

				if (_log.isInfoEnabled()) {
					_log.info(
						StringBundler.concat(
							"Updating table ", tableName, " to use engine ",
							PropsValues.DATABASE_MYSQL_ENGINE));
				}

				statement.executeUpdate(
					StringBundler.concat(
						"alter table ", tableName, " engine ",
						PropsValues.DATABASE_MYSQL_ENGINE));
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(VerifyMySQL.class);

}