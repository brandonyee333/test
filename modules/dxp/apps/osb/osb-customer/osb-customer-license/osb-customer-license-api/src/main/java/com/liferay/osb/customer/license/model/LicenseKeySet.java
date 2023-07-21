/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the LicenseKeySet service. Represents a row in the &quot;OSB_LicenseKeySet&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.license.model.impl.LicenseKeySetImpl"
)
@ProviderType
public interface LicenseKeySet extends LicenseKeySetModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.license.model.impl.LicenseKeySetImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LicenseKeySet, Long>
		LICENSE_KEY_SET_ID_ACCESSOR = new Accessor<LicenseKeySet, Long>() {

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

}