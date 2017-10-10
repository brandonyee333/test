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

package com.liferay.osb.customer.release.notes.remote.util.comparator;

import com.liferay.osb.customer.release.notes.remote.model.JIRAIssue;

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