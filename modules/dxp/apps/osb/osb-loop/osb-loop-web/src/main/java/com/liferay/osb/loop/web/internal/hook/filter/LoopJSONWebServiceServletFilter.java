/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.hook.filter;

import com.liferay.osb.loop.web.internal.util.LoopRequestHeaderRegistryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Timothy Bell
 */
@Component(
	immediate = true,
	property = {
		"after-filter=JSON Web Service Servlet Filter", "dispatcher=FORWARD",
		"dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=Loop JSON Web Service Servlet Filter",
		"url-pattern=/api/jsonws/invoke/*"
	},
	service = Filter.class
)
public class LoopJSONWebServiceServletFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		LoopRequestHeaderRegistryUtil.registerRequestHeaders(request);

		filterChain.doFilter(request, response);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoopJSONWebServiceServletFilter.class);

}