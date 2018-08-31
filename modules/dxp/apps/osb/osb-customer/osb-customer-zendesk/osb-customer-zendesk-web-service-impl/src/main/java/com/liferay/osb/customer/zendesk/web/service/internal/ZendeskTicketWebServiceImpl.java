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
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskTicketWebService.class)
public class ZendeskTicketWebServiceImpl implements ZendeskTicketWebService {

	public ZendeskTicket getZendeskTicket(long zendeskTicketId)
		throws PortalException {

		JSONObject responseJSONObject = _zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "tickets/" + zendeskTicketId +
				".json",
			StringPool.BLANK);

		return _translate(responseJSONObject.getJSONObject("ticket"));
	}

	private ZendeskTicket _translate(JSONObject jsonObject) {
		ZendeskTicket zendeskTicket = new ZendeskTicket();

		zendeskTicket.setDescription(jsonObject.getString("description"));
		zendeskTicket.setZendeskTicketId(jsonObject.getLong("id"));
		zendeskTicket.setZendeskOrganizationId(
			jsonObject.getLong("organiztion_id"));
		zendeskTicket.setSubject(jsonObject.getString("subject"));

		return zendeskTicket;
	}

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}