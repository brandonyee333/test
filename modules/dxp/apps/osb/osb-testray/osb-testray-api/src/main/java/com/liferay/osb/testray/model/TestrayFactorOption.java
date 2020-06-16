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
 * @see com.liferay.osb.testray.model.impl.TestrayFactorOptionImpl
 * @see com.liferay.osb.testray.model.impl.TestrayFactorOptionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.testray.model.impl.TestrayFactorOptionImpl")
@ProviderType
public interface TestrayFactorOption extends TestrayFactorOptionModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.testray.model.impl.TestrayFactorOptionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayFactorOption, Long> TESTRAY_FACTOR_OPTION_ID_ACCESSOR =
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