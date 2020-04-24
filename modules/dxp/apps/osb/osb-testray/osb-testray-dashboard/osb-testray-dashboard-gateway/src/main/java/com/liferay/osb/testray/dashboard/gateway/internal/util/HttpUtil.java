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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.net.URL;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author Ethan Bustad
 */
public class HttpUtil {

	public static final String BASIC_AUTH_CREDENTIAL_SEPARATOR = ":";

	public static String doGet(String urlString, Map<String, String> headers) {
		try {
			URL url = new URL(urlString);

			HttpsURLConnection connection =
				(HttpsURLConnection)url.openConnection();

			for (Map.Entry<String, String> header : headers.entrySet()) {
				connection.setRequestProperty(
					header.getKey(), header.getValue());
			}

			connection.connect();

			try {
				return StringUtil.read(connection.getInputStream());
			}
			catch (IOException ioe) {
				_log.error(ioe.getMessage());
			}
		}
		catch (Exception e) {
			_log.error("Hitting URL " + urlString + " failed", e);
		}

		return null;
	}

	public static String doGet(
		String urlString, String username, String password) {

		Map<String, String> headers = new HashMap<>();

		if (Validator.isNotNull(username)) {
			String credentials =
				username + BASIC_AUTH_CREDENTIAL_SEPARATOR + password;

			String base64Credentials = new String(
				Base64.encode(credentials.getBytes()));

			headers.put("Authorization", "Basic " + base64Credentials);
		}

		return doGet(urlString, headers);
	}

	private static final Log _log = LogFactoryUtil.getLog(HttpUtil.class);

}