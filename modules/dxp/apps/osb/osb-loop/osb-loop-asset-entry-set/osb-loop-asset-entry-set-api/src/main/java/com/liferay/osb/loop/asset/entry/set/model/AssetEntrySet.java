/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetImpl"
)
@ProviderType
public interface AssetEntrySet extends AssetEntrySetModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetEntrySet, Long>
		ASSET_ENTRY_SET_ID_ACCESSOR = new Accessor<AssetEntrySet, Long>() {

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