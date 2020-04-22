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

package com.liferay.post.upgrade.fix.LPS_66133.osgi.commands;

import com.liferay.message.boards.kernel.model.MBCategoryConstants;
import com.liferay.message.boards.kernel.model.MBDiscussion;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.post.upgrade.fix.osgi.commands.BasePostUpgradeFixOSGiCommands;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Díaz
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

	public static final String FUNCTION = "LPS_66133";

	public void LPS_66133() {
		execute();
	}

	@Override
	protected void doExecute() throws Exception {
		String tempTableName = "TEMP_TABLE_" + StringUtil.randomString(4);

		try {
			runSQL("create table " + tempTableName + " (threadId LONG)");

			StringBundler sb = new StringBundler(8);

			sb.append("insert into ");
			sb.append(tempTableName);
			sb.append(" select MBMessage.threadId from MBThread, MBMessage ");
			sb.append("where MBThread.threadId = MBMessage.threadId and ");
			sb.append("MBThread.categoryId = ");
			sb.append(MBCategoryConstants.DISCUSSION_CATEGORY_ID);
			sb.append(" group by MBMessage.threadId having ");
			sb.append("count(MBMessage.messageId) = 1");

			runSQL(sb.toString());

			long classNameId = _portal.getClassNameId(
				MBDiscussion.class.getName());

			sb = new StringBundler(7);

			sb.append("delete from AssetEntry where classPK in (");
			sb.append("select MBMessage.messageId from MBMessage inner join ");
			sb.append(tempTableName);
			sb.append(" on MBMessage.threadId = ");
			sb.append(tempTableName);
			sb.append(".threadId) and classNameId = ");
			sb.append(classNameId);

			runSQL(sb.toString());

			sb = new StringBundler(4);

			sb.append("delete from MBDiscussion where threadId in (");
			sb.append("select threadId from ");
			sb.append(tempTableName);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			runSQL(sb.toString());

			sb = new StringBundler(4);

			sb.append("delete from MBMessage where threadId in (");
			sb.append("select threadId from ");
			sb.append(tempTableName);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			runSQL(sb.toString());

			sb = new StringBundler(4);

			sb.append("delete from MBThread where threadId in (");
			sb.append("select threadId from ");
			sb.append(tempTableName);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			runSQL(sb.toString());
		}
		finally {
			try {
				runSQL("drop table " + tempTableName);
			}
			catch (Exception e) {
				String msg = StringBundler.concat(
					"An exception was thrown while deleting temporary table ",
					tempTableName, " of ", getCommand());

				log.error(msg, e);
			}
		}
	}

	@Override
	protected String getFunction() {
		return FUNCTION;
	}

	@Reference
	private Portal _portal;

}