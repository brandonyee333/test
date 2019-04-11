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
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = {"async=true", "service.ranking:Integer=1"},
	service = ZendeskOrganizationWebService.class
)
public class AsyncZendeskOrganizationWebService
	extends DefaultZendeskOrganizationWebService
	implements ZendeskOrganizationWebService {

	@Override
	public void deleteZendeskOrganization(long zendeskOrganizationId)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "organizations/" +
				zendeskOrganizationId + ".json";

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "delete", null, null, "zendesk.organization.delete");

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

}