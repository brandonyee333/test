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

import com.liferay.osb.model.AssetStatsDay;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AssetStatsDay in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsDay
 * @generated
 */
public class AssetStatsDayCacheModel implements CacheModel<AssetStatsDay>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{assetStatsDayId=");
		sb.append(assetStatsDayId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", day=");
		sb.append(day);
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

	public AssetStatsDay toEntityModel() {
		AssetStatsDayImpl assetStatsDayImpl = new AssetStatsDayImpl();

		assetStatsDayImpl.setAssetStatsDayId(assetStatsDayId);
		assetStatsDayImpl.setClassNameId(classNameId);
		assetStatsDayImpl.setClassPK(classPK);
		assetStatsDayImpl.setDay(day);
		assetStatsDayImpl.setYear(year);
		assetStatsDayImpl.setViewCount(viewCount);
		assetStatsDayImpl.setDownloadCount(downloadCount);
		assetStatsDayImpl.setPurchaseCount(purchaseCount);

		if (currencyCodeRevenues == null) {
			assetStatsDayImpl.setCurrencyCodeRevenues(StringPool.BLANK);
		}
		else {
			assetStatsDayImpl.setCurrencyCodeRevenues(currencyCodeRevenues);
		}

		assetStatsDayImpl.resetOriginalValues();

		return assetStatsDayImpl;
	}

	public long assetStatsDayId;
	public long classNameId;
	public long classPK;
	public int day;
	public int year;
	public long viewCount;
	public long downloadCount;
	public long purchaseCount;
	public String currencyCodeRevenues;
}