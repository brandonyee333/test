/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.connector.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class ZendeskConnectorConfigurationValues {

	public static final String ZENDESK_API_ERROR_EMAIL_ADDRESS =
		GetterUtil.getString(
			ZendeskConnectorConfigurationUtil.get(
				"zendesk.api.error.email.address"));

	public static final long ZENDESK_API_LIMIT_RETRY_WAIT_TIME =
		GetterUtil.getLong(
			ZendeskConnectorConfigurationUtil.get(
				"zendesk.api.limit.retry.wait.time"));

	public static final int ZENDESK_API_RETRY_ATTEMPTS = GetterUtil.getInteger(
		ZendeskConnectorConfigurationUtil.get("zendesk.api.retry.attempts"));

	public static final long ZENDESK_API_RETRY_WAIT_TIME = GetterUtil.getLong(
		ZendeskConnectorConfigurationUtil.get("zendesk.api.retry.wait.time"));

	public static final String ZENDESK_API_TOKEN = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("zendesk.api.token"));

	public static final String ZENDESK_DOMAIN_NAME = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("zendesk.domain.name"));

	public static final String ZENDESK_EMAIL_ADDRESS = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("zendesk.email.address"));

}