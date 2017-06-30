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

import com.liferay.osb.model.AssetReceiptSupport;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetReceiptSupport in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptSupport
 * @generated
 */
public class AssetReceiptSupportCacheModel implements CacheModel<AssetReceiptSupport>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", assetReceiptSupportId=");
		sb.append(assetReceiptSupportId);
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
		sb.append(", supportLifetime=");
		sb.append(supportLifetime);
		sb.append("}");

		return sb.toString();
	}

	public AssetReceiptSupport toEntityModel() {
		AssetReceiptSupportImpl assetReceiptSupportImpl = new AssetReceiptSupportImpl();

		if (uuid == null) {
			assetReceiptSupportImpl.setUuid(StringPool.BLANK);
		}
		else {
			assetReceiptSupportImpl.setUuid(uuid);
		}

		assetReceiptSupportImpl.setAssetReceiptSupportId(assetReceiptSupportId);
		assetReceiptSupportImpl.setUserId(userId);

		if (userName == null) {
			assetReceiptSupportImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetReceiptSupportImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetReceiptSupportImpl.setCreateDate(null);
		}
		else {
			assetReceiptSupportImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetReceiptSupportImpl.setModifiedDate(null);
		}
		else {
			assetReceiptSupportImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetReceiptSupportImpl.setAssetReceiptId(assetReceiptId);
		assetReceiptSupportImpl.setAssetLicenseId(assetLicenseId);
		assetReceiptSupportImpl.setAssetEntryId(assetEntryId);
		assetReceiptSupportImpl.setOwnerClassNameId(ownerClassNameId);
		assetReceiptSupportImpl.setOwnerClassPK(ownerClassPK);
		assetReceiptSupportImpl.setProductClassNameId(productClassNameId);
		assetReceiptSupportImpl.setProductClassPK(productClassPK);

		if (productId == null) {
			assetReceiptSupportImpl.setProductId(StringPool.BLANK);
		}
		else {
			assetReceiptSupportImpl.setProductId(productId);
		}

		if (startDate == Long.MIN_VALUE) {
			assetReceiptSupportImpl.setStartDate(null);
		}
		else {
			assetReceiptSupportImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			assetReceiptSupportImpl.setEndDate(null);
		}
		else {
			assetReceiptSupportImpl.setEndDate(new Date(endDate));
		}

		assetReceiptSupportImpl.setUsageType(usageType);
		assetReceiptSupportImpl.setSupportLifetime(supportLifetime);

		assetReceiptSupportImpl.resetOriginalValues();

		return assetReceiptSupportImpl;
	}

	public String uuid;
	public long assetReceiptSupportId;
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
	public long supportLifetime;
}