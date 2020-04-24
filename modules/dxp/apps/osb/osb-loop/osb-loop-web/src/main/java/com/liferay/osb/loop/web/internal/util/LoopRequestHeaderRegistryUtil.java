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