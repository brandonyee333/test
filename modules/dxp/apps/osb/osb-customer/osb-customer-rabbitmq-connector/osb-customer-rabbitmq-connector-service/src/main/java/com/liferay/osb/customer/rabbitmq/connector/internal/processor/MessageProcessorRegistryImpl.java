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
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.util.Collection;
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

	public void activateMessageProcessor(String messageProcessorKey)
		throws Exception {

		MessageProcessor messageProcessor = _messageProcessors.get(
			messageProcessorKey);

		_consumerManager.addConsumer(messageProcessor);
	}

	public void deactivateMessageProcessor(String messageProcessorKey)
		throws Exception {

		MessageProcessor messageProcessor = _messageProcessors.get(
			messageProcessorKey);

		_consumerManager.deleteConsumer(messageProcessor);
	}

	public Map<String, MessageProcessor> getMessageProcessors() {
		return _messageProcessors;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unregisterMessageProcessor"
	)
	protected void registerMessageProcessor(MessageProcessor messageProcessor)
		throws Exception {

		_consumerManager.addConsumer(messageProcessor);

		_messageProcessors.put(PortalUUIDUtil.generate(), messageProcessor);
	}

	protected void unregisterMessageProcessor(
		MessageProcessor messageProcessor) {

		Collection<MessageProcessor> messageProcessors =
			_messageProcessors.values();

		if (!messageProcessors.remove(messageProcessor)) {
			if (_log.isInfoEnabled()) {
				Class<?> clazz = messageProcessor.getClass();

				_log.info("No message processor exists for " + clazz.getName());
			}

			return;
		}

		_consumerManager.deleteConsumer(messageProcessor);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageProcessorRegistryImpl.class);

	@Reference
	private ConsumerManager _consumerManager;

	private final Map<String, MessageProcessor> _messageProcessors =
		new ConcurrentHashMap<>();

}