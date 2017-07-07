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

package com.liferay.osb.hook.upgrade.v3_0_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20140620125908338_AppEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20140620125908338L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAppEntry();
	}

	protected void upgradeAppEntry() throws Exception {
		for (long appEntryId : _MALFORMED_APP_ENTRY_IDS) {
			AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(
				appEntryId);

			if (appEntry != null) {
				AppEntryLocalServiceUtil.deleteAppEntry(appEntry);
			}
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select count(*) from OSB_AppEntry where " +
					"developerEntryId is null or developerEntryId = 0");

			rs = ps.executeQuery();

			long count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to set the developer entry ID for " + count +
						" app entries");
			}

			ps = con.prepareStatement(
				"select count(*) from OSB_AppVersion where " +
					"developerEntryId is null or developerEntryId = 0");

			rs = ps.executeQuery();

			count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to set developer entry ID for " + count +
						" app versions");
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
}

	private static final long[] _MALFORMED_APP_ENTRY_IDS = {
		17109010, 27269710, 27269788, 27269997
	};

}
*/

}