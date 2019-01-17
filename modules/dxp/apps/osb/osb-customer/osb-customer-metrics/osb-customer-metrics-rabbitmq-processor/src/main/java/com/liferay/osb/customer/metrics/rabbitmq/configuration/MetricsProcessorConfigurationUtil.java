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

package com.liferay.osb.customer.metrics.rabbitmq.configuration;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;

/**
 * @author Kyle Bischof
 */
public class MetricsProcessorConfigurationUtil {

	public static String get(String key) {
		return _configuration.get(key);
	}

	public static String[] getArray(String key) {
		return _configuration.getArray(key);
	}

	private static final Configuration _configuration =
		ConfigurationFactoryUtil.getConfiguration(
			MetricsProcessorConfigurationUtil.class.getClassLoader(),
			"portlet");

}