/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.petra.string.StringBundler;

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
		throws Exception {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "organizations/",
			String.valueOf(zendeskOrganizationId), ".json");

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "delete", null, null, "zendesk.organization.delete");

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

}