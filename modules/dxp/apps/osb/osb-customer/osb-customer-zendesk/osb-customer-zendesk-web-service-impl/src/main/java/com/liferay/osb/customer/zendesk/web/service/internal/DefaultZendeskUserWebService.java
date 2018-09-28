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
import com.liferay.osb.customer.zendesk.connector.util.ZendeskBaseWebService;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.customer.zendesk.web.service.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.osb.customer.zendesk.web.service.exception.UnsupportedServiceException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;

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

		throw new UnsupportedServiceException();
	}

	public ZendeskUser createOrUpdateZendeskUser(
			String email, String externalId, String locale, String name,
			String organizationName, Set<String> tags)
		throws PortalException {

		try {
			ZendeskUser zendeskUser = getZendeskUser(
				email, externalId, locale, name, organizationName, tags);

			String endpoint =
				ZendeskRESTEndpoints.URL_API_V2 +
					ZendeskRESTEndpoints.USERS_CREATE_OR_UPDATE;

			JSONObject zendeskUserJSONObject = zendeskUser.toJSONObject();

			JSONObject responseJSONObject = _zendeskBaseWebService.post(
				endpoint, zendeskUserJSONObject.toString());

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					OSB_RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.user.create.or.update", responseJSONObject);

			return _translate(responseJSONObject);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public void createZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		throw new UnsupportedServiceException();
	}

	public void deleteZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		throw new UnsupportedServiceException();
	}

	public void deleteZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		throw new UnsupportedServiceException();
	}

	public Map<Long, Long> getOrganizationMemberships(long zendeskUserId)
		throws PortalException {

		String getEndpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/organization_memberships.json";

		JSONObject organizationMembershipsJSONObject =
			_zendeskBaseWebService.get(getEndpoint, StringPool.BLANK);

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

	public ZendeskUser getZendeskUser(long userId) throws PortalException {
		User user = _userLocalService.getUser(userId);

		Map<String, String> parameters = new HashMap<>();

		parameters.put("external_id", user.getUuid());

		JSONObject responseJSONObject = _zendeskBaseWebService.get(
			ZendeskRESTEndpoints.URL_API_V2 + "users/search.json", parameters);

		JSONArray usersJSONArray = responseJSONObject.getJSONArray("users");

		if (usersJSONArray.length() <= 0) {
			return null;
		}

		return _translate(usersJSONArray.getJSONObject(0));
	}

	public ZendeskUser getZendeskUser(
		String email, String externalId, String locale, String name,
		String organizationName, Set<String> tags) {

		ZendeskUser zendeskUser = new ZendeskUser();

		zendeskUser.setEmail(email);
		zendeskUser.setExternalId(externalId);
		zendeskUser.setLocale(locale);
		zendeskUser.setName(name);
		zendeskUser.setOrganizationName(organizationName);

		if ((tags != null) && !tags.isEmpty()) {
			zendeskUser.setTags(tags);
		}

		return zendeskUser;
	}

	@Reference(unbind = "-")
	protected void setMessagePublisher(MessagePublisher messagePublisher) {
		_messagePublisher = messagePublisher;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Reference(unbind = "-")
	protected void setZendeskBaseWebService(
		ZendeskBaseWebService zendeskBaseWebService) {

		_zendeskBaseWebService = zendeskBaseWebService;
	}

	private ZendeskUser _translate(JSONObject jsonObject) {
		ZendeskUser zendeskUser = new ZendeskUser();

		zendeskUser.setEmail(jsonObject.getString("email"));
		zendeskUser.setExternalId(jsonObject.getString("externalId"));
		zendeskUser.setLocale(jsonObject.getString("locale"));
		zendeskUser.setName(jsonObject.getString("name"));
		zendeskUser.setZendeskUserId(jsonObject.getLong("id"));

		return zendeskUser;
	}

	private static MessagePublisher _messagePublisher;
	private static UserLocalService _userLocalService;
	private static ZendeskBaseWebService _zendeskBaseWebService;

}