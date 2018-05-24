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

package com.liferay.watson.login.constants;

/**
 * @author Steven Smith
 */
public class WatsonTokenAuthEntryConstants {

	public static final int AUTHORIZATION_STATUS_APPROVED = 0;

	public static final int AUTHORIZATION_STATUS_ERROR = -1;

	public static final int AUTHORIZATION_STATUS_EXPIRED = 2;

	public static final int AUTHORIZATION_STATUS_INVALID = 3;

	public static final int AUTHORIZATION_STATUS_INVALID_IP = 4;

	public static final int AUTHORIZATION_STATUS_INVALID_IP_WARNING = 5;

	public static final String AUTHORIZATION_STATUS_LABEL_APPROVED = "approved";

	public static final String AUTHORIZATION_STATUS_LABEL_EXPIRED = "expired";

	public static final String AUTHORIZATION_STATUS_LABEL_INVALID = "invalid";

	public static final String AUTHORIZATION_STATUS_LABEL_INVALID_IP =
		"invalid-ip";

	public static final String AUTHORIZATION_STATUS_LABEL_INVALID_IP_WARNING =
		"invalid-ip-warning";

	public static final String AUTHORIZATION_STATUS_LABEL_PENDING = "pending";

	public static final int AUTHORIZATION_STATUS_PENDING = 1;

	public static String getWatsonTokenAuthEntryStatusLabel(int status) {
		if (status == AUTHORIZATION_STATUS_APPROVED) {
			return AUTHORIZATION_STATUS_LABEL_APPROVED;
		}
		else if (status == AUTHORIZATION_STATUS_EXPIRED) {
			return AUTHORIZATION_STATUS_LABEL_EXPIRED;
		}
		else if (status == AUTHORIZATION_STATUS_INVALID_IP) {
			return AUTHORIZATION_STATUS_LABEL_INVALID_IP;
		}
		else if (status == AUTHORIZATION_STATUS_INVALID_IP_WARNING) {
			return AUTHORIZATION_STATUS_LABEL_INVALID_IP_WARNING;
		}
		else if (status == AUTHORIZATION_STATUS_PENDING) {
			return AUTHORIZATION_STATUS_LABEL_PENDING;
		}
		else {
			return AUTHORIZATION_STATUS_LABEL_INVALID;
		}
	}

}