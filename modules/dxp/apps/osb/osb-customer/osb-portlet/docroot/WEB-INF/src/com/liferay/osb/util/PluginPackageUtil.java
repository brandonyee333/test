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

package com.liferay.osb.util;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Ryan Park
 */
public class PluginPackageUtil {

	public static PluginPackage readPluginPackage(String fileName, File file) {
		try {
			return doReadPluginPackage(fileName, file);
		}
		catch (Exception e) {
			return null;
		}
	}

	public static Properties readPluginPackageProperties(File file) {
		return getProperties(file, "WEB-INF/liferay-plugin-package.properties");
	}

	public static Properties readRelengProperties(String fileName, File file) {
		if (fileName.endsWith(".jar")) {
			return getProperties(file, "META-INF/liferay-releng.properties");
		}

		return getProperties(file, "WEB-INF/liferay-releng.properties");
	}

	protected static PluginPackage doReadPluginPackage(
			String fileName, File file)
		throws Exception {

		if ((file == null) || !file.exists() || file.isDirectory()) {
			return null;
		}

		InputStream is = null;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(file);

			ZipEntry zipEntry = zipFile.getEntry(
				"WEB-INF/liferay-plugin-package.properties");

			if (zipEntry != null) {
				is = zipFile.getInputStream(zipEntry);

				String displayName = getDisplayName(fileName);

				String propertiesString = StringUtil.read(is);

				Properties properties = PropertiesUtil.load(propertiesString);

				if (!hasLiferayVersions(properties)) {
					return null;
				}
				else {
					return DeployManagerUtil.readPluginPackageProperties(
						displayName, properties);
				}
			}
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException ioe) {
				}
			}

			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}
		}

		return null;
	}

	protected static String getDisplayName(String fileName) {
		if (StringUtil.endsWith(fileName, ".jar") ||
			StringUtil.endsWith(fileName, ".war") ||
			StringUtil.endsWith(fileName, ".xml")) {

			return fileName.substring(0, fileName.length() - 4);
		}

		return fileName;
	}

	protected static Properties getProperties(File file, String path) {
		if ((file == null) || !file.exists() || file.isDirectory()) {
			return null;
		}

		InputStream is = null;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(file);

			ZipEntry zipEntry = zipFile.getEntry(path);

			if (zipEntry == null) {
				return null;
			}

			is = zipFile.getInputStream(zipEntry);

			String propertiesString = StringUtil.read(is);

			return PropertiesUtil.load(propertiesString);
		}
		catch (Exception e) {
			return null;
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException ioe) {
				}
			}

			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}
		}
	}

	protected static boolean hasLiferayVersions(Properties properties) {
		return Validator.isNotNull(properties.getProperty("liferay-versions"));
	}

}