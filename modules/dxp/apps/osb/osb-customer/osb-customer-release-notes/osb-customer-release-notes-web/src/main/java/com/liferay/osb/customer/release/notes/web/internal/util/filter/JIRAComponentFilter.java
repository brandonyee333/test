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

package com.liferay.osb.customer.release.notes.web.internal.util.filter;

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