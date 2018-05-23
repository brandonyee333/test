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

/**
 * @author Riccardo Ferrari
 */
public class MessageBusCommandMessage extends CommandMessage {

	public String getDestinationName() {
		return _destinationName;
	}

	@Override
	public String getPayload() {
		return (String)super.getPayload();
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

	public void setDestinationName(String destinationName) {
		_destinationName = destinationName;
	}

	public void setPayload(String payload) {
		super.setPayload(payload);
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

	private String _destinationName;
	private String _response;
	private String _responseDestinationName;
	private String _responseId;

}