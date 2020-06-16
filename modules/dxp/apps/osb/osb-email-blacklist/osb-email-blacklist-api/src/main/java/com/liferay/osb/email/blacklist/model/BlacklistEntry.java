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
 * @see com.liferay.osb.email.blacklist.model.impl.BlacklistEntryImpl
 * @see com.liferay.osb.email.blacklist.model.impl.BlacklistEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.email.blacklist.model.impl.BlacklistEntryImpl")
@ProviderType
public interface BlacklistEntry extends BlacklistEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.email.blacklist.model.impl.BlacklistEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<BlacklistEntry, Long> BLACKLIST_ENTRY_ID_ACCESSOR =
		new Accessor<BlacklistEntry, Long>() {
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