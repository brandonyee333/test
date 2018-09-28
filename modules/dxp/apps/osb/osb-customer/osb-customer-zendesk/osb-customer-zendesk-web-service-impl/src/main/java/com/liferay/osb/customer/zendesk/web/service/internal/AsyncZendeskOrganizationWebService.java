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

import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.model.ZendeskOrganization;
import com.liferay.osb.customer.zendesk.web.service.ZendeskOrganizationWebService;
import com.liferay.osb.customer.zendesk.web.service.api.ZendeskAPICall;
import com.liferay.osb.customer.zendesk.web.service.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.portal.kernel.exception.PortalException;

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
			String partnerCode, String sla, String status,
			String supportLanguage, String supportRegion, Set<String> tags,
			String tier)
		throws PortalException {

		try {
			ZendeskOrganization zendeskOrganization = getZendeskOrganization(
				externalId, name, partnerFirstLineSupport, partnerCode, sla,
				status, supportLanguage, supportRegion, tags, tier);

			String endpoint =
				ZendeskRESTEndpoints.URL_API_V2 +
					ZendeskRESTEndpoints.ORGANIZATIONS_CREATE_OR_UPDATE;

			ZendeskAPICall zendeskAPICall = new ZendeskAPICall(
				endpoint, "post", zendeskOrganization.toJSONObject(),
				"zendesk.organization.create");

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					ZENDESK_RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service", zendeskAPICall.toJSONObject());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Reference
	private MessagePublisher _messagePublisher;

}