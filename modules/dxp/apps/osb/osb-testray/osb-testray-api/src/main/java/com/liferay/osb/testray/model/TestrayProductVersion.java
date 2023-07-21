/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the TestrayProductVersion service. Represents a row in the &quot;OSB_TestrayProductVersion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestrayProductVersionModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.testray.model.impl.TestrayProductVersionImpl"
)
@ProviderType
public interface TestrayProductVersion
	extends PersistedModel, TestrayProductVersionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.testray.model.impl.TestrayProductVersionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayProductVersion, Long>
		TESTRAY_PRODUCT_VERSION_ID_ACCESSOR =
			new Accessor<TestrayProductVersion, Long>() {

				@Override
				public Long get(TestrayProductVersion testrayProductVersion) {
					return testrayProductVersion.getTestrayProductVersionId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<TestrayProductVersion> getTypeClass() {
					return TestrayProductVersion.class;
				}

			};

}