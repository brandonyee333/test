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

package com.liferay.osb.hook.upgrade.v5_1_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jenny Chen
 */
public class UpgradeAccountEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select corpProjectUuid from OSB_AccountEntry where " +
					"corpProjectId = 0 and corpProjectUuid is not null";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				String corpProjectUuid = rs.getString("corpProjectUuid");

				CorpProject corpProject =
					CorpProjectLocalServiceUtil.fetchCorpProjectByUuid(
						corpProjectUuid);

				if (corpProject == null) {
					_log.error("Unable to find corpProject " + corpProjectUuid);

					continue;
				}

				StringBundler sb = new StringBundler(6);

				sb.append("update OSB_AccountEntry set corpProjectId = ");
				sb.append(corpProject.getCorpProjectId());
				sb.append(" where corpProjectUuid = ");
				sb.append(StringPool.APOSTROPHE);
				sb.append(corpProjectUuid);
				sb.append(StringPool.APOSTROPHE);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeAccountEntry.class);

}