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
import com.liferay.osb.customer.zendesk.model.ZendeskOrganizationMembership;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = {"async=true", "service.ranking:Integer=1"},
	service = ZendeskOrganizationMembershipWebService.class
)
public class AsyncZendeskOrganizationMembershipWebService
	extends DefaultZendeskOrganizationMembershipWebService
	implements ZendeskOrganizationMembershipWebService {

	@Override
	public void createOrganizationMemberships(
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
	public void deleteOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.ORGANIZATION_MEMBERSHIPS_DESTROY_MANY;

		List<ZendeskOrganizationMembership> organizationMemberships =
			getZendeskUserOrganizationMemberships(zendeskUserId);

		if (organizationMemberships.isEmpty()) {
			return;
		}

		StringBundler sb = new StringBundler(
			(organizationMemberships.size() * 2) - 1);

		for (ZendeskOrganizationMembership organizationMembership :
				organizationMemberships) {

			if (!ArrayUtil.contains(
					zendeskOrganizationIds,
					organizationMembership.getZendeskOrganizationId())) {

				continue;
			}

			sb.append(
				organizationMembership.getZendeskOrganizationMembershipId());

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

}