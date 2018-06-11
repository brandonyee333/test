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

package com.liferay.osb.customer.release.notes.jira.util.comparator;

import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;

import java.util.Comparator;

/**
 * @author Samuel Kong
 */
public class JIRAIssueComparator implements Comparator<JIRAIssue> {

	@Override
	public int compare(JIRAIssue jiraIssue1, JIRAIssue jiraIssue2) {
		if (jiraIssue1.getType() > jiraIssue2.getType()) {
			return 1;
		}

		if (jiraIssue1.getType() < jiraIssue2.getType()) {
			return -1;
		}

		if (jiraIssue1.getPriority() > jiraIssue2.getPriority()) {
			return 1;
		}

		if (jiraIssue1.getPriority() < jiraIssue2.getPriority()) {
			return -1;
		}

		String key1 = jiraIssue1.getKey();
		String key2 = jiraIssue2.getKey();

		return key1.compareTo(key2);
	}

}