/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletApp;
import com.liferay.portal.kernel.portlet.PortletContextFactory;
import com.liferay.portal.security.lang.DoPrivilegedUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletContext;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletContextFactoryImpl implements PortletContextFactory {

	@Override
	public PortletContext create(
		Portlet portlet, ServletContext servletContext) {

		Map<String, PortletContext> portletContexts = _pool.get(
			portlet.getRootPortletId());

		if (portletContexts == null) {
			portletContexts = new ConcurrentHashMap<>();

			_pool.put(portlet.getRootPortletId(), portletContexts);
		}

		PortletContext portletContext = null;

		if (portlet.isUndeployedPortlet()) {
			portletContexts.remove(portlet.getPortletId());
		}
		else {
			portletContext = portletContexts.get(portlet.getPortletId());
		}

		if (portletContext != null) {
			return DoPrivilegedUtil.wrap(portletContext);
		}

		PortletApp portletApp = portlet.getPortletApp();

		if (!portlet.isUndeployedPortlet() && portletApp.isWARFile()) {
			servletContext = portletApp.getServletContext();
		}

		portletContext = new PortletContextImpl(portlet, servletContext);

		portletContexts.put(portlet.getPortletId(), portletContext);

		return DoPrivilegedUtil.wrap(portletContext);
	}

	@Override
	public PortletContext createUntrackedInstance(
		Portlet portlet, ServletContext servletContext) {

		PortletContext portletContext = new PortletContextImpl(
			portlet, servletContext);

		return DoPrivilegedUtil.wrap(portletContext);
	}

	@Override
	public void destroy(Portlet portlet) {
		_pool.remove(portlet.getRootPortletId());
	}

	private final Map<String, Map<String, PortletContext>> _pool =
		new ConcurrentHashMap<>();

}