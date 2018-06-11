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

package com.liferay.osb.customer.web.internal.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.URLCodec;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 */
@Component(
	property = {
		"dispatcher=FORWARD", "dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=Enforce Authentication Filter",
		"url-pattern=/documents/*", "url-pattern=/group/*",
		"url-pattern=/user/*", "url-pattern=/web/*"
	},
	service = Filter.class
)
public class EnforceAuthenticationFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		try {
			User user = _portal.getUser(request);

			if (user != null) {
				return false;
			}

			String uri = request.getRequestURI();

			if (uri.startsWith("/web/guest")) {
				return false;
			}

			String portletId = ParamUtil.getString(request, "p_p_id");

			if (portletId.equals(PortletKeys.LOGIN) &&
				LiferayWindowState.isMaximized(request)) {

				return false;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
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

		response.sendRedirect(
			_portal.getPathMain() + "/portal/login?redirect=" +
				URLCodec.encodeURL(request.getRequestURI()));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EnforceAuthenticationFilter.class);

	@Reference
	private Portal _portal;

}