/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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