/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.filter;

import com.liferay.compat.portal.kernel.servlet.HttpHeaders;
import com.liferay.osb.util.PortletPropsValues;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 */
public class MarketplaceFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;

		String serverName = request.getServerName();

		if (serverName.equals(
				PortletPropsValues.MARKETPLACE_IN_PRODUCT_DOMAIN)) {

			response.setHeader(_P3P_HEADER_KEY, _P3P_HEADER_VALUE);
			response.setHeader(_X_FRAME_OPTIONS_KEY, _X_FRAME_OPTIONS_VALUE);
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	private static final String _P3P_HEADER_KEY = "P3P";

	private static final String _P3P_HEADER_VALUE =
		"CP=\"Read our privacy policy at " +
			"https://www.liferay.com/about-us/privacy\"";

	private static final String _X_FRAME_OPTIONS_KEY =
		HttpHeaders.X_FRAME_OPTIONS;

	private static final String _X_FRAME_OPTIONS_VALUE = "AllowAll";

}