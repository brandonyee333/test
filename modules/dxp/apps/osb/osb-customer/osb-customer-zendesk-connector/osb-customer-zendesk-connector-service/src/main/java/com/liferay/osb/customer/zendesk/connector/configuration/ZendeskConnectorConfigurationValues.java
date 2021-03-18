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