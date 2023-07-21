/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.settings.authentication.cas.web.internal.servlet.taglib;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Adds a CAS tab to the Authentication section of the Portal Settings user
 * interface in the Control Panel.
 *
 * @author Tomas Polesovsky
 */
@Component(
	immediate = true, property = "portal.settings.authentication.tabs.name=cas",
	service = DynamicInclude.class
)
public class PortalSettingsCASAuthenticationDynamicInclude
	extends BaseDynamicInclude {

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(_JSP_PATH);

		try {
			requestDispatcher.include(request, response);
		}
		catch (ServletException se) {
			_log.error("Unable to include JSP " + _JSP_PATH, se);

			throw new IOException("Unable to include JSP " + _JSP_PATH, se);
		}
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portal.settings.authentication.cas.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static final String _JSP_PATH =
		"/com.liferay.portal.settings.web/cas.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		PortalSettingsCASAuthenticationDynamicInclude.class);

	private ServletContext _servletContext;

}