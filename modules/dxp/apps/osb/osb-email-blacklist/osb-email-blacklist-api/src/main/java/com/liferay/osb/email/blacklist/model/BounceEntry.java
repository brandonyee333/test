/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the BounceEntry service. Represents a row in the &quot;OSB_BounceEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jamie Sammons
 * @see BounceEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.email.blacklist.model.impl.BounceEntryImpl"
)
@ProviderType
public interface BounceEntry extends BounceEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BounceEntry, Long> BOUNCE_ENTRY_ID_ACCESSOR =
		new Accessor<BounceEntry, Long>() {

			@Override
			public Long get(BounceEntry bounceEntry) {
				return bounceEntry.getBounceEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<BounceEntry> getTypeClass() {
				return BounceEntry.class;
			}

		};

}