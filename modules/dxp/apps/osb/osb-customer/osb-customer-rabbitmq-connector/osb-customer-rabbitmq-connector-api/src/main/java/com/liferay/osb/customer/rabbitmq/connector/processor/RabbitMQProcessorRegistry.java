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

import java.util.Map;

/**
 * @author Amos Fong
 */
public interface RabbitMQProcessorRegistry {

	public void activateRabbitMQProcessor(String rabbitMQProcessorId)
		throws Exception;

	public void deactivateRabbitMQProcessor(String rabbitMQProcessorId)
		throws Exception;

	public Map<String, RabbitMQProcessor> getRabbitMQProcessors();

}