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

package com.liferay.osb.hook.upgrade.v3_5_6;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.User;

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
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
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
			DataAccess.cleanUp(ps, rs);
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