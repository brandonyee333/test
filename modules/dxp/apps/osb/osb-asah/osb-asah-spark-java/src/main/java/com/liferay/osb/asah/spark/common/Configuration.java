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

package com.liferay.osb.asah.spark.common;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

/**
 * @author Marcellus Tavares
 */
public class Configuration {

	public Configuration(String path) {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(path);

		try {
			_properties.load(inputStream);
		}
		catch (IOException ioException) {
			throw new RuntimeException(
				"Unable to load properties file", ioException);
		}
	}

	public String get(String propertyKey, String defaultPropertyValue) {
		return _properties.getProperty(propertyKey, defaultPropertyValue);
	}

	private final Properties _properties = new Properties();

}