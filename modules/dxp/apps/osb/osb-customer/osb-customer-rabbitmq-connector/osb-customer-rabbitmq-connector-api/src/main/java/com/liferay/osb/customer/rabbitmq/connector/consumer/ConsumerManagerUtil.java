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

package com.liferay.osb.customer.rabbitmq.connector.consumer;

import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Amos Fong
 * @author Vishal Reddy
 */
public class ConsumerManagerUtil {

	public static void addConsumer(String queue, MessageRouter messageRouter)
		throws Exception {

		getService().addConsumer(queue, messageRouter);
	}

	public static void consumeMessage() {
		getService().consumeMessage();
	}

	public static void consumeMessages(
			String queue, long messageCount, MessageRouter messageRouter)
		throws Exception {

		getService().consumeMessages(queue, messageCount, messageRouter);
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