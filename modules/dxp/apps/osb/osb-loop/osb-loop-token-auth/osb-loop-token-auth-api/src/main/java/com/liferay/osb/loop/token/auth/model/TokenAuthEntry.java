/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryImpl"
)
@ProviderType
public interface TokenAuthEntry extends PersistedModel, TokenAuthEntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TokenAuthEntry, Long>
		TOKEN_AUTH_ENTRY_ID_ACCESSOR = new Accessor<TokenAuthEntry, Long>() {

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