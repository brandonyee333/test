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
public interface JIRAIssueFinder {

	public int countByIsRelatedToJIRAIssue(long jiraIssueId);

	public int countByJIRAProjectVersion(long jiraProjectVersionId);

	public int countByL_K(String label, String jiraProjectKey);

	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
			findByIsRelatedToJIRAIssue(long jiraIssueId);

	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
			findByIsRelatedToJIRAIssue(long jiraIssueId, int start, int end);

	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
			findByJIRAProjectVersion(long jiraProjectVersionId);

	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue>
			findByJIRAProjectVersion(
				long jiraProjectVersionId, int start, int end);

	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue
		findByKey(String jiraIssueKey);

	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> findByL_K(
			String label, String jiraProjectKey);

	public java.util.List
		<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> findByL_K(
			String label, String jiraProjectKey, int start, int end);

}