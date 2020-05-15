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

package com.liferay.osb.asah.backend.configuration;

import io.prometheus.client.filter.MetricsFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author André Miranda
 */
@Configuration
@Profile("!test")
public class MetricsConfiguration {

	@Bean
	public MetricsFilter faroBackendMetricsFilter() {
		return new MetricsFilter("backend_request_seconds", null, null, null);
	}

}