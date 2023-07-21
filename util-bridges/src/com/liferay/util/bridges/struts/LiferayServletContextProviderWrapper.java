/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.bridges.struts;

import com.liferay.portal.kernel.servlet.ServletContextProvider;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael Young
 */
public class LiferayServletContextProviderWrapper
	implements org.apache.portals.bridges.common.ServletContextProvider {

	@Override
	public HttpServletRequest getHttpServletRequest(
		GenericPortlet portlet, PortletRequest portletRequest) {

		ServletContextProvider provider = _getProvider(portlet);

		return provider.getHttpServletRequest(portlet, portletRequest);
	}

	@Override
	public HttpServletResponse getHttpServletResponse(
		GenericPortlet portlet, PortletResponse portletResponse) {

		ServletContextProvider provider = _getProvider(portlet);

		return provider.getHttpServletResponse(portlet, portletResponse);
	}

	@Override
	public ServletContext getServletContext(GenericPortlet portlet) {
		ServletContextProvider provider = _getProvider(portlet);

		return provider.getServletContext(portlet);
	}

	private ServletContextProvider _getProvider(GenericPortlet portlet) {
		PortletContext portletContext = portlet.getPortletContext();

		if (_provider == null) {
			_provider = (ServletContextProvider)portletContext.getAttribute(
				ServletContextProvider.STRUTS_BRIDGES_CONTEXT_PROVIDER);
		}

		return _provider;
	}

	private ServletContextProvider _provider;

}