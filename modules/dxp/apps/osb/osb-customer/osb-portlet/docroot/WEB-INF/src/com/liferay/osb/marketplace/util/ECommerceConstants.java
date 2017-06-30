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

package com.liferay.osb.marketplace.util;

import com.liferay.osb.model.AssetLicenseConstants;

/**
 * @author Ryan Park
 */
public class ECommerceConstants {

	public static final long CLASS_PK_DEVELOPER = -2;

	public static final long CLASS_PK_STANDARD = -1;

	public static final String EC_PRODUCT_ENTRY_NAME_MARKETPLACE_SUBSCRIPTION =
		"Liferay Marketplace Developer Subscription";

	public static final String EC_PRODUCT_TYPE_NAME_MARKETPLACE_APP =
		"Liferay Marketplace App";

	public static final String EC_PRODUCT_TYPE_NAME_MARKETPLACE_APP_SUPPORT =
		"Liferay Marketplace App Support";

	public static final String EC_PRODUCT_TYPE_NAME_MARKETPLACE_SUBSCRIPTION =
		"Liferay Marketplace Developer Subscription";

	public static final String STORE_NAME_MARKETPLACE = "Liferay Marketplace";

	public static long EC_PRODUCT_ENTRY_ID_MARKETPLACE_SUBSCRIPTION = 0;

	public static long EC_PRODUCT_TYPE_ID_MARKETPLACE_APP = 0;

	public static long EC_PRODUCT_TYPE_ID_MARKETPLACE_APP_SUPPORT = 0;

	public static long EC_PRODUCT_TYPE_ID_MARKETPLACE_SUBSCRIPTION = 0;

	public static long getClassPK(int usageType) {
		if (usageType == AssetLicenseConstants.USAGE_TYPE_DEVELOPER) {
			return ECommerceConstants.CLASS_PK_DEVELOPER;
		}
		else if (usageType == AssetLicenseConstants.USAGE_TYPE_STANDARD) {
			return ECommerceConstants.CLASS_PK_STANDARD;
		}
		else {
			return 0;
		}
	}

}