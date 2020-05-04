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

package com.liferay.osb.asah.backend.spring;

import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @author Vishal Reddy
 */
@ComponentScan("com.liferay.osb.asah.backend")
public class OSBAsahBackendSpringBootApplication
	extends OSBAsahSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OSBAsahBackendSpringBootApplication.class, args);
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
				"classpath:/application*.properties"));

		return propertySourcesPlaceholderConfigurer;
	}

}