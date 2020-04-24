/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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