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

package com.liferay.osb.asah.common.servlet.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shinn Lok
 */
public class ServletRequestUtil {

	public static String getOriginalURL(HttpServletRequest httpServletRequest) {
		return _getURL(
			_getScheme(httpServletRequest), _getServerName(httpServletRequest),
			_getServerPort(httpServletRequest));
	}

	public static String getURL(HttpServletRequest httpServletRequest) {
		return _getURL(
			httpServletRequest.getScheme(), httpServletRequest.getServerName(),
			httpServletRequest.getServerPort());
	}

	private static String _getScheme(HttpServletRequest httpServletRequest) {
		String forwardedProtocol = httpServletRequest.getHeader(
			"X-Forwarded-Proto");

		if (forwardedProtocol != null) {
			return forwardedProtocol;
		}

		return httpServletRequest.getScheme();
	}

	private static String _getServerName(
		HttpServletRequest httpServletRequest) {

		String forwardedHost = httpServletRequest.getHeader("X-Forwarded-Host");

		if (forwardedHost != null) {
			return forwardedHost;
		}

		return httpServletRequest.getServerName();
	}

	private static int _getServerPort(HttpServletRequest httpServletRequest) {
		String forwardedPort = httpServletRequest.getHeader("X-Forwarded-Port");

		if (forwardedPort != null) {
			return Integer.parseInt(forwardedPort);
		}

		return httpServletRequest.getServerPort();
	}

	private static String _getURL(
		String scheme, String serverName, int serverPort) {

		StringBuilder sb = new StringBuilder();

		sb.append(scheme);
		sb.append("://");
		sb.append(serverName);

		if ((serverPort > 0) && (serverPort != 80) && (serverPort != 443)) {
			sb.append(":");
			sb.append(serverPort);
		}

		return sb.toString();
	}

}