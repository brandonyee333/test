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