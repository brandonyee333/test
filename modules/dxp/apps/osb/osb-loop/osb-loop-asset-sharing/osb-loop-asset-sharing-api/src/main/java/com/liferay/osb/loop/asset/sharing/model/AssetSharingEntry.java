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
 * @see com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryImpl
 * @see com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryImpl")
@ProviderType
public interface AssetSharingEntry extends AssetSharingEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<AssetSharingEntry, Long> CLASS_NAME_ID_ACCESSOR =
		new Accessor<AssetSharingEntry, Long>() {
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

	public static final Accessor<AssetSharingEntry, Long> CLASS_PK_ACCESSOR = new Accessor<AssetSharingEntry, Long>() {
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

	public static final Accessor<AssetSharingEntry, Long> SHARED_TO_CLASS_NAME_ID_ACCESSOR =
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

	public static final Accessor<AssetSharingEntry, Long> SHARED_TO_CLASS_PK_ACCESSOR =
		new Accessor<AssetSharingEntry, Long>() {
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