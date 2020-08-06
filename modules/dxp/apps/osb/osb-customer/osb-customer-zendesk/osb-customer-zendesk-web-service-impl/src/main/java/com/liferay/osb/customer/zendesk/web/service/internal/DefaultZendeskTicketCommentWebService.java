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
import com.liferay.osb.customer.zendesk.model.ZendeskTicketComment;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketCommentWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.search.SearchHitsImpl;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.QueryFactory;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskTicketCommentWebService.class)
public class DefaultZendeskTicketCommentWebService
	implements ZendeskTicketCommentWebService {

	public ZendeskTicketComment addZendeskTicketComment(
			long zendeskTicketId, long zendeskUserId, String htmlBody)
		throws PortalException {

		JSONObject jsonObject = getZendeskTicketCommentJSONObject(
			zendeskUserId, htmlBody);

		JSONObject responseJSONObject = _zendeskBaseWebService.put(
			StringBundler.concat(
				ZendeskRESTEndpoints.URL_API_V2, "tickets/",
				String.valueOf(zendeskTicketId), ".json"),
			jsonObject.toString());

		JSONObject auditJSONObject = responseJSONObject.getJSONObject("audit");

		JSONArray eventsJSONArray = auditJSONObject.getJSONArray("events");

		for (int i = 0; i < eventsJSONArray.length(); i++) {
			JSONObject eventJSONObject = eventsJSONArray.getJSONObject(i);

			String type = eventJSONObject.getString("type");

			if (type.equals("Comment")) {
				return zendeskConverter.toZendeskTicketComment(eventJSONObject);
			}
		}

		return null;
	}

	public List<ZendeskTicketComment> getZendeskTicketComments(
			long zendeskTicketId)
		throws PortalException {

		Query query = queryFactory.createQuery();

		List<ZendeskTicketComment> zendeskTicketComments = new ArrayList<>();

		int page = 1;

		while (page > 0) {
			query.setPage(page);

			SearchHits<ZendeskTicketComment> searchHits = search(
				zendeskTicketId, query);

			zendeskTicketComments.addAll(searchHits.getResults());

			page = searchHits.getNextPage();
		}

		return zendeskTicketComments;
	}

	protected JSONObject getZendeskTicketCommentJSONObject(
		long zendeskUserId, String htmlBody) {

		JSONObject commentJSONObject = JSONFactoryUtil.createJSONObject();

		commentJSONObject.put("author_id", zendeskUserId);
		commentJSONObject.put("html_body", htmlBody);

		JSONObject ticketJSONObject = JSONFactoryUtil.createJSONObject();

		ticketJSONObject.put("comment", commentJSONObject);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("ticket", ticketJSONObject);

		return jsonObject;
	}

	protected SearchHits<ZendeskTicketComment> search(
			long zendeskTicketId, Query query)
		throws PortalException {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "tickets/",
			String.valueOf(zendeskTicketId), "/comments.json");

		JSONObject responseJSONObject = _zendeskBaseWebService.get(
			endpoint, query.getParameters());

		return toSearchHits(responseJSONObject);
	}

	protected SearchHits<ZendeskTicketComment> toSearchHits(
			JSONObject responseJSONObject)
		throws PortalException {

		SearchHits<ZendeskTicketComment> searchHits = new SearchHitsImpl<>();

		searchHits.setCount(responseJSONObject.getInt("count"));

		String nextPageURL = responseJSONObject.getString("next_page");

		if (Validator.isNotNull(nextPageURL)) {
			String page = _http.getParameter(nextPageURL, "page", false);

			searchHits.setNextPage(GetterUtil.getInteger(page));
		}

		JSONArray jsonArray = responseJSONObject.getJSONArray("comments");

		searchHits.setResults(
			zendeskConverter.toZendeskTicketComments(jsonArray));

		return searchHits;
	}

	@Reference
	protected QueryFactory queryFactory;

	@Reference
	protected ZendeskConverter zendeskConverter;

	@Reference
	private Http _http;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}