/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.jdbc;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import javax.sql.DataSource;

/**
 * @author Brian Wing Shun Chan
 */
public class MappingSqlQueryFactoryUtil {

	public static <T> MappingSqlQuery<T> getMappingSqlQuery(
		DataSource dataSource, String sql, RowMapper<T> rowMapper,
		ParamSetter... paramSetters) {

		return getMappingSqlQueryFactory().getMappingSqlQuery(
			dataSource, sql, rowMapper, paramSetters);
	}

	public static MappingSqlQueryFactory getMappingSqlQueryFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			MappingSqlQueryFactoryUtil.class);

		return _mappingSqlUpdateFactory;
	}

	public void setMappingSqlQueryFactory(
		MappingSqlQueryFactory mappingSqlUpdateFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_mappingSqlUpdateFactory = mappingSqlUpdateFactory;
	}

	private static MappingSqlQueryFactory _mappingSqlUpdateFactory;

}