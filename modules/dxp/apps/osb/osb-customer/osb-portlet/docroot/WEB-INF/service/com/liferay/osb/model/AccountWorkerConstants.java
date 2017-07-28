/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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