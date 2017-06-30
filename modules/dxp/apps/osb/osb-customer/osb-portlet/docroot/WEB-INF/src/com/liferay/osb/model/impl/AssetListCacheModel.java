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

import com.liferay.osb.model.AssetList;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing AssetList in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AssetList
 * @generated
 */
public class AssetListCacheModel implements CacheModel<AssetList>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{assetListId=");
		sb.append(assetListId);
		sb.append(", assetCategoryId=");
		sb.append(assetCategoryId);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	public AssetList toEntityModel() {
		AssetListImpl assetListImpl = new AssetListImpl();

		assetListImpl.setAssetListId(assetListId);
		assetListImpl.setAssetCategoryId(assetCategoryId);
		assetListImpl.setType(type);

		assetListImpl.resetOriginalValues();

		return assetListImpl;
	}

	public long assetListId;
	public long assetCategoryId;
	public int type;
}