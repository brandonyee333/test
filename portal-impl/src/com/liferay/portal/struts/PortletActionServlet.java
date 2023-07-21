/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.struts;

import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletActionServlet extends ActionServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);

		ServletContext servletContext = getServletContext();

		ModuleConfig moduleConfig = (ModuleConfig)servletContext.getAttribute(
			Globals.MODULE_KEY);

		PortletRequestProcessor portletRequestProcessor =
			PortletRequestProcessor.getInstance(this, moduleConfig);

		servletContext.setAttribute(
			WebKeys.PORTLET_STRUTS_PROCESSOR, portletRequestProcessor);
	}

}