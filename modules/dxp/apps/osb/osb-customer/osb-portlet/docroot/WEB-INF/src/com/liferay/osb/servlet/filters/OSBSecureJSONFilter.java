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

package com.liferay.osb.servlet.filters;

import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ProtectedServletRequest;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Brent Krone-Schmidt
 */
public class OSBSecureJSONFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;

		try {
			String requestToken = request.getHeader("OSB_API_Token");

			String apiToken = PortletProps.get(_apiTokenPropertyKey);

			if (Validator.isNotNull(requestToken) &&
				Validator.isNotNull(apiToken) &&
				requestToken.equals(apiToken)) {

				User user = UserLocalServiceUtil.getUser(
					OSBConstants.USER_JSON_USER_ID);

				PermissionChecker permissionChecker =
					PermissionCheckerFactoryUtil.create(user);

				PermissionThreadLocal.setPermissionChecker(permissionChecker);

				PrincipalThreadLocal.setName(OSBConstants.USER_JSON_USER_ID);

				request = new ProtectedServletRequest(
					request, String.valueOf(user.getUserId()),
					HttpServletRequest.DIGEST_AUTH);

				filterChain.doFilter(request, servletResponse);

				return;
			}
		}
		catch (Exception e) {
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) {
		_apiTokenPropertyKey = filterConfig.getInitParameter("token_property");
	}

	private String _apiTokenPropertyKey;

}