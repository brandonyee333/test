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

	public int countByL_K(java.lang.String label,
		java.lang.String jiraProjectKey);

	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> findByIsRelatedToJIRAIssue(
		long jiraIssueId);

	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> findByIsRelatedToJIRAIssue(
		long jiraIssueId, int start, int end);

	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> findByJIRAProjectVersion(
		long jiraProjectVersionId);

	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> findByJIRAProjectVersion(
		long jiraProjectVersionId, int start, int end);

	public com.liferay.osb.customer.release.notes.jira.model.JIRAIssue findByKey(
		java.lang.String jiraIssueKey);

	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> findByL_K(
		java.lang.String label, java.lang.String jiraProjectKey);

	public java.util.List<com.liferay.osb.customer.release.notes.jira.model.JIRAIssue> findByL_K(
		java.lang.String label, java.lang.String jiraProjectKey, int start,
		int end);
}