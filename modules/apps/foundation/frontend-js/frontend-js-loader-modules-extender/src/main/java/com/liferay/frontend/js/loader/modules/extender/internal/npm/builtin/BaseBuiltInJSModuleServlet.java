/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.loader.modules.extender.internal.npm.builtin;

import com.liferay.frontend.js.loader.modules.extender.npm.JSModule;
import com.liferay.frontend.js.loader.modules.extender.npm.ModuleNameUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides a base abstract class to implement servlets that return JavaScript
 * modules tracked by the {@link
 * com.liferay.frontend.js.loader.modules.extender.npm.NPMRegistry}.
 *
 * @author Adolfo Pérez
 */
public abstract class BaseBuiltInJSModuleServlet extends HttpServlet {

	/**
	 * Returns the requested module. This is a template method that must be
	 * implemented by subclasses to lookup the requested module.
	 *
	 * @param  moduleName the module's name
	 * @return the {@link JSModule} object describing the module
	 */
	protected abstract JSModule getJSModule(String moduleName);

	@Override
	protected void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		JSModule jsModule = _resolveJSModule(request);

		if (jsModule == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);

			return;
		}

		String contentType = null;
		InputStream inputStream = null;

		String pathInfo = request.getPathInfo();

		if (pathInfo.endsWith(".map")) {
			contentType = ContentTypes.APPLICATION_JSON;
			inputStream = jsModule.getSourceMapInputStream();
		}
		else {
			contentType = ContentTypes.TEXT_JAVASCRIPT_UTF8;
			inputStream = jsModule.getInputStream();
		}

		response.setContentType(contentType);

		ServletOutputStream servletOutputStream = response.getOutputStream();

		try {
			StreamUtil.transfer(inputStream, servletOutputStream, false);
		}
		catch (Exception e) {
			response.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				"Unable to read file");
		}
		finally {
			inputStream.close();
		}
	}

	private JSModule _resolveJSModule(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();

		String identifier = pathInfo.substring(1);

		if (pathInfo.endsWith(".map")) {
			int index = identifier.lastIndexOf(StringPool.PERIOD);

			identifier = identifier.substring(0, index);
		}

		String moduleName = ModuleNameUtil.toModuleName(identifier);

		return getJSModule(moduleName);
	}

}