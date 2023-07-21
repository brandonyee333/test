/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.admin.web.internal.portlet;

import com.liferay.osb.loop.token.auth.constants.ActionKeys;
import com.liferay.osb.loop.token.auth.constants.TokenAuthPortletKeys;
import com.liferay.osb.loop.token.auth.service.TokenAuthEntryService;
import com.liferay.osb.loop.token.auth.service.permission.TokenAuthPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.layout-cacheable=true",
		"javax.portlet.display-name=Token Auth Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.name=" + TokenAuthPortletKeys.TOKEN_AUTH_ADMIN,
		"javax.portlet.portlet.info.keywords=Token Auth Admin",
		"javax.portlet.portlet.info.short-title=Token Auth Admin",
		"javax.portlet.portlet.info.title=Token Auth Admin",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = {Portlet.class, TokenAuthAdminPortlet.class}
)
public class TokenAuthAdminPortlet extends MVCPortlet {

	public void deleteTokenAuthEntry(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		long tokenAuthEntryId = ParamUtil.getLong(
			actionRequest, "tokenAuthEntryId");

		try {
			_tokenAuthEntryService.deleteTokenAuthEntry(tokenAuthEntryId);

			SessionMessages.add(actionRequest, "tokenAuthEntryDeleted");
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass());
		}
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (TokenAuthPermission.contains(
				themeDisplay.getPermissionChecker(),
				ActionKeys.MANAGE_TOKENS)) {

			return true;
		}

		return false;
	}

	@Reference
	private TokenAuthEntryService _tokenAuthEntryService;

}