/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.sybase.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.InsertSQLBuilder;
import com.liferay.portal.tools.data.partitioning.sql.builder.sybase.exporter.serializer.SybaseFieldSerializer;

/**
 * @author Manuel de la Peña
 */
public class SybaseInsertSQLBuilder extends InsertSQLBuilder {

	public SybaseInsertSQLBuilder() {
		super(new SybaseFieldSerializer());
	}

	@Override
	public String buildInsert(String tableName, String[] fields) {
		String buildInsert = super.buildInsert(tableName, fields);

		int index = buildInsert.lastIndexOf(";\n");

		return buildInsert.substring(0, index) + "\nGO\n";
	}

}