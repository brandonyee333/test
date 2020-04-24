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