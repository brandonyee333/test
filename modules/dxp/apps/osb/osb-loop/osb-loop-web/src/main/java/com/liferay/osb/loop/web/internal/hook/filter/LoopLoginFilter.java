/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.hook.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Timothy Bell
 * @author Calvin Keum
 */
@Component(
	immediate = true,
	property = {
		"after-filter=SPA Filter", "dispatcher=FORWARD", "dispatcher=REQUEST",
		"servlet-context-name=", "servlet-filter-name=Loop Login Filter",
		"url-pattern=/*"
	},
	service = Filter.class
)
public class LoopLoginFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		String currentURL = (String)request.getAttribute(WebKeys.CURRENT_URL);

		if (currentURL.startsWith("/c") || currentURL.startsWith("/o")) {
			return false;
		}

		String ppid = request.getParameter("p_p_id");
		String state = request.getParameter("p_p_state");

		if ((Objects.equals(ppid, PortletKeys.FAST_LOGIN) ||
			 Objects.equals(ppid, PortletKeys.LOGIN)) &&
			Objects.equals(state, LiferayWindowState.MAXIMIZED.toString())) {

			return false;
		}

		return true;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		User currentUser = _portal.getUser(request);

		if (currentUser == null) {
			String currentURL = _portal.getCurrentURL(request);

			response.sendRedirect(
				_http.addParameter("/c/portal/login", "redirect", currentURL));

			return;
		}

		filterChain.doFilter(request, response);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoopLoginFilter.class);

	@Reference
	private Http _http;

	@Reference
	private Portal _portal;

}