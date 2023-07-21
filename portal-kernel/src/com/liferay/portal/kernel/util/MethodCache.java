/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class MethodCache {

	public static void reset() {
		_methods.clear();
	}

	/**
	 * @see MethodKey
	 */
	protected static Method get(MethodKey methodKey)
		throws NoSuchMethodException {

		Method method = _methods.get(methodKey);

		if (method == null) {
			Class<?> declaringClass = methodKey.getDeclaringClass();

			method = declaringClass.getDeclaredMethod(
				methodKey.getMethodName(), methodKey.getParameterTypes());

			if (!method.isAccessible()) {
				method.setAccessible(true);
			}

			_methods.put(methodKey, method);
		}

		return method;
	}

	private static final Map<MethodKey, Method> _methods =
		new ConcurrentHashMap<>();

}