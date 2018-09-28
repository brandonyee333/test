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

package com.liferay.osb.customer.zendesk.web.service.api;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Kyle Bischof
 */
public class ZendeskAPICall {

	public ZendeskAPICall() {
	}

	public ZendeskAPICall(
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

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
	}

	public void setMethod(String method) {
		_method = method;
	}

	public void setPayload(JSONObject payload) {
		_payload = payload;
	}

	public void setResponseRoutingKey(String responseRoutingKey) {
		_responseRoutingKey = responseRoutingKey;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("endpoint", _endpoint);
		jsonObject.put("method", _method);
		jsonObject.put("payload", _payload.toJSONString());

		if (Validator.isNotNull(_responseRoutingKey)) {
			jsonObject.put("responseRoutingKey", _responseRoutingKey);
		}

		return jsonObject;
	}

	private String _endpoint;
	private String _method;
	private JSONObject _payload;
	private String _responseRoutingKey;

}