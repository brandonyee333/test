/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.configuration.css.web.internal.servlet.taglib;

import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = DynamicInclude.class)
public class PortletConfigurationCSSBottomDynamicInclude
	extends BaseDynamicInclude {

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		PrintWriter printWriter = response.getWriter();

		printWriter.print(
			StringUtil.replace(
				_TMPL_CONTENT, StringPool.POUND, StringPool.POUND, _values));
	}

	@Override
	public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {
		dynamicIncludeRegistry.register("/html/common/themes/bottom.jsp#post");
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portlet.configuration.css.web)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		String proxyPath = PortalUtil.getPathProxy();

		_values = Collections.singletonMap(
			"contextPath", proxyPath.concat(servletContext.getContextPath()));
	}

	private static final String _TMPL_CONTENT = StringUtil.read(
		PortletConfigurationCSSBottomDynamicInclude.class,
		"/META-INF/resources/definitions.tmpl");

	private Map<String, String> _values;

}