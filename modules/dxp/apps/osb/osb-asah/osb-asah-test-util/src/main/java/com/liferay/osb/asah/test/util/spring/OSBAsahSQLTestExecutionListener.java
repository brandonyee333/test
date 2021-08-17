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

import com.liferay.osb.asah.test.util.annotation.SQLResource;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * @author Leslie Wong
 */
public class OSBAsahSQLTestExecutionListener
	extends AbstractTestExecutionListener {

	@Override
	public void afterTestMethod(TestContext testContext) throws SQLException {
		if (!_isTestExecutionListenerEnabled()) {
			return;
		}

		if (_postgreSQLDataSource != null) {
			try (Connection connection =
					_postgreSQLDataSource.getConnection()) {

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

				DatabasePopulatorUtils.execute(
					new ResourceDatabasePopulator(
						new ClassPathResource("data.sql")),
					_postgreSQLDataSource);
			}
		}

		if (_trinoDataSource != null) {
			try (Connection connection = _trinoDataSource.getConnection()) {
				DatabaseMetaData databaseMetaData = connection.getMetaData();

				ResultSet resultSet = databaseMetaData.getTables(
					connection.getCatalog(), connection.getSchema(), null,
					new String[] {"TABLE"});

				while (resultSet.next()) {
					try (PreparedStatement preparedStatement =
							connection.prepareStatement(
								"DELETE FROM " +
									resultSet.getString("TABLE_NAME"))) {

						preparedStatement.execute();
					}
				}
			}
		}

		for (String cacheName : _cacheManager.getCacheNames()) {
			Cache cache = _cacheManager.getCache(cacheName);

			if (cache != null) {
				cache.invalidate();
			}
		}
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		if (!_isTestExecutionListenerEnabled()) {
			return;
		}

		Class<?> clazz = testContext.getTestClass();

		SQLResource[] sqlResources = clazz.getAnnotationsByType(
			SQLResource.class);

		if (sqlResources.length == 0) {
			return;
		}

		if (sqlResources.length > 1) {
			throw new IllegalArgumentException(
				"Only 1 SQLResource annotation allowed");
		}

		_prepareTables(clazz, sqlResources[0]);
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		if (!_isTestExecutionListenerEnabled()) {
			return;
		}

		SQLResource sqlResource = AnnotatedElementUtils.getMergedAnnotation(
			testContext.getTestMethod(), SQLResource.class);

		if (sqlResource != null) {
			_prepareTables(testContext.getTestClass(), sqlResource);
		}
	}

	private String _getResourcePath(SQLResource sqlResource) {
		if (StringUtils.startsWith(sqlResource.resourcePath(), "/")) {
			return sqlResource.resourcePath();
		}

		return "dependencies/" + sqlResource.resourcePath();
	}

	private boolean _isTestExecutionListenerEnabled() {
		if ((_postgreSQLDataSource != null) || (_trinoDataSource != null)) {
			return true;
		}

		return false;
	}

	private void _prepareTables(Class<?> clazz, SQLResource sqlResource)
		throws Exception {

		if (!Objects.equals(sqlResource.resourcePath(), "")) {
			ClassPathResource classPathResource = new ClassPathResource(
				_getResourcePath(sqlResource), clazz);

			DataSource dataSource = _resolveDataSource(
				sqlResource.dataSource());

			if (!classPathResource.isFile()) {
				DatabasePopulatorUtils.execute(
					new ResourceDatabasePopulator(classPathResource),
					dataSource);

				return;
			}

			File file = classPathResource.getFile();

			String replaceSQLVariables =
				TestExecutionListenerUtil.replaceSQLVariables(
					new String(
						Files.readAllBytes(file.toPath()),
						StandardCharsets.UTF_8));

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ByteArrayResource(
						replaceSQLVariables.getBytes(StandardCharsets.UTF_8))),
				dataSource);
		}
	}

	private DataSource _resolveDataSource(String dataSource) {
		if (Objects.equals(dataSource, "trinoDataSource")) {
			return _trinoDataSource;
		}

		return _postgreSQLDataSource;
	}

	@Autowired
	private CacheManager _cacheManager;

	@Autowired(required = false)
	@Qualifier("postgreSQLDataSource")
	private DataSource _postgreSQLDataSource;

	@Autowired(required = false)
	@Qualifier("trinoDataSource")
	private DataSource _trinoDataSource;

}