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

package com.liferay.osb.customer.web.internal.constants;

import java.util.Locale;

/**
 * @author Alan Zhang
 */
public class OSBJournalArticleConstants {

	public static final Locale[] LOCALES = {Locale.JAPAN, Locale.US};

	public static final long PERMISSION_TYPE_EXTERNAL = 2;

	public static final long PERMISSION_TYPE_INTERNAL = 1;

	public static final long[] PERMISSION_TYPES = {
		PERMISSION_TYPE_INTERNAL, PERMISSION_TYPE_EXTERNAL
	};

	public static String getPermissionTypeLabel(long permissionType) {
		if (permissionType == PERMISSION_TYPE_EXTERNAL) {
			return "external";
		}
		else {
			return "internal";
		}
	}

}