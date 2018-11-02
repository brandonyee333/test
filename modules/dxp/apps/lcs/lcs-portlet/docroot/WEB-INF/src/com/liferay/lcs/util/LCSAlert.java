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

package com.liferay.lcs.util;

/**
 * @author Igor Beslic
 * @author Marko Cikos
 */
public enum LCSAlert {

	ERROR_ENVIRONMENT_MISMATCH(
		"danger",
		"the-automatic-activation-token-file-does-not-match-the-environment"),
	ERROR_INVALID_ENVIRONMENT_TYPE(
		"danger",
		"this-server-is-registered-to-the-environment-of-the-wrong-type"),
	ERROR_INVALID_USER_CREDENTIALS(
		"danger",
		"the-credentials-of-the-user-that-created-the-automatic-activation-" +
			"token-file-are-no-longer-valid"),
	ERROR_INVALID_TOKEN(
		"danger", "the-automatic-activation-token-file-is-invalid"),
	ERROR_MISSING_TOKEN(
		"danger", "the-automatic-activation-token-file-is-not-present"),
	ERROR_MULTIPLE_TOKENS(
		"danger", "more-than-one-automatic-activation-token-file-is-present"),
	SUCCESS_CONNECTION_TO_LCS_VALID("success", "connection-to-lcs-is-valid"),
	SUCCESS_VALID_TOKEN(
		"success", "the-automatic-activation-token-file-is-valid"),
	WARNING_HANDSHAKE_EXPIRED(
		"warning", "the-connection-to-liferay-connected-services-has-expired"),
	WARNING_LCS_PORTLET_NEW_VERSION_AVAILABLE(
		"warning",
		"please-download-and-install-the-latest-version-of-liferay-connected-" +
			"services-client");

	public String getCSSClass() {
		return "alert alert-" + getType();
	}

	public String getLabel() {
		return _label;
	}

	public String getType() {
		return _type;
	}

	private LCSAlert(String type, String label) {
		_type = type;
		_label = label;
	}

	private final String _label;
	private final String _type;

}