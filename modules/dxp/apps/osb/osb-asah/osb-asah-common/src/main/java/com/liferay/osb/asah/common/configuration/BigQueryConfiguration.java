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

import com.google.cloud.NoCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;

import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class BigQueryConfiguration {

	@Bean
	@ConditionalOnGoogleApplicationCredentials(matchIfMissing = true)
	@Profile("dev")
	public BigQuery devBigQuery() {
		BigQueryOptions.Builder builder = BigQueryOptions.newBuilder();

		BigQueryOptions bigQueryOptions = builder.setCredentials(
			NoCredentials.getInstance()
		).setHost(
			"http://bigqueryemulator:9050"
		).setProjectId(
			"osbasahdev"
		).build();

		return bigQueryOptions.getService();
	}

	@Bean
	@ConditionalOnGoogleApplicationCredentials
	@Primary
	public BigQuery prodBigQuery() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		return bigQueryOptions.getService();
	}

	@Bean
	@ConditionalOnGoogleApplicationCredentials(matchIfMissing = true)
	@Profile("test")
	public BigQuery testBigQuery() {
		BigQueryOptions.Builder builder = BigQueryOptions.newBuilder();

		BigQueryOptions bigQueryOptions = builder.setCredentials(
			NoCredentials.getInstance()
		).setHost(
			"http://localhost:9050"
		).setProjectId(
			"osbasah"
		).build();

		return bigQueryOptions.getService();
	}

}