/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganizationMembership;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
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
		throws Exception {

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

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

	@Override
	public void deleteOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws Exception {

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

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

}