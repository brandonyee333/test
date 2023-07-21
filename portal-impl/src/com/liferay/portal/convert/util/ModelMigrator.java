/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.util;

import com.liferay.portal.kernel.model.BaseModel;

import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;

/**
 * @author Cristina González
 * @author Miguel Pastor
 */
public interface ModelMigrator {

	public void migrate(
			DataSource dataSource, List<Class<? extends BaseModel<?>>> models)
		throws IOException, SQLException;

}