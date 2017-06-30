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

package com.liferay.osb.hook.upgrade.v1_0_0;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetAudit;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.model.AssetStatsWeek;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetAuditLocalServiceUtil;
import com.liferay.osb.service.AssetStatsWeekLocalServiceUtil;
import com.liferay.osb.util.comparator.AssetAuditCreateDateComparator;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Peter Shin
 */
public class UpgradeAssetStatsWeek extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAssetStatsWeeks();
	}

	protected void updateAssetStatsWeeks() throws Exception {
		if (tableHasColumn("OSB_AssetStatsWeek", "startDate")) {
			runSQL("alter table OSB_AssetStatsWeek drop column startDate");
		}

		if (tableHasColumn("OSB_AssetStatsWeek", "endDate")) {
			runSQL("alter table OSB_AssetStatsWeek drop column endDate");
		}

		List<AssetAudit> assetAudits =
			AssetAuditLocalServiceUtil.getAssetAudits(
				0, 1, new AssetAuditCreateDateComparator(true));

		if (assetAudits.isEmpty()) {
			return;
		}

		AssetAudit firstAssetAudit = assetAudits.get(0);

		Calendar calendar = CalendarFactoryUtil.getCalendar(
			TimeZoneUtil.getTimeZone(StringPool.UTC));

		calendar.setTime(firstAssetAudit.getCreateDate());

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		int count = AssetStatsWeekLocalServiceUtil.getAssetStatsWeeksCount();

		int pages = count / _DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * _DEFAULT_INTERVAL);

			int end = start + _DEFAULT_INTERVAL;

			List<AssetStatsWeek> assetStatsWeeks =
				AssetStatsWeekLocalServiceUtil.getAssetStatsWeeks(start, end);

			for (AssetStatsWeek assetStatsWeek : assetStatsWeeks) {
				AssetStatsWeekLocalServiceUtil.deleteAssetStatsWeek(
					assetStatsWeek);
			}
		}

		Date now = DateUtil.newDate();

		count = AppEntryLocalServiceUtil.getAppEntriesCount();

		pages = count / _DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * _DEFAULT_INTERVAL);

			int end = start + _DEFAULT_INTERVAL;

			List<AppEntry> appEntries = AppEntryLocalServiceUtil.getAppEntries(
				start, end);

			for (AppEntry appEntry : appEntries) {
				String className = AppEntry.class.getName();
				long classPK = appEntry.getAppEntryId();

				updateAssetStatsWeeks(className, classPK, calendar, now);
			}
		}
	}

	protected void updateAssetStatsWeeks(
			String className, long classPK, Calendar calendar, Date now)
		throws SystemException {

		Calendar startCalendar = (Calendar)calendar.clone();

		while (startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			startCalendar.add(Calendar.DAY_OF_MONTH, -1);
		}

		Calendar endCalendar = (Calendar)calendar.clone();

		endCalendar.set(Calendar.HOUR_OF_DAY, 23);
		endCalendar.set(Calendar.MINUTE, 59);
		endCalendar.set(Calendar.SECOND, 59);

		while (endCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			endCalendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		do {
			int viewCount = AssetAuditLocalServiceUtil.getAssetAuditsCount(
				startCalendar.getTime(), endCalendar.getTime(), className,
				classPK, AssetAuditConstants.TYPE_VIEW);
			int downloadCount = AssetAuditLocalServiceUtil.getAssetAuditsCount(
				startCalendar.getTime(), endCalendar.getTime(), className,
				classPK, AssetAuditConstants.TYPE_DOWNLOAD);
			int purchaseCount = AssetAuditLocalServiceUtil.getAssetAuditsCount(
				startCalendar.getTime(), endCalendar.getTime(), className,
				classPK, AssetAuditConstants.TYPE_PURCHASE);

			AssetStatsWeekLocalServiceUtil.updateAssetStatsWeek(
				className, classPK, endCalendar.get(Calendar.WEEK_OF_YEAR),
				endCalendar.get(Calendar.YEAR), viewCount, downloadCount,
				purchaseCount, StringPool.BLANK);

			startCalendar.add(Calendar.WEEK_OF_YEAR, 1);

			endCalendar.add(Calendar.WEEK_OF_YEAR, 1);
		}
		while (DateUtil.compareTo(startCalendar.getTime(), now) < 0);
	}

	private static final int _DEFAULT_INTERVAL = 200;

}