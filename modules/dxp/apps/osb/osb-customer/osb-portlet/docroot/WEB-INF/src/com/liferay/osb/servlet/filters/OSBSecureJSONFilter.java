/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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