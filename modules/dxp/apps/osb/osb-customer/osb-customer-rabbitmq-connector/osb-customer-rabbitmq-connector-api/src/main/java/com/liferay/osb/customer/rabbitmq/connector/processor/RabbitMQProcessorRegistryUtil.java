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

package com.liferay.osb.customer.rabbitmq.connector.processor;

import com.liferay.osgi.util.ServiceTrackerFactory;

import java.util.Map;

import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Amos Fong
 */
public class RabbitMQProcessorRegistryUtil {

	public static void activateRabbitMQProcessor(String rabbitMQProcessorKey)
		throws Exception {

		RabbitMQProcessorRegistry rabbitMQProcessorRegistry =
			_serviceTracker.getService();

		rabbitMQProcessorRegistry.activateRabbitMQProcessor(
			rabbitMQProcessorKey);
	}

	public static void deactivateRabbitMQProcessor(String rabbitMQProcessorKey)
		throws Exception {

		RabbitMQProcessorRegistry rabbitMQProcessorRegistry =
			_serviceTracker.getService();

		rabbitMQProcessorRegistry.deactivateRabbitMQProcessor(
			rabbitMQProcessorKey);
	}

	public static Map<String, RabbitMQProcessor> getRabbitMQProcessors() {
		RabbitMQProcessorRegistry rabbitMQProcessorRegistry =
			_serviceTracker.getService();

		return rabbitMQProcessorRegistry.getRabbitMQProcessors();
	}

	private static final
		ServiceTracker<?, RabbitMQProcessorRegistry> _serviceTracker =
			ServiceTrackerFactory.open(RabbitMQProcessorRegistry.class);

}