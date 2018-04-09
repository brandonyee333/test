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

package com.liferay.watson.login.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WatsonTokenAuthEntry service. Represents a row in the &quot;WatsonTokenAuthEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonTokenAuthEntryModel
 * @see com.liferay.watson.login.model.impl.WatsonTokenAuthEntryImpl
 * @see com.liferay.watson.login.model.impl.WatsonTokenAuthEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.login.model.impl.WatsonTokenAuthEntryImpl")
@ProviderType
public interface WatsonTokenAuthEntry extends WatsonTokenAuthEntryModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.login.model.impl.WatsonTokenAuthEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonTokenAuthEntry, Long> WATSON_TOKEN_AUTH_ENTRY_ID_ACCESSOR =
		new Accessor<WatsonTokenAuthEntry, Long>() {
			@Override
			public Long get(WatsonTokenAuthEntry watsonTokenAuthEntry) {
				return watsonTokenAuthEntry.getWatsonTokenAuthEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonTokenAuthEntry> getTypeClass() {
				return WatsonTokenAuthEntry.class;
			}
		};

	public boolean isExpired();
}