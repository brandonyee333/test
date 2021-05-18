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

import javax.sql.DataSource;

import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Configuration
public class JooqConfiguration {

	@Bean
	public ConnectionProvider defaultConnectionProvider(
		DataSource dataSource) {

		return new DataSourceConnectionProvider(
			new TransactionAwareDataSourceProxy(dataSource));
	}

	@Bean
	public org.jooq.Configuration defaultConfiguration(
		ConnectionProvider connectionProvider) {

		DefaultConfiguration defaultConfiguration = new DefaultConfiguration();

		defaultConfiguration.set(connectionProvider);

		return defaultConfiguration;
	}

	@Bean
	public DSLContext defaultDSLContext(org.jooq.Configuration configuration) {
		return new DefaultDSLContext(configuration);
	}

}