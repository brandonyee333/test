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

package com.liferay.osb.hook.auth;

import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceAction;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionMapping;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionsManagerUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.security.auth.AuthToken;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 * @author Joan Kim
 */
public class OSBAuthToken implements AuthToken {

	public OSBAuthToken(AuthToken authToken) {
		_authToken = authToken;
	}

	@Override
	public void addCSRFToken(
		HttpServletRequest request, LiferayPortletURL liferayPortletURL) {

		// TODO Auto-generated method stub

	}

	@Override
	public void addPortletInvocationToken(
		HttpServletRequest request, LiferayPortletURL liferayPortletURL) {

		// TODO Auto-generated method stub

	}

	@Override
	public void check(HttpServletRequest request) throws PortalException {
		if (isIgnoreAction(request)) {
			return;
		}

		_authToken.check(request);
	}

	@Override
	public void checkCSRFToken(HttpServletRequest request, String origin)
		throws PrincipalException {

		// TODO Auto-generated method stub

	}

	public AuthToken getAuthToken() {
		return _authToken;
	}

	@Override
	public String getToken(HttpServletRequest request) {
		return _authToken.getToken(request);
	}

	@Override
	public String getToken(
		HttpServletRequest request, long plid, String portletId) {

		return _authToken.getToken(request, plid, portletId);
	}

	@Override
	public boolean isValidPortletInvocationToken(
		HttpServletRequest request, Layout layout, Portlet portlet) {

		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean isValidPortletInvocationToken(
		HttpServletRequest request, long plid, String portletId,
		String strutsAction, String tokenValue) {

		// TODO Auto-generated method stub

		return false;
	}

	protected boolean isIgnoreAction(HttpServletRequest request) {

		// JSON web service

		String servletPath = request.getServletPath();

		if (servletPath.equals(_JSON_WEB_SERVICE_SERVLET_PATH)) {
			try {
				JSONWebServiceAction jsonWebServiceAction =
					JSONWebServiceActionsManagerUtil.getJSONWebServiceAction(
						request);

				JSONWebServiceActionMapping jsonWebServiceActionMapping =
					jsonWebServiceAction.getJSONWebServiceActionMapping();

				String actionMethodName = StringPool.BLANK;

				if (jsonWebServiceActionMapping != null) {
					Method actionMethod =
						jsonWebServiceActionMapping.getActionMethod();

					actionMethodName = actionMethod.getName();
				}

				/* TODO Need to test JSON Web Service without the PropsKeys,
				 * see LPS-28844
				String[] jsonWebServicePublicMethods = PropsUtil.getArray(
					PropsKeys.JSONWS_WEB_SERVICE_PUBLIC_METHODS);

				if (ArrayUtil.contains(
						jsonWebServicePublicMethods, actionMethodName)) {

					return true;
				}

				**/
			}
			catch (Exception e) {
			}
		}

		// MVC portlet

		String ppid = ParamUtil.getString(request, "p_p_id");

		if (ppid.equals(OSBPortletKeys.OSB_LICENSE)) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			if (themeDisplay.getScopeGroupId() ==
					OSBConstants.GROUP_LICENSE_ID) {

				return true;
			}
		}

		return false;
	}

	private static final String _JSON_WEB_SERVICE_SERVLET_PATH = "/api/jsonws";

	private final AuthToken _authToken;

}