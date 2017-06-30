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
public class AssetListConstants {

	public static final int TYPE_APP_UPDATES = 7;

	public static final int TYPE_BANNER = 3;

	public static final int TYPE_FEATURED = 1;

	public static final int TYPE_MEDIUM_SIZED_CENTER_AD_1 = 4;

	public static final int TYPE_MEDIUM_SIZED_CENTER_AD_2 = 5;

	public static final int TYPE_MEDIUM_SIZED_CENTER_AD_3 = 6;

	public static final int TYPE_NEW_AND_INTERESTING = 2;

	public static final int TYPE_NEW_APPS = 8;

	public static final int[] TYPES_EDITABLE_LISTS = {
		TYPE_FEATURED, TYPE_NEW_AND_INTERESTING, TYPE_BANNER,
		TYPE_MEDIUM_SIZED_CENTER_AD_1, TYPE_MEDIUM_SIZED_CENTER_AD_2,
		TYPE_MEDIUM_SIZED_CENTER_AD_3
	};

	public static String getTypeLabel(int type) {
		return getTypeLabel(type, 0);
	}

	public static String getTypeLabel(int type, long assetCategoryId) {
		if (type == TYPE_APP_UPDATES) {
			return "app-updates";
		}
		else if (type == TYPE_BANNER) {
			return "banner";
		}
		else if ((type == TYPE_FEATURED) && (assetCategoryId > 0)) {
			return "featured-x";
		}
		else if ((type == TYPE_FEATURED) && (assetCategoryId <= 0)) {
			return "featured-apps";
		}
		else if (type == TYPE_MEDIUM_SIZED_CENTER_AD_1) {
			return "medium-sized-center-ad-1";
		}
		else if (type == TYPE_MEDIUM_SIZED_CENTER_AD_2) {
			return "medium-sized-center-ad-2";
		}
		else if (type == TYPE_MEDIUM_SIZED_CENTER_AD_3) {
			return "medium-sized-center-ad-3";
		}
		else if (type == TYPE_NEW_APPS) {
			return "new-apps";
		}
		else if (type == TYPE_NEW_AND_INTERESTING) {
			return "new-and-interesting";
		}
		else {
			return "N/A";
		}
	}

}