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

package com.liferay.osb.hook.upgrade.v3_1_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.DDLRecordSetConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Calvin Keum
 */
public class Upgrade_20141024135500277_DDLRecordSet extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20141024135500277L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select recordSetId, name, description from DDLRecordSet " +
					"where scope = " +
						DDLRecordSetConstants.SCOPE_TRAINING_EVENTS);

			rs = ps.executeQuery();

			while (rs.next()) {
				long recordSetId = rs.getLong("recordSetId");
				String name = rs.getString("name");
				String description = rs.getString("description");

				name = name.replaceAll(
					_TRAINING_EVENT_DATE_REGEX, StringPool.BLANK);

				description = description.replaceAll(
					_TRAINING_EVENT_DATE_REGEX, StringPool.BLANK);

				ps = connection.prepareStatement(
					"update DDLRecordSet set name = ?, description = ? where " +
						"recordSetId = " + recordSetId);

				ps.setString(1, name);
				ps.setString(2, description);

				ps.executeUpdate();
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private final String _TRAINING_EVENT_DATE_REGEX =
		"(, [A-Z][a-z]{2} [0-9]{1,2}, [0-9]{4} - [A-Z][a-z]{2} [0-9]{1,2}, " +
			"[0-9]{4})";

	 */

}