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