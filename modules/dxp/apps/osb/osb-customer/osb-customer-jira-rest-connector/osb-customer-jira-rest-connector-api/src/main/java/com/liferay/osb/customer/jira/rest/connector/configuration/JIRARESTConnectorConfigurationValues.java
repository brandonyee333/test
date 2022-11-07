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

package com.liferay.osb.customer.jira.rest.connector.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Amos Fong
 */
public class JIRARESTConnectorConfigurationValues {

	public static final String JIRA_API_TOKEN = GetterUtil.getString(
		JIRARESTConnectorConfigurationUtil.get("jira.api.token"));

	public static final String JIRA_DOMAIN_NAME = GetterUtil.getString(
		JIRARESTConnectorConfigurationUtil.get("jira.domain.name"));

	public static final String JIRA_USER_NAME = GetterUtil.getString(
		JIRARESTConnectorConfigurationUtil.get("jira.user.name"));

}