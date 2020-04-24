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

import com.liferay.osb.testray.issue.engine.jira.web.internal.constants.TestrayIssueEngineJiraPortletKeys;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.scribe.builder.api.Api;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @author Ethan Bustad
 */
@Component(immediate = true, service = TestrayOAuthService.class)
public class TestrayOAuthService {

	public Token getOAuthAccessToken(User user) {
		PortletPreferences portletPreferences =
			_fetchOrAddUserPortletPreferences(user);

		return new Token(
			portletPreferences.getValue("jiraOAuthAccessToken", null),
			portletPreferences.getValue("jiraOAuthAccessSecret", null));
	}

	public String getOAuthAuthorizationURL(User user, String callbackURL)
		throws Exception {

		OAuthService oAuthService = _getOAuthService(callbackURL);

		Token requestToken = oAuthService.getRequestToken();

		PortletPreferences portletPreferences =
			_fetchOrAddUserPortletPreferences(user);

		portletPreferences.setValue(
			"jiraOAuthRequestSecret", requestToken.getSecret());
		portletPreferences.setValue(
			"jiraOAuthRequestToken", requestToken.getToken());

		portletPreferences.store();

		return oAuthService.getAuthorizationUrl(requestToken);
	}

	public boolean hasAccessToken(User user) {
		PortletPreferences portletPreferences =
			_portletPreferencesLocalService.fetchPreferences(
				user.getCompanyId(), user.getUserId(),
				PortletKeys.PREFS_OWNER_TYPE_USER, 0L,
				TestrayIssueEngineJiraPortletKeys.TESTRAY_ISSUE_ENGINE_JIRA);

		if (portletPreferences == null) {
			return false;
		}

		String jiraOAuthAccessToken = portletPreferences.getValue(
			"jiraOAuthAccessToken", null);
		String jiraOAuthAccessSecret = portletPreferences.getValue(
			"jiraOAuthAccessSecret", null);

		if ((jiraOAuthAccessToken == null) || (jiraOAuthAccessSecret == null)) {
			return false;
		}

		return true;
	}

	public void updateOAuthAccessToken(
			HttpServletRequest request, User user, String callbackURL)
		throws Exception {

		OAuthService oAuthService = _getOAuthService(callbackURL);

		PortletPreferences portletPreferences =
			_fetchOrAddUserPortletPreferences(user);

		Token requestToken = new Token(
			portletPreferences.getValue("jiraOAuthRequestToken", null),
			portletPreferences.getValue("jiraOAuthRequestSecret", null));

		String oAuthVerifier = request.getParameter(OAuthConstants.VERIFIER);

		Token accessToken = oAuthService.getAccessToken(
			requestToken, new Verifier(oAuthVerifier));

		portletPreferences.setValue(
			"jiraOAuthAccessSecret", accessToken.getSecret());
		portletPreferences.setValue(
			"jiraOAuthAccessToken", accessToken.getToken());

		portletPreferences.setValue("jiraOAuthRequestSecret", StringPool.BLANK);
		portletPreferences.setValue("jiraOAuthRequestToken", StringPool.BLANK);

		portletPreferences.store();
	}

	private PortletPreferences _fetchOrAddUserPortletPreferences(User user) {
		PortletPreferences portletPreferences =
			_portletPreferencesLocalService.fetchPreferences(
				user.getCompanyId(), user.getUserId(),
				PortletKeys.PREFS_OWNER_TYPE_USER, 0L,
				TestrayIssueEngineJiraPortletKeys.TESTRAY_ISSUE_ENGINE_JIRA);

		if (portletPreferences == null) {
			_portletPreferencesLocalService.addPortletPreferences(
				user.getCompanyId(), user.getUserId(),
				PortletKeys.PREFS_OWNER_TYPE_USER, 0,
				TestrayIssueEngineJiraPortletKeys.TESTRAY_ISSUE_ENGINE_JIRA,
				null, null);

			portletPreferences =
				_portletPreferencesLocalService.fetchPreferences(
					user.getCompanyId(), user.getUserId(),
					PortletKeys.PREFS_OWNER_TYPE_USER, 0,
					TestrayIssueEngineJiraPortletKeys.
						TESTRAY_ISSUE_ENGINE_JIRA);
		}

		return portletPreferences;
	}

	private OAuthService _getOAuthService(String callbackURL) {
		Api jiraApiHelper = new JiraApiHelper(_http);

		OAuthConfig oAuthConfig = new OAuthConfig(
			TestrayIssueEngineJiraWebValues.JIRA_OAUTH_CONSUMER_KEY,
			TestrayIssueEngineJiraWebValues.JIRA_OAUTH_SHARED_SECRET,
			callbackURL, SignatureType.Header, null, null);

		return jiraApiHelper.createService(oAuthConfig);
	}

	@Reference
	private Http _http;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}