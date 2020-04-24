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

package com.liferay.osb.testray.issue.engine.jira.web.util;

import com.liferay.osb.testray.issue.engine.jira.web.internal.util.JiraRequestSender;
import com.liferay.osb.testray.issue.engine.jira.web.internal.util.TestrayIssueEngineJiraWebValues;
import com.liferay.osb.testray.issue.engine.jira.web.internal.util.TestrayOAuthService;
import com.liferay.osb.testray.issue.engine.util.TestrayIssueEngine;
import com.liferay.osb.testray.model.TestrayCase;
import com.liferay.osb.testray.model.TestrayCaseResult;
import com.liferay.osb.testray.model.TestrayIssue;
import com.liferay.osb.testray.service.TestrayCaseLocalService;
import com.liferay.osb.testray.service.TestrayCaseResultLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionResponse;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.scribe.model.Token;
import org.scribe.model.Verb;

/**
 * @author Ethan Bustad
 */
@Component(immediate = true, service = TestrayIssueEngine.class)
public class JiraIssueEngine implements TestrayIssueEngine {

	@Override
	public void addComment(String comment, String issueName, User user)
		throws Exception {

		if (!_testrayOAuthService.hasAccessToken(user)) {
			_log.error("No JIRA access token for user " + user.getFullName());

			return;
		}

		Token accessToken = _testrayOAuthService.getOAuthAccessToken(user);

		JSONObject payloadJSONObject = _jsonFactory.createJSONObject();

		payloadJSONObject.put("body", comment);

		_jiraRequestSender.addJiraRequest(
			TestrayIssueEngineJiraWebValues.JIRA_REST_API_LINK_URL + issueName +
				"/comment",
			payloadJSONObject.toString(), user.getFullName(), accessToken,
			Verb.POST);

		_jiraRequestSender.nonblockingSendJiraRequests();
	}

	@Override
	public void addLink(
			String linkURL, String linkTitle, String issueName, User user)
		throws Exception {

		if (!_testrayOAuthService.hasAccessToken(user)) {
			_log.error("No JIRA access token for user " + user.getFullName());

			return;
		}

		Token accessToken = _testrayOAuthService.getOAuthAccessToken(user);

		JSONObject payloadJSONObject = _jsonFactory.createJSONObject();

		JSONObject object = _jsonFactory.createJSONObject();

		object.put("title", linkTitle);
		object.put("url", linkURL);

		payloadJSONObject.put("object", object);

		_jiraRequestSender.addJiraRequest(
			TestrayIssueEngineJiraWebValues.JIRA_REST_API_LINK_URL + issueName +
				"/remotelink",
			payloadJSONObject.toString(), user.getFullName(), accessToken,
			Verb.POST);

		_jiraRequestSender.nonblockingSendJiraRequests();
	}

	@Override
	public void authorize(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String authorizeActionURL =
			themeDisplay.getPortalURL() + "/c/testray/jira/authorize";

		authorizeActionURL = _http.setParameter(
			authorizeActionURL, "redirect", themeDisplay.getURLCurrent());

		PortletResponse portletResponse = (PortletResponse)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_RESPONSE);

		if (!(portletResponse instanceof ActionResponse)) {
			String message = LanguageUtil.format(
				request,
				"unable-to-automatically-authorize-jira.-please-access-x-to-" +
					"continue",
				"/c/testray/jira/authorize");

			throw new IOException(message);
		}

		ActionResponse actionResponse = (ActionResponse)portletResponse;

