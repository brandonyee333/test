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
 * The extended model interface for the TestrayFactorOption service. Represents a row in the &quot;OSB_TestrayFactorOption&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestrayFactorOptionModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.testray.model.impl.TestrayFactorOptionImpl"
)
@ProviderType
public interface TestrayFactorOption
	extends PersistedModel, TestrayFactorOptionModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.testray.model.impl.TestrayFactorOptionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayFactorOption, Long>
		TESTRAY_FACTOR_OPTION_ID_ACCESSOR =
			new Accessor<TestrayFactorOption, Long>() {

				@Override
				public Long get(TestrayFactorOption testrayFactorOption) {
					return testrayFactorOption.getTestrayFactorOptionId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<TestrayFactorOption> getTypeClass() {
					return TestrayFactorOption.class;
				}

			};

}