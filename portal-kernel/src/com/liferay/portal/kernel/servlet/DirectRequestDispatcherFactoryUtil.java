/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

/**
 * @author Raymond Augé
 */
public class DirectRequestDispatcherFactoryUtil {

	public static DirectRequestDispatcherFactory
		getDirectRequestDispatcherFactory() {

		PortalRuntimePermission.checkGetBeanProperty(
			DirectRequestDispatcherFactoryUtil.class);

		return _directRequestDispatcherFactory;
	}

	public static RequestDispatcher getRequestDispatcher(
		ServletContext servletContext, String path) {

		return getDirectRequestDispatcherFactory().getRequestDispatcher(
			servletContext, path);
	}

	public static RequestDispatcher getRequestDispatcher(
		ServletRequest servletRequest, String path) {

		return getDirectRequestDispatcherFactory().getRequestDispatcher(
			servletRequest, path);
	}

	public void setDirectRequestDispatcherFactory(
		DirectRequestDispatcherFactory directRequestDispatcherFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_directRequestDispatcherFactory = directRequestDispatcherFactory;
	}

	private static DirectRequestDispatcherFactory
		_directRequestDispatcherFactory;

}