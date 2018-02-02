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

import java.util.Map;

/**
 * @author Amos Fong
 * @author Vishal Reddy
 */
public interface ConsumerManagerLocalService {

	public void activateConsumer(String rabbitMQConsumerKey) throws Exception;

	public void consumeMessage();

	public void consumeMessages(String queue, long messageCount,
		Object rabbitMQConsumer) throws Exception;

	public void deactivateConsumer(String rabbitMQConsumerKey);

	public Map<String, ConsumerBag> getConsumersMap();

	public String registerConsumer(String queue,
		int prefetchCount, Object rabbitMQConsumer)
		throws Exception;

	public void resetChannels() throws Exception;

	public void unregisterConsumer(String rabbitMQConsumerKey);

}