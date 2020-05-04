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

package com.liferay.osb.asah.common.configuration.impl;

import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.FileConfiguration;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseFileConfigurationImpl implements FileConfiguration {

	public BaseFileConfigurationImpl() {
		ClassLoader classLoader = Configuration.class.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			getFileName() + ".properties");

		if (inputStream != null) {
			try {
				properties.load(inputStream);
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
		}
		else {
			throw new RuntimeException(
				"Unable to load " + getFileName() + ".properties");
		}

		InputStream extInputStream = classLoader.getResourceAsStream(
			getFileName() + "-ext.properties");

		if (extInputStream != null) {
			try {
				properties.load(extInputStream);
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
		}
	}

	@Override
	public String getDataSourceId() {
		return properties.getProperty("data.source.id");
	}

	@Override
	public String getDataSourceState() {
		return properties.getProperty("data.source.state");
	}

	@Override
	public String getDataSourceStatus() {
		return properties.getProperty("data.source.status");
	}

	protected abstract String getFileName();

	protected final Properties properties = new Properties();

	private static final Log _log = LogFactory.getLog(
		BaseFileConfigurationImpl.class);

}