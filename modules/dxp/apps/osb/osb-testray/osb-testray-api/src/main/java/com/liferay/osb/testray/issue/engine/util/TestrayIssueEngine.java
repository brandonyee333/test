/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.issue.engine.util;

import com.liferay.osb.testray.model.TestrayIssue;
import com.liferay.portal.kernel.model.User;

import java.io.IOException;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ethan Bustad
 */
public interface TestrayIssueEngine {

	public void addLink(
			String linkURL, String linkTitle, String issueName, User user)
		throws Exception;

	public void authorize(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException;

	public ExternalLinkHelper getExternalLinkHelper();

	public Map<String, String> getIssueFields(
			String issueName, User user, String... fields)
		throws IOException;

	public boolean isAuthorized(User user);

	public void updateIssues(Collection<TestrayIssue> testrayIssues, User user)
		throws Exception;

	public interface ExternalLinkHelper {

		public String getAllIssuesLinkLabel(String[] issueNames);

		public String getAllIssuesLinkURL(String[] issueNames);

		public String getIssueLinkLabel(String issueName);

		public String getIssueLinkURL(String issueName);

	}

}