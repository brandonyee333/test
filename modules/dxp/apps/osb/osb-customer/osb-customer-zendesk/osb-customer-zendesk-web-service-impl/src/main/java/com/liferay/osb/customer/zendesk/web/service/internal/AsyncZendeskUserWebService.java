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

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = {"async=true", "service.ranking:Integer=1"},
	service = ZendeskUserWebService.class
)
public class AsyncZendeskUserWebService
	extends DefaultZendeskUserWebService implements ZendeskUserWebService {

	@Override
	public ZendeskUser createOrUpdateZendeskUser(
			String externalId, String email, String zendeskLocale, String name,
			String organizationName, Set<String> tags)
		throws Exception {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.USERS_CREATE_OR_UPDATE;

		JSONObject jsonObject = getZendeskUserJSONObject(
			externalId, email, zendeskLocale, name, organizationName, tags);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject,
			"zendesk.user.create.or.update");

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());

		return null;
	}

	@Override
	public void createZendeskUserOrganizationSubscription(
			long zendeskUserId, long zendeskOrganizationId)
		throws Exception {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.ORGANIZATION_SUBSCRIPTIONS;

		JSONObject organizationSubscriptionsJSONObject =
			JSONFactoryUtil.createJSONObject();

		organizationSubscriptionsJSONObject.put(
			"organization_id", zendeskOrganizationId);
		organizationSubscriptionsJSONObject.put("user_id", zendeskUserId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"organization_subscription", organizationSubscriptionsJSONObject);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject, null);

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

	public void deleteZendeskUser(long zendeskUserId) throws Exception {
		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "users/",
			String.valueOf(zendeskUserId), ".json");

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "delete", null, null, "zendesk.user.delete");

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

	@Override
	public void updateZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (String tag : tags) {
			jsonArray.put(tag);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("tags", jsonArray);

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "users/",
			String.valueOf(zendeskUserId), "/tags.json");

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject, null);

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

}