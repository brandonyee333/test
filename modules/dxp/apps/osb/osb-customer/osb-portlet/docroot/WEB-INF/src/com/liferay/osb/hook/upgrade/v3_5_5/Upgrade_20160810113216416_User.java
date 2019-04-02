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

package com.liferay.osb.hook.upgrade.v3_5_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Amos Fong
 */
public class Upgrade_20160810113216416_User extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select userId, defaultUser, screenName from User_ where " +
					"(screenName REGEXP '^[0-9]+$') and (status = 0)");

			rs = ps.executeQuery();

			while (rs.next()) {
				boolean defaultUser = rs.getBoolean("defaultUser");
				String screenName = rs.getString("screenName");
				long userId = rs.getLong("userId");

				if (defaultUser) {
					continue;
				}

				try {
					UserLocalServiceUtil.updateScreenName(
						userId, "user." + screenName);
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		Upgrade_20160810113216416_User.class);

}