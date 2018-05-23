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

package com.liferay.lcs.messaging;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class MessageBusMessage extends Message {

	public Object get(String key) {
		return _values.get(key);
	}

	public String getDestinationName() {
		return _destinationName;
	}

	public Object getPayload() {
		return _payload;
	}

	public String getResponse() {
		return _response;
	}

	public String getResponseDestinationName() {
		return _responseDestinationName;
	}

	public String getResponseId() {
		return _responseId;
	}

	public Map<String, Object> getValues() {
		return _values;
	}

	public void put(String key, String value) {
		_values.put(key, value);
	}

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setPayload(Object payload) {
		_payload = payload;
	}

	public void setResponse(String response) {
		_response = response;
	}

	public void setResponseDestinationName(String responseDestinationName) {
		_responseDestinationName = responseDestinationName;
	}

	public void setResponseId(String responseId) {
		_responseId = responseId;
	}

	public void setValues(Map<String, Object> values) {
		_values = values;
	}

	private String _destinationName;
	private Object _payload;
	private String _response;
	private String _responseDestinationName;
	private String _responseId;
	private Map<String, Object> _values = new HashMap<String, Object>();

}