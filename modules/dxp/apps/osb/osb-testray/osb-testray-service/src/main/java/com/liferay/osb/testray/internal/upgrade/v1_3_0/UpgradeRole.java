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

package com.liferay.osb.testray.internal.upgrade.v1_3_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Objects;

/**
 * @author Andrew Kim
 */
public class UpgradeRole extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (_hasSubtype()) {
			return;
		}

		runSQL(
			"update Role_ set subtype = 'com.liferay.osb.testray' where name " +
				"in (" + _getRoleNamesString() + StringPool.CLOSE_PARENTHESIS);
	}

	private String _getRoleNamesString() {
		StringBundler sb = new StringBundler(_TESTRAY_ROLE_NAMES.length * 4);

		for (String testrayRoleName : _TESTRAY_ROLE_NAMES) {
			sb.append(StringPool.APOSTROPHE);
			sb.append(testrayRoleName);
			sb.append(StringPool.APOSTROPHE);
			sb.append(StringPool.COMMA);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	private boolean _hasSubtype() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select * from Role_ where name in (" + _getRoleNamesString() +
					StringPool.CLOSE_PARENTHESIS);

			rs = ps.executeQuery();

			while (rs.next()) {
				String subtype = rs.getString("subtype");

				if (!Objects.equals(subtype, "com.liferay.osb.testray")) {
					return false;
				}
			}

			return true;
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final String[] _TESTRAY_ROLE_NAMES = {
		"Test Designer", "Test Lead", "Tester", "Testray Administrator",
		"Testray User"
	};

}