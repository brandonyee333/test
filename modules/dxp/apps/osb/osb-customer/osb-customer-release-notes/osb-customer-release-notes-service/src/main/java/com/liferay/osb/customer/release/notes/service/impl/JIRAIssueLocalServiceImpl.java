/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.release.notes.service.impl;

import com.liferay.osb.customer.release.notes.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.service.base.JIRAIssueLocalServiceBaseImpl;
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