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
 * The extended model interface for the AssetEntrySetLike service. Represents a row in the &quot;AssetEntrySetLike&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLikeModel
 * @see com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeImpl
 * @see com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeImpl")
@ProviderType
public interface AssetEntrySetLike extends AssetEntrySetLikeModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetEntrySetLike, Long> ASSET_ENTRY_SET_ID_ACCESSOR =
		new Accessor<AssetEntrySetLike, Long>() {
			@Override
			public Long get(AssetEntrySetLike assetEntrySetLike) {
				return assetEntrySetLike.getAssetEntrySetId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetEntrySetLike> getTypeClass() {
				return AssetEntrySetLike.class;
			}
		};

	public static final Accessor<AssetEntrySetLike, Long> CLASS_NAME_ID_ACCESSOR =
		new Accessor<AssetEntrySetLike, Long>() {
			@Override
			public Long get(AssetEntrySetLike assetEntrySetLike) {
				return assetEntrySetLike.getClassNameId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetEntrySetLike> getTypeClass() {
				return AssetEntrySetLike.class;
			}
		};

	public static final Accessor<AssetEntrySetLike, Long> CLASS_PK_ACCESSOR = new Accessor<AssetEntrySetLike, Long>() {
			@Override
			public Long get(AssetEntrySetLike assetEntrySetLike) {
				return assetEntrySetLike.getClassPK();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetEntrySetLike> getTypeClass() {
				return AssetEntrySetLike.class;
			}
		};
}