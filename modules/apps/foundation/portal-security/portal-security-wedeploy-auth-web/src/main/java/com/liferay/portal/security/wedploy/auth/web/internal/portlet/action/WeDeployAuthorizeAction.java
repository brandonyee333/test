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

package com.liferay.portal.security.wedploy.auth.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.wedploy.auth.web.internal.constants.WeDeployAuthPortletKeys;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Supritha Sundaram
 */
@Component(
	immediate = true, property = {"path=/portal/oauth/authorize"},
	service = StrutsAction.class
)
public class WeDeployAuthorizeAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String clientId = ParamUtil.getString(request, "client_id");
		String redirectURI = ParamUtil.getString(request, "redirect_uri");
		String scope = ParamUtil.getString(request, "scope");

		if (!isSignedIn()) {
			sendLoginRedirect(request, response, themeDisplay.getPlid());
		}
		else {
			PortletURL portletURL = PortletURLFactoryUtil.create(
				request, WeDeployAuthPortletKeys.WEDEPLOY_AUTH,
				themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

			portletURL.setParameter("client_id", clientId);
			portletURL.setParameter("redirect_uri", redirectURI);
			portletURL.setParameter("scope", scope);
			portletURL.setParameter("saveLastPath", "0");
			portletURL.setPortletMode(PortletMode.VIEW);

			String redirect = portletURL.toString();

			response.sendRedirect(redirect);
		}

		return null;
	}

	protected boolean isSignedIn() {
		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if ((permissionChecker == null) || !permissionChecker.isSignedIn()) {
			return false;
		}

		return true;
	}

	protected void sendLoginRedirect(
			HttpServletRequest request, HttpServletResponse response, long plid)
		throws Exception {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, PortletKeys.LOGIN, plid, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcRenderCommandName", "/view");

		response.sendRedirect(portletURL.toString());
	}

}