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
import com.liferay.osb.customer.zendesk.web.service.internal.util.MessagePublisherUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskUserWebService.class)
public class DefaultZendeskUserWebService implements ZendeskUserWebService {

	public void addZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public ZendeskUser createOrUpdateZendeskUser(
			String externalId, String email, String locale, String name,
			String organizationName, Set<String> tags)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 +
				ZendeskRESTEndpoints.USERS_CREATE_OR_UPDATE;

		JSONObject jsonObject = getZendeskUserJSONObject(
			email, externalId, locale, name, organizationName, tags);

		JSONObject responseJSONObject = _zendeskBaseWebService.post(
			endpoint, jsonObject.toString());

		_messagePublisherUtil.sendEventNotification(
			"zendesk.user.create.or.update", responseJSONObject);

		return toZendeskUser(responseJSONObject);
	}

	public void createZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public void deleteZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public void deleteZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	public Map<Long, Long> getOrganizationMemberships(long zendeskUserId)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/organization_memberships.json";

		JSONObject organizationMembershipsJSONObject =
			_zendeskBaseWebService.get(endpoint, StringPool.BLANK);

		Map<Long, Long> organizationMembershipMap = new HashMap<>();

		JSONArray membershipsJSONArray =
			organizationMembershipsJSONObject.getJSONArray(
				"organization_memberships");

		for (int i = 0; i < membershipsJSONArray.length(); i++) {
			JSONObject membershipJSONObject =
				membershipsJSONArray.getJSONObject(i);

			long id = membershipJSONObject.getLong("id");
			long organizationId = membershipJSONObject.getLong(
				"organization_id");

			organizationMembershipMap.put(organizationId, id);
		}

		return organizationMembershipMap;
	}

	public ZendeskUser getZendeskUser(String externalId)
		throws PortalException {

		Map<String, String> parameters = new HashMap<>();

		parameters.put("external_id", externalId);

		JSONObject responseJSONObject = _zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "users/search.json", parameters);

		JSONArray usersJSONArray = responseJSONObject.getJSONArray("users");

		if (usersJSONArray.length() <= 0) {
			return null;
		}

		return toZendeskUser(usersJSONArray.getJSONObject(0));
	}

	protected JSONObject getZendeskUserJSONObject(
		String externalId, String email, String locale, String name,
		String organizationName, Set<String> tags) {

		JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

		userJSONObject.put("name", name);

		if (Validator.isNotNull(email)) {
			userJSONObject.put("email", email);
		}

		if (Validator.isNotNull(externalId)) {
			userJSONObject.put("external_id", externalId);
		}

		if (Validator.isNotNull(locale)) {
			userJSONObject.put("locale", locale);
		}

		JSONObject organizationJSONObject = JSONFactoryUtil.createJSONObject();

		organizationJSONObject.put("name", organizationName);

		userJSONObject.put("organization", organizationJSONObject);

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

	@Reference(unbind = "-")
	protected void setMessagePublisherUtil(
		MessagePublisherUtil messagePublisherUtil) {

		_messagePublisherUtil = messagePublisherUtil;
	}

	@Reference(unbind = "-")
	protected void setZendeskBaseWebService(
		ZendeskBaseWebService zendeskBaseWebService) {

		_zendeskBaseWebService = zendeskBaseWebService;
	}

	protected ZendeskUser toZendeskUser(JSONObject jsonObject) {
		ZendeskUser zendeskUser = new ZendeskUser();

		zendeskUser.setEmail(jsonObject.getString("email"));
		zendeskUser.setExternalId(jsonObject.getString("externalId"));
		zendeskUser.setLocale(jsonObject.getString("locale"));
		zendeskUser.setName(jsonObject.getString("name"));
		zendeskUser.setZendeskUserId(jsonObject.getLong("id"));

		return zendeskUser;
	}

	private MessagePublisherUtil _messagePublisherUtil;
	private ZendeskBaseWebService _zendeskBaseWebService;

}