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

package com.liferay.osb.customer.rabbitmq.connector.router;

import com.liferay.osgi.util.ServiceTrackerFactory;

import java.util.Map;

import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Amos Fong
 */
public class MessageRouterRegistryUtil {

	public static MessageRouter getMessageRouter(String queue) {
		MessageRouterRegistry messageRouterRegistry =
			_serviceTracker.getService();

		return messageRouterRegistry.getMessageRouter(queue);
	}

	public static Map<String, MessageRouter> getMessageRoutersMap() {
		MessageRouterRegistry messageRouterRegistry =
			_serviceTracker.getService();

		return messageRouterRegistry.getMessageRoutersMap();
	}

	private static final
		ServiceTracker<?, MessageRouterRegistry> _serviceTracker =
			ServiceTrackerFactory.open(MessageRouterRegistry.class);

}