/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalClassLoaderUtil {

	public static ClassLoader getClassLoader() {
		PortalRuntimePermission.checkGetClassLoader("portal");

		return _classLoader;
	}

	public static boolean isPortalClassLoader(ClassLoader classLoader) {
		if (classLoader == _classLoader) {
			return true;
		}

		return false;
	}

	public static void setClassLoader(ClassLoader classLoader) {
		PortalRuntimePermission.checkSetBeanProperty(
			PortalClassLoaderUtil.class);

		_classLoader = classLoader;
	}

	private static ClassLoader _classLoader;

}