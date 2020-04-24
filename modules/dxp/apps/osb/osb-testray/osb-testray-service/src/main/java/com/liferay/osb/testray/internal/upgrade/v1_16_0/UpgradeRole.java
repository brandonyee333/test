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

package com.liferay.osb.testray.internal.upgrade.v1_16_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author William Newbury
 */
public class UpgradeRole extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!_hasRoles(_TESTRAY_ROLE_NAMES_TO_DELETE)) {
			return;
		}

		runSQL(
			"delete from Role_ where name in (" +
				_getRoleNamesString(_TESTRAY_ROLE_NAMES_TO_DELETE) +
					StringPool.CLOSE_PARENTHESIS);

		if (!_hasRoles(_TESTRAY_ROLE_NAMES_TO_UPDATE)) {
			return;
		}

		runSQL(
			"update Role_ set subtype=\"com.liferay.osb.testray\" where name " +
				"in (" + _getRoleNamesString(_TESTRAY_ROLE_NAMES_TO_UPDATE) +
					StringPool.CLOSE_PARENTHESIS);
	}

	private String _getRoleNamesString(String[] testrayRoleNames) {
		StringBundler sb = new StringBundler(testrayRoleNames.length * 4);

		for (String testrayRoleName : testrayRoleNames) {
			sb.append(StringPool.APOSTROPHE);
			sb.append(testrayRoleName);
			sb.append(StringPool.APOSTROPHE);
			sb.append(StringPool.COMMA);
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	private boolean _hasRoles(String[] testrayRoleNames) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select * from Role_ where name in (" +
					_getRoleNamesString(testrayRoleNames) +
						StringPool.CLOSE_PARENTHESIS);

			rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}

			return false;
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final String[] _TESTRAY_ROLE_NAMES_TO_DELETE = {"Test Lead"};

	private static final String[] _TESTRAY_ROLE_NAMES_TO_UPDATE = {
		"Testray Analyst", "Testray Lead"
	};

}