/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public class LoopRequestHeaderRegistryUtil {

	public static Map<String, List<String>> getRequestHeaders() {
		Map<String, List<String>> requestHeaders = _requestHeadersRegistry.get(
			getKey());

		if (requestHeaders == null) {
			return new HashMap<>();
		}

		return requestHeaders;
	}

	public static void registerRequestHeaders(HttpServletRequest request) {
		String key = getKey();

		if (Validator.isNull(key)) {
			return;
		}

		_requestHeadersRegistry.put(
			key, LoopHttpServletRequestUtil.getRequestHeaders(request));
	}

	public static void unregisterRequestHeaders() {
		_requestHeadersRegistry.remove(getKey());
	}

	protected static String getKey() {
		try {
			PermissionChecker permissionChecker =
				PermissionThreadLocal.getPermissionChecker();

			if (permissionChecker == null) {
				return StringPool.BLANK;
			}

			User user = UserLocalServiceUtil.getUser(
				permissionChecker.getUserId());

			Thread currentThread = Thread.currentThread();

			return currentThread.getId() + StringPool.UNDERLINE +
				user.getUserId();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	private static final Map<String, Map<String, List<String>>>
		_requestHeadersRegistry = new HashMap<>();

}