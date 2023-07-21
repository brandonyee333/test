/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.message.subscriber;

import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=zendesk.service",
	service = ZendeskWebServiceMessageSubscriber.class
)
public class ZendeskWebServiceMessageSubscriber extends BaseMessageSubscriber {

	protected void doReceive(JSONObject jsonObject) throws Exception {
		ZendeskRequest zendeskRequest = ZendeskRequest.getInstance(jsonObject);

		JSONObject responseJSONObject = _zendeskBaseWebService.send(
			zendeskRequest);

		handleResponseErrors(responseJSONObject);

		if (zendeskRequest.hasResponseRoutingKey()) {
			_messagePublisher.publish(
				zendeskRequest.getResponseRoutingKey(),
				new Message(responseJSONObject.toString()));
		}
	}

	@Reference
	private MessagePublisher _messagePublisher;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}