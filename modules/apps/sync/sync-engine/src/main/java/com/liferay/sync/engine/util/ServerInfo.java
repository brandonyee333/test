/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import com.liferay.sync.engine.document.library.model.SyncContext;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class ServerInfo {

	public static boolean isCompatible(SyncContext syncContext) {
		return isCompatible(syncContext.getPluginVersion(), "6.2.0.4");
	}

	public static boolean supportsDeviceRegistration(long syncAccountId) {
		return isCompatible(syncAccountId, "6.2.0.7");
	}

	public static boolean supportsModuleFramework(long syncAccountId) {
		return isCompatible(syncAccountId);
	}

	public static boolean supportsModuleFramework(String pluginVersion) {
		return isCompatible(pluginVersion);
	}

	public static boolean supportsPartialDownloads(long syncAccountId) {
		return isCompatible(syncAccountId, "6.2.0.5");
	}

	public static boolean supportsRetrieveFromCache(long syncAccountId) {
		return isCompatible(syncAccountId, "6.2.0.5");
	}

	protected static int getPluginMajorVersion(String pluginVersion) {
		String[] pluginVersionParts = pluginVersion.split("\\.");

		if (pluginVersionParts.length == 3) {
			return Integer.valueOf(pluginVersionParts[0]);
		}

		return Integer.valueOf(pluginVersionParts[0] + pluginVersionParts[1]);
	}

	protected static int getPluginMinorVersion(String pluginVersion) {
		String[] pluginVersionParts = pluginVersion.split("\\.");

		if (pluginVersionParts.length == 3) {
			return Integer.valueOf(pluginVersionParts[1]);
		}

		return Integer.valueOf(pluginVersionParts[2]);
	}

	protected static int getPluginPatchVersion(String pluginVersion) {
		String[] pluginVersionParts = pluginVersion.split("\\.");

		if (pluginVersionParts.length == 3) {
			return Integer.valueOf(pluginVersionParts[2]);
		}

		return Integer.valueOf(pluginVersionParts[3]);
	}

	protected static boolean isCompatible(
		long syncAccountId, String... pluginMinimumVersions) {

		SyncAccount syncAccount = SyncAccountService.fetchSyncAccount(
			syncAccountId);

		return isCompatible(
			syncAccount.getPluginVersion(), pluginMinimumVersions);
	}

	protected static boolean isCompatible(
		String pluginVersion, String... pluginMinimumVersions) {

		if (pluginVersion == null) {
			return false;
		}

		String[] pluginVersionParts = pluginVersion.split("\\.");

		if (pluginVersionParts.length == 3) {
			return true;
		}

		String key = pluginVersion + Arrays.toString(pluginMinimumVersions);

		Boolean compatible = _compatibilityMap.get(key);

		if (compatible != null) {
			return compatible;
		}

		for (String pluginMinimumVersion : pluginMinimumVersions) {
			if (getPluginMajorVersion(pluginVersion) != getPluginMajorVersion(
					pluginMinimumVersion)) {

				continue;
			}

			if (getPluginPatchVersion(pluginVersion) >= getPluginPatchVersion(
					pluginMinimumVersion)) {

				_compatibilityMap.put(key, true);

				return true;
			}
		}

		_compatibilityMap.put(key, false);

		return false;
	}

	private static final Map<String, Boolean> _compatibilityMap =
		new HashMap<>();

}