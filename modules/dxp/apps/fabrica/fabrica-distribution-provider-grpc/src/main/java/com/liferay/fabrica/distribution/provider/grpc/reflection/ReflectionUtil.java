/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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