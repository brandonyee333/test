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

package com.liferay.watson.hook.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.TicketLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;

import java.util.Objects;

import javax.portlet.WindowState;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brent Krone-Schmidt
 */
@Component(
	immediate = true,
	property = {
		"after-filter=SPA Filter", "dispatcher=FORWARD", "dispatcher=REQUEST",
		"servlet-filter-name=Watson Login Forward Filter", "url-pattern=/*"
	},
	service = Filter.class
)
public class WatsonFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		String currentURL = (String)request.getAttribute("CURRENT_URL");

		if (currentURL.startsWith("/c") || currentURL.startsWith("/o")) {
			return false;
		}

		String ppid = request.getParameter("p_p_id");

		if (Objects.equals(ppid, PortletKeys.FAST_LOGIN) ||
			Objects.equals(ppid, PortletKeys.LOGIN)) {

			String state = request.getParameter("p_p_state");

			if (Objects.equals(state, WindowState.MAXIMIZED.toString())) {
				return false;
			}
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

		User currentUser = PortalUtil.getUser(request);

		if (currentUser == null) {
			String ticketKey = request.getParameter("ticketKey");

			if (Validator.isNotNull(ticketKey)) {
				Ticket ticket = _ticketLocalService.fetchTicket(ticketKey);

				if (ticket != null) {
					User ticketUser = _userLocalService.fetchUser(
						ticket.getClassPK());

					if (ticketUser != null) {
						filterChain.doFilter(request, response);

						return;
					}
				}
			}

			response.sendRedirect("/c/portal/login");

			return;
		}

		filterChain.doFilter(request, response);
	}

	@Reference(unbind = "-")
	protected void setTicketLocalService(
		TicketLocalService ticketLocalService) {

		_ticketLocalService = ticketLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(WatsonFilter.class);

	private TicketLocalService _ticketLocalService;
	private UserLocalService _userLocalService;

}