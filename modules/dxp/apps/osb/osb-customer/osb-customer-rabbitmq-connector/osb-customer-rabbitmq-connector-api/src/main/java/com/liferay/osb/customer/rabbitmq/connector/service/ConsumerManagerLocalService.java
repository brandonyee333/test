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
import com.liferay.osb.customer.rabbitmq.connector.processor.RabbitMQProcessor;

/**
 * @author Amos Fong
 * @author Vishal Reddy
 */
public interface ConsumerManagerLocalService {

	public void addConsumer(RabbitMQProcessor rabbitMQProcessor)
		throws Exception;

	public void connect();

	public void consumeMessage();

	public void consumeMessages(
			RabbitMQProcessor rabbitMQProcessor, long messageCount)
		throws Exception;

	public void deleteConsumer(RabbitMQProcessor rabbitMQProcessor);

	public void disconnect();

	public Consumer getConsumer(RabbitMQProcessor rabbitMQProcessor);

	public boolean isConnected();

	public void reconnect();

	public void resetChannels() throws Exception;

}