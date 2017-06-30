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

package com.liferay.osb.marketplaceadmin.messaging;

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.marketplace.util.MarketplaceEmailUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.util.comparator.AssetReceiptLicenseEndDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.DateUtil;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Joan Kim
 * @author Ryan Park
 */
public class SendExpiringAssetReceiptNotificationsMessageListener
	extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Date now = new Date();

		for (int daysRemainingInterval : DAYS_REMAINING_INTERVALS) {
			sendMail(now, daysRemainingInterval);
		}
	}

	protected AssetReceiptLicense getAssetReceiptLicense(long assetReceiptId)
		throws Exception {

		List<AssetReceiptLicense> assetReceiptLicenses =
			AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(
				assetReceiptId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				new AssetReceiptLicenseEndDateComparator(false));

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			if (assetReceiptLicense.getLicenseLifetime() ==
					AssetLicenseConstants.LIFETIME_TRIAL) {

				continue;
			}

			return assetReceiptLicense;
		}

		return null;
	}

	protected void sendMail(Date now, int daysRemaining) throws Exception {
		Date endDateGT = new Date(
			now.getTime() + ((daysRemaining - 1) * Time.DAY));
		Date endDateLT = new Date(now.getTime() + (daysRemaining * Time.DAY));

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("endDateGT", endDateGT);
		params.put("endDateLT", endDateLT);
		params.put(
			"licenseType", AssetLicenseConstants.LICENSE_TYPE_PRODUCTION);

		List<AssetReceipt> assetReceipts =
			AssetReceiptLocalServiceUtil.getAssetReceipts(
				PortalUtil.getClassNameId(AppEntry.class), params,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (AssetReceipt assetReceipt : assetReceipts) {
			AssetReceiptLicense assetReceiptLicense = getAssetReceiptLicense(
				assetReceipt.getAssetReceiptId());

			if (assetReceiptLicense == null) {
				continue;
			}

			if (DateUtil.compareTo(
					endDateLT, assetReceiptLicense.getEndDate()) < 0) {

				continue;
			}

			MarketplaceEmailUtil.sendAssetReceiptReminderMail(
				assetReceipt, daysRemaining);
		}
	}

	protected final int[] DAYS_REMAINING_INTERVALS = new int[] {90, 60, 30, 0};

}