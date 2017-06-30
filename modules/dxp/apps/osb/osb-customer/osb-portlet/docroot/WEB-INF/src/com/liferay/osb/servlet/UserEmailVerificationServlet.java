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

package com.liferay.osb.servlet;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jeremy Fu
 * @author Joan H. Kim
 */
public class UserEmailVerificationServlet extends HttpServlet {

	@Override
	public void service(
		HttpServletRequest request, HttpServletResponse response) {

		String apiToken = request.getHeader("OSB_Verify_User_API_Token");

		String userUuid = ParamUtil.getString(request, "userUuid");

		if (Validator.isNotNull(userUuid) &&
			!apiToken.equals(PortletPropsValues.VERIFY_USER_API_TOKEN)) {

			if (_log.isDebugEnabled()) {
				_log.debug("API token does not match");
			}

			return;
		}

		try {
			User user = null;

			if (Validator.isNotNull(userUuid)) {
				user = UserLocalServiceUtil.getUserByUuid(userUuid);
			}
			else {
				long userId = PortalUtil.getUserId(request);

				if (userId > 0) {
					user = UserLocalServiceUtil.fetchUser(userId);
				}
			}

			if (user == null) {
				return;
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				request);

			serviceContext.setPathMain(PortalUtil.getPathMain());
			serviceContext.setPortalURL(PortalUtil.getPortalURL(request));

			UserLocalServiceUtil.sendEmailAddressVerification(
				user, user.getEmailAddress(), serviceContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserEmailVerificationServlet.class);

}