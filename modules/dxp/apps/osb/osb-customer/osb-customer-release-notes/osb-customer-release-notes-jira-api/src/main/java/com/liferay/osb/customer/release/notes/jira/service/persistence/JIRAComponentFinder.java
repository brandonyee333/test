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

package com.liferay.osb.customer.release.notes.jira.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface JIRAComponentFinder {

	public int countByJIRAIssue(long jiraIssueId);

	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAComponent>
			findByJIRAIssue(long jiraIssueId);

	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAComponent>
			findByJIRAIssue(long jiraIssueId, int start, int end);

}