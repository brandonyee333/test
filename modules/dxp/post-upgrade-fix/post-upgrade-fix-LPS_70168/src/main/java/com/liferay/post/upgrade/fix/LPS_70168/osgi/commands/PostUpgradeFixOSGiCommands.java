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

package com.liferay.post.upgrade.fix.LPS_70168.osgi.commands;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.FullNameGenerator;
import com.liferay.portal.kernel.security.auth.FullNameGeneratorFactory;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.post.upgrade.fix.osgi.commands.BasePostUpgradeFixOSGiCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael Bowerman
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

	public static final String FUNCTION = "LPS_70168";

	public void LPS_70168() {
		execute();
	}

	@Override
	protected void doExecute() throws Exception {
		Connection connection = DataAccess.getConnection();

		try (PreparedStatement ps1 = connection.prepareStatement(
				"select distinct userId from DLFolder where userName is null " +
					"or userName = ''");
			ResultSet rs = ps1.executeQuery();
			PreparedStatement ps2 = AutoBatchPreparedStatementUtil.autoBatch(
				connection.prepareStatement(
					"update DLFolder set userName = ? where userId = ? and " +
						"(userName is null or userName = '')"))) {

			while (rs.next()) {
				long userId = rs.getLong("userId");

				String userName = getUserName(userId);

				if (Validator.isNotNull(userName)) {
					ps2.setString(1, userName);
					ps2.setLong(2, userId);

					ps2.addBatch();
				}
				else {
					if (_log.isInfoEnabled()) {
						_log.info("User " + userId + " does not exist");
					}
				}
			}

			ps2.executeBatch();
		}
	}

	@Override
	protected String getFunction() {
		return FUNCTION;
	}

	protected String getUserName(long userId) throws Exception {
		Connection connection = DataAccess.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
				"select firstName, middleName, lastName from User_ where " +
					"userId = ?")) {

			ps.setLong(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String firstName = rs.getString("firstName");
					String middleName = rs.getString("middleName");
					String lastName = rs.getString("lastName");

					FullNameGenerator fullNameGenerator =
						FullNameGeneratorFactory.getInstance();

					return fullNameGenerator.getFullName(
						firstName, middleName, lastName);
				}

				return StringPool.BLANK;
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PostUpgradeFixOSGiCommands.class);

}