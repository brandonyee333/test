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
 * @author Ryan Park
 */
public class AssetStatsConstants {

	public static int INTERVAL_DAY = 1;

	public static int INTERVAL_MONTH = 3;

	public static int INTERVAL_WEEK = 2;

	public static int STATS_TYPE_DOWNLOAD_COUNT = 2;

	public static String STATS_TYPE_DOWNLOAD_COUNT_KEY = "totalDownloadCount";

	public static int STATS_TYPE_PURCHASE_COUNT = 3;

	public static String STATS_TYPE_PURCHASE_COUNT_KEY = "totalPurchaseCount";

	public static int STATS_TYPE_REVENUE_AMOUNT = 4;

	public static String STATS_TYPE_REVENUE_AMOUNT_KEY = "totalRevenueAmount";

	public static int STATS_TYPE_TOP_COMPANIES = 5;

	public static String STATS_TYPE_TOP_COMPANIES_KEY = "topCompanies";

	public static int STATS_TYPE_VIEW_COUNT = 1;

	public static String STATS_TYPE_VIEW_COUNT_KEY = "totalViewCount";

	public static String getStatsTypeKey(int statsType) {
		if (statsType == STATS_TYPE_TOP_COMPANIES) {
			return STATS_TYPE_TOP_COMPANIES_KEY;
		}
		else if (statsType == STATS_TYPE_DOWNLOAD_COUNT) {
			return STATS_TYPE_DOWNLOAD_COUNT_KEY;
		}
		else if (statsType == STATS_TYPE_PURCHASE_COUNT) {
			return STATS_TYPE_PURCHASE_COUNT_KEY;
		}
		else if (statsType == STATS_TYPE_REVENUE_AMOUNT) {
			return STATS_TYPE_REVENUE_AMOUNT_KEY;
		}
		else if (statsType == STATS_TYPE_VIEW_COUNT) {
			return STATS_TYPE_VIEW_COUNT_KEY;
		}

		return StringPool.BLANK;
	}

}