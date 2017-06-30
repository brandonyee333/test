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

import com.liferay.osb.model.AssetReceiptLicense;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetReceiptLicense in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicense
 * @generated
 */
public class AssetReceiptLicenseCacheModel implements CacheModel<AssetReceiptLicense>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", assetReceiptLicenseId=");
		sb.append(assetReceiptLicenseId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", assetReceiptId=");
		sb.append(assetReceiptId);
		sb.append(", assetLicenseId=");
		sb.append(assetLicenseId);
		sb.append(", assetEntryId=");
		sb.append(assetEntryId);
		sb.append(", ownerClassNameId=");
		sb.append(ownerClassNameId);
		sb.append(", ownerClassPK=");
		sb.append(ownerClassPK);
		sb.append(", productClassNameId=");
		sb.append(productClassNameId);
		sb.append(", productClassPK=");
		sb.append(productClassPK);
		sb.append(", productId=");
		sb.append(productId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", usageType=");
		sb.append(usageType);
		sb.append(", licenseType=");
		sb.append(licenseType);
		sb.append(", licenseTypeAllotment=");
		sb.append(licenseTypeAllotment);
		sb.append(", licenseLifetime=");
		sb.append(licenseLifetime);
		sb.append("}");

		return sb.toString();
	}

	public AssetReceiptLicense toEntityModel() {
		AssetReceiptLicenseImpl assetReceiptLicenseImpl = new AssetReceiptLicenseImpl();

		if (uuid == null) {
			assetReceiptLicenseImpl.setUuid(StringPool.BLANK);
		}
		else {
			assetReceiptLicenseImpl.setUuid(uuid);
		}

		assetReceiptLicenseImpl.setAssetReceiptLicenseId(assetReceiptLicenseId);
		assetReceiptLicenseImpl.setUserId(userId);

		if (userName == null) {
			assetReceiptLicenseImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetReceiptLicenseImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetReceiptLicenseImpl.setCreateDate(null);
		}
		else {
			assetReceiptLicenseImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetReceiptLicenseImpl.setModifiedDate(null);
		}
		else {
			assetReceiptLicenseImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetReceiptLicenseImpl.setAssetReceiptId(assetReceiptId);
		assetReceiptLicenseImpl.setAssetLicenseId(assetLicenseId);
		assetReceiptLicenseImpl.setAssetEntryId(assetEntryId);
		assetReceiptLicenseImpl.setOwnerClassNameId(ownerClassNameId);
		assetReceiptLicenseImpl.setOwnerClassPK(ownerClassPK);
		assetReceiptLicenseImpl.setProductClassNameId(productClassNameId);
		assetReceiptLicenseImpl.setProductClassPK(productClassPK);

		if (productId == null) {
			assetReceiptLicenseImpl.setProductId(StringPool.BLANK);
		}
		else {
			assetReceiptLicenseImpl.setProductId(productId);
		}

		if (startDate == Long.MIN_VALUE) {
			assetReceiptLicenseImpl.setStartDate(null);
		}
		else {
			assetReceiptLicenseImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			assetReceiptLicenseImpl.setEndDate(null);
		}
		else {
			assetReceiptLicenseImpl.setEndDate(new Date(endDate));
		}

		assetReceiptLicenseImpl.setUsageType(usageType);
		assetReceiptLicenseImpl.setLicenseType(licenseType);
		assetReceiptLicenseImpl.setLicenseTypeAllotment(licenseTypeAllotment);
		assetReceiptLicenseImpl.setLicenseLifetime(licenseLifetime);

		assetReceiptLicenseImpl.resetOriginalValues();

		return assetReceiptLicenseImpl;
	}

	public String uuid;
	public long assetReceiptLicenseId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long assetReceiptId;
	public long assetLicenseId;
	public long assetEntryId;
	public long ownerClassNameId;
	public long ownerClassPK;
	public long productClassNameId;
	public long productClassPK;
	public String productId;
	public long startDate;
	public long endDate;
	public int usageType;
	public int licenseType;
	public long licenseTypeAllotment;
	public long licenseLifetime;
}