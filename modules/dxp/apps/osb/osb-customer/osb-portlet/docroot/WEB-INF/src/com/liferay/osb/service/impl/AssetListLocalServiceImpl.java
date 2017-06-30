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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.AssetList;
import com.liferay.osb.service.base.AssetListLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Douglas Wong
 */
public class AssetListLocalServiceImpl extends AssetListLocalServiceBaseImpl {

	public AssetList addAssetList(long assetCategoryId, int type)
		throws SystemException {

		long assetListId = counterLocalService.increment();

		AssetList assetList = assetListPersistence.create(assetListId);

		assetList.setAssetCategoryId(assetCategoryId);
		assetList.setType(type);

		assetListPersistence.update(assetList, false);

		return assetList;
	}

	public AssetList fetchAssetList(long assetCategoryId, int type)
		throws SystemException {

		return assetListPersistence.fetchByACI_T(assetCategoryId, type);
	}

	public AssetList getAssetList(long assetCategoryId, int type)
		throws PortalException, SystemException {

		return assetListPersistence.findByACI_T(assetCategoryId, type);
	}

	public List<AssetList> getAssetLists(long assetEntryId)
		throws SystemException {

		return assetListFinder.findByAssetEntryId(assetEntryId);
	}

}