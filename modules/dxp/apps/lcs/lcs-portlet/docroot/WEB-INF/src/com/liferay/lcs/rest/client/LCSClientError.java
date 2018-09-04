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

import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mladen Cikara
 * @author Igor Beslic
 */
public enum LCSClientError {

	GENERAL_LCS_PLATFORM_ERROR(999, "General LCS platform error."),
	ILLEGAL_PARAMETER_ERROR(
		7,
		"An illegal request parameter is received. Please make sure " +
			"parameter format is correct and try again."),
	INTERNAL_LCS_PLATFORM_ERROR(
		998, "Internal LCS platform error. Please try again later."),
	LCS_SUBSCRIPTION_ENTRY_ERROR_NO_ACTIVE_SUBSCRIPTION(
		3,
		"There is no active subscription on the project. Please contact " +
			"Liferay provisioning."),
	LCS_SUBSCRIPTION_ENTRY_ERROR_NO_FREE_ACTIVATION_KEY(
		4,
		"There are no available activation keys. Please unregister server to " +
			"release an activation key. If LCS lost connection to a server " +
				"in the environment, or a server unexpectedly shut down, LCS " +
					"will automatically release an activation key after a " +
						"few minutes."),
	LCS_SUBSCRIPTION_ENTRY_ERROR_NO_SUBSCRIPTION_TYPE(
		5,
		"There is no subscription on the project of a type defined in your " +
			"environment. To extend your subscription please contact Liferay " +
				"provisioning. If you wish to use a different subscription, " +
					"please unregister server and register to an environment " +
						"with an active subscription type."),
	LCS_SUBSCRIPTION_ENTRY_ERROR_TOO_MANY_PROCESSOR_CORES(
		6,
		"Number of server processor cores exceeds maximum number of " +
			"processor cores allowed by your subscriptions."),
	NO_SUCH_LCS_SUBSCRIPTION_ENTRY(1, "No such subscription on the project."),
	REQUIRED_PARAMETER_MISSING(2, "Required parameter is missing."),
	UNDEFINED(0, "Undefined error.");

	public static LCSClientError getRESTError(
		JSONWebServiceInvocationException jsonwsie) {

		return getRESTError(jsonwsie.getMessage());
	}

	public static LCSClientError getRESTError(String message) {
		if (Validator.isNull(message)) {
			return UNDEFINED;
		}

		Matcher matcher = _errorMessagePattern.matcher(message);

		if (matcher.find()) {
			int errorCode = Integer.parseInt(matcher.group(1));

			return toLCSClientError(errorCode);
		}

		return UNDEFINED;
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

	public String getErrorDescription() {
		return _errorDescription;
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

	private LCSClientError(int errorCode, String errorDescription) {
		_errorCode = errorCode;
		_errorDescription = errorDescription;
	}

	private static final Log _log = LogFactoryUtil.getLog(LCSClientError.class);

	private static final Pattern _errorMessagePattern = Pattern.compile(
		"errorCode\":\\s*(\\d+).+message\":.+status\":\\s*(\\d+)");

	private final int _errorCode;
	private final String _errorDescription;

}