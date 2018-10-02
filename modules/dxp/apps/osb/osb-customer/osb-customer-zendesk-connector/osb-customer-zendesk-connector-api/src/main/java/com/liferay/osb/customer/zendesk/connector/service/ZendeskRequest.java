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

package com.liferay.osb.customer.zendesk.connector.service;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Kyle Bischof
 */
public class ZendeskRequest {

	public static ZendeskRequest getInstance(JSONObject jsonObject) {
		return new ZendeskRequest(
			jsonObject.getString("_FIELD_ENDPOINT"),
			jsonObject.getString("_FIELD_METHOD"),
			jsonObject.getJSONObject("_FIELD_PAYLOAD"),
			jsonObject.getString("_FIELD_RESPONSE_ROUTING_KEY"));
	}

	public ZendeskRequest(
		String endpoint, String method, JSONObject payload,
		String responseRoutingKey) {

		_endpoint = endpoint;
		_method = method;
		_payload = payload;
		_responseRoutingKey = responseRoutingKey;
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public String getMethod() {
		return _method;
	}

	public JSONObject getPayload() {
		return _payload;
	}

	public String getResponseRoutingKey() {
		return _responseRoutingKey;
	}

	public boolean hasResponseRoutingKey() {
		if (Validator.isNotNull(_responseRoutingKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(_FIELD_ENDPOINT, _endpoint);
		jsonObject.put(_FIELD_METHOD, _method);
		jsonObject.put(_FIELD_PAYLOAD, _payload);

		if (Validator.isNotNull(_responseRoutingKey)) {
			jsonObject.put(_FIELD_RESPONSE_ROUTING_KEY, _responseRoutingKey);
		}

		return jsonObject;
	}

	private static final String _FIELD_ENDPOINT = "endpoint";

	private static final String _FIELD_METHOD = "method";

	private static final String _FIELD_PAYLOAD = "payload";

	private static final String _FIELD_RESPONSE_ROUTING_KEY =
		"responseRoutingKey";

	private final String _endpoint;
	private final String _method;
	private final JSONObject _payload;
	private final String _responseRoutingKey;

}