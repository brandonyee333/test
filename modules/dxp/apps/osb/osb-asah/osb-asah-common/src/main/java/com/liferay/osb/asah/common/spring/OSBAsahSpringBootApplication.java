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

package com.liferay.osb.asah.common.spring;

import com.liferay.osb.asah.common.upgrade.UpgradeCheck;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.MetricExportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Eddie Olson
 * @author Rachael Koestartyo
 */
@ComponentScan("com.liferay.osb.asah.common")
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@SpringBootApplication(
	exclude = {
		MetricExportAutoConfiguration.class, SecurityAutoConfiguration.class
	}
)
public class OSBAsahSpringBootApplication {

	@Autowired(required = false)
	private UpgradeCheck _upgradeCheck;

}