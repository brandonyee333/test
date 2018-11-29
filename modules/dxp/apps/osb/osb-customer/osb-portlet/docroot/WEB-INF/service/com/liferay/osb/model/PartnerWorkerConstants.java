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

import com.liferay.osb.util.OSBConstants;

/**
 * @author Kyle Bischof
 */
public class PartnerWorkerConstants {

	public static final long[] OSB_CORP_ENTRY_ROLE_IDS = {
		OSBConstants.ROLE_OSB_CORP_PARTNER_MANAGER_ID,
		OSBConstants.ROLE_OSB_CORP_PARTNER_MEMBER_ID,
		OSBConstants.ROLE_OSB_CORP_PARTNER_WATCHER_ID
	};

	public static final int ROLE_MANAGER = 1;

	public static final int ROLE_MEMBER = 2;

	public static final int ROLE_WATCHER = 3;

	public static long getCorpEntryRoleId(int role) {
		if (role == ROLE_MANAGER) {
			return OSBConstants.ROLE_OSB_CORP_PARTNER_MANAGER_ID;
		}
		else if (role == ROLE_MEMBER) {
			return OSBConstants.ROLE_OSB_CORP_PARTNER_MEMBER_ID;
		}
		else if (role == ROLE_WATCHER) {
			return OSBConstants.ROLE_OSB_CORP_PARTNER_WATCHER_ID;
		}
		else {
			return 0;
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