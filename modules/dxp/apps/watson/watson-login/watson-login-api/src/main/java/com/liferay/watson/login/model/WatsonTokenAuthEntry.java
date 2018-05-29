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