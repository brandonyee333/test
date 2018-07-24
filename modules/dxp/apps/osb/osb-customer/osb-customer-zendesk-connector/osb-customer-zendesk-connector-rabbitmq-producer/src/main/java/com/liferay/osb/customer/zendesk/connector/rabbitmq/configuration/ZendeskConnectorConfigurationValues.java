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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Kyle Bischof
 */
public class ZendeskConnectorConfigurationValues {

	public static final String RABBITMQ_LOGIN_PASSWORD = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("rabbitmq.login.password"));

	public static final String RABBITMQ_LOGIN_USERNAME = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("rabbitmq.login.username"));

	public static final String RABBITMQ_MESSAGE_EXCHANGE_NAME =
		GetterUtil.getString(
			ZendeskConnectorConfigurationUtil.get(
				"rabbitmq.message.exchange.name"));

	public static final String RABBITMQ_SERVER_HOST = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("rabbitmq.server.host"));

	public static final String RABBITMQ_SERVER_PORT = GetterUtil.getString(
		ZendeskConnectorConfigurationUtil.get("rabbitmq.server.port"));

}