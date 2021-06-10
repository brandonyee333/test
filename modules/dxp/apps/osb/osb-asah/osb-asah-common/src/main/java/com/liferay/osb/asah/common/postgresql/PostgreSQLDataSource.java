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

package com.liferay.osb.asah.common.postgresql;

import com.liferay.osb.asah.common.constants.CredentialConstants;
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import com.zaxxer.hikari.HikariDataSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Inácio Nery
 */
public class PostgreSQLDataSource extends AbstractRoutingDataSource {

	public PostgreSQLDataSource(int hikariMaxPoolSize) {
		_hikariMaximumPoolSize = hikariMaxPoolSize;
	}

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return ProjectIdThreadLocal.getProjectId();
	}

	@Override
	protected DataSource determineTargetDataSource() {
		Object lookupKey = determineCurrentLookupKey();

		DataSource dataSource = _resolvedDataSources.get(lookupKey);

		if (dataSource == null) {
			dataSource = resolveSpecifiedDataSource(lookupKey);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"DataSource %s created for %s", dataSource, lookupKey));
			}

			_resolvedDataSources.put(lookupKey, dataSource);
		}

		return dataSource;
	}

	@Override
	protected DataSource resolveSpecifiedDataSource(Object dataSource)
		throws IllegalArgumentException {

		if (dataSource == null) {
			dataSource = "public";
		}

		if (dataSource instanceof DataSource) {
			return (DataSource)dataSource;
		}

		HikariDataSource hikariDataSource = new HikariDataSource();

		hikariDataSource.setConnectionInitSql(
			"SET TIME ZONE 'UTC'; CREATE SCHEMA IF NOT EXISTS " + dataSource);
		hikariDataSource.setConnectionTimeout(TimeUnit.SECONDS.toMillis(30));
		hikariDataSource.setIdleTimeout(TimeUnit.SECONDS.toMillis(60));
		hikariDataSource.setJdbcUrl(_buildJdbcUrl(dataSource));
		hikariDataSource.setLeakDetectionThreshold(
			TimeUnit.SECONDS.toMillis(20));
		hikariDataSource.setMaximumPoolSize(_hikariMaximumPoolSize);
		hikariDataSource.setMaxLifetime(TimeUnit.SECONDS.toMillis(120));
		hikariDataSource.setPassword(CredentialConstants.POSTGRESQL_PASSWORD);
		hikariDataSource.setUsername(CredentialConstants.POSTGRESQL_USER);

		if (Objects.equals("global", dataSource)) {
			hikariDataSource.setMaximumPoolSize(2);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("global_tables.sql")),
				hikariDataSource);
		}
		else {
			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("functions-3.0.0.sql")),
				hikariDataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("tables-3.0.0.sql")),
				hikariDataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					true, true, null,
					new ClassPathResource("constraints-3.0.0.sql")),
				hikariDataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("indexes-3.0.0.sql")),
				hikariDataSource);

			DatabasePopulatorUtils.execute(
				new ResourceDatabasePopulator(
					new ClassPathResource("data-3.0.0.sql")),
				hikariDataSource);
		}

		return hikariDataSource;
	}

	private String _buildJdbcUrl(Object dataSource) {
		StringBuilder sb = new StringBuilder("jdbc:postgresql://");

		String[] transportAddressParts = StringUtils.split(
			ServiceConstants.POSTGRESQL_SERVER_IP, ':');

		sb.append(transportAddressParts[0]);

		sb.append(":");

		int port = 5432;

		if (transportAddressParts.length == 2) {
			port = Integer.parseInt(transportAddressParts[1]);
		}

		sb.append(port);
		sb.append("/");
		sb.append(CredentialConstants.POSTGRESQL_DB);
		sb.append("?currentSchema=");
		sb.append(dataSource);

		return sb.toString();
	}

	private static final Log _log = LogFactory.getLog(
		PostgreSQLDataSource.class);

	private final int _hikariMaximumPoolSize;
	private final Map<Object, DataSource> _resolvedDataSources =
		new HashMap<>();

}