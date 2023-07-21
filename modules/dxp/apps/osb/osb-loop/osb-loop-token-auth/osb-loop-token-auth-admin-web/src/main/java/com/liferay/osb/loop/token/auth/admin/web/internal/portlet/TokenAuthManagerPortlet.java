/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.admin.web.internal.portlet;

import com.liferay.osb.loop.token.auth.constants.TokenAuthPortletKeys;
import com.liferay.osb.loop.token.auth.service.TokenAuthEntryService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.system=true",
		"com.liferay.portlet.use-default-template=false",
		"javax.portlet.display-name=Token Auth Manager",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=/manager/view.jsp",
		"javax.portlet.name=" + TokenAuthPortletKeys.TOKEN_AUTH_MANAGER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class TokenAuthManagerPortlet extends MVCPortlet {

	public void deleteTokenAuthEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long tokenAuthEntryId = ParamUtil.getLong(
			actionRequest, "tokenAuthEntryId");

		_tokenAuthEntryService.deleteTokenAuthEntry(tokenAuthEntryId);
	}

	public void generateTokenAuthEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String device = ParamUtil.getString(actionRequest, "device");

		_tokenAuthEntryService.addTokenAuthEntry(device);
	}

	@Reference
	private TokenAuthEntryService _tokenAuthEntryService;

}