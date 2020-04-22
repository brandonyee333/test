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

package com.liferay.post.upgrade.fix.LPS_66599.osgi.commands;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.post.upgrade.fix.osgi.commands.BasePostUpgradeFixOSGiCommands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alberto Chaparro
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

	public static final String FUNCTION = "LPS_66599";

	public void LPS_66599() {
		execute();
	}

	@Override
	protected void doExecute() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("select MBThread.groupId, MBDiscussion.discussionId from ");
		sb.append("MBDiscussion inner join MBThread on MBDiscussion.threadId ");
		sb.append("= MBThread.threadId where MBDiscussion.groupId = 0");

		try (Connection connection = DataAccess.getConnection();
			PreparedStatement ps1 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update MBDiscussion set groupId = ? where discussionId " +
						"= ?");
			PreparedStatement ps2 = connection.prepareStatement(
				sb.toString())) {

			try (ResultSet rs = ps2.executeQuery()) {
				while (rs.next()) {
					long groupId = rs.getLong(1);
					long discussionId = rs.getLong(2);

					ps1.setLong(1, groupId);
					ps1.setLong(2, discussionId);

					ps1.addBatch();
				}

				ps1.executeBatch();
			}
		}
	}

	@Override
	protected String getFunction() {
		return FUNCTION;
	}

}