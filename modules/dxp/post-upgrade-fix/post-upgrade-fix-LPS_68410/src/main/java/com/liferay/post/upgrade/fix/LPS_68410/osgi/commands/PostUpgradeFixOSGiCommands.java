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

package com.liferay.post.upgrade.fix.LPS_68410.osgi.commands;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.post.upgrade.fix.osgi.commands.BasePostUpgradeFixOSGiCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Christopher Kian
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=" + PostUpgradeFixOSGiCommands.FUNCTION,
		"osgi.command.scope=" + PostUpgradeFixOSGiCommands.SCOPE
	},
	service = PostUpgradeFixOSGiCommands.class
)
public class PostUpgradeFixOSGiCommands extends BasePostUpgradeFixOSGiCommands {

	public static final String FUNCTION = "LPS_68410";

	public void LPS_68410() {
		execute();
	}

	protected void alterNVarcharColumnsSQLServer() throws SQLException {
		StringBundler sb = new StringBundler(3);

		sb.append("select table_name, column_name from ");
		sb.append("INFORMATION_SCHEMA.COLUMNS where DATA_TYPE = 'nvarchar' ");
		sb.append("and character_maximum_length = 2000");

		Connection connection = DataAccess.getConnection();

		try (PreparedStatement ps1 = connection.prepareStatement(sb.toString());
			ResultSet rs1 = ps1.executeQuery()) {

			while (rs1.next()) {
				String tableName = rs1.getString(1);
				String columnName = rs1.getString(2);

				sb = new StringBundler(5);

				sb.append("alter table ");
				sb.append(tableName);
				sb.append(" alter column ");
				sb.append(columnName);
				sb.append(" nvarchar(4000)");

				try (PreparedStatement ps2 = connection.prepareStatement(
						sb.toString())) {

					ps2.execute();
				}
				catch (SQLException sqle) {
					if (sqle.getErrorCode() == 1441) {
						if (log.isWarnEnabled()) {
							sb = new StringBundler(6);

							sb.append("Unable to alter length of column ");
							sb.append(columnName);
							sb.append(" for table ");
							sb.append(tableName);
							sb.append(" because it contains values larger ");
							sb.append("than the new column length");

							log.warn(sb.toString());
						}
					}
					else {
						throw sqle;
					}
				}
			}
		}
	}

	protected void alterVarcharColumnsSybase() throws SQLException {
		StringBundler sb = new StringBundler(3);

		sb.append("select o.name, c.name from sysobjects o, syscolumns c, ");
		sb.append("systypes t where o.id = c.id and c.type = t.type and ");
		sb.append("t.name = 'varchar' and c.length = 1000");

		Connection connection = DataAccess.getConnection();

		try (PreparedStatement ps1 = connection.prepareStatement(sb.toString());
			ResultSet rs1 = ps1.executeQuery()) {

			while (rs1.next()) {
				String tableName = rs1.getString(1);
				String columnName = rs1.getString(2);

				sb = new StringBundler(5);

				sb.append("alter table ");
				sb.append(tableName);
				sb.append(" modify ");
				sb.append(columnName);
				sb.append(" varchar(4000)");

				try (PreparedStatement ps2 = connection.prepareStatement(
						sb.toString())) {

					ps2.execute();
				}
				catch (SQLException sqle) {
					if (sqle.getErrorCode() == 1441) {
						if (log.isWarnEnabled()) {
							sb = new StringBundler(6);

							sb.append("Unable to alter length of column ");
							sb.append(columnName);
							sb.append(" for table ");
							sb.append(tableName);
							sb.append(" because it contains values larger ");
							sb.append("than the new column length");

							log.warn(sb.toString());
						}
					}
					else {
						throw sqle;
					}
				}
			}
		}
	}

	@Override
	protected void doExecute() throws Exception {
		DB db = DBManagerUtil.getDB();

		if (db.getDBType() == DBType.SQLSERVER) {
			alterNVarcharColumnsSQLServer();
		}
		else if (db.getDBType() == DBType.SYBASE) {
			alterVarcharColumnsSybase();
		}
	}

	@Override
	protected String getFunction() {
		return FUNCTION;
	}

}