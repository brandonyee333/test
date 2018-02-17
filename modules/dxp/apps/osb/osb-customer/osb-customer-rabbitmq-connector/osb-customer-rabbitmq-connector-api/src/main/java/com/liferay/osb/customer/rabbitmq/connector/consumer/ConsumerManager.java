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

package com.liferay.osb.customer.rabbitmq.connector.consumer;

import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;

/**
 * @author Amos Fong
 * @author Vishal Reddy
 */
public interface ConsumerManager {

	public void addConsumer(String queue, MessageRouter messageRouter)
		throws Exception;

	public void consumeMessage();

	public void consumeMessages(
			String queue, long messageCount, MessageRouter messageRouter)
		throws Exception;

	public void deleteConsumer(String queue);

	public Consumer getConsumer(String queue);

	public void resetChannels() throws Exception;

}