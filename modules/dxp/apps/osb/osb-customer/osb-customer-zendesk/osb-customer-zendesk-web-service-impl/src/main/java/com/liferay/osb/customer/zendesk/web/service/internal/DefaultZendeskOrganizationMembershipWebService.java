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
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganizationMembership;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.MessagePublisherUtil;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = ZendeskOrganizationMembershipWebService.class
)
public class DefaultZendeskOrganizationMembershipWebService
	implements ZendeskOrganizationMembershipWebService {

	public void createOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public void deleteOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public List<ZendeskOrganizationMembership> getOrganizationMemberships(
			long zendeskOrganizationId)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "organizations/" +
				zendeskOrganizationId + "/organization_memberships.json";

		JSONObject organizationMembershipsJSONObject =
			zendeskBaseWebService.get(endpoint, StringPool.BLANK);

		JSONArray membershipsJSONArray =
			organizationMembershipsJSONObject.getJSONArray(
				"organization_memberships");

		return zendeskConverter.toZendeskOrganizationMemberships(
			membershipsJSONArray);
	}

	public List<ZendeskOrganizationMembership>
			getZendeskUserOrganizationMemberships(long zendeskUserId)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/organization_memberships.json";

		JSONObject organizationMembershipsJSONObject =
			zendeskBaseWebService.get(endpoint, StringPool.BLANK);

		JSONArray membershipsJSONArray =
			organizationMembershipsJSONObject.getJSONArray(
				"organization_memberships");

		return zendeskConverter.toZendeskOrganizationMemberships(
			membershipsJSONArray);
	}

	@Reference
	protected MessagePublisherUtil messagePublisherUtil;

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

	@Reference
	protected ZendeskConverter zendeskConverter;

}