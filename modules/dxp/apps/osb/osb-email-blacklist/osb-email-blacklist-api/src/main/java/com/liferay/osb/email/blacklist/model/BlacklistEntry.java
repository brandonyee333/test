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
 * The extended model interface for the BlacklistEntry service. Represents a row in the &quot;OSB_BlacklistEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jamie Sammons
 * @see BlacklistEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.email.blacklist.model.impl.BlacklistEntryImpl"
)
@ProviderType
public interface BlacklistEntry extends BlacklistEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.email.blacklist.model.impl.BlacklistEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BlacklistEntry, Long>
		BLACKLIST_ENTRY_ID_ACCESSOR = new Accessor<BlacklistEntry, Long>() {

			@Override
			public Long get(BlacklistEntry blacklistEntry) {
				return blacklistEntry.getBlacklistEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<BlacklistEntry> getTypeClass() {
				return BlacklistEntry.class;
			}

		};

}