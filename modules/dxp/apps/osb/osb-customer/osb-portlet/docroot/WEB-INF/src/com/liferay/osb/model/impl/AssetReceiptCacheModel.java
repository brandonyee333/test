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

import com.liferay.osb.model.AssetReceipt;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetReceipt in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceipt
 * @generated
 */
public class AssetReceiptCacheModel implements CacheModel<AssetReceipt>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{assetReceiptId=");
		sb.append(assetReceiptId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", assetEntryId=");
		sb.append(assetEntryId);
		sb.append(", ownerClassNameId=");
		sb.append(ownerClassNameId);
		sb.append(", ownerClassPK=");
		sb.append(ownerClassPK);
		sb.append(", legalEntityName=");
		sb.append(legalEntityName);
		sb.append(", productClassNameId=");
		sb.append(productClassNameId);
		sb.append(", productClassPK=");
		sb.append(productClassPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", currencyEntryId=");
		sb.append(currencyEntryId);
		sb.append(", actualPrice=");
		sb.append(actualPrice);
		sb.append("}");

		return sb.toString();
	}

	public AssetReceipt toEntityModel() {
		AssetReceiptImpl assetReceiptImpl = new AssetReceiptImpl();

		assetReceiptImpl.setAssetReceiptId(assetReceiptId);
		assetReceiptImpl.setUserId(userId);

		if (userName == null) {
			assetReceiptImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetReceiptImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetReceiptImpl.setCreateDate(null);
		}
		else {
			assetReceiptImpl.setCreateDate(new Date(createDate));
		}

		assetReceiptImpl.setAssetEntryId(assetEntryId);
		assetReceiptImpl.setOwnerClassNameId(ownerClassNameId);
		assetReceiptImpl.setOwnerClassPK(ownerClassPK);

		if (legalEntityName == null) {
			assetReceiptImpl.setLegalEntityName(StringPool.BLANK);
		}
		else {
			assetReceiptImpl.setLegalEntityName(legalEntityName);
		}

		assetReceiptImpl.setProductClassNameId(productClassNameId);
		assetReceiptImpl.setProductClassPK(productClassPK);
		assetReceiptImpl.setType(type);
		assetReceiptImpl.setCurrencyEntryId(currencyEntryId);
		assetReceiptImpl.setActualPrice(actualPrice);

		assetReceiptImpl.resetOriginalValues();

		return assetReceiptImpl;
	}

	public long assetReceiptId;
	public long userId;
	public String userName;
	public long createDate;
	public long assetEntryId;
	public long ownerClassNameId;
	public long ownerClassPK;
	public String legalEntityName;
	public long productClassNameId;
	public long productClassPK;
	public long type;
	public long currencyEntryId;
	public double actualPrice;
}