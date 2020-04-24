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

import java.io.IOException;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ethan Bustad
 */
public interface TestrayIssueEngine {

	public void addComment(String comment, String issueName, User user)
		throws Exception;

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