/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.web.servlet.context.helper.internal;

import com.liferay.portal.osgi.web.servlet.jsp.compiler.JspServlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class JspServletWrapper extends HttpServlet {

	public JspServletWrapper(String jspFile) {
		_jspFile = jspFile;
	}

	@Override
	public void destroy() {
		_servlet.destroy();
	}

	@Override
	public ServletConfig getServletConfig() {
		return _servlet.getServletConfig();
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		_servlet.init(servletConfig);
	}

	@Override
	public void service(
			ServletRequest servletRequest, ServletResponse servletResponse)
		throws IOException, ServletException {

		String curJspFile = (String)servletRequest.getAttribute(
			JspServlet.JSP_FILE);

		if (_jspFile != null) {
			servletRequest.setAttribute(JspServlet.JSP_FILE, _jspFile);
		}

		try {
			_servlet.service(servletRequest, servletResponse);
		}
		finally {
			servletRequest.setAttribute(JspServlet.JSP_FILE, curJspFile);
		}
	}

	private final String _jspFile;
	private final Servlet _servlet = new JspServlet();

}