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

package com.liferay.osb.customer.metrics.impl.internal.rabbitmq;

import com.liferay.osb.customer.metrics.api.rabbitmq.MessagePublisher;
import com.liferay.osb.customer.metrics.impl.configuration.MetricsConfigurationValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MessagePublisher.class)
public class MessagePublisherImpl implements MessagePublisher {

	public void sendMessage(String routingKey, JSONObject jsonObject)
		throws PortalException {

		try {
			_messagePublisher.sendMessage(
				MetricsConfigurationValues.METRICS_RABBITMQ_EXCHANGE_NAME,
				routingKey, jsonObject);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Reference
	private com.liferay.osb.customer.rabbitmq.connector.publisher.
		MessagePublisher _messagePublisher;

}