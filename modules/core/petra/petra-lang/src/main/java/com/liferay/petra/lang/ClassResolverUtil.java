/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.petra.lang;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuyang Zhou
 */
public class ClassResolverUtil {

	public static Class<?> resolve(String className, ClassLoader classLoader)
		throws ClassNotFoundException {

		try {
			return Class.forName(className, false, classLoader);
		}
		catch (ClassNotFoundException cnfe) {
			Class<?> clazz = _primitiveClasses.get(className);

			if (clazz != null) {
				return clazz;
			}

			throw cnfe;
		}
	}

	private static final Map<String, Class<?>> _primitiveClasses =
		new HashMap<>(9, 1.0F);

	static {
		_primitiveClasses.put("boolean", boolean.class);
		_primitiveClasses.put("byte", byte.class);
		_primitiveClasses.put("char", char.class);
		_primitiveClasses.put("double", double.class);
		_primitiveClasses.put("float", float.class);
		_primitiveClasses.put("int", int.class);
		_primitiveClasses.put("long", long.class);
		_primitiveClasses.put("short", short.class);
		_primitiveClasses.put("void", void.class);
	}

}