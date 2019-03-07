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

package com.liferay.osb.customer.jira.rest.connector.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JIRAIssueLocalService}.
 *
 * @author Noah Sherrill
 * @see JIRAIssueLocalService
 * @generated
 */
@ProviderType
public class JIRAIssueLocalServiceWrapper
	implements JIRAIssueLocalService, ServiceWrapper<JIRAIssueLocalService> {

	public JIRAIssueLocalServiceWrapper(
		JIRAIssueLocalService jiraIssueLocalService) {

		_jiraIssueLocalService = jiraIssueLocalService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createJIRAIssue(
			String projectKey, String issueType, String summary,
			String description, String assigneeName,
			java.util.Set<String> labels,
			java.util.Map<String, Object> customFields, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraIssueLocalService.createJIRAIssue(
			projectKey, issueType, summary, description, assigneeName, labels,
			customFields, status);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getJIRAIssue(
			String ticketKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraIssueLocalService.getJIRAIssue(ticketKey);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getJIRAIssues(String jql)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraIssueLocalService.getJIRAIssues(jql);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _jiraIssueLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateJIRAIssue(
			String projectKey, String ticketKey, String summary,
			String description, String assigneeName,
			java.util.Set<String> labels,
			java.util.Map<String, Object> customFields, String status)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _jiraIssueLocalService.updateJIRAIssue(
			projectKey, ticketKey, summary, description, assigneeName, labels,
			customFields, status);
	}

	@Override
	public JIRAIssueLocalService getWrappedService() {
		return _jiraIssueLocalService;
	}

	@Override
	public void setWrappedService(JIRAIssueLocalService jiraIssueLocalService) {
		_jiraIssueLocalService = jiraIssueLocalService;
	}

	private JIRAIssueLocalService _jiraIssueLocalService;

}