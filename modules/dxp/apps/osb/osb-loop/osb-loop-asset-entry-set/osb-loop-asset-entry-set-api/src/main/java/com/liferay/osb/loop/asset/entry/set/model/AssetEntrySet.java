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

package com.liferay.osb.loop.asset.entry.set.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AssetEntrySet service. Represents a row in the &quot;AssetEntrySet&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetModel
 * @see com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetImpl
 * @see com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetImpl")
@ProviderType
public interface AssetEntrySet extends AssetEntrySetModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetEntrySet, Long> ASSET_ENTRY_SET_ID_ACCESSOR =
		new Accessor<AssetEntrySet, Long>() {
			@Override
			public Long get(AssetEntrySet assetEntrySet) {
				return assetEntrySet.getAssetEntrySetId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetEntrySet> getTypeClass() {
				return AssetEntrySet.class;
			}
		};

	@com.liferay.portal.kernel.json.JSON(include = true)
	public java.util.List<AssetEntrySet> getChildAssetEntrySets();

	public void setChildAssetEntrySets(
		java.util.List<AssetEntrySet> childAssetEntrySets)
		throws com.liferay.portal.kernel.exception.PortalException;
}