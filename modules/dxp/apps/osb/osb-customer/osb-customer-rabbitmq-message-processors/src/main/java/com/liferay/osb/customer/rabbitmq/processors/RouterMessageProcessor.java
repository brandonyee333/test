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

package com.liferay.osb.customer.rabbitmq.processors;

import com.liferay.osb.customer.rabbitmq.configuration.RabbitMQConfiguration;
import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.osb.customer.rabbitmq.parsers.MessageParser;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Amos Fong
 */
@Component(immediate = true)
public class RouterMessageProcessor implements MessageProcessor {

	public int getPrefetchCount() {
		return 1;
	}

	public String getQueue() {
		return _configuration.queue();
	}

	public int process(
		String routingKey, String message, Map<String, Object> properties) {

		MessageParser messageParser = _messageParsersMap.get(routingKey);

		if (messageParser != null) {
			messageParser.parse(message);
		}
		else if (_log.isDebugEnabled()) {
			_log.debug("No parser found for routing key: " + routingKey);
		}

		return RESPONSE_ACK;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = ConfigurableUtil.createConfigurable(
			RabbitMQConfiguration.class, properties);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unregisterMessageParser"
	)
	protected void registerMessageParser(
		MessageParser messageParser, Map<String, Object> properties) {

		Object value = properties.get("routing.key");

		String[] routingKeys = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		for (String routingKey : routingKeys) {
			_messageParsersMap.put(routingKey, messageParser);
		}
	}

	protected void unregisterMessageParser(
		MessageParser messageParser, Map<String, Object> properties) {

		Object value = properties.get("routing.key");

		String[] routingKeys = GetterUtil.getStringValues(
			value, new String[] {String.valueOf(value)});

		for (String routingKey : routingKeys) {
			_messageParsersMap.remove(routingKey);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RouterMessageProcessor.class);

	private volatile RabbitMQConfiguration _configuration;
	private final Map<String, MessageParser> _messageParsersMap =
		new HashMap<>();

}