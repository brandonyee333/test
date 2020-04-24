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