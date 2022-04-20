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

import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.postgresql.PostgreSQLDataSource;
import com.liferay.osb.asah.common.postgresql.PostgreSQLSchemaManager;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

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
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.util.InMemoryResource;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class PostgreSQLSchemaManagerImpl implements PostgreSQLSchemaManager {

	@Override
	public void createGlobalSchema() {
		if (!(_dataSource instanceof PostgreSQLDataSource)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"createGlobalSchema has no effect on data source " +
						_dataSource);
			}

			return;
		}

		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new InMemoryResource(
						"CREATE SCHEMA IF NOT EXISTS " +
							ProjectIdThreadLocal.getProjectId())),
				_dataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("tables-global.sql")),
				_dataSource);

			if (_log.isInfoEnabled()) {
				_log.info("Global schema created successfully");
			}
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	@Override
	public void createSchema(Project project) {
		if (!(_dataSource instanceof PostgreSQLDataSource)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"createSchema has no effect on data source " + _dataSource);
			}

			return;
		}

		try {
			ProjectIdThreadLocal.setProject(project);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new InMemoryResource(
						"CREATE SCHEMA IF NOT EXISTS " +
							ProjectIdThreadLocal.getProjectId())),
				_dataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("functions.sql")),
				_dataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("tables-current.sql")),
				_dataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					true, true, null,
					new ClassPathResource("constraints-current.sql")),
				_dataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("indexes-current.sql")),
				_dataSource);

			if (_environment.acceptsProfiles(Profiles.of("dev"))) {
				ResourceDatabasePopulator resourceDatabasePopulator =
					new ResourceDatabasePopulator(
						new ClassPathResource("bq-functions.sql"));

				resourceDatabasePopulator.setSeparator("COMMIT;");

				DatabasePopulatorUtils.execute(
					resourceDatabasePopulator, _dataSource);

				DatabasePopulatorUtils.execute(
					new ResourceDatabasePopulator(
						new ClassPathResource("bq-tables.sql"),
						new ClassPathResource("bq-views.sql")),
					_dataSource);
			}

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("data.sql")),
				_dataSource);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Schema for project %s created successfully",
						project.getId()));
			}
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	@Override
	public boolean existsTable(Project project, String tableName) {
		try {
			ProjectIdThreadLocal.setProject(project);

			try (Connection connection = _dataSource.getConnection()) {
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
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	private static final Log _log = LogFactory.getLog(
		PostgreSQLSchemaManagerImpl.class);

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _dataSource;

	@Autowired
	private Environment _environment;

}