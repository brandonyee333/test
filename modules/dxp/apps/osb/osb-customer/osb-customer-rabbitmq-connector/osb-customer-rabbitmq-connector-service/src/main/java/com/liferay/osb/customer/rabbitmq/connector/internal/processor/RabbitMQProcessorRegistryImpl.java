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

import com.liferay.osb.customer.rabbitmq.connector.processor.RabbitMQProcessor;
import com.liferay.osb.customer.rabbitmq.connector.processor.RabbitMQProcessorRegistry;
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
@Component(immediate = true, service = RabbitMQProcessorRegistry.class)
public class RabbitMQProcessorRegistryImpl
	implements RabbitMQProcessorRegistry {

	public void activateRabbitMQProcessor(String rabbitMQProcessorKey)
		throws Exception {

		RabbitMQProcessor rabbitMQProcessor = _rabbitMQProcessors.get(
			rabbitMQProcessorKey);

		_consumerManager.addConsumer(rabbitMQProcessor);
	}

	public void deactivateRabbitMQProcessor(String rabbitMQProcessorKey)
		throws Exception {

		RabbitMQProcessor rabbitMQProcessor = _rabbitMQProcessors.get(
			rabbitMQProcessorKey);

		_consumerManager.deleteConsumer(rabbitMQProcessor);
	}

	public Map<String, RabbitMQProcessor> getRabbitMQProcessors() {
		return _rabbitMQProcessors;
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unregisterRabbitMQProcessor"
	)
	protected void registerRabbitMQProcessor(
			RabbitMQProcessor rabbitMQProcessor)
		throws Exception {

		_consumerManager.addConsumer(rabbitMQProcessor);

		_rabbitMQProcessors.put(PortalUUIDUtil.generate(), rabbitMQProcessor);
	}

	protected void unregisterRabbitMQProcessor(
		RabbitMQProcessor rabbitMQProcessor) {

		Collection<RabbitMQProcessor> rabbitMQProcessors =
			_rabbitMQProcessors.values();

		if (!rabbitMQProcessors.remove(rabbitMQProcessor)) {
			if (_log.isInfoEnabled()) {
				Class<?> clazz = rabbitMQProcessor.getClass();

				_log.info("No message processor exists for " + clazz.getName());
			}

			return;
		}

		_consumerManager.deleteConsumer(rabbitMQProcessor);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RabbitMQProcessorRegistryImpl.class);

	@Reference
	private ConsumerManager _consumerManager;

	private final Map<String, RabbitMQProcessor> _rabbitMQProcessors =
		new ConcurrentHashMap<>();

}