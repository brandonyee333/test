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

package com.liferay.osb.marketplacedeveloperapps.messaging;

import com.liferay.compat.portal.kernel.util.BigDecimalUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetAudit;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.model.AssetStatsMonth;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetAuditLocalServiceUtil;
import com.liferay.osb.service.AssetStatsMonthLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.math.BigDecimal;

import java.util.Calendar;
import java.util.List;

/**
 * @author Peter Shin
 * @author Ryan Park
 */
public class AssetStatsMonthMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		updateAssetStatsMonths();
	}

	protected void updateAssetStatsMonth(
			String className, long classPK, Calendar startCalendar,
			Calendar endCalendar)
		throws Exception {

		int viewCount = AssetAuditLocalServiceUtil.getAssetAuditsCount(
			startCalendar.getTime(), endCalendar.getTime(), className, classPK,
			AssetAuditConstants.TYPE_VIEW);
		int downloadCount = AssetAuditLocalServiceUtil.getAssetAuditsCount(
			startCalendar.getTime(), endCalendar.getTime(), className, classPK,
			AssetAuditConstants.TYPE_DOWNLOAD);
		int purchaseCount = AssetAuditLocalServiceUtil.getAssetAuditsCount(
			startCalendar.getTime(), endCalendar.getTime(), className, classPK,
			AssetAuditConstants.TYPE_PURCHASE);

		UnicodeProperties currencyCodeRevenuesProperties =
			new UnicodeProperties(true);

		List<AssetAudit> assetAudits =
			AssetAuditLocalServiceUtil.getAssetAudits(
				startCalendar.getTime(), endCalendar.getTime(), className,
				classPK, AssetAuditConstants.TYPE_PURCHASE, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (AssetAudit assetAudit : assetAudits) {
			if (Validator.isNull(assetAudit.getCurrencyCode())) {
				continue;
			}

			String currencyCodeRevenue =
				currencyCodeRevenuesProperties.getProperty(
					assetAudit.getCurrencyCode(), "0");

			double amount = BigDecimalUtil.add(
				new BigDecimal(currencyCodeRevenue), assetAudit.getPrice());

			currencyCodeRevenuesProperties.setProperty(
				assetAudit.getCurrencyCode(), String.valueOf(amount));
		}

		AssetStatsMonthLocalServiceUtil.updateAssetStatsMonth(
			className, classPK, endCalendar.get(Calendar.MONTH),
			endCalendar.get(Calendar.YEAR), viewCount, downloadCount,
			purchaseCount, currencyCodeRevenuesProperties.toString());
	}

	protected void updateAssetStatsMonths() throws Exception {
		Calendar calendar = CalendarFactoryUtil.getCalendar(
			TimeZoneUtil.getTimeZone(StringPool.UTC));

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Calendar startCalendar = (Calendar)calendar.clone();

		Calendar endCalendar = (Calendar)calendar.clone();

		endCalendar.set(Calendar.HOUR_OF_DAY, 23);
		endCalendar.set(Calendar.MINUTE, 59);
		endCalendar.set(Calendar.SECOND, 59);

		endCalendar.add(Calendar.MONTH, 1);
		endCalendar.add(Calendar.DAY_OF_MONTH, -1);

		int count = AppEntryLocalServiceUtil.getAppEntriesCount();

		int pages = count / _DEFAULT_INTERVAL;

		for (int i = 0; i <= pages; i++) {
			int start = (i * _DEFAULT_INTERVAL);

			int end = start + _DEFAULT_INTERVAL;

			List<AppEntry> appEntries = AppEntryLocalServiceUtil.getAppEntries(
				start, end);

			for (AppEntry appEntry : appEntries) {
				String className = AppEntry.class.getName();
				long classPK = appEntry.getAppEntryId();

				updatePreviousAssetStatsMonth(
					className, classPK, startCalendar, endCalendar);

				updateAssetStatsMonth(
					className, classPK, startCalendar, endCalendar);
			}
		}
	}

	protected void updatePreviousAssetStatsMonth(
			String className, long classPK, Calendar startCalendar,
			Calendar endCalendar)
		throws Exception {

		AssetStatsMonth assetStatsMonth =
			AssetStatsMonthLocalServiceUtil.fetchAssetStatsMonth(
				className, classPK, endCalendar.get(Calendar.MONTH),
				endCalendar.get(Calendar.YEAR));

		if (assetStatsMonth != null) {
			return;
		}

		Calendar previousStartCalendar = (Calendar)startCalendar.clone();

		previousStartCalendar.add(Calendar.MONTH, -1);

		Calendar previousEndCalendar = (Calendar)endCalendar.clone();

		previousEndCalendar.set(Calendar.DAY_OF_MONTH, 1);

		previousEndCalendar.add(Calendar.DAY_OF_MONTH, -1);

		AssetStatsMonth previousAssetStatsMonth =
			AssetStatsMonthLocalServiceUtil.fetchAssetStatsMonth(
				className, classPK, previousEndCalendar.get(Calendar.MONTH),
				previousEndCalendar.get(Calendar.YEAR));

		if (previousAssetStatsMonth == null) {
			return;
		}

		updateAssetStatsMonth(
			className, classPK, previousStartCalendar, previousEndCalendar);
	}

	private static final int _DEFAULT_INTERVAL = 200;

}