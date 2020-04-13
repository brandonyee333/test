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

package com.liferay.osb.community.extensions.internal.servlet.filter;

import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.permission.PortletPermissionUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	property = {
		"before-filter=SPA Filter", "dispatcher=FORWARD", "dispatcher=REQUEST",
		"servlet-context-name=",
		"servlet-filter-name=OSB Community Control Panel Filter",
		"url-pattern=/group/control_panel/*"
	},
	service = Filter.class
)
public class ControlPanelFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		long userId = _portal.getUserId(request);

		User user = _userLocalService.getUser(userId);

		PermissionChecker permissionChecker =
			PermissionCheckerFactoryUtil.create(user);

		String portletId = ParamUtil.getString(request, "p_p_id");

		if (!PortletPermissionUtil.contains(
				permissionChecker, user.getGroupId(), 0, portletId,
				ActionKeys.ACCESS_IN_CONTROL_PANEL, true)) {

			_portal.sendError(
				HttpServletResponse.SC_NOT_FOUND, new NoSuchGroupException(),
				request, response);

			return;
		}

		super.processFilter(request, response, filterChain);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ControlPanelFilter.class);

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}