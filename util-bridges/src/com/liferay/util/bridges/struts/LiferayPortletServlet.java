/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.bridges.struts;

import com.liferay.portal.kernel.servlet.ServletContextProvider;

import javax.servlet.ServletContext;

import org.apache.portals.bridges.struts.PortletServlet;

/**
 * @author Michael Young
 */
public class LiferayPortletServlet extends PortletServlet {

	@Override
	public ServletContext getServletContext() {
		ServletContext servletContext = super.getServletContext();

		ServletContextProvider servletContextProvider =
			(ServletContextProvider)servletContext.getAttribute(
				ServletContextProvider.STRUTS_BRIDGES_CONTEXT_PROVIDER);

		if (servletContextProvider != null) {
			servletContext = servletContextProvider.getServletContext(
				servletContext);
		}

		return servletContext;
	}

}