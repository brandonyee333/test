/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class MessageBusCommandMessage extends CommandMessage {

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