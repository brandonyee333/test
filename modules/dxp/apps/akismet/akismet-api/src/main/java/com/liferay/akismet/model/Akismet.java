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

package com.liferay.osb.community.akismet.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Akismet service. Represents a row in the &quot;OSBCommunity_Akismet&quot; database table, with each column mapped to a property of this class.
 *
 * @author Jamie Sammons
 * @see AkismetModel
 * @see com.liferay.osb.community.akismet.model.impl.AkismetImpl
 * @see com.liferay.osb.community.akismet.model.impl.AkismetModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.community.akismet.model.impl.AkismetImpl")
@ProviderType
public interface Akismet extends AkismetModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.community.akismet.model.impl.AkismetImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Akismet, Long> AKISMET_ID_ACCESSOR = new Accessor<Akismet, Long>() {
			@Override
			public Long get(Akismet akismet) {
				return akismet.getAkismetId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Akismet> getTypeClass() {
				return Akismet.class;
			}
		};
}