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

package com.liferay.osb.loop.web.internal.configuration;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.configuration.Filter;

/**
 * @author Timothy Bell
 */
public class LoopWebConfigurationUtil {

	public static String get(String key) {
		return _configuration.get(key);
	}

	public static String get(String key, Filter filter) {
		return _configuration.get(key, filter);
	}

	public static String[] getArray(String key) {
		return _configuration.getArray(key);
	}

	public static String[] getArray(String key, Filter filter) {
		return _configuration.getArray(key, filter);
	}

	private static final Configuration _configuration =
		ConfigurationFactoryUtil.getConfiguration(
			LoopWebConfigurationUtil.class.getClassLoader(), "portlet");

}