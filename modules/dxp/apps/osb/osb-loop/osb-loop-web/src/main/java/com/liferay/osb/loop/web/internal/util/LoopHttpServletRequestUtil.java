/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public class LoopHttpServletRequestUtil {

	public static Map<String, List<String>> getRequestHeaders(
		HttpServletRequest request) {

		Map<String, List<String>> headersMap = new HashMap<>();

		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();

			Enumeration<String> headers = request.getHeaders(headerName);

			headersMap.put(
				StringUtil.toLowerCase(headerName), Collections.list(headers));
		}

		return headersMap;
	}

	public static boolean isMobileRequest(String userAgent) {
		if (StringUtil.startsWith(userAgent, _USER_AGENT_PREFIX_ANDROID) ||
			StringUtil.startsWith(userAgent, _USER_AGENT_PREFIX_IOS)) {

			return true;
		}

		return false;
	}

	private static final String _USER_AGENT_PREFIX_ANDROID = "okhttp";

	private static final String _USER_AGENT_PREFIX_IOS = "Liferay%20Loop";

}