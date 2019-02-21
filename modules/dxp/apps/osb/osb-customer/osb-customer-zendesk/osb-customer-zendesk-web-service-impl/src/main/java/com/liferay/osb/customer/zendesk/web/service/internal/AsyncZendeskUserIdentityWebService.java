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
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserIdentityWebService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "async=true",
	service = ZendeskUserIdentityWebService.class
)
public class AsyncZendeskUserIdentityWebService
	extends DefaultZendeskUserIdentityWebService
	implements ZendeskUserIdentityWebService {

	@Override
	public void createZendeskUserIdentity(
			long zendeskUserId, String type, String value)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				ZendeskRESTEndpoints.IDENTITIES;

		JSONObject identityJSONObject = JSONFactoryUtil.createJSONObject();

		identityJSONObject.put("type", type);
		identityJSONObject.put("value", value);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("identity", identityJSONObject);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "post", null, jsonObject, "zendesk.user.identity.create");

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Override
	public void deleteZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String type)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/identities/" + zendeskUserIdentityId + ".json";

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "delete", null, null, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

	@Override
	public void updateZendeskUserIdentity(
			long zendeskUserId, long zendeskUserIdentityId, String value)
		throws PortalException {

		String endpoint =
			ZendeskRESTEndpoints.URL_API_V2 + "users/" + zendeskUserId +
				"/identities/" + zendeskUserIdentityId + ".json";

		JSONObject identityJSONObject = JSONFactoryUtil.createJSONObject();

		identityJSONObject.put("value", value);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("identity", identityJSONObject);

		ZendeskRequest zendeskRequest = new ZendeskRequest(
			endpoint, "put", null, jsonObject, null);

		messagePublisherUtil.sendAsyncZendeskRequest(zendeskRequest);
	}

}