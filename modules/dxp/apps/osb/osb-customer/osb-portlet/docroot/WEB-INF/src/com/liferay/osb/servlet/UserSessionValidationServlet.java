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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jeremy Fu
 */
public class UserSessionValidationServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		long userId = PortalUtil.getUserId(request);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			if (userId > 0) {
				jsonObject.put("pAuthToken", AuthTokenUtil.getToken(request));

				User user = UserLocalServiceUtil.getUser(userId);

				jsonObject.put("sessionValid", !user.isDefaultUser());
			}
			else {
				jsonObject.put("sessionValid", false);
			}

			ServletOutputStream servletOutputStream =
				response.getOutputStream();

			String jsonObjectString = jsonObject.toString();

			servletOutputStream.write(
				jsonObjectString.getBytes(StringPool.UTF8));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserSessionValidationServlet.class);

}