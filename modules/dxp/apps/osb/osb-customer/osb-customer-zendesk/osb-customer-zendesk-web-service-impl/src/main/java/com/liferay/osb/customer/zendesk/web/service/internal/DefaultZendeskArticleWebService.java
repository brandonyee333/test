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
import com.liferay.osb.customer.zendesk.model.ZendeskArticle;
import com.liferay.osb.customer.zendesk.web.service.ZendeskArticleWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.search.SearchHitsImpl;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskArticleWebService.class)
public class DefaultZendeskArticleWebService
	implements ZendeskArticleWebService {

	public SearchHits<ZendeskArticle> getZendeskArticles(Query query)
		throws PortalException {

		JSONObject responseJSONObject = _zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "help_center/articles.json",
			query.getParameters());

		return toSearchHits(responseJSONObject);
	}

	protected SearchHits<ZendeskArticle> toSearchHits(
			JSONObject responseJSONObject)
		throws PortalException {

		SearchHits<ZendeskArticle> searchHits = new SearchHitsImpl<>();

		searchHits.setCount(responseJSONObject.getInt("count"));

		String nextPageURL = responseJSONObject.getString("next_page");

		if (Validator.isNotNull(nextPageURL)) {
			String page = _http.getParameter(nextPageURL, "page", false);

			searchHits.setNextPage(GetterUtil.getInteger(page));
		}

		JSONArray jsonArray = responseJSONObject.getJSONArray("articles");

		searchHits.setResults(zendeskConverter.toZendeskArticles(jsonArray));

		return searchHits;
	}

	@Reference
	protected ZendeskConverter zendeskConverter;

	@Reference
	private Http _http;

	@Reference
	private ZendeskBaseWebService _zendeskBaseWebService;

}