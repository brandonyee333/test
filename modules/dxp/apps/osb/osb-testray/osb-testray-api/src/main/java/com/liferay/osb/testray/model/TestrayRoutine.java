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
 * The extended model interface for the TestrayRoutine service. Represents a row in the &quot;OSB_TestrayRoutine&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestrayRoutineModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.testray.model.impl.TestrayRoutineImpl"
)
@ProviderType
public interface TestrayRoutine extends PersistedModel, TestrayRoutineModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.testray.model.impl.TestrayRoutineImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayRoutine, Long>
		TESTRAY_ROUTINE_ID_ACCESSOR = new Accessor<TestrayRoutine, Long>() {

			@Override
			public Long get(TestrayRoutine testrayRoutine) {
				return testrayRoutine.getTestrayRoutineId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TestrayRoutine> getTypeClass() {
				return TestrayRoutine.class;
			}

		};

}