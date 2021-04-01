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

import com.liferay.osb.asah.common.postgresql.PostgreSQLDataSource;
import com.liferay.osb.asah.common.postgresql.converter.JSONObjectToPGobjectConverter;
import com.liferay.osb.asah.common.postgresql.converter.PGobjectToJSONObjectConverter;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.AnsiDialect;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.PostgresDialect;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.TransactionManager;

/**
 * @author Inácio Nery
 */
@Configuration
@EnableJdbcRepositories(
	basePackages = "com.liferay.osb.asah.common.repository",
	namedQueriesLocation = "classpath*:com/liferay/osb/asah/common/repository/*-sql.xml"
)
public class JDBCConfiguration extends AbstractJdbcConfiguration {

	@Bean
	@ConditionalOnProperty(
		havingValue = "false", matchIfMissing = true,
		value = "osb.asah.postgresql.enabled"
	)
	public DataSource elasticsearchDataSource() {
		return new SimpleDriverDataSource();
	}

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

		JdbcTemplate jdbcTemplate =
			(JdbcTemplate)namedParameterJdbcOperations.getJdbcOperations();

		if (jdbcTemplate.getDataSource() instanceof SimpleDriverDataSource) {
			return AnsiDialect.INSTANCE;
		}

		if (jdbcTemplate.getDataSource() instanceof PostgreSQLDataSource) {
			return PostgresDialect.INSTANCE;
		}

		throw new IllegalArgumentException(
			String.format(
				"Invalid dialect for %s. Please provide a valid dialect.",
				jdbcTemplate));
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

	@Bean
	@ConditionalOnProperty(
		havingValue = "true", value = "osb.asah.postgresql.enabled"
	)
	public DataSource postgreSQLDataSource() {
		return new PostgreSQLDataSource(_hikaryMaximumPoolSize);
	}

	@Bean
	public TransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Value("${spring.datasource.hikari.maximum-pool-size:10}")
	private int _hikaryMaximumPoolSize;

}