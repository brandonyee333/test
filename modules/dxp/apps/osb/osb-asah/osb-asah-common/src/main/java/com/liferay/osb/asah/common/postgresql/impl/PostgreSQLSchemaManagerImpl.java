/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.postgresql.impl;

import com.liferay.osb.asah.common.postgresql.PostgreSQLDataSource;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class PostgreSQLSchemaManagerImpl implements PostgreSQLSchemaManager {

	@Override
	public void createGlobalSchema() {
		if (!(_postgreSQLDataSource instanceof PostgreSQLDataSource)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"createGlobalSchema has no effect on data source " +
						_postgreSQLDataSource);
			}

			return;
		}

		PostgreSQLDataSource dataSource =
			(PostgreSQLDataSource)_postgreSQLDataSource;

		if (!dataSource.isGlobal()) {
			throw new IllegalStateException("Unable to create global schema");
		}

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("tables-global.sql")),
			_postgreSQLDataSource);
	}

	@Override
	public void createSchema() {
		if (!(_postgreSQLDataSource instanceof PostgreSQLDataSource)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"createSchema has no effect on data source " +
						_postgreSQLDataSource);
			}

			return;
		}

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("functions.sql")),
			_postgreSQLDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("tables-current.sql")),
			_postgreSQLDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				true, true, null,
				new ClassPathResource("constraints-current.sql")),
			_postgreSQLDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("indexes-current.sql")),
			_postgreSQLDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(new ClassPathResource("data.sql")),
			_postgreSQLDataSource);
	}

	@Override
	public boolean existsTable(String tableName) {
		try (Connection connection = _postgreSQLDataSource.getConnection()) {
			DatabaseMetaData databaseMetaData = connection.getMetaData();

			ResultSet resultSet = databaseMetaData.getTables(
				null, null, StringUtils.lowerCase(tableName),
				new String[] {"TABLE"});

			return resultSet.next();
		}
		catch (SQLException sqlException) {
			return false;
		}
	}

	private static final Log _log = LogFactory.getLog(
		PostgreSQLSchemaManagerImpl.class);

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _postgreSQLDataSource;

}