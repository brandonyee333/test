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

package com.liferay.osb.loop.asset.entry.set.service.persistence.impl;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetLikeFinderBaseImpl extends BasePersistenceImpl<AssetEntrySetLike> {
	public AssetEntrySetLikeFinderBaseImpl() {
		setModelClass(AssetEntrySetLike.class);
	}

	/**
	 * Returns the asset entry set like persistence.
	 *
	 * @return the asset entry set like persistence
	 */
	public AssetEntrySetLikePersistence getAssetEntrySetLikePersistence() {
		return assetEntrySetLikePersistence;
	}

	/**
	 * Sets the asset entry set like persistence.
	 *
	 * @param assetEntrySetLikePersistence the asset entry set like persistence
	 */
	public void setAssetEntrySetLikePersistence(
		AssetEntrySetLikePersistence assetEntrySetLikePersistence) {
		this.assetEntrySetLikePersistence = assetEntrySetLikePersistence;
	}

	@BeanReference(type = AssetEntrySetLikePersistence.class)
	protected AssetEntrySetLikePersistence assetEntrySetLikePersistence;
}