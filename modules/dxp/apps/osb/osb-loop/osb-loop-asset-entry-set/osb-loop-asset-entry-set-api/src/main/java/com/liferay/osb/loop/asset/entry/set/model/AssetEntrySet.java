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