/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.sharing.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the AssetSharingEntry service. Represents a row in the &quot;AssetSharingEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryImpl"
)
@ProviderType
public interface AssetSharingEntry
	extends AssetSharingEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetSharingEntry, Long>
		CLASS_NAME_ID_ACCESSOR = new Accessor<AssetSharingEntry, Long>() {

			@Override
			public Long get(AssetSharingEntry assetSharingEntry) {
				return assetSharingEntry.getClassNameId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetSharingEntry> getTypeClass() {
				return AssetSharingEntry.class;
			}

		};
	public static final Accessor<AssetSharingEntry, Long> CLASS_PK_ACCESSOR =
		new Accessor<AssetSharingEntry, Long>() {

			@Override
			public Long get(AssetSharingEntry assetSharingEntry) {
				return assetSharingEntry.getClassPK();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetSharingEntry> getTypeClass() {
				return AssetSharingEntry.class;
			}

		};
	public static final Accessor<AssetSharingEntry, Long>
		SHARED_TO_CLASS_NAME_ID_ACCESSOR =
			new Accessor<AssetSharingEntry, Long>() {

				@Override
				public Long get(AssetSharingEntry assetSharingEntry) {
					return assetSharingEntry.getSharedToClassNameId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<AssetSharingEntry> getTypeClass() {
					return AssetSharingEntry.class;
				}

			};
	public static final Accessor<AssetSharingEntry, Long>
		SHARED_TO_CLASS_PK_ACCESSOR = new Accessor<AssetSharingEntry, Long>() {

			@Override
			public Long get(AssetSharingEntry assetSharingEntry) {
				return assetSharingEntry.getSharedToClassPK();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<AssetSharingEntry> getTypeClass() {
				return AssetSharingEntry.class;
			}

		};

}