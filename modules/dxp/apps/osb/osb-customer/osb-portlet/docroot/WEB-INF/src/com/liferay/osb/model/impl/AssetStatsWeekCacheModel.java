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

import com.liferay.osb.model.AssetStatsWeek;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AssetStatsWeek in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsWeek
 * @generated
 */
public class AssetStatsWeekCacheModel implements CacheModel<AssetStatsWeek>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{assetStatsWeekId=");
		sb.append(assetStatsWeekId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", week=");
		sb.append(week);
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

	public AssetStatsWeek toEntityModel() {
		AssetStatsWeekImpl assetStatsWeekImpl = new AssetStatsWeekImpl();

		assetStatsWeekImpl.setAssetStatsWeekId(assetStatsWeekId);
		assetStatsWeekImpl.setClassNameId(classNameId);
		assetStatsWeekImpl.setClassPK(classPK);
		assetStatsWeekImpl.setWeek(week);
		assetStatsWeekImpl.setYear(year);
		assetStatsWeekImpl.setViewCount(viewCount);
		assetStatsWeekImpl.setDownloadCount(downloadCount);
		assetStatsWeekImpl.setPurchaseCount(purchaseCount);

		if (currencyCodeRevenues == null) {
			assetStatsWeekImpl.setCurrencyCodeRevenues(StringPool.BLANK);
		}
		else {
			assetStatsWeekImpl.setCurrencyCodeRevenues(currencyCodeRevenues);
		}

		assetStatsWeekImpl.resetOriginalValues();

		return assetStatsWeekImpl;
	}

	public long assetStatsWeekId;
	public long classNameId;
	public long classPK;
	public int week;
	public int year;
	public long viewCount;
	public long downloadCount;
	public long purchaseCount;
	public String currencyCodeRevenues;
}