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

package com.liferay.portal.tools.data.partitioning.sql.builder.db2.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.BaseDataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Manuel de la Peña
 */
public class DB2DataPartitioningExporter extends BaseDataPartitioningExporter {

	public DB2DataPartitioningExporter() {
		String osName = System.getProperty("os.name");

		if (osName.contains("windows")) {
			_fileExtension = ".bat";
			_scriptComment = "::";
		}
		else {
			_fileExtension = ".sh";
			_scriptComment = "#";
		}
	}

	@Override
	public void export(ExportContext exportContext) {
		_exportContext = exportContext;

		_outputDirName = _exportContext.getOutputDirName();

		if (!_outputDirName.endsWith(File.separator)) {
			_outputDirName += File.separator;
		}

		super.export(exportContext);
	}

	@Override
	public String getControlTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder();

		sb.append("select t1.");
		sb.append(getTableNameFieldName());
		sb.append(" from sysibm.tables t1 where t1.table_catalog = '");
		sb.append(exportContext.getCatalogName());
		sb.append("' and t1.table_schema = '");
		sb.append(exportContext.getSchemaName());
		sb.append("' and t1.");
		sb.append(getTableNameFieldName());
		sb.append(" not in (");
		sb.append(getPartitionedTableNamesSQL(exportContext));
		sb.append(") group by t1.");
		sb.append(getTableNameFieldName());
		sb.append(" order by t1.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getOutputFileExtension() {
		return _fileExtension;
	}

	@Override
	public String getPartitionedTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder();

		sb.append("select t2.");
		sb.append(getTableNameFieldName());
		sb.append(" from syscat.columns c2, sysibm.tables t2 where ");
		sb.append("c2.tabschema=t2.table_schema and t2.");
		sb.append(getTableNameFieldName());
		sb.append("=c2.tabname and t2.table_catalog = '");
		sb.append(exportContext.getCatalogName());
		sb.append("' and c2.colname = 'COMPANYID' group by t2.");
		sb.append(getTableNameFieldName());
		sb.append(" order by t2.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getTableNameFieldName() {
		return "table_name";
	}

	@Override
	public void write(
		long companyId, String tableName, OutputStream outputStream) {

		DataSource dataSource = getDataSource();

		String sql = "select * from " + tableName;

		if (companyId > 0) {
			sql += " where companyId = ?";
		}

		try (Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = buildPreparedStatement(
				connection, sql, companyId);
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				StringBuilder sb = new StringBuilder(8);

				sb.append(_scriptComment);
				sb.append(" Commands to export/import ");
				sb.append(tableName);
				sb.append(" table:\n");

				if (_hasClobColumn(resultSet)) {
					sb.append(_getExportBlobCommand(companyId, tableName));
					sb.append("\n");
					sb.append(_getImportBlobCommand(tableName));
				}
				else {
					sb.append(_getExportCommand(companyId, tableName));
					sb.append("\n");
					sb.append(_getImportCommand(tableName));
				}

				sb.append("\n\n");

				String commands = sb.toString();

				outputStream.write(commands.getBytes());
			}
		}
		catch (IOException | SQLException e) {
			_logger.error(
				"Unable to generate export/import statements for table " +
					tableName,
				e);
		}
	}

	private String _getExportBlobCommand(long companyId, String tableName) {
		StringBuilder sb = new StringBuilder();

		sb.append("export to ");
		sb.append(_outputDirName);
		sb.append(tableName);
		sb.append(".del of del lobs to ");
		sb.append(_outputDirName);
		sb.append(" lobfile lobfile1, lobfile2 ");
		sb.append("modified by lobsinfile ");
		sb.append("select * from ");
		sb.append(tableName);

		if (companyId > 0) {
			sb.append(" where companyId = ");
			sb.append(companyId);
		}

		return sb.toString();
	}

	private String _getExportCommand(long companyId, String tableName) {
		StringBuilder sb = new StringBuilder();

		sb.append("export to ");
		sb.append(_outputDirName);
		sb.append(tableName);
		sb.append(".ifx of ifx messages ");
		sb.append(_outputDirName);
		sb.append(tableName);
		sb.append(".txt ");
		sb.append("select * from ");
		sb.append(tableName);

		if (companyId > 0) {
			sb.append(" where companyId = ");
			sb.append(companyId);
		}

		return sb.toString();
	}

	private String _getImportBlobCommand(String tableName) {
		StringBuilder sb = new StringBuilder();

		sb.append("import from ");
		sb.append(_outputDirName);
		sb.append(tableName);
		sb.append(".del of del lobs from ");
		sb.append(_outputDirName);
		sb.append(" modified by lobsinfile ");
		sb.append("insert into ");
		sb.append(tableName);

		return sb.toString();
	}

	private String _getImportCommand(String tableName) {
		StringBuilder sb = new StringBuilder();

		sb.append("import from ");
		sb.append(_outputDirName);
		sb.append(tableName);
		sb.append(".ixf of ixf messages ");
		sb.append(_outputDirName);
		sb.append(tableName);
		sb.append(".txt ");
		sb.append("insert into ");
		sb.append(tableName);

		return sb.toString();
	}

	private boolean _hasClobColumn(ResultSet resultSet) throws SQLException {
		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

		int columnCount = resultSetMetaData.getColumnCount();

		for (int i = 0; i < columnCount; i++) {
			Object object = resultSet.getObject(i + 1);

			if (object instanceof Clob) {
				return true;
			}
		}

		return false;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		DB2DataPartitioningExporter.class);

	private ExportContext _exportContext;
	private final String _fileExtension;
	private String _outputDirName;
	private final String _scriptComment;

}