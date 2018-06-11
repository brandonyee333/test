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

import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class BaseMessageRouter implements MessageRouter {

	@Override
	public void route(
		String routingKey, String message, Map<String, Object> properties) {

		List<MessageProcessor> messageProcessors = new ArrayList<>();

		if (_messageProcessorRoutes.containsKey(StringPool.STAR)) {
			messageProcessors.addAll(
				_messageProcessorRoutes.get(StringPool.STAR));
		}

		if (_messageProcessorRoutes.containsKey(routingKey)) {
			messageProcessors.addAll(_messageProcessorRoutes.get(routingKey));
		}

		for (MessageProcessor messageProcessor : messageProcessors) {
			if (_log.isDebugEnabled()) {
				Class<?> messageProcessorClass = messageProcessor.getClass();

				_log.debug(
					"Routing message to " + messageProcessorClass.getName());
			}

			messageProcessor.process(routingKey, message, properties);
		}

		if (_log.isDebugEnabled() && messageProcessors.isEmpty()) {
			_log.debug("No processor was found with routing key " + routingKey);
		}
	}

	protected void addRoute(
		MessageProcessor messageProcessor, Map<String, Object> properties) {

		String[] routingKeys = getRoutingKeys(properties);

		for (String routingKey : routingKeys) {
			List<MessageProcessor> messageProcessors =
				_messageProcessorRoutes.get(routingKey);

			if (messageProcessors == null) {
				messageProcessors = new ArrayList<>();

				_messageProcessorRoutes.put(routingKey, messageProcessors);
			}

			messageProcessors.add(messageProcessor);
		}
	}

	protected String[] getRoutingKeys(Map<String, Object> properties) {
		Object value = properties.get("routing.key");

		return GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageRouter.class);

	private final Map<String, List<MessageProcessor>> _messageProcessorRoutes =
		new HashMap<>();

}