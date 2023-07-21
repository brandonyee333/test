/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service.internal;

import com.liferay.osb.customer.zendesk.connector.constants.ZendeskRESTEndpoints;
import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserIdentityWebService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = {"async=true", "service.ranking:Integer=1"},
	service = ZendeskUserIdentityWebService.class
)
public class AsyncZendeskUserIdentityWebService
	extends DefaultZendeskUserIdentityWebService
	implements ZendeskUserIdentityWebService {

	@Override
	public void createZendeskUserIdentity(
			long zendeskUserId, String type, String value)
		throws Exception {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "users/",
			String.valueOf(zendeskUserId), ZendeskRESTEndpoints.IDENTITIES);

		JSONObject identityJSONObject = JSONFactoryUtil.createJSONObject();

		identityJSONObject.put("type", type);
		identityJSONObject.put("value", value);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("identity", identityJSONObject);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject, "zendesk.user.identity.create");

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

	@Override
	public void deleteZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String type)
		throws Exception {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "users/",
			String.valueOf(zendeskUserId), "/identities/",
			String.valueOf(zendeskUserIdentityId), ".json");

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "delete", null, null, null);

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

	@Override
	public void updateZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String value)
		throws Exception {

		String endpoint = StringBundler.concat(
			ZendeskRESTEndpoints.URL_API_V2, "users/",
			String.valueOf(zendeskUserId), "/identities/",
			String.valueOf(zendeskUserIdentityId), ".json");

		JSONObject identityJSONObject = JSONFactoryUtil.createJSONObject();

		identityJSONObject.put("value", value);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("identity", identityJSONObject);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "put", null, jsonObject, null);

		messagePublisher.publish(
			"zendesk.service", zendeskRequest.getMessage());
	}

}