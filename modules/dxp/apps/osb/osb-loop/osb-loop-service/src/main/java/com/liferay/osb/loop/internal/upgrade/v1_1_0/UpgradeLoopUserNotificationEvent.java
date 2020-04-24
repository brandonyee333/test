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

package com.liferay.osb.loop.internal.upgrade.v1_1_0;

import com.liferay.osb.loop.model.impl.LoopUserNotificationEventModelImpl;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Timothy Bell
 */
public class UpgradeLoopUserNotificationEvent extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn(
				LoopUserNotificationEventModelImpl.TABLE_NAME, "createTime")) {

			runSQL(
				"alter table LoopUserNotificationEvent add column createTime " +
					"long after loopUserNotificationEventId");
		}

		if (!hasColumn(
				LoopUserNotificationEventModelImpl.TABLE_NAME,
				"recipientUserId")) {

			runSQL(
				"alter table LoopUserNotificationEvent add column " +
					"recipientUserId long after createTime");
		}

		if (!hasColumn(
				LoopUserNotificationEventModelImpl.TABLE_NAME, "received")) {

			runSQL(
				"alter table LoopUserNotificationEvent add column received " +
					"boolean after type_");
		}

		if (!hasColumn(
				LoopUserNotificationEventModelImpl.TABLE_NAME, "opened")) {

			runSQL(
				"alter table LoopUserNotificationEvent add column opened " +
					"boolean after received");
		}

		upgradeLoopUserNotificationEvent();

		if (hasColumn(
				LoopUserNotificationEventModelImpl.TABLE_NAME,
				"userNotificationEventId")) {

			runSQL(
				"alter table LoopUserNotificationEvent drop column " +
					"userNotificationEventId");
		}

		if (tableHasIndex(
				LoopUserNotificationEventModelImpl.TABLE_NAME, "IX_20D4709")) {

			runSQL(
				"alter table LoopUserNotificationEvent drop index IX_20D4709");
		}

		runSQL("truncate table UserNotificationEvent");
	}

	protected void updateLoopUserNotificationEvent(
			long loopUserNotificationEventId, long recipientUserId,
			long createTime, boolean opened, boolean received)
		throws Exception {

		StringBundler sb = new StringBundler(10);

		sb.append("update LoopUserNotificationEvent set createTime = ");
		sb.append(createTime);
		sb.append(", recipientUserId = ");
		sb.append(recipientUserId);
		sb.append(", received = ");
		sb.append(received);
		sb.append(", opened = ");
		sb.append(opened);
		sb.append(" where loopUserNotificationEventId = ");
		sb.append(loopUserNotificationEventId);

		runSQL(sb.toString());
	}

	protected void upgradeLoopUserNotificationEvent() throws Exception {
		PreparedStatement ps = null;

		StringBundler sb = new StringBundler(4);

		sb.append("select * from LoopUserNotificationEvent inner join ");
		sb.append("UserNotificationEvent on ");
		sb.append("LoopUserNotificationEvent.userNotificationEventId = ");
		sb.append("UserNotificationEvent.userNotificationEventId");

		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long loopUserNotificationEventId = rs.getLong(
					"LoopUserNotificationEvent.loopUserNotificationEventId");
				long userId = rs.getLong("UserNotificationEvent.userId");
				long timestamp = rs.getLong("UserNotificationEvent.timestamp");
				boolean delivered = rs.getBoolean(
					"UserNotificationEvent.delivered");
				boolean archived = rs.getBoolean(
					"UserNotificationEvent.archived");

				updateLoopUserNotificationEvent(
					loopUserNotificationEventId, userId, timestamp, delivered,
					archived);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}