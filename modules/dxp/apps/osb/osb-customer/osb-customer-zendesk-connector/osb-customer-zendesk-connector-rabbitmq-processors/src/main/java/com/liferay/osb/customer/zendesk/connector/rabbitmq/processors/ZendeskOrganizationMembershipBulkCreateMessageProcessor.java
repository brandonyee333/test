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
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.rabbitmq.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "routing.key=zendesk.service.organization.membership.bulk.create",
	service = ZendeskOrganizationMembershipBulkCreateMessageProcessor.class
)
public class ZendeskOrganizationMembershipBulkCreateMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		long zendeskUserId = 0;

		JSONObject userJSONObject = jsonObject.getJSONObject("userObject");

		if (userJSONObject != null) {
			JSONObject userResponseJSONObject = _zendeskBaseWebService.post(
				ZendeskRESTEndpoints.URL_API_V2 +
					ZendeskRESTEndpoints.USERS_CREATE_OR_UPDATE,
				userJSONObject.toString());

			handleResponseErrors(userResponseJSONObject);

			userJSONObject = userResponseJSONObject.getJSONObject("user");

			zendeskUserId = userJSONObject.getLong("id");

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.user.create.or.update", userResponseJSONObject);
		}
		else {
			zendeskUserId = jsonObject.getLong("user_id");
		}

		JSONArray organizationsJSONArray = jsonObject.getJSONArray(
			"organizationsArray");

		JSONObject organizationMembershipJSONObject =
			getOrganizationMembershipJSONObject(
				organizationsJSONArray, zendeskUserId);

		JSONObject responseJSONObject = _zendeskBaseWebService.post(
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.ORGANIZATION_MEMBERSHIPS_CREATE_MANY,
			organizationMembershipJSONObject.toString());

		handleResponseErrors(responseJSONObject);
	}

	protected JSONObject getOrganizationMembershipJSONObject(
		JSONArray organizations, long zendeskUserId) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < organizations.length(); i++) {
			long organizationId = organizations.getLong(i);

			JSONObject membershipJSONObject =
				JSONFactoryUtil.createJSONObject();

			membershipJSONObject.put("organization_id", organizationId);
			membershipJSONObject.put("user_id", zendeskUserId);

			jsonArray.put(membershipJSONObject);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("organization_memberships", jsonArray);

		return jsonObject;
	}

	@Reference
	private MessagePublisher _messagePublisher;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}