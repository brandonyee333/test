/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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

	public static final String JIRA_EMAIL_ADDRESS = GetterUtil.getString(
		JIRARESTConnectorConfigurationUtil.get("jira.email.address"));

}