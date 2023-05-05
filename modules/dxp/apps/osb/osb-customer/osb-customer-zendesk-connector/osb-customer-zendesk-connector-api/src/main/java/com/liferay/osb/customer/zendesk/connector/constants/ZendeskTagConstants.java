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

	public static final String GS_OPPORTUNITY = "gs_opportunity";

	public static final String OSB_CUSTOMER = "osb_customer";

	public static final String OSB_KNOWLEDGE_BASE = "osb_kb";

	public static final String OSB_LIFERAY_EMPLOYEE = "osb_lr_employee";

	public static final String OSB_PARTNER = "osb_partner";

	public static final String PREFIX_OSB = "osb_";

	public static final String WATCHER = "_watcher";

	public static String getWatcherTag(long externalId) {
		return PREFIX_OSB + String.valueOf(externalId) + WATCHER;
	}

}