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

package com.liferay.osb.customer.web.internal.constants;

import java.util.Locale;

/**
 * @author Alan Zhang
 */
public class OSBJournalArticleConstants {

	public static final Locale[] LOCALES = {Locale.JAPAN, Locale.US};

	public static final long PERMISSION_TYPE_EXTERNAL = 2;

	public static final long PERMISSION_TYPE_INTERNAL = 1;

	public static final long[] PERMISSION_TYPES =
		{PERMISSION_TYPE_INTERNAL, PERMISSION_TYPE_EXTERNAL};

	public static String getPermissionTypeLabel(long permissionType) {
		if (permissionType == PERMISSION_TYPE_EXTERNAL) {
			return "external";
		}
		else {
			return "internal";
		}
	}

}