/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.service.impl;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.osb.loop.asset.entry.set.service.base.AssetEntrySetLikeLocalServiceBaseImpl;
import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK;

import java.util.List;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetLikeLocalServiceImpl
	extends AssetEntrySetLikeLocalServiceBaseImpl {

	public void deleteAssetEntrySetLikes(long assetEntrySetId) {
		assetEntrySetLikePersistence.removeByAssetEntrySetId(assetEntrySetId);
	}

	@Override
	public AssetEntrySetLike fetchAssetEntrySetLike(
		long assetEntrySetId, long classNameId, long classPK) {

		AssetEntrySetLikePK assetEntrySetLikePK = new AssetEntrySetLikePK(
			assetEntrySetId, classNameId, classPK);

		return assetEntrySetLikePersistence.fetchByPrimaryKey(
			assetEntrySetLikePK);
	}

	public int getAssetEntrySetLikeCount(long assetEntrySetId) {
		return assetEntrySetLikePersistence.countByAssetEntrySetId(
			assetEntrySetId);
	}

	@Override
	public List<AssetEntrySetLike> getAssetEntrySetLikes(
		long assetEntrySetId, long classNameId, long classPK, int start,
		int end) {

		return assetEntrySetLikeFinder.findByAESI_NotC_C(
			assetEntrySetId, classNameId, classPK, start, end);
	}

}