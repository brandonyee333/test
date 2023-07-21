/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.servlet.filters;

import com.liferay.wsrp.constants.WSRPPortletKeys;
import com.liferay.wsrp.internal.axis.WSRPHTTPSender;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.http.whiteboard.HttpWhiteboardConstants;

/**
 * @author Michael Young
 * @author Peter Fellwock
 */
@Component(
	immediate = true,
	property = {
		HttpWhiteboardConstants.HTTP_WHITEBOARD_CONTEXT_SELECT + "=(osgi.http.whiteboard.context.name=wsrp-service)",
		HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_DISPATCHER + "=" + HttpWhiteboardConstants.DISPATCHER_INCLUDE,
		HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_NAME + "=com.liferay.wsrp.servlet.filters.WSRPFilter",
		HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_PATTERN + "=/" + WSRPPortletKeys.WSRP_ADMIN + "/*",
		HttpWhiteboardConstants.HTTP_WHITEBOARD_FILTER_PATTERN + "=/" + WSRPPortletKeys.WSRP_CONSUMER + "/*"
	},
	service = Filter.class
)
public class WSRPFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;

		HttpSession session = request.getSession();

		_wsrpConsumerManagerFactory.setSession(session);

		WSRPHTTPSender.setCurrentRequest((HttpServletRequest)servletRequest);

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Reference
	private WSRPConsumerManagerFactory _wsrpConsumerManagerFactory;

}