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
 * The extended model interface for the LicenseKeySet service. Represents a row in the &quot;OSB_LicenseKeySet&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetModel
 * @see com.liferay.osb.model.impl.LicenseKeySetImpl
 * @see com.liferay.osb.model.impl.LicenseKeySetModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.LicenseKeySetImpl")
@ProviderType
public interface LicenseKeySet extends LicenseKeySetModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.LicenseKeySetImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LicenseKeySet, Long> LICENSE_KEY_SET_ID_ACCESSOR =
		new Accessor<LicenseKeySet, Long>() {
			@Override
			public Long get(LicenseKeySet licenseKeySet) {
				return licenseKeySet.getLicenseKeySetId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LicenseKeySet> getTypeClass() {
				return LicenseKeySet.class;
			}
		};

	public AccountEntry getAccountEntry()
		throws com.liferay.portal.kernel.exception.PortalException;
}