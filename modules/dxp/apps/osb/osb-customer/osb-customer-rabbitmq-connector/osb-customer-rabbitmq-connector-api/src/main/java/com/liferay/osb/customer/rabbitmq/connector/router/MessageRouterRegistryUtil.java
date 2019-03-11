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

	private static final ServiceTracker<?, MessageRouterRegistry>
		_serviceTracker = ServiceTrackerFactory.open(
			MessageRouterRegistry.class);

}