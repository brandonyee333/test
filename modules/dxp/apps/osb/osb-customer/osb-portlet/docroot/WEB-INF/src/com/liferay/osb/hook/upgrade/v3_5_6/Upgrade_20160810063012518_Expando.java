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

package com.liferay.osb.hook.upgrade.v3_5_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.User;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jenny Chen
 */
public class Upgrade_20160810063012518_Expando extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160810063012518L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select classPK from ExpandoValue where ((columnId = ?) or " +
					"(columnId = ?)) and classPK not in (select userId from " +
						"OSB_AccountEntry where type_ = 4)");

			ExpandoColumn studioExpandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(
					OSBConstants.COMPANY_ID, User.class.getName(),
					ExpandoTableConstants.DEFAULT_TABLE_NAME, "osbStudioEULA");
			ExpandoColumn trialExpandoColumn =
				ExpandoColumnLocalServiceUtil.getColumn(
					OSBConstants.COMPANY_ID, User.class.getName(),
					ExpandoTableConstants.DEFAULT_TABLE_NAME, "osbTrialEULA");

			ps.setLong(1, studioExpandoColumn.getColumnId());
			ps.setLong(2, trialExpandoColumn.getColumnId());

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("classPK");

				updateExpandoValues(OSBConstants.COMPANY_ID, userId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateExpandoValues(long companyId, long userId)
		throws Exception {

		ExpandoValue studioExpandoValue = ExpandoValueLocalServiceUtil.getValue(
			companyId, User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "osbStudioEULA", userId);
		ExpandoValue trialExpandoValue = ExpandoValueLocalServiceUtil.getValue(
			companyId, User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, "osbTrialEULA", userId);

		if ((studioExpandoValue != null) && (trialExpandoValue != null)) {
			ExpandoValueLocalServiceUtil.deleteValue(studioExpandoValue);
			ExpandoValueLocalServiceUtil.deleteValue(trialExpandoValue);
		}
	}

}