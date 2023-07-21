/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.issue.engine.jira.web.internal.util;

import com.liferay.osb.testray.issue.engine.jira.web.internal.constants.TestrayIssueEngineJiraStrutsActions;
import com.liferay.portal.kernel.portlet.PortletURLFactory;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = "path=" + TestrayIssueEngineJiraStrutsActions.AUTHORIZE,
	service = StrutsAction.class
)
public class JiraAuthorizeAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String accessTokenActionURL =
			themeDisplay.getPortalURL() + "/c" +
				TestrayIssueEngineJiraStrutsActions.ACCESS_TOKEN;

		String redirect = ParamUtil.getString(request, "redirect");

		accessTokenActionURL = _http.setParameter(
			accessTokenActionURL, "redirect", redirect);

		String oAuthAuthorizationURL =
			_testrayOAuthService.getOAuthAuthorizationURL(
				themeDisplay.getUser(), accessTokenActionURL);

		response.sendRedirect(oAuthAuthorizationURL);

		return null;
	}

	@Reference
	private Http _http;

	@Reference
	private PortletURLFactory _portletURLFactory;

	@Reference
	private TestrayOAuthService _testrayOAuthService;

}