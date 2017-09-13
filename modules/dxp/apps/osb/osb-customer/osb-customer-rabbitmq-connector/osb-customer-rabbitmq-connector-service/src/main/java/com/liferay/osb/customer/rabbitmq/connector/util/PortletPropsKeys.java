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

package com.liferay.rabbitmq.util;

/**
 * @author Amos Fong
 */
public interface PortletPropsKeys {

	public static final String RABBITMQ_DEBUG_MODE_ENABLED =
		"rabbitmq.debug.mode.enabled";

	public static final String RABBITMQ_LOGIN_PASSWORD =
		"rabbitmq.login.password";

	public static final String RABBITMQ_LOGIN_USERNAME =
		"rabbitmq.login.username";

	public static final String RABBITMQ_SERVER_HOST = "rabbitmq.server.host";

	public static final String RABBITMQ_SERVER_PORT = "rabbitmq.server.port";

}