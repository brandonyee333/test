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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class ZendeskRequest {

	public static ZendeskRequest getInstance(JSONObject jsonObject) {
		Map<String, String> parameters = null;

		JSONObject parametersJSONObject = jsonObject.getJSONObject(
			_FIELD_PARAMETERS);

		if (parametersJSONObject != null) {
			parameters = new HashMap<>();

			Iterator<String> keys = parametersJSONObject.keys();

			while (keys.hasNext()) {
				String key = keys.next();

				parameters.put(key, parametersJSONObject.getString(key));
			}
		}

		return new ZendeskRequest(
			jsonObject.getString(_FIELD_ENDPOINT),
			jsonObject.getString(_FIELD_METHOD), parameters,
			jsonObject.getJSONObject(_FIELD_BODY),
			jsonObject.getString(_FIELD_RESPONSE_ROUTING_KEY));
	}

	public ZendeskRequest(
		String endpoint, String method, Map<String, String> parameters,
		JSONObject body, String responseRoutingKey) {

		_endpoint = endpoint;
		_method = method;
		_parameters = parameters;
		_body = body;
		_responseRoutingKey = responseRoutingKey;
	}

	public JSONObject getBody() {
		return _body;
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public String getMethod() {
		return _method;
	}

	public Map<String, String> getParameters() {
		return _parameters;
	}

	public String getResponseRoutingKey() {
		return _responseRoutingKey;
	}

	public boolean hasParameters() {
		if (_parameters != null) {
			return true;
		}
		else {
			return false;
		}
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

		if (hasParameters()) {
			JSONObject parametersJSONObject =
				JSONFactoryUtil.createJSONObject();

			for (Map.Entry<String, String> entry : _parameters.entrySet()) {
				parametersJSONObject.put(entry.getKey(), entry.getValue());
			}

			jsonObject.put(_FIELD_PARAMETERS, parametersJSONObject);
		}

		if (Validator.isNotNull(_body)) {
			jsonObject.put(_FIELD_BODY, _body);
		}

		if (hasResponseRoutingKey()) {
			jsonObject.put(_FIELD_RESPONSE_ROUTING_KEY, _responseRoutingKey);
		}

		return jsonObject;
	}

	private static final String _FIELD_BODY = "body";

	private static final String _FIELD_ENDPOINT = "endpoint";

	private static final String _FIELD_METHOD = "method";

	private static final String _FIELD_PARAMETERS = "parameters";

	private static final String _FIELD_RESPONSE_ROUTING_KEY =
		"responseRoutingKey";

	private final JSONObject _body;
	private final String _endpoint;
	private final String _method;
	private final Map<String, String> _parameters;
	private final String _responseRoutingKey;

}