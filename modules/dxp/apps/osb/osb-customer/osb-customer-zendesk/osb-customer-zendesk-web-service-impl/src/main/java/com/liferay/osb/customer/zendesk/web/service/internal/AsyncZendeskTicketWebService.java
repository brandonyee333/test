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
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.util.MessagePublisherUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "async=true",
	service = ZendeskTicketWebService.class
)
public class AsyncZendeskTicketWebService
	extends DefaultZendeskTicketWebService implements ZendeskTicketWebService {

	@Override
	public void updateZendeskTickets(List<ZendeskTicket> zendeskTickets)
		throws PortalException {

		JSONArray ticketsJSONArray = JSONFactoryUtil.createJSONArray();

		for (ZendeskTicket zendeskTicket : zendeskTickets) {
			JSONObject ticketJSONObject = JSONFactoryUtil.createJSONObject();

			ticketJSONObject.put("id", zendeskTicket.getZendeskTicketId());
			ticketJSONObject.put(
				"requester_id", zendeskTicket.getRequesterId());

			ticketsJSONArray.put(ticketJSONObject);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("tickets", ticketsJSONArray);

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.TICKETS_UPDATE_MANY;

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "put", null, jsonObject, null);

		_messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Reference
	private MessagePublisherUtil _messagePublisherUtil;

}