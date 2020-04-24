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