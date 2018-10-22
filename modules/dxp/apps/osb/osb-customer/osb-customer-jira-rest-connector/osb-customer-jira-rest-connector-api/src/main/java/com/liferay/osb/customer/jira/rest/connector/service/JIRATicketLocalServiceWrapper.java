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
 * Provides a wrapper for {@link JIRATicketLocalService}.
 *
 * @author Noah Sherrill
 * @see JIRATicketLocalService
 * @generated
 */
@ProviderType
public class JIRATicketLocalServiceWrapper implements JIRATicketLocalService,
	ServiceWrapper<JIRATicketLocalService> {
	public JIRATicketLocalServiceWrapper(
		JIRATicketLocalService jiraTicketLocalService) {
		_jiraTicketLocalService = jiraTicketLocalService;
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject createJIRATicket(
		String projectKey, String issueType, String summary,
		String description, String assigneeName, java.util.Set<String> labels,
		java.util.Map<String, Object> customFields, String status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraTicketLocalService.createJIRATicket(projectKey, issueType,
			summary, description, assigneeName, labels, customFields, status);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getJIRATicket(
		String ticketKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraTicketLocalService.getJIRATicket(ticketKey);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject getJIRATickets(String jql)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraTicketLocalService.getJIRATickets(jql);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _jiraTicketLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.json.JSONObject updateJIRATicket(
		String projectKey, String ticketKey, String summary,
		String description, String assigneeName, java.util.Set<String> labels,
		java.util.Map<String, Object> customFields, String status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _jiraTicketLocalService.updateJIRATicket(projectKey, ticketKey,
			summary, description, assigneeName, labels, customFields, status);
	}

	@Override
	public JIRATicketLocalService getWrappedService() {
		return _jiraTicketLocalService;
	}

	@Override
	public void setWrappedService(JIRATicketLocalService jiraTicketLocalService) {
		_jiraTicketLocalService = jiraTicketLocalService;
	}

	private JIRATicketLocalService _jiraTicketLocalService;
}