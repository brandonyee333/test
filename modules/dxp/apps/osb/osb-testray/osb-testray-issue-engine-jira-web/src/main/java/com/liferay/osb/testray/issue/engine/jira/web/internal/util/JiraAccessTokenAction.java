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

package com.liferay.osb.testray.issue.engine.jira.web.internal.util;

import com.liferay.osb.testray.issue.engine.jira.web.internal.constants.TestrayIssueEngineJiraStrutsActions;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
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
	property = "path=" + TestrayIssueEngineJiraStrutsActions.ACCESS_TOKEN,
	service = StrutsAction.class
)
public class JiraAccessTokenAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String redirect = ParamUtil.getString(request, "redirect");

		if (Validator.isNull(redirect)) {
			redirect = themeDisplay.getPortalURL();
		}

		_testrayOAuthService.updateOAuthAccessToken(
			request, themeDisplay.getUser(), redirect);

		response.sendRedirect(redirect);

		return null;
	}

	@Reference
	private TestrayOAuthService _testrayOAuthService;

}