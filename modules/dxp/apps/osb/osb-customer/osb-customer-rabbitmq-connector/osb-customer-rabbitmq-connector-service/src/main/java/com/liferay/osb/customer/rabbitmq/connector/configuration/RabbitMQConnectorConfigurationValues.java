/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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