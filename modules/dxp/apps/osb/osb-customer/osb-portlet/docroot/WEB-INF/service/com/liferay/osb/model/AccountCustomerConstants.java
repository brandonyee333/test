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

/**
 * @author Kyle Bischof
 */
public class AccountCustomerConstants {

	public static final int NOTIFICATIONS_ALL = 1;

	public static final int NOTIFICATIONS_NONE = 2;

	public static final int ROLE_DEVELOPER = 2;

	public static final int ROLE_MANAGER = 1;

	public static final int ROLE_SALES = 3;

	public static final int ROLE_WATCHER = 4;

	public static final int[] ROLES =
		{ROLE_DEVELOPER, ROLE_MANAGER, ROLE_SALES, ROLE_WATCHER};

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
		if (role == ROLE_DEVELOPER) {
			return "developer";
		}
		else if (role == ROLE_MANAGER) {
			return "manager";
		}
		else if (role == ROLE_SALES) {
			return "sales";
		}
		else if (role == ROLE_WATCHER) {
			return "watcher";
		}
		else {
			return null;
		}
	}

}