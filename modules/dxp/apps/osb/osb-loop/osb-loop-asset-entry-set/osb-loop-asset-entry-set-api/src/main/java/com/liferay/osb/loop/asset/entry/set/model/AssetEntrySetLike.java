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
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeImpl"
)
@ProviderType
public interface AssetEntrySetLike
	extends AssetEntrySetLikeModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetEntrySetLike, Long>
		ASSET_ENTRY_SET_ID_ACCESSOR = new Accessor<AssetEntrySetLike, Long>() {

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
	public static final Accessor<AssetEntrySetLike, Long>
		CLASS_NAME_ID_ACCESSOR = new Accessor<AssetEntrySetLike, Long>() {

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
	public static final Accessor<AssetEntrySetLike, Long> CLASS_PK_ACCESSOR =
		new Accessor<AssetEntrySetLike, Long>() {

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