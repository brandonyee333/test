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

package com.liferay.osb.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the LCSSubscriptionEntry service. Represents a row in the &quot;OSB_LCSSubscriptionEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LCSSubscriptionEntryModel
 * @see com.liferay.osb.model.impl.LCSSubscriptionEntryImpl
 * @see com.liferay.osb.model.impl.LCSSubscriptionEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.LCSSubscriptionEntryImpl")
@ProviderType
public interface LCSSubscriptionEntry extends LCSSubscriptionEntryModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.LCSSubscriptionEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LCSSubscriptionEntry, Long> LCS_SUBSCRIPTION_ENTRY_ID_ACCESSOR =
		new Accessor<LCSSubscriptionEntry, Long>() {
			@Override
			public Long get(LCSSubscriptionEntry lcsSubscriptionEntry) {
				return lcsSubscriptionEntry.getLcsSubscriptionEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LCSSubscriptionEntry> getTypeClass() {
				return LCSSubscriptionEntry.class;
			}
		};
}