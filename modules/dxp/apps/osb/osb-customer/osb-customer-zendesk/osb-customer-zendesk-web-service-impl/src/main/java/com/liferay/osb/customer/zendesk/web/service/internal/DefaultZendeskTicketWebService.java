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
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.exception.NoSuchZendeskTicketException;
import com.liferay.osb.customer.zendesk.web.service.internal.search.SearchHitsImpl;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.osb.customer.zendesk.web.service.search.ZendeskTicketQuery;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskTicketWebService.class)
public class DefaultZendeskTicketWebService implements ZendeskTicketWebService {

	public List<ZendeskTicket> getRequesterZendeskTickets(long zendeskUserId)
		throws PortalException {

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				ZendeskRESTEndpoints.TICKETS_REQUESTED,
			StringPool.BLANK);

		List<ZendeskTicket> zendeskTickets = new ArrayList<>();

		JSONArray ticketsJSONArray = responseJSONObject.getJSONArray("tickets");

		for (int i = 0; i < ticketsJSONArray.length(); i++) {
			JSONObject jsonObject = ticketsJSONArray.getJSONObject(i);

			zendeskTickets.add(zendeskConverter.toZendeskTicket(jsonObject));
		}

		return zendeskTickets;
	}

	public ZendeskTicket getZendeskTicket(long zendeskTicketId)
		throws PortalException {

		try {
			JSONObject responseJSONObject = zendeskBaseWebService.get(
				ZendeskRESTEndpoints.URL_API_V2 + "tickets/" + zendeskTicketId +
					".json",
				StringPool.BLANK);

			return zendeskConverter.toZendeskTicket(
				responseJSONObject.getJSONObject("ticket"));
		}
		catch (NoSuchModelException nsme) {
			throw new NoSuchZendeskTicketException(nsme);
		}
	}

	public SearchHits<ZendeskTicket> search(
			ZendeskTicketQuery zendeskTicketQuery)
		throws PortalException {

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "search.json",
			zendeskTicketQuery.getParameters());

		return toSearchHits(responseJSONObject);
	}

	public void updateZendeskTickets(List<ZendeskTicket> zendeskTickets)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	protected SearchHits<ZendeskTicket> toSearchHits(
		JSONObject responseJSONObject) {

		SearchHits<ZendeskTicket> searchHits = new SearchHitsImpl<>();

		searchHits.setCount(responseJSONObject.getInt("count"));

		String nextPageURL = responseJSONObject.getString("next_page");

		if (Validator.isNotNull(nextPageURL)) {
			String page = http.getParameter(nextPageURL, "page", false);

			searchHits.setNextPage(GetterUtil.getInteger(page));
		}

		List<ZendeskTicket> zendeskTickets = new ArrayList<>();

		JSONArray jsonArray = responseJSONObject.getJSONArray("results");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			zendeskTickets.add(zendeskConverter.toZendeskTicket(jsonObject));
		}

		searchHits.setResults(zendeskTickets);

		return searchHits;
	}

	@Reference
	protected Http http;

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

	@Reference
	protected ZendeskConverter zendeskConverter;

}