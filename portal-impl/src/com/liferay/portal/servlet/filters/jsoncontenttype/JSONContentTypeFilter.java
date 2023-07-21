/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet.filters.jsoncontenttype;

import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.servlet.filters.BasePortalFilter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Igor Spasic
 */
public class JSONContentTypeFilter extends BasePortalFilter {

	@Override
	public void init(FilterConfig filterConfig) {
		super.init(filterConfig);
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		if (!BrowserSnifferUtil.isIe(request)) {
			processFilter(
				JSONContentTypeFilter.class.getName(), request, response,
				filterChain);
		}
		else {
			processFilter(
				JSONContentTypeFilter.class.getName(), request,
				new JSONContentTypeResponse(response), filterChain);
		}
	}

}