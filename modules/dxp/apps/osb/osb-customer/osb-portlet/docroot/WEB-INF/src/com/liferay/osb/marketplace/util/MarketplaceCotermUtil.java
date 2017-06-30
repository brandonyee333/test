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

import com.liferay.compat.portal.kernel.util.BigDecimalUtil;
import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPricing;
import com.liferay.osb.model.AppPricingItem;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.AssetReceiptSupport;
import com.liferay.osb.model.CountryAppPricing;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppPricingItemLocalServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptSupportLocalServiceUtil;
import com.liferay.osb.service.CountryAppPricingLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AssetLicenseLicenseTypeAllotmentComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Douglas Wong
 * @author Joan Kim
 */
public class MarketplaceCotermUtil {

	public static double getCotermLicensePrice(
			List<AssetReceiptLicense> assetReceiptLicenses, long countryId,
			long appEntryId, int usageType)
		throws PortalException, SystemException {

		Map<Date, List<AssetReceiptLicense>> assetReceiptLicensesMap =
			new HashMap<Date, List<AssetReceiptLicense>>();

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			List<AssetReceiptLicense> endDateAssetReceiptLicenses =
				assetReceiptLicensesMap.get(assetReceiptLicense.getEndDate());

			if (endDateAssetReceiptLicenses == null) {
				endDateAssetReceiptLicenses =
					new ArrayList<AssetReceiptLicense>();

				assetReceiptLicensesMap.put(
					assetReceiptLicense.getEndDate(),
					endDateAssetReceiptLicenses);
			}

			endDateAssetReceiptLicenses.add(assetReceiptLicense);
		}

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		List<AssetLicense> assetLicenses =
			AssetLicenseLocalServiceUtil.getAssetLicenses(
				AppVersion.class.getName(), appVersion.getAppVersionId(),
				usageType, AssetLicenseConstants.LICENSE_TYPE_PRODUCTION,
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
				new AssetLicenseLicenseTypeAllotmentComparator(false));

		Date newEndDate = new Date(
			System.currentTimeMillis() +
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION);

		double price = 0.0;

		for (Map.Entry<Date, List<AssetReceiptLicense>> entry :
				assetReceiptLicensesMap.entrySet()) {

			Date endDate = (Date)entry.getKey();
			List<AssetReceiptLicense> endDateAssetReceiptLicenses =
				(List<AssetReceiptLicense>)entry.getValue();

			long totalAllotment = 0;

			for (AssetReceiptLicense endDateAssetReceiptLicense :
					endDateAssetReceiptLicenses) {

				totalAllotment +=
					endDateAssetReceiptLicense.getLicenseTypeAllotment();
			}

			for (AssetLicense assetLicense : assetLicenses) {
				long quantity =
					totalAllotment / assetLicense.getLicenseTypeAllotment();

				totalAllotment -= quantity;

				long time = newEndDate.getTime() - endDate.getTime();

				double prorate =
					(double)time / AssetLicenseConstants.LIFETIME_SUBSCRIPTION;

				AppPricingItem appPricingItem =
					AppPricingItemLocalServiceUtil.fetchAppPricingItem(
						appVersion.getAppVersionId(), countryId,
						assetLicense.getAssetLicenseId());

				double unitPrice = BigDecimalUtil.multiply(
					appPricingItem.getPrice(), prorate);

				double linePrice = BigDecimalUtil.multiply(quantity, unitPrice);

				price = BigDecimalUtil.add(price, linePrice);

				if (totalAllotment == 0) {
					break;
				}
				else if (totalAllotment < 0) {
					throw new UnsupportedOperationException();
				}
			}

			if (totalAllotment > 0) {
				throw new UnsupportedOperationException();
			}
		}

		return BigDecimalUtil.scale(price, 2, RoundingMode.HALF_UP);
	}

	public static double getCotermLicensePrice(
			long assetReceiptId, long countryId, long appEntryId, int usageType)
		throws PortalException, SystemException {

		AssetReceipt assetReceipt =
			AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

		List<AssetReceiptLicense> assetReceiptLicenses =
			AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenses(
				assetReceipt.getAssetReceiptId(), usageType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		return getCotermLicensePrice(
			assetReceiptLicenses, countryId, appEntryId, usageType);
	}

	public static long getCotermProratedDays(long assetReceiptId, int usageType)
		throws PortalException, SystemException {

		AssetReceipt assetReceipt =
			AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
			assetReceipt.getProductClassPK());

		Date endDate = null;

		if (appEntry.getLicenseLifetime() ==
				AssetLicenseConstants.LIFETIME_PERPETUAL) {

			endDate = assetReceipt.getActiveAssetReceiptSupportsEndDate(
				usageType);
		}
		else {
			endDate = assetReceipt.getActiveAssetReceiptLicensesEndDate(
				usageType);
		}

		if (endDate == null) {
			return 0;
		}

		Date newEndDate = new Date(
			System.currentTimeMillis() +
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION);

		return (newEndDate.getTime() - endDate.getTime()) / Time.DAY;
	}

	public static double getCotermSupportPrice(
			long assetReceiptId, long countryId, long appEntryId, int usageType)
		throws PortalException, SystemException {

		AssetReceipt assetReceipt =
			AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

		List<AssetReceiptSupport> assetReceiptSupports =
			AssetReceiptSupportLocalServiceUtil.getActiveAssetReceiptSupports(
				assetReceipt.getAssetReceiptId(), usageType, 0, 1);

		long licenseTypeAllotment =
			AssetReceiptLicenseLocalServiceUtil.
				getActiveAssetReceiptLicenseTypeAllotment(
					assetReceipt.getAssetReceiptId(), usageType);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		CountryAppPricing countryAppPricing =
			CountryAppPricingLocalServiceUtil.fetchCountryAppPricing(
				appVersion.getAppVersionId(), countryId);

		AppPricing appPricing = countryAppPricing.getAppPricing();

		double price =
			licenseTypeAllotment * appPricing.getSupportPrice(usageType);

		if (assetReceiptSupports.isEmpty()) {
			return price;
		}

		AssetReceiptSupport assetReceiptSupport = assetReceiptSupports.get(0);

		Date newEndDate = new Date(
			System.currentTimeMillis() +
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION);

		Date endDate = assetReceiptSupport.getEndDate();

		long time = newEndDate.getTime() - endDate.getTime();

		double prorate =
			(double)time / AssetLicenseConstants.LIFETIME_SUBSCRIPTION;

		return BigDecimalUtil.multiply(prorate, price);
	}

}