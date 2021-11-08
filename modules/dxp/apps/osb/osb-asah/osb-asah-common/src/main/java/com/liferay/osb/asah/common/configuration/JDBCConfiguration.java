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

package com.liferay.osb.asah.common.configuration;

import com.liferay.osb.asah.common.constants.CredentialConstants;
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.postgresql.PostgreSQLDataSource;
import com.liferay.osb.asah.common.postgresql.converter.JSONObjectToPGobjectConverter;
import com.liferay.osb.asah.common.postgresql.converter.PGobjectToJSONObjectConverter;

import com.zaxxer.hikari.HikariDataSource;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

import org.postgresql.ds.PGSimpleDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.PostgresDialect;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.util.InMemoryResource;
import org.springframework.transaction.TransactionManager;

/**
 * @author Inácio Nery
 */
@Configuration
@EnableJdbcRepositories(
	basePackages = "com.liferay.osb.asah.common.repository",
	excludeFilters = @ComponentScan.Filter(Primary.class),
	namedQueriesLocation = "classpath*:com/liferay/osb/asah/common/repository/*-sql.xml"
)
public class JDBCConfiguration extends AbstractJdbcConfiguration {

	@Bean
	@Override
	public JdbcCustomConversions jdbcCustomConversions() {
		return new JdbcCustomConversions(
			Arrays.asList(
				new JSONObjectToPGobjectConverter(),
				new PGobjectToJSONObjectConverter()));
	}

	@Bean
	@Override
	public Dialect jdbcDialect(
		NamedParameterJdbcOperations namedParameterJdbcOperations) {

		return PostgresDialect.INSTANCE;
	}

	@Bean
	public NamedParameterJdbcOperations namedParameterJdbcOperations(
		DataSource dataSource) {

		return new NamedParameterJdbcTemplate(new JdbcTemplate(dataSource));
	}

	@Bean
	public NamingStrategy namingStrategy() {
		return new NamingStrategy() {

			@Override
			public String getColumnName(
				RelationalPersistentProperty relationalPersistentProperty) {

				String columnName = relationalPersistentProperty.getName();

				columnName = columnName.replace("_", "");

				return columnName.toLowerCase();
			}

			@Override
			public String getTableName(Class<?> clazz) {
				String tableName = clazz.getSimpleName();

				tableName = tableName.replace("_", "");

				return tableName.toLowerCase();
			}

		};
	}

	@Bean("postgreSQLDataSource")
	@Primary
	@Profile({"dev", "prod"})
	public DataSource postgreSQLDataSource() {
		return new PostgreSQLDataSource(
			_hikariConnectionTimeout, _hikariIdleTimeout,
			_hikariLeakDetectionThreshold, _hikariMaximumPoolSize,
			_hikariMaxLifetime, _hikariMinimumIdleSize);
	}

	@Bean("postgreSQLDataSource")
	@Primary
	@Profile("test")
	public DataSource testPostgreSQLDataSource() {
		PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();

		pgSimpleDataSource.setServerName("localhost");
		pgSimpleDataSource.setPortNumber(5432);
		pgSimpleDataSource.setDatabaseName(CredentialConstants.POSTGRESQL_DB);
		pgSimpleDataSource.setUser(CredentialConstants.POSTGRESQL_USER);
		pgSimpleDataSource.setPassword(CredentialConstants.POSTGRESQL_PASSWORD);
		pgSimpleDataSource.setCurrentSchema("test");

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new InMemoryResource(
					"SET TIME ZONE 'UTC'; CREATE SCHEMA IF NOT EXISTS test")),
			pgSimpleDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("functions.sql")),
			pgSimpleDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(new ClassPathResource("tables.sql")),
			pgSimpleDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				new ClassPathResource("sequences.sql")),
			pgSimpleDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(
				true, true, null, new ClassPathResource("constraints.sql")),
			pgSimpleDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(new ClassPathResource("indexes.sql")),
			pgSimpleDataSource);

		DatabasePopulatorUtils.execute(
			new ResourceDatabasePopulator(new ClassPathResource("data.sql")),
			pgSimpleDataSource);

		return pgSimpleDataSource;
	}

	@Bean
	public TransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("trinoDataSource")
	@ConditionalOnProperty(
		havingValue = "true", value = "osb.asah.trino.enabled"
	)
	public DataSource trinoDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();

		hikariDataSource.setConnectionTimeout(TimeUnit.SECONDS.toMillis(30));
		hikariDataSource.setIdleTimeout(TimeUnit.SECONDS.toMillis(60));
		hikariDataSource.setJdbcUrl(_buildTrinoJDBCURL());
		hikariDataSource.setLeakDetectionThreshold(
			TimeUnit.SECONDS.toMillis(20));
		hikariDataSource.setMaximumPoolSize(_hikariMaximumPoolSize);
		hikariDataSource.setMaxLifetime(TimeUnit.SECONDS.toMillis(120));
		hikariDataSource.setUsername(CredentialConstants.TRINO_USER);

		return hikariDataSource;
	}

	private String _buildTrinoJDBCURL() {
		StringBuilder sb = new StringBuilder("jdbc:trino://");

		String[] transportAddressParts = StringUtils.split(
			ServiceConstants.TRINO_SERVER_IP, ':');

		sb.append(transportAddressParts[0]);

		sb.append(":");

		int port = 8080;

		if (transportAddressParts.length == 2) {
			port = Integer.parseInt(transportAddressParts[1]);
		}

		sb.append(port);

		return sb.toString();
	}

	@Value("${spring.datasource.hikari.connection-timeout:240}")
	private int _hikariConnectionTimeout;

	@Value("${spring.datasource.hikari.idle-timeout:60}")
	private int _hikariIdleTimeout;

	@Value("${spring.datasource.hikari.leak-detection-threshold:60}")
	private int _hikariLeakDetectionThreshold;

	@Value("${spring.datasource.hikari.maximum-pool-size:10}")
	private int _hikariMaximumPoolSize;

	@Value("${spring.datasource.hikari.max-lifetime:360}")
	private int _hikariMaxLifetime;

	@Value("${spring.datasource.hikari.minimum-idle-size:1}")
	private int _hikariMinimumIdleSize;

}