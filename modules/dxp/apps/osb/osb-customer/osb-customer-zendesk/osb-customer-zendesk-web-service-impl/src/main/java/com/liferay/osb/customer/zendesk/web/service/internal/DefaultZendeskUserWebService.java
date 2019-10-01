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
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.customer.zendesk.web.service.internal.search.SearchHitsImpl;
import com.liferay.osb.customer.zendesk.web.service.internal.util.MessagePublisherUtil;
import com.liferay.osb.customer.zendesk.web.service.internal.util.ZendeskConverter;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = ZendeskUserWebService.class
)
public class DefaultZendeskUserWebService implements ZendeskUserWebService {

	public void addZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public ZendeskUser createOrUpdateZendeskUser(
			String externalId, String email, String zendeskLocale, String name,
			String organizationName, Set<String> tags)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.USERS_CREATE_OR_UPDATE;

		JSONObject jsonObject = getZendeskUserJSONObject(
			externalId, email, zendeskLocale, name, organizationName, tags);

		JSONObject responseJSONObject = zendeskBaseWebService.post(
			endpoint, jsonObject.toString());

		messagePublisherUtil.sendEventNotification(
			"zendesk.user.create.or.update", responseJSONObject);

		JSONObject userJSONObject = responseJSONObject.getJSONObject("user");

		return zendeskConverter.toZendeskUser(userJSONObject);
	}

	public void createZendeskUserOrganizationSubscription(
			long zendeskUserId, long zendeskOrganizationId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public void deleteZendeskUser(long zendeskUserId) throws PortalException {
		throw new UnsupportedOperationException();
	}

	public void deleteZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public ZendeskUser getZendeskUser(long zendeskUserId)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				".json";

		JSONObject jsonObject = zendeskBaseWebService.get(
			endpoint, StringPool.BLANK);

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		return zendeskConverter.toZendeskUser(userJSONObject);
	}

	public ZendeskUser getZendeskUserByEmail(String email)
		throws PortalException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("query", email);

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "users/search.json", parameters);

		JSONArray usersJSONArray = responseJSONObject.getJSONArray("users");

		if (usersJSONArray.length() <= 0) {
			return null;
		}

		return zendeskConverter.toZendeskUser(usersJSONArray.getJSONObject(0));
	}

	public ZendeskUser getZendeskUserByExternalId(String externalId)
		throws PortalException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("external_id", externalId);

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "users/search.json", parameters);

		JSONArray usersJSONArray = responseJSONObject.getJSONArray("users");

		if (usersJSONArray.length() <= 0) {
			return null;
		}

		return zendeskConverter.toZendeskUser(usersJSONArray.getJSONObject(0));
	}

	public List<ZendeskUser> getZendeskUsers(long[] zendeskUserIds)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/show_many.json?ids=" +
				StringUtil.merge(zendeskUserIds);

		JSONObject jsonObject = zendeskBaseWebService.get(
			endpoint, StringPool.BLANK);

		JSONArray usersJSONArray = jsonObject.getJSONArray("users");

		return zendeskConverter.toZendeskUsers(usersJSONArray);
	}

	public SearchHits<ZendeskUser> getZendeskUsers(Query query)
		throws PortalException {

		JSONObject responseJSONObject = zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "users/search.json",
			query.getParameters());

		return toSearchHits(responseJSONObject);
	}

	protected JSONObject getZendeskUserJSONObject(
		String externalId, String email, String zendeskLocale, String name,
		String organizationName, Set<String> tags) {

		JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

		userJSONObject.put("name", name);

		if (Validator.isNotNull(email)) {
			userJSONObject.put("email", email);
		}

		if (Validator.isNotNull(externalId)) {
			userJSONObject.put("external_id", externalId);
		}

		if (Validator.isNotNull(zendeskLocale)) {
			userJSONObject.put("locale", zendeskLocale);
		}

		if (Validator.isNotNull(organizationName)) {
			JSONObject organizationJSONObject =
				JSONFactoryUtil.createJSONObject();

			organizationJSONObject.put("name", organizationName);

			userJSONObject.put("organization", organizationJSONObject);
		}

		JSONArray tagsJSONArray = JSONFactoryUtil.createJSONArray();

		if ((tags != null) && !tags.isEmpty()) {
			for (String tag : tags) {
				tagsJSONArray.put(tag);
			}

			userJSONObject.put("tags", tagsJSONArray);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("user", userJSONObject);

		return jsonObject;
	}

	protected SearchHits<ZendeskUser> toSearchHits(
			JSONObject responseJSONObject)
		throws PortalException {

		SearchHits<ZendeskUser> searchHits = new SearchHitsImpl<>();

		searchHits.setCount(responseJSONObject.getInt("count"));

		String nextPageURL = responseJSONObject.getString("next_page");

		if (Validator.isNotNull(nextPageURL)) {
			String page = http.getParameter(nextPageURL, "page", false);

			searchHits.setNextPage(GetterUtil.getInteger(page));
		}

		JSONArray jsonArray = responseJSONObject.getJSONArray("users");

		searchHits.setResults(zendeskConverter.toZendeskUsers(jsonArray));

		return searchHits;
	}

	@Reference
	protected Http http;

	@Reference
	protected MessagePublisherUtil messagePublisherUtil;

	@Reference
	protected ZendeskBaseWebService zendeskBaseWebService;

	@Reference
	protected ZendeskConverter zendeskConverter;

}