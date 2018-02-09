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

package com.liferay.osb.customer.rabbitmq.connector.internal.processor;

import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessorRegistry;
import com.liferay.osb.customer.rabbitmq.connector.service.ConsumerManager;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = MessageProcessorRegistry.class)
public class MessageProcessorRegistryImpl implements MessageProcessorRegistry {

	@Override
	public List<MessageProcessor> getMessageProcessors(
		String queue, String routingKey) {

		String cacheKey = queue + StringPool.POUND + routingKey;

		List<MessageProcessor> messageProcessors =
			_messageProcessorsFinderCache.get(cacheKey);

		if (messageProcessors != null) {
			return messageProcessors;
		}

		messageProcessors = new ArrayList<>();

		List<MessageProcessor> queueMessageProcessors =
			_messageProcessorsMap.get(queue);

		for (MessageProcessor messageProcessor : queueMessageProcessors) {
			String[] routingKeys = messageProcessor.getRoutingKeys();

			for (String curRoutingKey : routingKeys) {
				if (curRoutingKey.equals(routingKey) ||
					curRoutingKey.equals(StringPool.STAR)) {

					messageProcessors.add(messageProcessor);

					break;
				}
			}
		}

		_messageProcessorsFinderCache.put(cacheKey, messageProcessors);

		return messageProcessors;
	}

	@Override
	public Map<String, List<MessageProcessor>> getMessageProcessorsMap() {
		return _messageProcessorsMap;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unregisterMessageProcessor"
	)
	protected synchronized void registerMessageProcessor(
			MessageProcessor messageProcessor)
		throws Exception {

		_consumerManager.addConsumer(messageProcessor.getQueue());

		List<MessageProcessor> messageProcessors = _messageProcessorsMap.get(
			messageProcessor.getQueue());

		if (messageProcessors == null) {
			messageProcessors = new ArrayList<>();

			_messageProcessorsMap.put(
				messageProcessor.getQueue(), messageProcessors);
		}

		messageProcessors.add(messageProcessor);

		_messageProcessorsFinderCache.clear();
	}

	protected synchronized void unregisterMessageProcessor(
		MessageProcessor messageProcessor) {

		List<MessageProcessor> messageProcessors = _messageProcessorsMap.get(
			messageProcessor.getQueue());

		if (messageProcessors != null) {
			messageProcessors.remove(messageProcessor);

			if (messageProcessors.isEmpty()) {
				_messageProcessorsMap.remove(messageProcessor.getQueue());

				_consumerManager.deleteConsumer(messageProcessor.getQueue());
			}
		}

		_messageProcessorsFinderCache.clear();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageProcessorRegistryImpl.class);

	@Reference
	private ConsumerManager _consumerManager;

	private final Map<String, List<MessageProcessor>>
		_messageProcessorsFinderCache = new ConcurrentHashMap<>();
	private final Map<String, List<MessageProcessor>> _messageProcessorsMap =
		new ConcurrentHashMap<>();

}