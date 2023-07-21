/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.dashboard.gateway.internal.util;

import com.liferay.portal.kernel.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ethan Bustad
 */
public class APITranslator {

	public static String getAPIURL(String server, String testrayAPIName) {
		String apiPath = _apiMap.get(testrayAPIName);

		if (apiPath == null) {
			throw new IllegalArgumentException(
				testrayAPIName + " is not a valid API");
		}

		return StringUtil.replace(
			_URL_TEMPLATE, new String[] {"{server}", "{apiPath}"},
			new String[] {server, apiPath});
	}

	public static void setAvailableAPIs(String[] availableAPIPairs) {
		_apiMap.clear();

		for (String availableAPIPair : availableAPIPairs) {
			String[] namePathPair = availableAPIPair.split(
				_NAME_PATH_DELIMITER);

			_apiMap.put(namePathPair[0].trim(), namePathPair[1].trim());
		}
	}

	private static final String _NAME_PATH_DELIMITER = ":";

	private static final String _URL_TEMPLATE =
		"https://{server}.liferay.com{apiPath}";

	private static final Map<String, String> _apiMap = new HashMap<>();

}