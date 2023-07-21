/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.database;

import com.liferay.portal.convert.util.ModelMigrator;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.List;

import javax.sql.DataSource;

/**
 * @author     Cristina González
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PortalDatabaseConverter implements DatabaseConverter {

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public void convert(DataSource dataSource) throws Exception {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setModelMigrator(ModelMigrator modelMigrator) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	protected List<Class<? extends BaseModel<?>>> getModelClassNames(
		String regex) {

		throw new UnsupportedOperationException();
	}

}