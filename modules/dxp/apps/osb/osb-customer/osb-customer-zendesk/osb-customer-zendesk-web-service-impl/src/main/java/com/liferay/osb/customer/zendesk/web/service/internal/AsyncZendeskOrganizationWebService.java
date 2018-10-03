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
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.MessagePublisherUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "async=true",
	service = ZendeskOrganizationWebService.class
)
public class AsyncZendeskOrganizationWebService
	extends DefaultZendeskOrganizationWebService
	implements ZendeskOrganizationWebService {

	@Override
	public void createOrUpdateZendeskOrganization(
			String externalId, String name, String partnerFirstLineSupport,
			String partnerOrganization, String sla, String status,
			String supportLanguage, String supportRegion, String tier,
			Set<String> tags)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.ORGANIZATIONS_CREATE_OR_UPDATE;

		JSONObject zendeskOrganizationJSONObject =
			getZendeskOrganizationJSONObject(
				externalId, name, partnerFirstLineSupport, partnerOrganization,
				sla, status, supportLanguage, supportRegion, tier, tags);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, zendeskOrganizationJSONObject,
			"zendesk.organization.create");

		_messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

}