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

package com.liferay.osb.customer.release.notes.jira.service.impl;

import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.jira.service.base.JIRAIssueLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Kong
 */
public class JIRAIssueLocalServiceImpl extends JIRAIssueLocalServiceBaseImpl {

	public List<JIRAIssue> getIsRelatedToJIRAIssues(long jiraIssueId) {
		return jiraIssueFinder.findByIsRelatedToJIRAIssue(jiraIssueId);
	}

	public JIRAIssue getJIRAIssue(String jiraIssueKey) {
		return jiraIssueFinder.findByKey(jiraIssueKey);
	}

	public List<JIRAIssue> getJIRAIssues(String[] jiraIssueKeys)
		throws PortalException {

		List<JIRAIssue> jiraIssues = new ArrayList<>();

		for (String jiraIssueKey : jiraIssueKeys) {
			JIRAIssue jiraIssue = getJIRAIssue(jiraIssueKey);

			if (jiraIssue != null) {
				jiraIssues.add(jiraIssue);
			}
		}

		return jiraIssues;
	}

	public List<JIRAIssue> getJIRALabelJIRAIssues(
		String label, String jiraProjectKey) {

		return jiraIssueFinder.findByL_K(label, jiraProjectKey);
	}

	public List<JIRAIssue> getJIRAProjectVersionJIRAIssues(
		long jiraProjectVersionId) {

		return jiraIssueFinder.findByJIRAProjectVersion(jiraProjectVersionId);
	}

}