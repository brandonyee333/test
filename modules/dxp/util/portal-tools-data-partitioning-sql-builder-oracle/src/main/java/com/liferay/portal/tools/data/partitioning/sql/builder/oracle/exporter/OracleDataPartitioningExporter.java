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

package com.liferay.portal.tools.data.partitioning.sql.builder.oracle.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.BaseDataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.InsertSQLBuilder;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.oracle.exporter.serializer.OracleFieldSerializer;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Manuel de la Peña
 */
public class OracleDataPartitioningExporter
	extends BaseDataPartitioningExporter {

	public OracleDataPartitioningExporter() {
		super(new InsertSQLBuilder(new OracleFieldSerializer()));

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
		String oracleHome = _environment.get("ORACLE_HOME");

		if ((oracleHome == null) || oracleHome.isEmpty()) {
			throw new IllegalStateException(
				"The environment variable ORACLE_HOME is not set");
		}

		String tnsAdmin = _environment.get("TNS_ADMIN");

		if ((tnsAdmin == null) || tnsAdmin.isEmpty()) {
			throw new IllegalStateException(
				"The environment variable TNS_ADMIN is not set");
		}

		_exportContext = exportContext;

		Properties properties = _exportContext.getProperties();

		String url = properties.getProperty("dataSource.url");

		Matcher matcher = _pattern.matcher(url);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("Oracle connection malformed");
		}

		String user = properties.getProperty("dataSource.user");
		String password = properties.getProperty("dataSource.password");

		String oracleSid = _environment.get("ORACLE_SID");

		if ((oracleSid == null) || oracleSid.isEmpty()) {
			oracleSid = matcher.group(4);
		}

		_credentials = user + "/" + password + "@" + oracleSid;

		super.export(exportContext);
	}

	@Override
	public String getControlTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder();

		sb.append("select ");
		sb.append(getTableNameFieldName());
		sb.append(" from user_tables where ");
		sb.append(getTableNameFieldName());
		sb.append(" not in (");
		sb.append(getPartitionedTableNamesSQL(exportContext));
		sb.append(") group by ");
		sb.append(getTableNameFieldName());
		sb.append(" order by ");
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

		sb.append("select ");
		sb.append(getTableNameFieldName());
		sb.append(" from cols where column_name = 'COMPANYID' group by ");
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

		try (Connection con = dataSource.getConnection();
			PreparedStatement ps = buildPreparedStatement(con, sql, companyId);
			ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				StringBuilder sb = new StringBuilder();

				sb.append(_scriptComment);
				sb.append(" Commands to export/import ");
				sb.append(tableName);
				sb.append(" table:\n");
				sb.append(_getExportCommand(companyId, tableName));
				sb.append("\n");
				sb.append(_getImportCommand(tableName));
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

	protected void setEnvironment(Map<String, String> environment) {
		_environment = environment;
	}

	private String _getExportCommand(long companyId, String tableName) {
		StringBuilder sb = new StringBuilder();

		sb.append("expdp ");
		sb.append(_credentials);
		sb.append(" tables=");
		sb.append(tableName);
		sb.append(" directory=DATA_PUMP_DIR ");
		sb.append("dumpfile=");
		sb.append(tableName);
		sb.append(".dmp logfile=");
		sb.append("expdp_");
		sb.append(tableName);
		sb.append(".log");

		if (companyId > 0) {
			sb.append(" query=");
			sb.append(_exportContext.getSchemaName());
			sb.append(".");
			sb.append(tableName);
			sb.append(":'\" where companyId = ");
			sb.append(companyId);
			sb.append("\"'");
		}

		return sb.toString();
	}

	private String _getImportCommand(String tableName) {
		StringBuilder sb = new StringBuilder();

		sb.append("impdp ");
		sb.append(_credentials);
		sb.append(" directory=DATA_PUMP_DIR dumpfile=");
		sb.append(tableName);
		sb.append(".dmp full=yes logfile=");
		sb.append("impdp_");
		sb.append(tableName);
		sb.append(".log");

		return sb.toString();
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		OracleDataPartitioningExporter.class);

	private static final Pattern _pattern = Pattern.compile(
		"(.*):@(\\S+):(\\d+):(.*)");

	private String _credentials;
	private Map<String, String> _environment = System.getenv();
	private ExportContext _exportContext;
	private final String _fileExtension;
	private final String _scriptComment;

}