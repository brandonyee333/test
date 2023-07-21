/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
			StringBundler.concat(
				"update Role_ set subtype = 'com.liferay.osb.testray' where ",
				"name in (", _getRoleNamesString(),
				StringPool.CLOSE_PARENTHESIS));
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