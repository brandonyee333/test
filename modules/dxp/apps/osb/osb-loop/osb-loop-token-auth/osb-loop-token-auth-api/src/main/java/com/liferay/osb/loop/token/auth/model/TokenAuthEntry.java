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

package com.liferay.osb.loop.token.auth.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the TokenAuthEntry service. Represents a row in the &quot;TokenAuthEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Bruno Farache
 * @see TokenAuthEntryModel
 * @see com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryImpl
 * @see com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryImpl")
@ProviderType
public interface TokenAuthEntry extends TokenAuthEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TokenAuthEntry, Long> TOKEN_AUTH_ENTRY_ID_ACCESSOR =
		new Accessor<TokenAuthEntry, Long>() {
			@Override
			public Long get(TokenAuthEntry tokenAuthEntry) {
				return tokenAuthEntry.getTokenAuthEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TokenAuthEntry> getTypeClass() {
				return TokenAuthEntry.class;
			}
		};
}