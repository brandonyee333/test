/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.sqlserver.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.BaseDataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.InsertSQLBuilder;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.sqlserver.exporter.serializer.SQLServerFieldSerializer;

/**
 * @author Manuel de la Peña
 */
public class SQLServerDataPartitioningExporter
	extends BaseDataPartitioningExporter {

	public SQLServerDataPartitioningExporter() {
		super(new InsertSQLBuilder(new SQLServerFieldSerializer()));
	}

	@Override
	public String getControlTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder(10);

		sb.append("select so1.");
		sb.append(getTableNameFieldName());
		sb.append(" from sysobjects so1 where type = 'U' and so1.");
		sb.append(getTableNameFieldName());
		sb.append(" not in (");
		sb.append(getPartitionedTableNamesSQL(exportContext));
		sb.append(") group by so1.");
		sb.append(getTableNameFieldName());
		sb.append(" order by so1.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getPartitionedTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder(5);

		sb.append("select so2.");
		sb.append(getTableNameFieldName());
		sb.append(" from syscolumns sc2, sysobjects so2 where sc2.id =  ");
		sb.append("so2.id and sc2.name = 'companyId' group by so2.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getTableNameFieldName() {
		return "name";
	}

}