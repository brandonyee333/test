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

package com.liferay.lcs.rest.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mladen Cikara
 * @author Igor Beslic
 */
public enum LCSClientError {

	NO_SUCH_LCS_SUBSCRIPTION_ENTRY(1), REQUIRED_PARAMETER_MISSING(2),
	UNDEFINED(0);

	public static LCSClientError getRESTError(
		JSONWebServiceInvocationException jsonwsie) {

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			ObjectNode objectNode = objectMapper.readValue(
				jsonwsie.getMessage(), ObjectNode.class);

			JsonNode errorCodeJsonNode = objectNode.get("errorCode");

			if (errorCodeJsonNode == null) {
				return UNDEFINED;
			}

			return toLCSClientError(errorCodeJsonNode.asInt());
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			return UNDEFINED;
		}
	}

	public static LCSClientError toLCSClientError(int errorCode) {
		for (LCSClientError lcsClientError : values()) {
			if (lcsClientError.getErrorCode() == errorCode) {
				return lcsClientError;
			}
		}

		return UNDEFINED;
	}

	public int getErrorCode() {
		return _errorCode;
	}

	public String toJSON(String message, int status, Object... args) {
		StringBuilder sb = new StringBuilder(7 + (args.length * 8));

		sb.append("{\"errorCode\": ");
		sb.append(getErrorCode());
		sb.append(", \"message\": \"");
		sb.append(message);
		sb.append("\", \"status\": ");
		sb.append(status);

		if (args.length == 0) {
			sb.append("}");

			return sb.toString();
		}

		sb.append(", \"args\": {");

		for (int i = 0; i < (args.length - 1); i = i + 2) {
			sb.append("\"");
			sb.append(args[i]);
			sb.append("\"");
			sb.append(":");
			sb.append("\"");
			sb.append(String.valueOf(args[i + 1]));
			sb.append("\"");

			if ((i + 2) < args.length) {
				sb.append(", ");
			}
		}

		sb.append("}}");

		return sb.toString();
	}

	private LCSClientError(int errorCode) {
		_errorCode = errorCode;
	}

	private static final Logger _log = LoggerFactory.getLogger(
		LCSClientError.class);

	private final int _errorCode;

}