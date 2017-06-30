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

import com.liferay.osb.model.AssetRecommendationEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AssetRecommendationEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetRecommendationEntry
 * @generated
 */
public class AssetRecommendationEntryCacheModel implements CacheModel<AssetRecommendationEntry>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{assetRecommendationEntryId=");
		sb.append(assetRecommendationEntryId);
		sb.append(", assetRecommendationSetId=");
		sb.append(assetRecommendationSetId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", viewedAlsoPurchasedCount=");
		sb.append(viewedAlsoPurchasedCount);
		sb.append(", purchasedAlsoPurchasedCount=");
		sb.append(purchasedAlsoPurchasedCount);
		sb.append("}");

		return sb.toString();
	}

	public AssetRecommendationEntry toEntityModel() {
		AssetRecommendationEntryImpl assetRecommendationEntryImpl = new AssetRecommendationEntryImpl();

		assetRecommendationEntryImpl.setAssetRecommendationEntryId(assetRecommendationEntryId);
		assetRecommendationEntryImpl.setAssetRecommendationSetId(assetRecommendationSetId);
		assetRecommendationEntryImpl.setClassNameId(classNameId);
		assetRecommendationEntryImpl.setClassPK(classPK);
		assetRecommendationEntryImpl.setViewedAlsoPurchasedCount(viewedAlsoPurchasedCount);
		assetRecommendationEntryImpl.setPurchasedAlsoPurchasedCount(purchasedAlsoPurchasedCount);

		assetRecommendationEntryImpl.resetOriginalValues();

		return assetRecommendationEntryImpl;
	}

	public long assetRecommendationEntryId;
	public long assetRecommendationSetId;
	public long classNameId;
	public long classPK;
	public int viewedAlsoPurchasedCount;
	public int purchasedAlsoPurchasedCount;
}