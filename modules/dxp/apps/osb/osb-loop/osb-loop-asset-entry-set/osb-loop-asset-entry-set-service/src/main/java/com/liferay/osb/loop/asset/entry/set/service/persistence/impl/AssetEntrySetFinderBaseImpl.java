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

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetFinderBaseImpl extends BasePersistenceImpl<AssetEntrySet> {
	@Override
	public Set<String> getBadColumnNames() {
		return getAssetEntrySetPersistence().getBadColumnNames();
	}

	/**
	 * Returns the asset entry set persistence.
	 *
	 * @return the asset entry set persistence
	 */
	public AssetEntrySetPersistence getAssetEntrySetPersistence() {
		return assetEntrySetPersistence;
	}

	/**
	 * Sets the asset entry set persistence.
	 *
	 * @param assetEntrySetPersistence the asset entry set persistence
	 */
	public void setAssetEntrySetPersistence(
		AssetEntrySetPersistence assetEntrySetPersistence) {
		this.assetEntrySetPersistence = assetEntrySetPersistence;
	}

	@BeanReference(type = AssetEntrySetPersistence.class)
	protected AssetEntrySetPersistence assetEntrySetPersistence;
}