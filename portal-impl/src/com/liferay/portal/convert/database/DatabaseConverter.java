/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.database;

import javax.sql.DataSource;

/**
 * @author Cristina González
 */
public interface DatabaseConverter {

	public void convert(DataSource dataSource) throws Exception;

}