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
 * The extended model interface for the TestrayBuildMetric service. Represents a row in the &quot;OSB_TestrayBuildMetric&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestrayBuildMetricModel
 * @see com.liferay.osb.testray.model.impl.TestrayBuildMetricImpl
 * @see com.liferay.osb.testray.model.impl.TestrayBuildMetricModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.testray.model.impl.TestrayBuildMetricImpl")
@ProviderType
public interface TestrayBuildMetric extends TestrayBuildMetricModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.testray.model.impl.TestrayBuildMetricImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayBuildMetric, Long> TESTRAY_BUILD_METRIC_ID_ACCESSOR =
		new Accessor<TestrayBuildMetric, Long>() {
			@Override
			public Long get(TestrayBuildMetric testrayBuildMetric) {
				return testrayBuildMetric.getTestrayBuildMetricId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TestrayBuildMetric> getTypeClass() {
				return TestrayBuildMetric.class;
			}
		};
}