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
 * The extended model interface for the TestrayBuild service. Represents a row in the &quot;OSB_TestrayBuild&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestrayBuildModel
 * @generated
 */
@ImplementationClassName("com.liferay.osb.testray.model.impl.TestrayBuildImpl")
@ProviderType
public interface TestrayBuild extends PersistedModel, TestrayBuildModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.testray.model.impl.TestrayBuildImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayBuild, Long> TESTRAY_BUILD_ID_ACCESSOR =
		new Accessor<TestrayBuild, Long>() {

			@Override
			public Long get(TestrayBuild testrayBuild) {
				return testrayBuild.getTestrayBuildId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TestrayBuild> getTypeClass() {
				return TestrayBuild.class;
			}

		};

}