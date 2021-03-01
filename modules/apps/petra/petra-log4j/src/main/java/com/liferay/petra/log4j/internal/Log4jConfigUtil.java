/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.petra.log4j.internal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Tina Tian
 */
public class Log4jConfigUtil {

	public static Map<String, String> configureLog4J(
		String xmlContent, String... removedAppenderNames) {

		Map<String, String> priorities = new HashMap<>();

		Document document = null;

		LoggerContext loggerContext =
			_centralizedConfiguration.getLoggerContext();

		AbstractConfiguration abstractConfiguration = null;

		try {
			SAXReader saxReader = new SAXReader();

			document = saxReader.read(new UnsyncStringReader(xmlContent));

			Element rootElement = document.getRootElement();

			if (Objects.equals("Configuration", rootElement.getName())) {
				if (!GetterUtil.getBoolean(
						rootElement.attributeValue("strict"))) {

					throw new Exception(
						"<Configuration> strict attribute requires true");
				}

				for (Element element : rootElement.elements()) {
					for (Element childElement : element.elements()) {
						_removeAppender(
							element, childElement, "AppenderRef", "Appender",
							removedAppenderNames);

						if (Objects.equals("Logger", childElement.getName())) {
							priorities.put(
								childElement.attributeValue("name"),
								childElement.attributeValue("level"));
						}
					}
				}

				abstractConfiguration = new XmlConfiguration(
					loggerContext, _getConfigurationSource(document));
			}
			else {
				for (Element childElement : rootElement.elements()) {
					_removeAppender(
						rootElement, childElement, "appender-ref", "appender",
						removedAppenderNames);

					if (Objects.equals("category", childElement.getName())) {
						Element priorityElement = childElement.element(
							"priority");

						priorities.put(
							childElement.attributeValue("name"),
							priorityElement.attributeValue("value"));
					}
				}

				abstractConfiguration =
					new org.apache.log4j.xml.XmlConfiguration(
						loggerContext, _getConfigurationSource(document), 0);
			}

			_centralizedConfiguration.addConfiguration(abstractConfiguration);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			priorities.clear();
		}

		return priorities;
	}

	public static java.util.logging.Level getJDKLevel(String levelString) {
		if (StringUtil.equalsIgnoreCase(levelString, Level.DEBUG.toString())) {
			return java.util.logging.Level.FINE;
		}
		else if (StringUtil.equalsIgnoreCase(
					levelString, Level.ERROR.toString())) {

			return java.util.logging.Level.SEVERE;
		}
		else if (StringUtil.equalsIgnoreCase(
					levelString, Level.WARN.toString())) {

			return java.util.logging.Level.WARNING;
		}

		return java.util.logging.Level.INFO;
	}

	public static Map<String, String> getPriorities() {
		Map<String, String> priorities = new HashMap<>();

		Map<String, LoggerConfig> loggerConfigs =
			_centralizedConfiguration.getLoggers();

		for (Map.Entry<String, LoggerConfig> loggerConfigEntry :
				loggerConfigs.entrySet()) {

			LoggerConfig loggerConfig = loggerConfigEntry.getValue();

			if (!_isRootLoggerConfig(loggerConfig)) {
				priorities.put(
					loggerConfigEntry.getKey(),
					String.valueOf(loggerConfig.getLevel()));
			}
		}

		return priorities;
	}

	public static void setLevel(String name, String priority) {
		Level level = Level.toLevel(priority);

		LoggerConfig loggerConfig = _centralizedConfiguration.getLogger(name);

		if (loggerConfig != null) {
			loggerConfig.setLevel(level);
		}
		else {
			loggerConfig = new LoggerConfig(name, level, true);

			_centralizedConfiguration.addLogger(name, loggerConfig);
		}

		LoggerContext loggerContext =
			_centralizedConfiguration.getLoggerContext();

		loggerContext.updateLoggers();
	}

	public static void shutdownLog4J() {
		LogManager.shutdown();
	}

	private static ConfigurationSource _getConfigurationSource(
			Document document)
		throws Exception {

		String xmlContent = document.asXML();

		return new ConfigurationSource(
			new UnsyncByteArrayInputStream(
				xmlContent.getBytes(StringPool.UTF8)));
	}

	private static boolean _isRootLoggerConfig(LoggerConfig loggerConfig) {
		if (Validator.isBlank(loggerConfig.getName())) {
			return true;
		}

		return false;
	}

	private static void _removeAppender(
		Element parentElement, Element element, String appenderRefTagName,
		String appenderTagName, String... removedAppenderNames) {

		if (removedAppenderNames.length == 0) {
			return;
		}

		for (String appenderName : removedAppenderNames) {
			if (Objects.equals(appenderTagName, element.getName()) &&
				Objects.equals(appenderName, element.attributeValue("name"))) {

				parentElement.remove(element);
			}

			for (Element childElement : element.elements()) {
				if (Objects.equals(
						appenderRefTagName, childElement.getName()) &&
					Objects.equals(
						appenderName, childElement.attributeValue("ref"))) {

					element.remove(childElement);
				}
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		Log4jConfigUtil.class);

	private static final CentralizedConfiguration _centralizedConfiguration =
		new CentralizedConfiguration();

}