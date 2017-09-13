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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Amos Fong
 */
public class PortletPropsValues {

	public static final boolean RABBITMQ_DEBUG_MODE_ENABLED =
		GetterUtil.getBoolean(
			PortletProps.get(PortletPropsKeys.RABBITMQ_DEBUG_MODE_ENABLED));

	public static final String RABBITMQ_LOGIN_PASSWORD = PortletProps.get(
		PortletPropsKeys.RABBITMQ_LOGIN_PASSWORD);

	public static final String RABBITMQ_LOGIN_USERNAME = PortletProps.get(
		PortletPropsKeys.RABBITMQ_LOGIN_USERNAME);

	public static final String RABBITMQ_SERVER_HOST = PortletProps.get(
		PortletPropsKeys.RABBITMQ_SERVER_HOST);

	public static final int RABBITMQ_SERVER_PORT = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.RABBITMQ_SERVER_PORT));

}