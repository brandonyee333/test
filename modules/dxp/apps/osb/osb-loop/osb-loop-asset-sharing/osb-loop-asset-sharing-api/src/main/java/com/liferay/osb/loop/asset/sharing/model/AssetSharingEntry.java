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