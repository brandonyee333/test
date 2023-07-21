/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.issue.engine.jira.web.internal.util;

/**
 * @author Ethan Bustad
 */
public class TestrayIssueEngineJiraWebValues {

	public static final String JIRA_BROWSE_URL =
		TestrayIssueEngineJiraWebUtil.get("osb.testray.jira.url") + "/browse/";

	public static final String JIRA_FIELD_ID_QA_TEST_NAME =
		TestrayIssueEngineJiraWebUtil.get(
			"osb.testray.jira.field.id.qa.test.name");

	public static final String JIRA_FIELD_ID_QA_TEST_SCORE =
		TestrayIssueEngineJiraWebUtil.get(
			"osb.testray.jira.field.id.qa.test.score");

	public static final String JIRA_JQL_URL =
		TestrayIssueEngineJiraWebUtil.get("osb.testray.jira.url") +
			"/issues/?jql=";

	public static final String JIRA_OAUTH_CONSUMER_KEY =
		TestrayIssueEngineJiraWebUtil.get(
			"osb.testray.jira.oauth.consumer.key");

	public static final String JIRA_OAUTH_RSA_PRIVATE_KEY =
		TestrayIssueEngineJiraWebUtil.get(
			"osb.testray.jira.oauth.rsa.private.key");

	public static final String JIRA_OAUTH_SHARED_SECRET =
		TestrayIssueEngineJiraWebUtil.get(
			"osb.testray.jira.oauth.shared.secret");

	public static final String JIRA_OAUTH_URL =
		TestrayIssueEngineJiraWebUtil.get("osb.testray.jira.url") +
			"/plugins/servlet/oauth/";

	public static final String JIRA_REST_API_ISSUE_URL =
		TestrayIssueEngineJiraWebUtil.get("osb.testray.jira.url") +
			"/rest/api/latest/issue/";

	public static final String JIRA_REST_API_LINK_URL =
		TestrayIssueEngineJiraWebUtil.get("osb.testray.jira.url") +
			"/rest/api/latest/issue/";

	public static final String JIRA_REST_API_URL =
		TestrayIssueEngineJiraWebUtil.get("osb.testray.jira.url") +
			"/rest/api/latest/";

	public static final String JIRA_URL = TestrayIssueEngineJiraWebUtil.get(
		"osb.testray.jira.url");

}