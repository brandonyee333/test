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

package com.liferay.osb.customer.admin.internal.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.servlet.ProtectedServletRequest;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"dispatcher=FORWARD", "dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=Secure JSON Filter", "url-pattern=/api/jsonws/*",
		"url-pattern=/o/account-attachment"
	},
	service = Filter.class
)
public class SecureJSONFilter extends BaseFilter {

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;

		try {
			String requestToken = request.getHeader("OSB_API_Token");

			if (Validator.isNotNull(requestToken) &&
				Validator.isNotNull(_apiToken) &&
				requestToken.equals(_apiToken)) {

				User user = _userLocalService.getUser(_USER_JSON_ID);

				PermissionChecker permissionChecker =
					PermissionCheckerFactoryUtil.create(user);

				PermissionThreadLocal.setPermissionChecker(permissionChecker);

				PrincipalThreadLocal.setName(_USER_JSON_ID);

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

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		_apiToken = String.valueOf(properties.get("api.token"));
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private static final long _USER_JSON_ID = 23463;

	private static final Log _log = LogFactoryUtil.getLog(
		SecureJSONFilter.class);

	private String _apiToken;

	@Reference
	private UserLocalService _userLocalService;

}