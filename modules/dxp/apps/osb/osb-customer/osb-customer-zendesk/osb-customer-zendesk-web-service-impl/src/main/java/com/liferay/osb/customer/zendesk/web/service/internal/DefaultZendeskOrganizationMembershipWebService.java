/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganizationMembership;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationMembershipWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.petra.string.StringBundler;
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
		throws Exception {

		throw new UnsupportedOperationException();
	}

	public void deleteOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	public List<ZendeskOrganizationMembership> getOrganizationMemberships(
			long zendeskOrganizationId)
		throws PortalException {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "organizations/",
			String.valueOf(zendeskOrganizationId),
			"/organization_memberships.json");

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

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "users/",
			String.valueOf(zendeskUserId), "/organization_memberships.json");

		JSONObject organizationMembershipsJSONObject =
			zendeskBaseWebService.get(endpoint, StringPool.BLANK);

		JSONArray membershipsJSONArray =
			organizationMembershipsJSONObject.getJSONArray(
				"organization_memberships");

		return zendeskConverter.toZendeskOrganizationMemberships(
			membershipsJSONArray);
	}

	@Reference
	protected MessagePublisher messagePublisher;

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

	@Reference
	protected ZendeskConverter zendeskConverter;

}