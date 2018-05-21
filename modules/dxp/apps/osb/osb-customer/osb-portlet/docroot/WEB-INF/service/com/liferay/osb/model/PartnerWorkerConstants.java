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

package com.liferay.osb.model;

/**
 * @author Kyle Bischof
 */
public class PartnerWorkerConstants {

	public static final int NOTIFICATIONS_ALL = 1;

	public static final int NOTIFICATIONS_NONE = 2;

	public static final int ROLE_MANAGER = 1;

	public static final int ROLE_MEMBER = 2;

	public static final int ROLE_WATCHER = 3;

	public static String getNotificationsLabel(int notifications) {
		if (notifications == NOTIFICATIONS_ALL) {
			return "all";
		}
		else if (notifications == NOTIFICATIONS_NONE) {
			return "none";
		}
		else {
			return null;
		}
	}

	public static String getRoleLabel(int role) {
		if (role == ROLE_MANAGER) {
			return "manager";
		}
		else if (role == ROLE_MEMBER) {
			return "member";
		}
		else if (role == ROLE_WATCHER) {
			return "watcher";
		}
		else {
			return "none";
		}
	}

}