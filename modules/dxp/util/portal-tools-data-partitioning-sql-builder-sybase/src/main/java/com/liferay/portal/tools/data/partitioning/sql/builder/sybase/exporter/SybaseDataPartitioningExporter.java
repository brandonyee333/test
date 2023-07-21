/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.data.partitioning.sql.builder.sybase.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.BaseDataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;

/**
 * @author Manuel de la Peña
 */
public class SybaseDataPartitioningExporter
	extends BaseDataPartitioningExporter {

	public SybaseDataPartitioningExporter() {
		super(new SybaseInsertSQLBuilder());
	}

	@Override
	public String getControlTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder(10);

		sb.append("select so1.");
		sb.append(getTableNameFieldName());
		sb.append(" from sysobjects so1 where so1.type = 'U' and so1.");
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
		sb.append(" from syscolumns sc2, sysobjects so2 where sc2.id = ");
		sb.append("so2.id and sc2.name = 'companyId' group by so2.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getTableNameFieldName() {
		return "name";
	}

}