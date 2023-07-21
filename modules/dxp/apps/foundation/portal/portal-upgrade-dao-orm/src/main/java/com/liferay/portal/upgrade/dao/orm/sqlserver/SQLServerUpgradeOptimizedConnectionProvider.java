/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.dao.orm.sqlserver;

import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.upgrade.dao.orm.BaseUpgradeOptimizedConnectionProvider;

/**
 * @author Miguel Pastor
 */
public class SQLServerUpgradeOptimizedConnectionProvider
	extends BaseUpgradeOptimizedConnectionProvider {

	@Override
	public DBType getDBType() {
		return DBType.SQLSERVER;
	}

}