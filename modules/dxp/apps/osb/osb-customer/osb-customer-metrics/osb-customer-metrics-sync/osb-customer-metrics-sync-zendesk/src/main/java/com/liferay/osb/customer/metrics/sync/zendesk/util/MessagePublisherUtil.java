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

package com.liferay.osb.customer.metrics.sync.zendesk.util;

import com.liferay.osb.customer.metrics.sync.zendesk.configuration.ZendeskSyncConfigurationValues;
import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MessagePublisherUtil.class)
public class MessagePublisherUtil {

	public void sendMessage(ZendeskRequest zendeskRequest)
		throws PortalException {

		try {
			_messagePublisher.sendMessage(
				ZendeskSyncConfigurationValues.
					ZENDESK_SERVICE_RABBITMQ_EXCHANGE_NAME,
				"zendesk.service", zendeskRequest.toJSONObject());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Reference
	private MessagePublisher _messagePublisher;

}