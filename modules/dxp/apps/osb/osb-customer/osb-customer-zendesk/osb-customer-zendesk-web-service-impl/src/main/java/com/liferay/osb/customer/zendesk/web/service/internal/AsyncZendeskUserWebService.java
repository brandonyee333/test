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
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.customer.zendesk.web.service.configuration.ZendeskConnectorConfigurationValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "async=true",
	service = ZendeskUserWebService.class
)
public class AsyncZendeskUserWebService
	extends DefaultZendeskUserWebService implements ZendeskUserWebService {

	@Override
	public void addZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		try {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (String tag : tags) {
				jsonArray.put(tag);
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("tags", jsonArray);

			String endpoint =
				ZendeskRESTEndpoints.URL_API_V2 + "users" + StringPool.SLASH +
					zendeskUserId + StringPool.SLASH + "tags.json";

			ZendeskRequest zendeskRequest = new ZendeskRequest(
				endpoint, "put", jsonObject, null);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					ZENDESK_RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service", zendeskRequest.toJSONObject());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
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

			ZendeskRequest zendeskRequest = new ZendeskRequest(
				endpoint, "post", zendeskUser.toJSONObject(),
				"zendesk.user.create.or.update");

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					ZENDESK_RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service", zendeskRequest.toJSONObject());

			return zendeskUser;
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	public void createZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		try {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (long organizationId : zendeskOrganizationIds) {
				JSONObject membershipJSONObject =
					JSONFactoryUtil.createJSONObject();

				membershipJSONObject.put("organization_id", organizationId);
				membershipJSONObject.put("user_id", zendeskUserId);

				jsonArray.put(membershipJSONObject);
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("organization_memberships", jsonArray);

			String endpoint =
				ZendeskRESTEndpoints.URL_API_V2 +
					ZendeskRESTEndpoints.ORGANIZATION_MEMBERSHIPS_CREATE_MANY;

			ZendeskRequest zendeskRequest = new ZendeskRequest(
				endpoint, "post", jsonObject, null);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					ZENDESK_RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service", zendeskRequest.toJSONObject());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	public void deleteZendeskUserOrganizationMemberships(
			long zendeskUserId, long[] zendeskOrganizationIds)
		throws PortalException {

		try {
			Map<Long, Long> organizationMemberships =
				getOrganizationMemberships(zendeskUserId);

			StringBundler sb = new StringBundler(
				(organizationMemberships.size() * 2) - 1);

			for (Map.Entry<Long, Long> entry :
					organizationMemberships.entrySet()) {

				if (!ArrayUtil.contains(
						zendeskOrganizationIds, entry.getKey())) {

					continue;
				}

				sb.append(entry.getValue());

				if (sb.index() < sb.capacity()) {
					sb.append(StringPool.COMMA);
				}
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("ids", sb.toString());

			JSONObject parametersJSONObject =
				JSONFactoryUtil.createJSONObject();

			parametersJSONObject.put("parameters", jsonObject);

			String endpoint =
				ZendeskRESTEndpoints.URL_API_V2 +
					ZendeskRESTEndpoints.ORGANIZATION_MEMBERSHIPS_DESTROY_MANY;

			ZendeskRequest zendeskRequest = new ZendeskRequest(
				endpoint, "delete", parametersJSONObject, null);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					ZENDESK_RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service", zendeskRequest.toJSONObject());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	public void deleteZendeskUserTags(long zendeskUserId, Set<String> tags)
		throws PortalException {

		try {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (String tag : tags) {
				jsonArray.put(tag);
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("tags", jsonArray);

			String endpoint =
				ZendeskRESTEndpoints.URL_API_V2 + "users" + StringPool.SLASH +
					zendeskUserId + StringPool.SLASH + "tags.json";

			ZendeskRequest zendeskRequest = new ZendeskRequest(
				endpoint, "delete", jsonObject, null);

			_messagePublisher.sendMessage(
				ZendeskConnectorConfigurationValues.
					ZENDESK_RABBITMQ_MESSAGE_EXCHANGE_NAME,
				"zendesk.service", zendeskRequest.toJSONObject());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Reference
	private MessagePublisher _messagePublisher;

}