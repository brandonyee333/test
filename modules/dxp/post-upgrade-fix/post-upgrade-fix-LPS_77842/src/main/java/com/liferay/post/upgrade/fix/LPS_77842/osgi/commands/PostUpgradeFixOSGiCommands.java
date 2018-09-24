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

package com.liferay.post.upgrade.fix.LPS_777842.osgi.command;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.post.upgrade.fix.BasePostUpgradeFixOSGiCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amadea Fejes
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=" + PostUpgradeFixOSGiCommands.FUNCTION,
		"osgi.command.scope=" + BasePostUpgradeFixOSGiCommands.SCOPE
	},
	service = PostUpgradeFixOSGiCommands.class
)
public class PostUpgradeFixOSGiCommands extends BasePostUpgradeFixOSGiCommands {

	public static final String FUNCTION = "LPS_77842";

	public void LPS_77842() {
		execute();
	}

	protected void alterRepositoryTableNameColumn() throws SQLException {
		StringBundler sb = new StringBundler(5);

		sb.append("alter table ");
		sb.append("repository");
		sb.append(" modify ");
		sb.append("name");

		DB db = DBManagerUtil.getDB();

		if (db.getDBType() == DBType.ORACLE) {
			sb.append(" varchar(200 CHAR)");
		}
		else {
			sb.append(" varchar(200)");
		}

		try (Connection connection = DataAccess.getConnection();
				PreparedStatement ps = connection.prepareStatement(
				sb.toString())) {

			ps.execute();
		}
	}

	@Override
	protected void doExecute() throws Exception {
		alterRepositoryTableNameColumn();
	}

	@Override
	protected String getFunction() {
		return FUNCTION;
	}

}