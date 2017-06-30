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

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.ecommerce.model.ECDocumentItem;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Date;

/**
 * @author Douglas Wong
 */
public class ECDocumentItemExtraSettings {

	public ECDocumentItemExtraSettings() {
		_extraSettingsProperties = new UnicodeProperties(true);
	}

	public ECDocumentItemExtraSettings(ECDocumentItem ecDocumentItem) {
		if (ecDocumentItem != null) {
			_extraSettingsProperties =
				ecDocumentItem.getExtraSettingsProperties();
		}
		else {
			_extraSettingsProperties = new UnicodeProperties(true);
		}
	}

	public Date getEndDate() {
		long endDateTime = GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceEndDate"));

		if (endDateTime <= 0) {
			return null;
		}

		return new Date(endDateTime);
	}

	public String getItemType() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplaceItemType"));
	}

	public long[] getProratedAssetReceiptLicenseIds() {
		String assetReceiptLicenseIds = _extraSettingsProperties.getProperty(
			"marketplaceProratedAssetReceiptLicenseIds");

		return StringUtil.split(assetReceiptLicenseIds, 0L);
	}

	public long getProratedDays() {
		return GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceProratedDays"));
	}

	public String getPurchaseType() {
		return GetterUtil.getString(
			_extraSettingsProperties.getProperty("marketplacePurchaseType"));
	}

	public Date getStartDate() {
		long startDateTime = GetterUtil.getLong(
			_extraSettingsProperties.getProperty("marketplaceStartDate"));

		if (startDateTime <= 0) {
			return null;
		}

		return new Date(startDateTime);
	}

	public int getUsageType() {
		return GetterUtil.getInteger(
			_extraSettingsProperties.getProperty("marketplaceUsageType"));
	}

	public void setEndDate(Date endDate) {
		_extraSettingsProperties.setProperty(
			"marketplaceEndDate", String.valueOf(endDate.getTime()));
	}

	public void setItemType(String itemType) {
		_extraSettingsProperties.setProperty(
			"marketplaceItemType", String.valueOf(itemType));
	}

	public void setProratedAssetReceiptLicenseIds(
		long[] assetReceiptLicenseIds) {

		_extraSettingsProperties.setProperty(
			"marketplaceProratedAssetReceiptLicenseIds",
			StringUtil.merge(assetReceiptLicenseIds));
	}

	public void setProratedDays(long proratedDays) {
		_extraSettingsProperties.setProperty(
			"marketplaceProratedDays", String.valueOf(proratedDays));
	}

	public void setPurchaseType(String purchaseType) {
		_extraSettingsProperties.setProperty(
			"marketplacePurchaseType", String.valueOf(purchaseType));
	}

	public void setStartDate(Date startDate) {
		_extraSettingsProperties.setProperty(
			"marketplaceStartDate", String.valueOf(startDate.getTime()));
	}

	public void setUsageType(int usageType) {
		_extraSettingsProperties.setProperty(
			"marketplaceUsageType", String.valueOf(usageType));
	}

	@Override
	public String toString() {
		return _extraSettingsProperties.toString();
	}

	private UnicodeProperties _extraSettingsProperties;

}