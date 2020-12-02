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

package com.liferay.osb.asah.monolith.spring;

import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
import com.liferay.osb.asah.upgrade.UpgradeProcessRunner;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author André Miranda
 */
@ComponentScan(
	basePackages = "com.liferay.osb.asah",
	excludeFilters = {
		@ComponentScan.Filter(MonolithExclude.class),
		@ComponentScan.Filter(
			pattern = ".*SpringBootApplication", type = FilterType.REGEX
		)
	}
)
@EnableCaching
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@SpringBootApplication(
	exclude = {
		MetricExportAutoConfiguration.class, SecurityAutoConfiguration.class
	}
)
public class OSBAsahMonolithSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OSBAsahMonolithSpringBootApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer
			propertySourcesPlaceholderConfigurer()
		throws Exception {

		PropertySourcesPlaceholderConfigurer
			propertySourcesPlaceholderConfigurer =
				new PropertySourcesPlaceholderConfigurer();

		PathMatchingResourcePatternResolver
			pathMatchingResourcePatternResolver =
				new PathMatchingResourcePatternResolver();

		propertySourcesPlaceholderConfigurer.setLocations(
			pathMatchingResourcePatternResolver.getResources(
				"classpath*:/application.properties"));

		return propertySourcesPlaceholderConfigurer;
	}

	@PostConstruct
	private void _init() throws Exception {
		_upgradeProcessRunner.run();
	}

	@Autowired
	private UpgradeProcessRunner _upgradeProcessRunner;

}