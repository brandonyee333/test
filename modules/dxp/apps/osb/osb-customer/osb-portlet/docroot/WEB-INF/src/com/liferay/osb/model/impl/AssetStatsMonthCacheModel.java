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

import com.liferay.osb.model.AssetStatsMonth;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AssetStatsMonth in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsMonth
 * @generated
 */
public class AssetStatsMonthCacheModel implements CacheModel<AssetStatsMonth>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{assetStatsMonthId=");
		sb.append(assetStatsMonthId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", month=");
		sb.append(month);
		sb.append(", year=");
		sb.append(year);
		sb.append(", viewCount=");
		sb.append(viewCount);
		sb.append(", downloadCount=");
		sb.append(downloadCount);
		sb.append(", purchaseCount=");
		sb.append(purchaseCount);
		sb.append(", currencyCodeRevenues=");
		sb.append(currencyCodeRevenues);
		sb.append("}");

		return sb.toString();
	}

	public AssetStatsMonth toEntityModel() {
		AssetStatsMonthImpl assetStatsMonthImpl = new AssetStatsMonthImpl();

		assetStatsMonthImpl.setAssetStatsMonthId(assetStatsMonthId);
		assetStatsMonthImpl.setClassNameId(classNameId);
		assetStatsMonthImpl.setClassPK(classPK);
		assetStatsMonthImpl.setMonth(month);
		assetStatsMonthImpl.setYear(year);
		assetStatsMonthImpl.setViewCount(viewCount);
		assetStatsMonthImpl.setDownloadCount(downloadCount);
		assetStatsMonthImpl.setPurchaseCount(purchaseCount);

		if (currencyCodeRevenues == null) {
			assetStatsMonthImpl.setCurrencyCodeRevenues(StringPool.BLANK);
		}
		else {
			assetStatsMonthImpl.setCurrencyCodeRevenues(currencyCodeRevenues);
		}

		assetStatsMonthImpl.resetOriginalValues();

		return assetStatsMonthImpl;
	}

	public long assetStatsMonthId;
	public long classNameId;
	public long classPK;
	public int month;
	public int year;
	public long viewCount;
	public long downloadCount;
	public long purchaseCount;
	public String currencyCodeRevenues;
}