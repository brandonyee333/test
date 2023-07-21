/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Newton (6.2.x)
 */
@Deprecated
public class PACLClassUtil {

	public static ClassLoader getCallerClassLoader(Class<?> callerClass) {
		return null;
	}

	public static String getClassLocation(Class<?> clazz) {
		return null;
	}

	public static String getJarLocation(Class<?> clazz) {
		return null;
	}

	public static PACLPolicy getPACLPolicy(boolean deep, boolean debug) {
		return null;
	}

	public static PACLPolicy getPACLPolicyByReflection(
		boolean deep, boolean debug) {

		return null;
	}

}