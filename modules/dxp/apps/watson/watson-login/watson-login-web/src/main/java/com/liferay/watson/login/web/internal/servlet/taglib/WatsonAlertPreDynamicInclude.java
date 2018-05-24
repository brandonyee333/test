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

package com.liferay.watson.login.web.internal.servlet.taglib;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.BaseDynamicInclude;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Adds a message when Liferay Portal's Sign In portlet is requested in response
 * to an expired session redirect. The message can indicate that a session
 * expiry occurred, form modifications were not saved, etc.
 *
 * @author Brent Krone-Schmidt
 */
@Component(immediate = true, service = DynamicInclude.class)
public class WatsonAlertPreDynamicInclude extends BaseDynamicInclude {

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		boolean sessionExpired = ParamUtil.getBoolean(
			request, "sessionExpired");

		if (sessionExpired) {
			RequestDispatcher requestDispatcher =
				_servletContext.getRequestDispatcher(_JSP_PATH);

			try {
				requestDispatcher.include(request, response);
			}
			catch (ServletException se) {
				_log.error("Unable to include JSP " + _JSP_PATH, se);

				throw new IOException("Unable to include JSP " + _JSP_PATH, se);
			}
		}
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register(
			"com.liferay.login.web#/login.jsp#alertPre");
	}

	private static final String _JSP_PATH = "/session_expired.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		WatsonAlertPreDynamicInclude.class);

	@Reference(target = "(osgi.web.symbolicname=com.liferay.watson.login.web)")
	private ServletContext _servletContext;

}