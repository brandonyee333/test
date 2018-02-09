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

import com.liferay.osb.customer.rabbitmq.connector.consumer.Consumer;
import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Amos Fong
 * @author Vishal Reddy
 */
public class ConsumerManagerUtil {

	public static void addConsumer(String queue) throws Exception {
		getService().addConsumer(queue);
	}

	public static void consumeMessage() {
		getService().consumeMessage();
	}

	public static void consumeMessages(
			String queue, long messageCount, MessageProcessor messageProcessor)
		throws Exception {

		getService().consumeMessages(queue, messageCount, messageProcessor);
	}

	public static void deleteConsumer(String queue) {
		getService().deleteConsumer(queue);
	}

	public static Consumer getConsumer(String queue) {
		return getService().getConsumer(queue);
	}

	public static ConsumerManager getService() {
		return _serviceTracker.getService();
	}

	public static void resetChannels() throws Exception {
		getService().resetChannels();
	}

	private static final ServiceTracker<ConsumerManager, ConsumerManager>
		_serviceTracker = ServiceTrackerFactory.open(ConsumerManager.class);

}