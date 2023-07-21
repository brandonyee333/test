/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

import java.net.URL;

import java.nio.file.Paths;

import org.slf4j.LoggerFactory;

/**
 * @author Michael Young
 */
public class LoggerUtil {

	public static void init() {
		String loggerConfigurationFilePathName = FileUtil.getFilePathName(
			PropsValues.SYNC_CONFIGURATION_DIRECTORY,
			PropsValues.SYNC_LOGGER_CONFIGURATION_FILE);

		_loggerContext = (LoggerContext)LoggerFactory.getILoggerFactory();

		_loggerContext.reset();

		JoranConfigurator joranConfigurator = new JoranConfigurator();

		joranConfigurator.setContext(_loggerContext);

		try {
			ClassLoader classLoader = LoggerUtil.class.getClassLoader();

			URL url = classLoader.getResource(
				PropsValues.SYNC_LOGGER_CONFIGURATION_FILE);

			joranConfigurator.doConfigure(url);

			if (FileUtil.exists(Paths.get(loggerConfigurationFilePathName))) {
				joranConfigurator.doConfigure(loggerConfigurationFilePathName);
			}
		}
		catch (Exception e) {
		}
	}

	public static void shutdown() {
		_loggerContext.stop();
	}

	private static LoggerContext _loggerContext;

}