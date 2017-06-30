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

import com.liferay.osb.model.AssetReceiptRedeemToken;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AssetReceiptRedeemToken in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptRedeemToken
 * @generated
 */
public class AssetReceiptRedeemTokenCacheModel implements CacheModel<AssetReceiptRedeemToken>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{AssetReceiptRedeemTokenId=");
		sb.append(AssetReceiptRedeemTokenId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", redeemEmailAddress=");
		sb.append(redeemEmailAddress);
		sb.append(", redeemDate=");
		sb.append(redeemDate);
		sb.append(", token=");
		sb.append(token);
		sb.append("}");

		return sb.toString();
	}

	public AssetReceiptRedeemToken toEntityModel() {
		AssetReceiptRedeemTokenImpl assetReceiptRedeemTokenImpl = new AssetReceiptRedeemTokenImpl();

		assetReceiptRedeemTokenImpl.setAssetReceiptRedeemTokenId(AssetReceiptRedeemTokenId);
		assetReceiptRedeemTokenImpl.setUserId(userId);

		if (userName == null) {
			assetReceiptRedeemTokenImpl.setUserName(StringPool.BLANK);
		}
		else {
			assetReceiptRedeemTokenImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetReceiptRedeemTokenImpl.setCreateDate(null);
		}
		else {
			assetReceiptRedeemTokenImpl.setCreateDate(new Date(createDate));
		}

		assetReceiptRedeemTokenImpl.setClassNameId(classNameId);
		assetReceiptRedeemTokenImpl.setClassPK(classPK);

		if (redeemEmailAddress == null) {
			assetReceiptRedeemTokenImpl.setRedeemEmailAddress(StringPool.BLANK);
		}
		else {
			assetReceiptRedeemTokenImpl.setRedeemEmailAddress(redeemEmailAddress);
		}

		if (redeemDate == Long.MIN_VALUE) {
			assetReceiptRedeemTokenImpl.setRedeemDate(null);
		}
		else {
			assetReceiptRedeemTokenImpl.setRedeemDate(new Date(redeemDate));
		}

		if (token == null) {
			assetReceiptRedeemTokenImpl.setToken(StringPool.BLANK);
		}
		else {
			assetReceiptRedeemTokenImpl.setToken(token);
		}

		assetReceiptRedeemTokenImpl.resetOriginalValues();

		return assetReceiptRedeemTokenImpl;
	}

	public long AssetReceiptRedeemTokenId;
	public long userId;
	public String userName;
	public long createDate;
	public long classNameId;
	public long classPK;
	public String redeemEmailAddress;
	public long redeemDate;
	public String token;
}