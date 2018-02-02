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

package com.liferay.osb.customer.rabbitmq.connector.service;

import com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerBag;
import com.liferay.osgi.util.ServiceTrackerFactory;

import java.util.Map;

import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Amos Fong
 * @author Vishal Reddy
 */
public class ConsumerManagerLocalServiceUtil {

	public static void activateConsumer(String rabbitMQConsumerKey)
		throws Exception {

		getService().activateConsumer(rabbitMQConsumerKey);
	}

	public static void consumeMessage() {
		getService().consumeMessage();
	}

	public static void consumeMessages(
			String queue, long messageCount, Object rabbitMQConsumer)
		throws Exception {

		getService().consumeMessages(queue, messageCount, rabbitMQConsumer);
	}

	public static void deactivateConsumer(String rabbitMQConsumerKey) {
		getService().deactivateConsumer(rabbitMQConsumerKey);
	}

	public static Map<String, ConsumerBag> getConsumersMap() {
		return getService().getConsumersMap();
	}

	public static ConsumerManagerLocalService getService() {
		return _serviceTracker.getService();
	}

	public static String registerConsumer(
			String queue, int prefetchCount, Object rabbitMQConsumer)
		throws Exception {

		return getService().registerConsumer(
			queue, prefetchCount, rabbitMQConsumer);
	}

	public static void resetChannels() throws Exception {
		getService().resetChannels();
	}

	public static void unregisterConsumer(String rabbitMQConsumerKey) {
		getService().unregisterConsumer(rabbitMQConsumerKey);
	}

	private static final ServiceTracker
		<ConsumerManagerLocalService, ConsumerManagerLocalService>
			_serviceTracker = ServiceTrackerFactory.open(
				ConsumerManagerLocalService.class);

}