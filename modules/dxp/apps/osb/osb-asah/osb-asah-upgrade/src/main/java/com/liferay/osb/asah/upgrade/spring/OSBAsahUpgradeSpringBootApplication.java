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

package com.liferay.osb.asah.upgrade.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.jdbc.DataSourceHealthContributorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Shinn Lok
 */
@ComponentScan(
	basePackages = {
		"com.liferay.osb.asah.common.bigquery",
		"com.liferay.osb.asah.common.configuration",
		"com.liferay.osb.asah.common.date.dog",
		"com.liferay.osb.asah.common.dog", "com.liferay.osb.asah.common.dxp",
		"com.liferay.osb.asah.common.elasticsearch",
		"com.liferay.osb.asah.common.faro.info",
		"com.liferay.osb.asah.common.http",
		"com.liferay.osb.asah.common.messaging",
		"com.liferay.osb.asah.common.postgresql",
		"com.liferay.osb.asah.common.repository",
		"com.liferay.osb.asah.common.security",
		"com.liferay.osb.asah.common.spring.cache",
		"com.liferay.osb.asah.common.spring.http",
		"com.liferay.osb.asah.common.wedeploy", "com.liferay.osb.asah.upgrade"
	}
)
@SpringBootApplication(
	exclude = {
		DataSourceHealthContributorAutoConfiguration.class,
		JooqAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class,
		MetricsAutoConfiguration.class,
		RedisRepositoriesAutoConfiguration.class,
		SecurityAutoConfiguration.class,
		UserDetailsServiceAutoConfiguration.class
	}
)
public class OSBAsahUpgradeSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OSBAsahUpgradeSpringBootApplication.class, args);
	}

}