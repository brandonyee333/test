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
 * The extended model interface for the TestraySuite service. Represents a row in the &quot;OSB_TestraySuite&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestraySuiteModel
 * @see com.liferay.osb.testray.model.impl.TestraySuiteImpl
 * @see com.liferay.osb.testray.model.impl.TestraySuiteModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.testray.model.impl.TestraySuiteImpl")
@ProviderType
public interface TestraySuite extends TestraySuiteModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.testray.model.impl.TestraySuiteImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestraySuite, Long> TESTRAY_SUITE_ID_ACCESSOR = new Accessor<TestraySuite, Long>() {
			@Override
			public Long get(TestraySuite testraySuite) {
				return testraySuite.getTestraySuiteId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TestraySuite> getTypeClass() {
				return TestraySuite.class;
			}
		};
}