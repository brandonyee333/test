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