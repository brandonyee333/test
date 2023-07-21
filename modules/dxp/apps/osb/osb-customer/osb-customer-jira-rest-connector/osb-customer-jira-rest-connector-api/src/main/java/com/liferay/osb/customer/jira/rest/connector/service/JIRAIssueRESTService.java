/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.jira.rest.connector.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Map;
import java.util.Set;

/**
 * @author Noah Sherrill
 */
@ProviderType
public interface JIRAIssueRESTService {

	public JSONObject createJIRAIssue(
			String projectKey, String issueType, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFields, String status)
		throws PortalException;

	public JSONObject getJIRAIssue(String ticketKey) throws PortalException;

	public JSONObject getJIRAIssues(
			String jql, String expand, String fields, int startAt,
			int maxResults)
		throws PortalException;

	public JSONObject updateJIRAIssue(
			String projectKey, String ticketKey, String summary,
			String description, String assigneeName, Set<String> labels,
			Map<String, Object> customFields, String status)
		throws PortalException;

}