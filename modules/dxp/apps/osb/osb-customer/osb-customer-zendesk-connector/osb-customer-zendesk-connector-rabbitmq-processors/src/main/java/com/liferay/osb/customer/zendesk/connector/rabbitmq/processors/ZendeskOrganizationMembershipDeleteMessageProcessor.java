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
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "routing.key=zendesk.service.organization.membership.delete",
	service = ZendeskOrganizationMembershipDeleteMessageProcessor.class
)
public class ZendeskOrganizationMembershipDeleteMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		long organizationId = jsonObject.getLong("organization_id");

		String getEndpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "organizations/" +
				organizationId + "/organization_memberships.json";

		JSONObject getResponseJSONObject = _zendeskBaseWebService.get(
			getEndpoint, StringPool.BLANK);

		handleResponseErrors(getResponseJSONObject);

		long id = 0;

		JSONArray jsonArray = getResponseJSONObject.getJSONArray(
			"organization_memberships");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject membershipJSONObject = jsonArray.getJSONObject(i);

			if (membershipJSONObject.getLong("organization_id") ==
					organizationId) {

				id = membershipJSONObject.getLong("id");

				break;
			}
		}

		String deleteEndpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "organization_memberships/" + id +
				".json";

		JSONObject deleteResponseJSONObject = _zendeskBaseWebService.delete(
			deleteEndpoint, StringPool.BLANK);

		handleResponseErrors(deleteResponseJSONObject);
	}

	@Reference
	private MessagePublisher _messagePublisher;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}