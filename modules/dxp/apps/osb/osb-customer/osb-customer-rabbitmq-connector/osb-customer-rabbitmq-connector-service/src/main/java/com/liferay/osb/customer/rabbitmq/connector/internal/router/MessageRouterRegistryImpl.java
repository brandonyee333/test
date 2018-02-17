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

package com.liferay.osb.customer.rabbitmq.connector.internal.router;

import com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerManager;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouterRegistry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

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
@Component(immediate = true)
public class MessageRouterRegistryImpl implements MessageRouterRegistry {

	@Override
	public MessageRouter getMessageRouter(String queue) {
		return _messageRoutersMap.get(queue);
	}

	@Override
	public Map<String, MessageRouter> getMessageRoutersMap() {
		return _messageRoutersMap;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unregisterMessageRouter"
	)
	protected synchronized void registerMessageRouter(
			MessageRouter messageRouter, Map<String, Object> properties)
		throws Exception {

		String queue = GetterUtil.getString(properties.get("queue"));

		if (Validator.isNull(queue)) {
			throw new Exception("Queue is empty");
		}

		_consumerManager.addConsumer(queue, messageRouter);

		_messageRoutersMap.put(queue, messageRouter);
	}

	protected synchronized void unregisterMessageRouter(
		MessageRouter messageRouter, Map<String, Object> properties) {

		String queue = GetterUtil.getString(properties.get("queue"));

		if (Validator.isNull(queue)) {
			return;
		}

		_consumerManager.deleteConsumer(queue);

		_messageRoutersMap.remove(queue);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MessageRouterRegistryImpl.class);

	@Reference
	private ConsumerManager _consumerManager;

	private final Map<String, MessageRouter> _messageRoutersMap =
		new ConcurrentHashMap<>();

}