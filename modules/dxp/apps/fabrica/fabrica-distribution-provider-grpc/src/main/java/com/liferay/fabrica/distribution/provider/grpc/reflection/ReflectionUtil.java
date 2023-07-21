/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fabrica.distribution.provider.grpc.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Miguel Pastor
 */
public class ReflectionUtil {

	public static Collection<Method> getInterfaceMethods(Class<?> clazz) {
		if (!clazz.isInterface()) {
			return Collections.emptySet();
		}

		List<Method> methods = new ArrayList<>();

		for (Method method : clazz.getDeclaredMethods()) {
			if (method.isSynthetic() || method.isBridge() ||
				Modifier.isStatic(method.getModifiers())) {

				continue;
			}

			methods.add(method);
		}

		return methods;
	}

}