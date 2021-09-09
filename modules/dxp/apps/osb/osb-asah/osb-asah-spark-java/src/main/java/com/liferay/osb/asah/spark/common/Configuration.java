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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Marcellus Tavares
 */
public class Configuration {

	public Configuration(String[] args, String path) {
		_args = _toMap(args);

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

	public String get(String propertyKey) {
		return get(propertyKey, null);
	}

	public String get(String propertyKey, String defaultPropertyValue) {
		String argumentValue = _args.get(propertyKey);

		if (argumentValue != null) {
			return argumentValue;
		}

		return _properties.getProperty(propertyKey, defaultPropertyValue);
	}

	private Map<String, String> _toMap(String[] args) {
		if ((args.length % 2) != 0) {
			throw new IllegalArgumentException(
				"Arguments length is not an even number");
		}

		Map<String, String> map = new HashMap<>();

		for (int i = 0; i < (args.length - 1); i += 2) {
			map.put(args[i], args[i + 1]);
		}

		return map;
	}

	private final Map<String, String> _args;
	private final Properties _properties = new Properties();

}