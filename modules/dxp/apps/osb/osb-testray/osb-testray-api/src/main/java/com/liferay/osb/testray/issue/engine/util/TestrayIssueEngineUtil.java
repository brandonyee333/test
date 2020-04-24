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

package com.liferay.osb.testray.issue.engine.util;

import com.liferay.osb.testray.model.TestrayIssue;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Ethan Bustad
 */
@Component(immediate = true, service = TestrayIssueEngineUtil.class)
public class TestrayIssueEngineUtil {

	public static void addComment(String comment, String issueName, User user)
		throws Exception {

		for (TestrayIssueEngine testrayIssueEngine : _testrayIssueEngines) {
			testrayIssueEngine.addComment(comment, issueName, user);
		}
	}

	public static void addLink(
			String linkURL, String linkTitle, String issueName, User user)
		throws Exception {

		for (TestrayIssueEngine testrayIssueEngine : _testrayIssueEngines) {
			testrayIssueEngine.addLink(linkURL, linkTitle, issueName, user);
		}
	}

	public static void authorize(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		for (TestrayIssueEngine testrayIssueEngine : _testrayIssueEngines) {
			if (!testrayIssueEngine.isAuthorized(themeDisplay.getUser())) {
				testrayIssueEngine.authorize(request, response);

				break;
			}
		}
	}

	public static List<TestrayIssueEngine.ExternalLinkHelper>
		getExternalLinkHelpers() {

		List<TestrayIssueEngine.ExternalLinkHelper> externalLinkHelpers =
			new ArrayList<>();

		for (TestrayIssueEngine testrayIssueEngine : _testrayIssueEngines) {
			externalLinkHelpers.add(testrayIssueEngine.getExternalLinkHelper());
		}

		return externalLinkHelpers;
	}

	public static Map<String, String> getIssueFields(
			String issueName, User user, String... fields)
		throws IOException {

		for (TestrayIssueEngine testrayIssueEngine : _testrayIssueEngines) {
			Map<String, String> issueFieldsMap =
				testrayIssueEngine.getIssueFields(issueName, user, fields);

			if (issueFieldsMap != null) {
				return issueFieldsMap;
			}
		}

		return null;
	}

	public static boolean isAuthorized(User user) {
		for (TestrayIssueEngine testrayIssueEngine : _testrayIssueEngines) {
			if (!testrayIssueEngine.isAuthorized(user)) {
				return false;
			}
		}

		return true;
	}

	public static boolean isTestrayIssueEngineEnabled() {
		if (_testrayIssueEngines.isEmpty()) {
			return false;
		}

		return true;
	}

	public static void updateIssues(
			Collection<TestrayIssue> testrayIssues, User user)
		throws Exception {

		for (TestrayIssueEngine testrayIssueEngine : _testrayIssueEngines) {
			testrayIssueEngine.updateIssues(testrayIssues, user);
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC, service = TestrayIssueEngine.class
	)
	protected void setTestrayIssueEngine(
		TestrayIssueEngine testrayIssueEngine) {

		_testrayIssueEngines.addIfAbsent(testrayIssueEngine);
	}

	protected void unsetTestrayIssueEngine(
		TestrayIssueEngine testrayIssueEngine) {

		_testrayIssueEngines.remove(testrayIssueEngine);
	}

	private static final CopyOnWriteArrayList<TestrayIssueEngine>
		_testrayIssueEngines = new CopyOnWriteArrayList<>();

}