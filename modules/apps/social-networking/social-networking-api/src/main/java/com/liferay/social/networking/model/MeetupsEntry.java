/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MeetupsEntry service. Represents a row in the &quot;SN_MeetupsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntryModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.social.networking.model.impl.MeetupsEntryImpl"
)
@ProviderType
public interface MeetupsEntry extends MeetupsEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.social.networking.model.impl.MeetupsEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MeetupsEntry, Long> MEETUPS_ENTRY_ID_ACCESSOR =
		new Accessor<MeetupsEntry, Long>() {

			@Override
			public Long get(MeetupsEntry meetupsEntry) {
				return meetupsEntry.getMeetupsEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MeetupsEntry> getTypeClass() {
				return MeetupsEntry.class;
			}

		};

}