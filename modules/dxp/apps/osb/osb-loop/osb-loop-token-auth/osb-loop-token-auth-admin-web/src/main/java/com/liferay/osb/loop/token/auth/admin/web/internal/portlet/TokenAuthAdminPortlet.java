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