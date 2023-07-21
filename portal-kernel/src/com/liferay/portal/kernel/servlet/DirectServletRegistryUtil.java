/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import javax.servlet.Servlet;

/**
 * @author Shuyang Zhou
 */
public class DirectServletRegistryUtil {

	public static void clearServlets() {
		getDirectServletRegistry().clearServlets();
	}

	public static DirectServletRegistry getDirectServletRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			DirectServletRegistryUtil.class);

		return _directServletRegistry;
	}

	public static Servlet getServlet(String path) {
		return getDirectServletRegistry().getServlet(path);
	}

	public static void putServlet(String path, Servlet servlet) {
		getDirectServletRegistry().putServlet(path, servlet);
	}

	public void setDirectServletRegistry(
		DirectServletRegistry directServletRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_directServletRegistry = directServletRegistry;
	}

	private static DirectServletRegistry _directServletRegistry;

}