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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.wedeploy.auth.exception.NoSuchAppException;
import com.liferay.portal.security.wedeploy.auth.exception.NoSuchTokenException;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthToken;
import com.liferay.portal.security.wedeploy.auth.service.WeDeployAuthTokenLocalService;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Supritha Sundaram
 */
@Component(
	immediate = true, property = {"path=/portal/oauth/access_token"},
	service = StrutsAction.class
)
public class WeDeployAccessTokenAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long userId = themeDisplay.getUserId();

		if (!isSignedIn()) {
			sendLoginRedirect(request, response, themeDisplay.getPlid());
		}
		else {
			String clientId = ParamUtil.getString(request, "client_id");
			String clientSecret = ParamUtil.getString(request, "client_secret");
			String authorizationToken = ParamUtil.getString(request, "code");
			String redirectURI = ParamUtil.getString(request, "redirect_uri");
			String scope = ParamUtil.getString(request, "scope");

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			try {
				WeDeployAuthToken weDeployAuthAccessToken =
					_weDeployAuthTokenLocalService.addAccessWeDeployAuthToken(
						userId, clientId, clientSecret, authorizationToken,
						new ServiceContext());

				jsonObject.put(
					"access_token", weDeployAuthAccessToken.getToken());
			}
			catch (NoSuchAppException nsae) {
				_log.error(nsae);
				jsonObject.put(
					"error_message",
					LanguageUtil.get(
						themeDisplay.getLocale(),
						"client-id-and-client-secret-do-not-match"));
			}
			catch (NoSuchTokenException nste) {
				_log.error(nste);
				jsonObject.put(
					"error_message",
					LanguageUtil.get(
						themeDisplay.getLocale(),
						"request-token-does-not-match"));
			}
			catch (Exception e) {
				_log.error(e);
				jsonObject.put(
					"error_message",
					LanguageUtil.get(
						themeDisplay.getLocale(),
						"there-was-an-error-processing-this-request"));
			}

			response.setContentType(ContentTypes.APPLICATION_JSON);
			response.setHeader(
				HttpHeaders.CACHE_CONTROL,
				HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);

			ServletResponseUtil.write(response, jsonObject.toString());
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

	private static final Log _log = LogFactoryUtil.getLog(
		WeDeployAccessTokenAction.class);

	@Reference
	private WeDeployAuthTokenLocalService _weDeployAuthTokenLocalService;

}