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

package com.liferay.osb.hook.upgrade.v3_1_5;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.ContextUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Joan Kim
 */
public class Upgrade_20141202100148451_AppPackagePlugin
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141202100148451L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select * from OSB_AppPackagePlugin");

			rs = ps.executeQuery();

			while (rs.next()) {
				String contextName = rs.getString("contextName");

				if (Validator.isNull(contextName)) {
					continue;
				}

				StringBundler sb = new StringBundler(4);

				sb.append("update OSB_AppPackagePlugin set contextName = '");
				sb.append(ContextUtil.getContextName(contextName, false));
				sb.append("' where appPackagePluginId = ");
				sb.append(rs.getLong("appPackagePluginId"));

				runSQL(sb.toString(), false);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}