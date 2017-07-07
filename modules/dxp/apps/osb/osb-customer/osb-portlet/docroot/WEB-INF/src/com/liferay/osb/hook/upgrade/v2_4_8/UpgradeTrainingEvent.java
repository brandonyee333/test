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

package com.liferay.osb.hook.upgrade.v2_4_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Locale;

*/

/**
 * @author Wesley Gong
 */
public class UpgradeTrainingEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingEvent();
	}

	protected void updateTrainingEvent() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select trainingEventId, languageId from OSB_TrainingEvent");

			rs = ps.executeQuery();

			while (rs.next()) {
				long trainingEventId = rs.getLong("trainingEventId");
				String languageId = rs.getString("languageId");

				Locale locale = LanguageUtil.getLocale(languageId);

				runSQL(
					"update OSB_TrainingEvent set languageId = '" +
						LocaleUtil.toLanguageId(locale) + "' where " +
							"trainingEventId = " + trainingEventId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}
*/

}