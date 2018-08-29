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
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "routing.key=zendesk.service.organization.membership.bulk.delete",
	service = ZendeskOrganizationMembershipBulkDeleteMessageProcessor.class
)
public class ZendeskOrganizationMembershipBulkDeleteMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		long userId = jsonObject.getLong("user_id");

		String getEndpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + userId +
				"/organization_memberships.json";

		JSONObject getResponseJSONObject = _zendeskBaseWebService.get(
			getEndpoint, StringPool.BLANK);

		handleResponseErrors(getResponseJSONObject);

		JSONArray membershipIds = JSONFactoryUtil.createJSONArray();

		JSONArray organizationIdsJSONArray = jsonObject.getJSONArray(
			"organization_ids");

		JSONArray membershipsJSONArray = getResponseJSONObject.getJSONArray(
			"organization_memberships");

		for (int i = 0; i < membershipsJSONArray.length(); i++) {
			JSONObject membershipJSONObject =
				membershipsJSONArray.getJSONObject(i);

			String organizationId = membershipJSONObject.getString(
				"organization_id");

			String organizationIdsString = organizationIdsJSONArray.toString();

			if (organizationIdsString.contains(organizationId)) {
				membershipIds.put(membershipJSONObject.get("id"));
			}
		}

		String ids = membershipIds.toString();

		Map<String, String> parameters = new HashMap<>();

		parameters.put("ids", ids.substring(1, ids.length() - 1));

		JSONObject responseJSONObject = _zendeskBaseWebService.delete(
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.ORGANIZATION_MEMBERSHIPS_DESTROY_MANY,
			parameters);

		handleResponseErrors(responseJSONObject);
	}

	@Reference
	private MessagePublisher _messagePublisher;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}