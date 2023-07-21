/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.js.loader.modules.extender.internal.npm.builtin;

import com.liferay.frontend.js.loader.modules.extender.npm.JSModule;
import com.liferay.frontend.js.loader.modules.extender.npm.NPMRegistry;

import javax.servlet.Servlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Iván Zaera
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.servlet.name=Serve Package Servlet",
		"osgi.http.whiteboard.servlet.pattern=/js/resolved-module/*",
		"service.ranking:Integer=" + (Integer.MAX_VALUE - 1000)
	},
	service = {BuiltInJSResolvedModuleServlet.class, Servlet.class}
)
public class BuiltInJSResolvedModuleServlet extends BaseBuiltInJSModuleServlet {

	@Override
	protected JSModule getJSModule(String moduleName) {
		return _npmRegistry.getResolvedJSModule(moduleName);
	}

	private static final long serialVersionUID = 2647715401054034600L;

	@Reference
	private NPMRegistry _npmRegistry;

}