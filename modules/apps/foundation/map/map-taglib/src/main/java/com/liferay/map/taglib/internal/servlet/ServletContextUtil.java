/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.map.taglib.internal.servlet;

import com.liferay.map.util.MapProviderHelper;
import com.liferay.map.util.MapProviderTracker;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true)
public class ServletContextUtil {

	public static final MapProviderHelper getMapProviderHelper() {
		return _instance._getMapProviderHelper();
	}

	public static final MapProviderTracker getMapProviderTracker() {
		return _instance._getMapProviderTracker();
	}

	public static final ServletContext getServletContext() {
		return _instance._getServletContext();
	}

	@Activate
	protected void activate() {
		_instance = this;
	}

	@Deactivate
	protected void deactivate() {
		_instance = null;
	}

	@Reference(unbind = "-")
	protected void setMapProviderHelper(MapProviderHelper mapProviderHelper) {
		_mapProviderHelper = mapProviderHelper;
	}

	@Reference(unbind = "-")
	protected void setMapProviderTracker(
		MapProviderTracker mapProviderTracker) {

		_mapProviderTracker = mapProviderTracker;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.map.taglib)", unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private MapProviderHelper _getMapProviderHelper() {
		return _mapProviderHelper;
	}

	private MapProviderTracker _getMapProviderTracker() {
		return _mapProviderTracker;
	}

	private ServletContext _getServletContext() {
		return _servletContext;
	}

	private static ServletContextUtil _instance;

	private MapProviderHelper _mapProviderHelper;
	private MapProviderTracker _mapProviderTracker;
	private ServletContext _servletContext;

}