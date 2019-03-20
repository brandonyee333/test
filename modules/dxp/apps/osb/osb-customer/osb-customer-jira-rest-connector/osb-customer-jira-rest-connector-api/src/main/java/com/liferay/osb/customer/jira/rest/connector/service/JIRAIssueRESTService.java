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