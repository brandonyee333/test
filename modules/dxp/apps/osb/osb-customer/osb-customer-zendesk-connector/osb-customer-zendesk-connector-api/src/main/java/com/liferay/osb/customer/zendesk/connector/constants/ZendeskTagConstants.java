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

package com.liferay.osb.customer.zendesk.connector.constants;

/**
 * @author Kyle Bischof
 */
public class ZendeskTagConstants {

	public static final String DEVELOPER_TOOLS = "developer_tools";

	public static final String ENTERPRISE_SEARCH = "enterprise_search";

	public static final String EXTENDED_PREMIUM_SUPPORT =
		"extended_premium_support";

	public static final String LIFERAY_ANALYTICS_CLOUD =
		"liferay_analytics_cloud";

	public static final String LIFERAY_COMMERCE = "liferay_commerce";

	public static final String LIFERAY_DXP = "liferay_dxp";

	public static final String LIFERAY_PORTAL = "liferay_portal";

	public static final String MANAGEMENT_TOOLS_LCS = "management_tools_lcs";

	public static final String MOBILE_DEVICE_DETECTION =
		"mobile_device_detection";

	public static final String MOBILE_EXPERIENCE = "mobile_experience";

	public static final String OSB_CUSTOMER = "osb_customer";

	public static final String OSB_KNOWLEDGE_BASE = "osb_kb";

	public static final String OSB_PARTNER = "osb_partner";

	public static final String PREFIX_OSB = "osb_";

	public static final String PRODUCTIVITY_TOOLS_SYNC =
		"productivity_tools_sync";

	public static final String SOCIAL_OFFICE = "social_office";

	public static final String WATCHER = "_watcher";

	public static String getWatcherTag(long externalId) {
		return PREFIX_OSB + String.valueOf(externalId) + WATCHER;
	}

}