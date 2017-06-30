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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AssetStatsConstants;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetStatsMonthImpl extends AssetStatsMonthBaseImpl {

	public AssetStatsMonthImpl() {
	}

	public Date getStartDate(TimeZone timeZone, Locale locale) {
		Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

		calendar.set(Calendar.YEAR, getYear());
		calendar.set(Calendar.MONTH, getMonth());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public long getStats(int statsType) {
		if (statsType == AssetStatsConstants.STATS_TYPE_VIEW_COUNT) {
			return getViewCount();
		}
		else if (statsType == AssetStatsConstants.STATS_TYPE_DOWNLOAD_COUNT) {
			return getDownloadCount();
		}
		else if (statsType == AssetStatsConstants.STATS_TYPE_PURCHASE_COUNT) {
			return getPurchaseCount();
		}

		return 0;
	}

}