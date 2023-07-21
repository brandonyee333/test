/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class PropsUtil {

	public static void addConfiguration(Configuration configuration) {
		_instance._addConfiguration(configuration);
	}

	public static String get(String key) {
		return _instance._get(key);
	}

	public static String[] getArray(String key) {
		return _instance._getArray(key);
	}

	public static void set(String key, String value) {
		_instance._set(key, value);
	}

	private PropsUtil() {
		try {
			Configuration configuration = new PropertiesConfiguration(
				"sync.properties");

			String syncConfigurationDirectory = configuration.getString(
				PropsKeys.SYNC_CONFIGURATION_DIRECTORY);

			syncConfigurationDirectory = syncConfigurationDirectory.replace(
				"${user.home}", System.getProperty("user.home"));

			Path path = Paths.get(
				syncConfigurationDirectory, "sync-ext.properties");

			Configuration extConfiguration = new PropertiesConfiguration(
				path.toFile());

			_compositeConfiguration.addConfiguration(extConfiguration);

			_compositeConfiguration.addConfiguration(configuration);
		}
		catch (ConfigurationException ce) {
			_logger.error("Unable to initialize", ce);
		}
	}

	private void _addConfiguration(Configuration configuration) {
		_compositeConfiguration.addConfiguration(configuration);
	}

	private String _get(String key) {
		String value = _compositeConfiguration.getString(key);

		if (value == null) {
			return "";
		}

		return value.replace("${user.home}", System.getProperty("user.home"));
	}

	private String[] _getArray(String key) {
		return _compositeConfiguration.getStringArray(key);
	}

	private void _set(String key, String value) {
		_compositeConfiguration.setProperty(key, value);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		PropsUtil.class);

	private static final PropsUtil _instance = new PropsUtil();

	private final CompositeConfiguration _compositeConfiguration =
		new CompositeConfiguration();

}