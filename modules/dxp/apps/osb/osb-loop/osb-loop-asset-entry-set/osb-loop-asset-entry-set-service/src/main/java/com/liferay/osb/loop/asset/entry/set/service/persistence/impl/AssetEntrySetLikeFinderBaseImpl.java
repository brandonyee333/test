/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
public class AssetEntrySetLikeFinderBaseImpl
	extends BasePersistenceImpl<AssetEntrySetLike> {

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