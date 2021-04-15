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

package com.liferay.lcs.client.platform.portal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Mladen Cikara
 * @author Igor Beslic
 */
public enum LCSRESTError {

	GENERAL_LCS_PLATFORM_ERROR(999, "An error occurred in LCS."),
	ILLEGAL_PARAMETER_ERROR(
		7,
		"LCS received a message from the LCS client that contains an illegal " +
			"parameter."),
	INTERNAL_LCS_PLATFORM_ERROR(998, "An error occurred in LCS."),
	LCS_CLUSTER_ENTRY_TOKEN_ERROR_INVALID_LCS_CLUSTER_ENTRY(
		201,
		"The environment token does not match the environment in which the " +
			"server is registered. If you are moving the server to a " +
				"different environment, please unregister the server from " +
					"the old environment."),
	LCS_CLUSTER_ENTRY_TOKEN_ERROR_INVALID_USER_CREDENTIALS(
		202,
		"The credentials of the user who created the environment token are " +
			"no longer valid. Please regenerate the token."),
	LCS_CLUSTER_ENTRY_TOKEN_ERROR_NO_SUCH_TOKEN(
		200,
		"The environment token is not valid and was likely regenerated in " +
			"LCS. Please download and install the new token."),
	LCS_CLUSTER_NODE_IS_ALREADY_ACTIVATED(
		400,
		"Detected attempt to activate an already active instance. This is " +
			"common issue for dynamic cloud environments if machine image " +
				"accidentally included " +
					"[LIFERAY_HOME]/data/license/server/lcsServerId file or " +
						"cluster nodes share [LIFERAY_HOME]/data folder."),
	LCS_PLATFORM_UNDEFINED_RESPONSE(
		800,
		"LCS Platform responds with undefined protocol message. Please check " +
			"LCS processor logs verify LCS platform connection configuration"),
	LCS_SUBSCRIPTION_ENTRY_ERROR_NO_ACTIVE_SUBSCRIPTION(
		3,
		"There are no active subscriptions in the LCS project. Please " +
			"contact Liferay provisioning."),
	LCS_SUBSCRIPTION_ENTRY_ERROR_NO_FREE_ACTIVATION_KEY(
		4,
		"Aborting Liferay instance activation. No activation keys are " +
			"available. Please unregister a server to free an activation " +
				"key, or contact Liferay provisioning for additional keys."),
	LCS_SUBSCRIPTION_ENTRY_ERROR_NO_SUBSCRIPTION_TYPE(
		5,
		"Aborting Liferay instance activation. The LCS project contains no " +
			"active subscriptions that match the environment's subscription " +
				"type. Please contact Liferay provisioning if your " +
					"subscription is expired."),
	LCS_SUBSCRIPTION_ENTRY_ERROR_TOO_MANY_PROCESSOR_CORES(
		6,
		"Aborting Liferay instance activation. The number of processor cores " +
			"in your server exceeds the number that your subscription " +
				"allows. Please contact Liferay provisioning to increase " +
					"your allowed processor cores."),
	NO_SUCH_LCS_SUBSCRIPTION_ENTRY(
		1,
		"The LCS project contains no active subscriptions. Please contact " +
			"Liferay provisioning."),
	REQUIRED_PARAMETER_MISSING(
		2,
		"LCS received a message from the LCS client that is missing a " +
			"required parameter."),
	UNDEFINED(0, StringPool.BLANK);

	public static LCSRESTError getRESTError(String message) {
		try {
			int idx = message.indexOf("errorCode");

			if (idx < 0) {
				return UNDEFINED;
			}

			idx = idx + 12;

			int errorCode = Integer.parseInt(
				message.substring(idx, message.indexOf(",", idx)));

			return toLCSClientError(errorCode);
		}
		catch (NumberFormatException nfe) {
			_log.error(
				"Unable to read error code from recieved error message: " +
					message,
				nfe);
		}

		return UNDEFINED;
	}

	public static LCSRESTError toLCSClientError(int errorCode) {
		for (LCSRESTError lcsRESTError : values()) {
			if (lcsRESTError.getErrorCode() == errorCode) {
				return lcsRESTError;
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
		StringBuilder sb = new StringBuilder(9 + (args.length * 8));

		sb.append("{\"errorCode\": ");
		sb.append(getErrorCode());
		sb.append(", \"errorDescription\": \"");
		sb.append(getErrorDescription());
		sb.append("\", \"message\": \"");
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

	private LCSRESTError(int errorCode, String errorDescription) {
		_errorCode = errorCode;
		_errorDescription = errorDescription;
	}

	private static final Log _log = LogFactoryUtil.getLog(LCSRESTError.class);

	private final int _errorCode;
	private final String _errorDescription;

}