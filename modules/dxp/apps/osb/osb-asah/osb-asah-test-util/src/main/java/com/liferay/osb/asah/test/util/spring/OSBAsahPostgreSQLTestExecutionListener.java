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

package com.liferay.osb.asah.test.util.spring;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.test.util.postgresql.PostgreSQLTables;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.Socket;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Objects;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class OSBAsahPostgreSQLTestExecutionListener
	extends AbstractTestExecutionListener {

	public OSBAsahPostgreSQLTestExecutionListener() {
		if (!_isPostgreSQLUp()) {
			throw new IllegalStateException(
				"Integration test infrastructure is not up. Please run " +
					"\"docker-compose -f docker-compose.integration-test.yml " +
						"up -d\" from the root project directory.");
		}
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws SQLException {
		PostgreSQLTables postgreSQLTables =
			AnnotatedElementUtils.getMergedAnnotation(
				testContext.getTestMethod(), PostgreSQLTables.class);

		try (Connection connection = _postgreSQLDataSource.getConnection()) {
			DatabaseMetaData databaseMetaData = connection.getMetaData();

			ResultSet resultSet = databaseMetaData.getTables(
				null, connection.getSchema(), null, new String[] {"TABLE"});

			while (resultSet.next()) {
				try (PreparedStatement preparedStatement =
						connection.prepareStatement(
							"TRUNCATE TABLE " +
								resultSet.getString("TABLE_NAME") +
									" CASCADE")) {

					preparedStatement.execute();
				}
			}

			if (StringUtils.isBlank(postgreSQLTables.dataSource())) {
				DatabasePopulatorUtils.execute(
					new ResourceDatabasePopulator(
						new ClassPathResource("data.sql")),
					_postgreSQLDataSource);
			}
		}
	}

	@Override
	public void beforeTestClass(TestContext testContext) {
		Class<?> clazz = testContext.getTestClass();

		PostgreSQLTables[] postgreSQLTables = clazz.getAnnotationsByType(
			PostgreSQLTables.class);

		if (postgreSQLTables.length == 0) {
			return;
		}
		else if (postgreSQLTables.length > 1) {
			throw new IllegalArgumentException(
				"Only 1 PostgreSQLTables annotation allowed");
		}

		_prepareTables(clazz, postgreSQLTables[0]);
	}

	@Override
	public void beforeTestMethod(TestContext testContext) {
		PostgreSQLTables postgreSQLTables =
			AnnotatedElementUtils.getMergedAnnotation(
				testContext.getTestMethod(), PostgreSQLTables.class);

		if (postgreSQLTables != null) {
			_prepareTables(testContext.getTestClass(), postgreSQLTables);
		}
	}

	private String _getResourcePath(PostgreSQLTables postgreSQLTables) {
		if (StringUtils.startsWith(postgreSQLTables.resourcePath(), "/")) {
			return postgreSQLTables.resourcePath();
		}

		return "dependencies/" + postgreSQLTables.resourcePath();
	}

	private boolean _isPostgreSQLUp() {
		return _pingHost(ServiceConstants.POSTGRESQL_SERVER_IP, 5432, 3000);
	}

	private boolean _pingHost(String hostname, int port, int timeout) {
		try (Socket socket = new Socket()) {
			socket.connect(new InetSocketAddress(hostname, port), timeout);

			return true;
		}
		catch (IOException ioe) {
			return false;
		}
	}

	private void _prepareTables(
		Class<?> clazz, PostgreSQLTables postgreSQLTables) {

		if (!Objects.equals(postgreSQLTables.resourcePath(), "")) {
			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource(
						_getResourcePath(postgreSQLTables), clazz)),
				_resolveDataSource(postgreSQLTables.dataSource()));
		}
	}

	private DataSource _resolveDataSource(String dataSource) {
		if (Objects.equals(dataSource, "trinoDataSource")) {
			return _trinoDataSource;
		}

		return _postgreSQLDataSource;
	}

	@Autowired
	private DataSource _postgreSQLDataSource;

	@Autowired
	@Qualifier("trinoDataSource")
	private DataSource _trinoDataSource;

}