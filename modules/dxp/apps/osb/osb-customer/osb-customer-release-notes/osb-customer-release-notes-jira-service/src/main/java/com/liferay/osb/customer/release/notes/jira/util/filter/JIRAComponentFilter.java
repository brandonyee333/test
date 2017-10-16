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

package com.liferay.osb.customer.release.notes.jira.util.filter;

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Samuel Kong
 */
public abstract class JIRAComponentFilter {

	public Map<JIRAComponent, Set<JIRAIssue>> processFilter(
		Map<JIRAComponent, Set<JIRAIssue>> jiraComponentMap,
		JIRAProjectVersion jiraProjectVersion) {

		if (!isEnabled(jiraProjectVersion)) {
			return jiraComponentMap;
		}

		Set<JIRAComponent> jiraComponentSet = jiraComponentMap.keySet();

		Iterator<JIRAComponent> itr = jiraComponentSet.iterator();

		while (itr.hasNext()) {
			JIRAComponent jiraComponent = itr.next();

			if (isIgnore(jiraComponent)) {
				itr.remove();
			}

			rename(jiraComponent);
		}

		return jiraComponentMap;
	}

	protected abstract boolean isEnabled(JIRAProjectVersion jiraProjectVersion);

	protected abstract boolean isIgnore(JIRAComponent jiraComponent);

	protected abstract void rename(JIRAComponent jiraComponent);

}