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

package com.liferay.osb.asah.dataflow.emulator.spring;

import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * @author Marcellus Tavares
 */
@ComponentScan("com.liferay.osb.asah.dataflow.emulator")
@EnableJdbcRepositories(basePackages = "com.liferay.osb.asah.dataflow.emulator")
public class OSBAsahDataflowEmulatorSpringBootApplication
	extends OSBAsahSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(
			OSBAsahDataflowEmulatorSpringBootApplication.class, args);
	}

}