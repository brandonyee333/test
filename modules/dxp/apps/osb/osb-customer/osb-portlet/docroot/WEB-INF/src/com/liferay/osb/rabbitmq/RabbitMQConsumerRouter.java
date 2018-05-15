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

package com.liferay.osb.rabbitmq;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class RabbitMQConsumerRouter implements RabbitMQConsumer {

	public RabbitMQConsumerRouter() {
		_rabbitMQConsumersMap.put(
			"dossiera.provisioning.create",
			new ProvisioningCreateRabbitMQConsumer());
		_rabbitMQConsumersMap.put(
			"dossiera.provisioning.update",
			new ProvisioningUpdateRabbitMQConsumer());
	}

	public int parse(
		String routingKey, String message, Map<String, Object> properties) {

		RabbitMQConsumer rabbitMQConsumer = _rabbitMQConsumersMap.get(
			routingKey);

		if (rabbitMQConsumer != null) {
			return rabbitMQConsumer.parse(routingKey, message, properties);
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug("No consumer found for routing key: " + routingKey);
			}

			return RESPONSE_ACK;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RabbitMQConsumerRouter.class);

	private Map<String, RabbitMQConsumer> _rabbitMQConsumersMap =
		new HashMap<>();

}