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