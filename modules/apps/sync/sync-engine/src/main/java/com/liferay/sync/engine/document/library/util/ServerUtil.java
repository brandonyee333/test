/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.util;

import com.liferay.sync.engine.util.ServerInfo;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 */
public class ServerUtil {

	public static String getDownloadURL(
		boolean supportsModuleFramework, String urlString) {

		if (!supportsModuleFramework) {
			try {
				URL url = new URL(urlString);

				int index = urlString.lastIndexOf(url.getPath());

				return urlString.substring(0, index);
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);

				return urlString;
			}
		}

		return urlString;
	}

	public static String getDownloadURL(long syncAccountId, String urlString) {
		return getDownloadURL(
			ServerInfo.supportsModuleFramework(syncAccountId), urlString);
	}

	public static String getDownloadURL(
		String pluginVersion, String urlString) {

		return getDownloadURL(
			ServerInfo.supportsModuleFramework(pluginVersion), urlString);
	}

	public static String getURLPath(
		boolean supportsModuleFramework, String urlPath) {

		if (supportsModuleFramework) {
			urlPath = urlPath.replace("/sync-web/", "/o/sync/");

			return urlPath.replace("/sync-web.", "/sync.");
		}

		return urlPath;
	}

	public static String getURLPath(long syncAccountId, String urlPath) {
		return getURLPath(
			ServerInfo.supportsModuleFramework(syncAccountId), urlPath);
	}

	public static String getURLPath(String pluginVersion, String urlPath) {
		return getURLPath(
			ServerInfo.supportsModuleFramework(pluginVersion), urlPath);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		ServerUtil.class);

}