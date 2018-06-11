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

package com.liferay.osb.customer.rabbitmq.connector.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Andrew Madrazo
 */
public class RabbitMQConnectorConfigurationValues {

	public static final boolean RABBITMQ_DEBUG_MODE_ENABLED =
		GetterUtil.getBoolean(
			RabbitMQConnectorConfigurationUtil.get(
				"rabbitmq.debug.mode.enabled"));

	public static final String RABBITMQ_LOGIN_PASSWORD = GetterUtil.getString(
		RabbitMQConnectorConfigurationUtil.get("rabbitmq.login.password"));

	public static final String RABBITMQ_LOGIN_USERNAME = GetterUtil.getString(
		RabbitMQConnectorConfigurationUtil.get("rabbitmq.login.username"));

	public static final String RABBITMQ_SERVER_HOST = GetterUtil.getString(
		RabbitMQConnectorConfigurationUtil.get("rabbitmq.server.host"));

	public static final int RABBITMQ_SERVER_PORT = GetterUtil.getInteger(
		RabbitMQConnectorConfigurationUtil.get("rabbitmq.server.port"));

}