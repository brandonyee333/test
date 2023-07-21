/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.util;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.Tuple;

import java.io.IOException;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * @author     Cristina González
 * @author     Miguel Pastor
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class ModelMigratorImpl implements ModelMigrator {

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void migrate(
			DataSource dataSource, List<Class<? extends BaseModel<?>>> models)
		throws IOException, SQLException {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected Map<String, Tuple> getModelTableDetails(Class<?> implClass) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected Tuple getTableDetails(
		Class<?> implClass, Field tableField, String tableFieldVar) {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected void migrateModel(
			Map<String, Tuple> modelTableDetails, DB db, Connection connection)
		throws IOException {

		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected void migrateTable(
		DB db, Connection connection, String tableName, Object[][] columns,
		String sqlCreate) {

		throw new UnsupportedOperationException();
	}

}