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
public class AccountCustomerConstants {

	public static final int ROLE_DEVELOPER = 2;

	public static final int ROLE_MANAGER = 1;

	public static final int ROLE_SALES = 3;

	public static final int ROLE_WATCHER = 4;

	public static final int[] ROLES = {
		ROLE_DEVELOPER, ROLE_MANAGER, ROLE_SALES, ROLE_WATCHER
	};

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