		actionResponse.sendRedirect(authorizeActionURL);
	}

	@Override
	public ExternalLinkHelper getExternalLinkHelper() {
		return _jiraExternalLinkHelper;
	}

	@Override
	public Map<String, String> getIssueFields(
			String issueName, User user, String... fields)
		throws IOException {

		if (ArrayUtil.isEmpty(fields)) {
			return Collections.emptyMap();
		}

		Map<String, String> issueFieldsMap = new HashMap<>();

		JSONObject fieldsJSONObject = null;

		StringBundler sb = new StringBundler(fields.length * 2 + 3);

		sb.append(TestrayIssueEngineJiraWebValues.JIRA_REST_API_ISSUE_URL);
		sb.append(issueName);

		sb.append("?fields=");

		for (String field : fields) {
			sb.append(field);
			sb.append(StringPool.COMMA);
		}

		sb.setIndex(sb.index() - 1);

		String response = _jiraRequestSender.getJiraResponse(
			sb.toString(), null, _testrayOAuthService.getOAuthAccessToken(user),
			Verb.GET);

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(response);

			fieldsJSONObject = jsonObject.getJSONObject("fields");
		}
		catch (JSONException jsone) {
			throw new IOException("Response is not valid JSON", jsone);
		}

		for (String field : fields) {
			issueFieldsMap.put(field, _getFieldValue(fieldsJSONObject, field));
		}

		return issueFieldsMap;
	}

	@Override
	public boolean isAuthorized(User user) {
		return _testrayOAuthService.hasAccessToken(user);
	}

	@Override
	public void updateIssues(Collection<TestrayIssue> testrayIssues, User user)
		throws Exception {

		if (!_testrayOAuthService.hasAccessToken(user)) {
			_log.error("No JIRA access token for user " + user.getFullName());

			return;
		}

		Token accessToken = _testrayOAuthService.getOAuthAccessToken(user);

		for (TestrayIssue testrayIssue : testrayIssues) {
			JSONObject payloadJSONObject = _jsonFactory.createJSONObject();

			JSONObject fieldsJSONObject = _jsonFactory.createJSONObject();

			int priorityTotal = 0;
			Set<String> testrayCaseNames = new HashSet<>();

			List<TestrayCaseResult> testrayCaseResults =
				_testrayCaseResultLocalService.
					getTestrayIssueTestrayCaseResults(
						testrayIssue.getTestrayIssueId());

			for (TestrayCaseResult testrayCaseResult : testrayCaseResults) {
				TestrayCase testrayCase =
					_testrayCaseLocalService.getTestrayCase(
						testrayCaseResult.getTestrayCaseId());

				String testrayCaseName = StringUtil.replace(
					testrayCase.getName(), CharPool.SPACE, CharPool.UNDERLINE);

				if (testrayCaseNames.contains(testrayCaseName)) {
					continue;
				}

				priorityTotal += testrayCase.getPriority();

				testrayCaseNames.add(testrayCaseName);
			}

			JSONArray testrayCaseNamesJSONArray = _jsonFactory.createJSONArray(
				_jsonFactory.looseSerialize(testrayCaseNames));

			fieldsJSONObject.put(
				TestrayIssueEngineJiraWebValues.JIRA_FIELD_ID_QA_TEST_NAME,
				testrayCaseNamesJSONArray);

			fieldsJSONObject.put(
				TestrayIssueEngineJiraWebValues.JIRA_FIELD_ID_QA_TEST_SCORE,
				priorityTotal);

			payloadJSONObject.put("fields", fieldsJSONObject);

			_jiraRequestSender.addJiraRequest(
				TestrayIssueEngineJiraWebValues.JIRA_REST_API_ISSUE_URL +
					testrayIssue.getName(),
				payloadJSONObject.toString(), user.getFullName(), accessToken,
				Verb.PUT);

			_jiraRequestSender.nonblockingSendJiraRequests();
		}
	}

	public static class JiraExternalLinkHelper implements ExternalLinkHelper {

		public String getAllIssuesLinkLabel(String[] issueNames) {
			return "view-in-jira";
		}

		public String getAllIssuesLinkURL(String[] issueNames) {
			StringBundler sb = new StringBundler((issueNames.length * 2) + 2);

			sb.append(TestrayIssueEngineJiraWebValues.JIRA_JQL_URL);
			sb.append("key%20in(");

			for (String issueName : issueNames) {
				sb.append(issueName);
				sb.append(StringPool.COMMA);
			}

			sb.setStringAt(StringPool.CLOSE_PARENTHESIS, sb.index());

			return sb.toString();
		}

		public String getIssueLinkLabel(String issueName) {
			return issueName;
		}

		public String getIssueLinkURL(String issueName) {
			return TestrayIssueEngineJiraWebValues.JIRA_BROWSE_URL + issueName;
		}

	}

	private String _getFieldValue(JSONObject jsonObject, String field) {
		JSONObject fieldValueJSONObject = jsonObject.getJSONObject(field);

		if (fieldValueJSONObject != null) {
			return fieldValueJSONObject.getString("name");
		}

		JSONArray fieldValueJSONArray = jsonObject.getJSONArray(field);

		if (fieldValueJSONArray != null) {
			String[] fieldValues = new String[fieldValueJSONArray.length()];

			for (int i = 0; i < fieldValueJSONArray.length(); i++) {
				fieldValueJSONObject = fieldValueJSONArray.getJSONObject(i);

				if (fieldValueJSONObject != null) {
					fieldValues[i] = fieldValueJSONObject.getString("name");
				}
				else {
					fieldValues[i] = fieldValueJSONArray.getString(i);
				}
			}

			return StringUtil.merge(fieldValues, StringPool.COMMA_AND_SPACE);
		}

		return jsonObject.getString(field);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JiraIssueEngine.class);

	@Reference
	private Http _http;

	private final ExternalLinkHelper _jiraExternalLinkHelper =
		new JiraExternalLinkHelper();

	@Reference
	private JiraRequestSender _jiraRequestSender;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private TestrayCaseLocalService _testrayCaseLocalService;

	@Reference
	private TestrayCaseResultLocalService _testrayCaseResultLocalService;

	@Reference
	private TestrayOAuthService _testrayOAuthService;

}