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

package com.liferay.osb.customer.rabbitmq.connector.internal.router;

import com.liferay.osb.customer.rabbitmq.connector.consumer.ConsumerManager;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouter;
import com.liferay.osb.customer.rabbitmq.connector.router.MessageRouterRegistry;
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
@Component(immediate = true, service = MessageRouterRegistry.class)
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
		policyOption = ReferencePolicyOption.GREEDY
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

	@Reference
	private ConsumerManager _consumerManager;

	private final Map<String, MessageRouter> _messageRoutersMap =
		new ConcurrentHashMap<>();

}