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

package com.liferay.osb.customer.web.internal.upgrade.v1_0_0;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jenny Chen
 */
public class UpgradeSubscription extends UpgradeProcess {

	public UpgradeSubscription(Portal portal) {
		_portal = portal;
	}

	@Override
	protected void doUpgrade() throws Exception {
		long journalArticleClassNameId = _portal.getClassNameId(
			JournalArticle.class.getName());

		long journalFolderClassNameId = _portal.getClassNameId(
			JournalFolder.class.getName());

		updateSubscriptionClassNameIds(
			journalFolderClassNameId, journalArticleClassNameId);

		updateSubscriptionGroupIds(journalArticleClassNameId);
	}

	protected long getJournalArticleGroupId(long classPK) throws Exception {
		String sql =
			"select groupId from JournalArticle where resourcePrimKey = ?";

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setLong(1, classPK);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong("groupId");
			}
		}

		return 0;
	}

	protected void updateSubscriptionClassNameIds(
			long oldClassNameId, long newClassNameId)
		throws Exception {

		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(5);

			sb.append("update Subscription set classNameId = ");
			sb.append(newClassNameId);
			sb.append(" where classNameId = ");
			sb.append(oldClassNameId);
			sb.append(" and groupId is null");

			runSQL(sb.toString());
		}
	}

	protected void updateSubscriptionGroupIds(long classNameId)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append("select subscriptionId, classPK from Subscription where ");
		sb.append("classNameId = ");
		sb.append(classNameId);
		sb.append(" and groupId is null");

		String sql1 = sb.toString();

		String sql2 =
			"update Subscription set groupId = ? where subscriptionId = ?";

		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(sql1);
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection, sql2)) {

			ResultSet rs = ps1.executeQuery();

			while (rs.next()) {
				long subscriptionId = rs.getLong("subscriptionId");
				long classPK = rs.getLong("classPK");

				long groupId = getJournalArticleGroupId(classPK);

				if (groupId != 0) {
					ps2.setLong(1, groupId);
					ps2.setLong(2, subscriptionId);

					ps2.addBatch();
				}
			}

			ps2.executeBatch();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeSubscription.class);

	private final Portal _portal;

}