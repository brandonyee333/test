/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.alert;

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
	ERROR_INVALID_TOKEN(
		"danger", "the-automatic-activation-token-file-is-invalid"),
	ERROR_INVALID_USER_CREDENTIALS(
		"danger",
		"the-credentials-of-the-user-that-created-the-automatic-activation-" +
			"token-file-are-no-longer-valid"),
	ERROR_MISSING_TOKEN(
		"danger", "the-automatic-activation-token-file-is-not-present"),
	ERROR_MULTIPLE_TOKENS(
		"danger", "more-than-one-automatic-activation-token-file-is-present"),
	SUCCESS_CONNECTION_TO_LCS_VALID("success", "connection-to-lcs-is-valid"),
	SUCCESS_VALID_TOKEN(
		"success", "the-automatic-activation-token-file-is-valid"),
	WARNING_HANDSHAKE_FAILED("warning", "unable-to-handshake-with-lcs-gateway"),
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