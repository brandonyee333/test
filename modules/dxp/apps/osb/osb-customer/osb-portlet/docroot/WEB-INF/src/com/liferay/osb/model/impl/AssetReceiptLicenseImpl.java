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

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptLicenseConstants;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

import java.util.Date;

/**
 * @author Amos Fong
 * @author Ryan Park
 */
public class AssetReceiptLicenseImpl extends AssetReceiptLicenseBaseImpl {

	public AssetReceiptLicenseImpl() {
	}

	public AssetEntry getAssetEntry() throws PortalException, SystemException {
		return AssetEntryLocalServiceUtil.getAssetEntry(getAssetEntryId());
	}

	public AssetLicense getAssetLicense()
		throws PortalException, SystemException {

		return AssetLicenseLocalServiceUtil.getAssetLicense(
			getAssetLicenseId());
	}

	public AssetReceipt getAssetReceipt()
		throws PortalException, SystemException {

		return AssetReceiptLocalServiceUtil.getAssetReceipt(
			getAssetReceiptId());
	}

	public int getAvailableLicenseKeyCount() throws SystemException {
		if (hasUnlimitedServers()) {
			return AssetReceiptLicenseConstants.LICENSE_KEY_COUNT_UNLIMITED;
		}

		int usedLicenseKeysCount =
			LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeysCount(
				getAssetReceiptLicenseId(), false, true);

		return (int)getLicenseTypeAllotment() - usedLicenseKeysCount;
	}

	public long getMaxUsers() {
		if (getLicenseType() != AssetLicenseConstants.LICENSE_TYPE_PER_USER) {
			return 0;
		}

		return getLicenseTypeAllotment();
	}

	public long getRemainingDays() {
		return getRemainingTime() / Time.DAY;
	}

	public long getRemainingTime() {
		Date endDate = getEndDate();

		return endDate.getTime() - System.currentTimeMillis();
	}

	public int getServers() {
		if (getLicenseType() != AssetLicenseConstants.LICENSE_TYPE_PRODUCTION) {
			return 0;
		}

		return (int)getLicenseTypeAllotment();
	}

	public String getUsageTypeLabel() {
		return AssetLicenseConstants.getUsageTypeLabel(getUsageType());
	}

	public boolean hasAvailableLicenseKeys() throws SystemException {
		if (hasUnlimitedServers()) {
			return true;
		}

		int count =
			LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeysCount(
				getAssetReceiptLicenseId(), false, true);

		if (count < getLicenseTypeAllotment()) {
			return true;
		}

		return false;
	}

	public boolean hasUnlimitedServers() {
		if ((getLicenseType() == AssetLicenseConstants.LICENSE_TYPE_PER_USER) ||
			(getLicenseType() ==
				AssetLicenseConstants.LICENSE_TYPE_UNLIMITED)) {

			return true;
		}

		return false;
	}

	public boolean isExpired() {
		if (getRemainingTime() < 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isExpiring() {
		if (getRemainingDays() <
				PortletPropsValues.MARKETPLACE_WARN_LICENSE_EXPIRATION) {

			return true;
		}
		else {
			return false;
		}
	}

}