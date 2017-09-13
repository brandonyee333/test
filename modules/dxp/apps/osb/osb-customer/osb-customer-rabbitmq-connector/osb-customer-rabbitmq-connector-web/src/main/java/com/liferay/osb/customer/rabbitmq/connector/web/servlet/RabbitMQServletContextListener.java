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

package com.liferay.rabbitmq.servlet;

import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.rabbitmq.connection.RabbitMQConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Amos Fong
 */
public class RabbitMQServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		RabbitMQConnectionManager connectionManager =
			RabbitMQConnectionManager.getInstance();

		connectionManager.disconnect();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() {
	}

	@Override
	protected void doPortalInit() throws Exception {
		RabbitMQConnectionManager connectionManager =
			RabbitMQConnectionManager.getInstance();

		connectionManager.connect();
	}

}