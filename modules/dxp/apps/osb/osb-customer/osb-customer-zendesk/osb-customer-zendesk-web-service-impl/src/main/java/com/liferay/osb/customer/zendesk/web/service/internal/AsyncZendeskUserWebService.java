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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "async=true",
	service = ZendeskUserWebService.class
)
public class AsyncZendeskUserWebService
	extends DefaultZendeskUserWebService implements ZendeskUserWebService {

	@Override
	public void addZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/tags.json";

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (String tag : tags) {
			jsonArray.put(tag);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("tags", jsonArray);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "put", null, jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Override
	public ZendeskUser createOrUpdateZendeskUser(
			String externalId, String email, String locale, String name,
			String organizationName, Set<String> tags)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.USERS_CREATE_OR_UPDATE;

		JSONObject jsonObject = getZendeskUserJSONObject(
			externalId, email, locale, name, organizationName, tags);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject,
			"zendesk.user.create.or.update");

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);

		return null;
	}

	@Override
	public void createZendeskUserIdentity(
			long zendeskUserId, String type, String value)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				ZendeskRESTEndpoints.IDENTITIES;

		JSONObject identityJSONObject = JSONFactoryUtil.createJSONObject();

		identityJSONObject.put("type", type);
		identityJSONObject.put("value", value);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("identity", identityJSONObject);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject, "zendesk.user.identity.create");

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Override
	public void createZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.ORGANIZATION_MEMBERSHIPS_CREATE_MANY;

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (long organizationId : zendeskOrganizationIds) {
			JSONObject membershipJSONObject =
				JSONFactoryUtil.createJSONObject();

			membershipJSONObject.put("organization_id", organizationId);
			membershipJSONObject.put("user_id", zendeskUserId);

			jsonArray.put(membershipJSONObject);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("organization_memberships", jsonArray);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Override
	public void deleteZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String type)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/identities/" + zendeskUserIdentityId + ".json";

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "delete", null, null, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Override
	public void deleteZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.ORGANIZATION_MEMBERSHIPS_DESTROY_MANY;

		Map<Long, Long> organizationMemberships = getOrganizationMemberships(
			zendeskUserId);

		StringBundler sb = new StringBundler(
			(organizationMemberships.size() * 2) - 1);

		for (Map.Entry<Long, Long> entry : organizationMemberships.entrySet()) {
			if (!ArrayUtil.contains(zendeskOrganizationIds, entry.getKey())) {
				continue;
			}

			sb.append(entry.getValue());

			if (sb.index() < sb.capacity()) {
				sb.append(StringPool.COMMA);
			}
		}

		Map<String, String> parameters = new HashMap<>();

		parameters.put("ids", sb.toString());

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "delete", parameters, null, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Override
	public void deleteZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (String tag : tags) {
			jsonArray.put(tag);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("tags", jsonArray);

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/tags.json";

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "delete", null, jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Override
	public void updateZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String value)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/identities/" + zendeskUserIdentityId + ".json";

		JSONObject identityJSONObject = JSONFactoryUtil.createJSONObject();

		identityJSONObject.put("value", value);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("identity", identityJSONObject);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "put", null, jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

}