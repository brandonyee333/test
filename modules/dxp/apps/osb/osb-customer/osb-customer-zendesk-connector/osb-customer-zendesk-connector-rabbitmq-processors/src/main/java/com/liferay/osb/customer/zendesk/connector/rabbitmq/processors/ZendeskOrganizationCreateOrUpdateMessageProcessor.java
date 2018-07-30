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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.processors;

import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskHttpUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "routing.key=zendesk.service.organization.create.or.update",
	service = ZendeskOrganizationCreateOrUpdateMessageProcessor.class
)
public class ZendeskOrganizationCreateOrUpdateMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject responseJSONObject = ZendeskHttpUtil.post(
			_ENDPOINT, jsonObject);

		handleResponseErrors(responseJSONObject);

		_messagePublisher.sendMessage(
			ZendeskConnectorConfigurationValues.RABBITMQ_MESSAGE_EXCHANGE_NAME,
			"zendesk.organization.create", responseJSONObject);
	}

	private static final String _ENDPOINT =
		"organizations/create_or_update.json";

	@Reference
	private MessagePublisher _messagePublisher;

}