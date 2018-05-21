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

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class AccountWorkerConstants {

	public static final int NOTIFICATIONS_ALL = 1;

	public static final int NOTIFICATIONS_NONE = 2;

	public static final int NOTIFICATIONS_SALES = 3;

	public static final int NOTIFICATIONS_TICKETS = 4;

	public static final int ROLE_ADVOCACY_SPECIALIST = 4;

	public static final int ROLE_EXPERIENCE_MANAGER = 1;

	public static final int ROLE_MANAGED_SERVICES = 5;

	public static final int ROLE_SALES = 2;

	public static final int ROLE_SALES_MANAGER = 3;

	public static final int[] ROLES_DEPRECATED =
		{ROLE_EXPERIENCE_MANAGER, ROLE_SALES_MANAGER};

	public static String getKey(long userId, long role) {
		return userId + StringPool.UNDERLINE + role;
	}

	public static String getNotificationsLabel(int notifications) {
		if (notifications == NOTIFICATIONS_ALL) {
			return "all";
		}
		else if (notifications == NOTIFICATIONS_NONE) {
			return "none";
		}
		else if (notifications == NOTIFICATIONS_SALES) {
			return "sales";
		}
		else if (notifications == NOTIFICATIONS_TICKETS) {
			return "tickets";
		}
		else {
			return null;
		}
	}

	public static String getRoleLabel(int role) {
		if (role == ROLE_ADVOCACY_SPECIALIST) {
			return "advocacy-specialist";
		}
		else if (role == ROLE_EXPERIENCE_MANAGER) {
			return "experience-manager";
		}
		else if (role == ROLE_MANAGED_SERVICES) {
			return "managed-services";
		}
		else if (role == ROLE_SALES) {
			return "sales";
		}
		else if (role == ROLE_SALES_MANAGER) {
			return "sales-manager";
		}
		else {
			return null;
		}
	}

}