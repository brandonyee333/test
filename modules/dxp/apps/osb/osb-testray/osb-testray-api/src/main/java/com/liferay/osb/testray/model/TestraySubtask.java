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
 * The extended model interface for the TestraySubtask service. Represents a row in the &quot;OSB_TestraySubtask&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestraySubtaskModel
 * @see com.liferay.osb.testray.model.impl.TestraySubtaskImpl
 * @see com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.testray.model.impl.TestraySubtaskImpl")
@ProviderType
public interface TestraySubtask extends TestraySubtaskModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.testray.model.impl.TestraySubtaskImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestraySubtask, Long> TESTRAY_SUBTASK_ID_ACCESSOR =
		new Accessor<TestraySubtask, Long>() {
			@Override
			public Long get(TestraySubtask testraySubtask) {
				return testraySubtask.getTestraySubtaskId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TestraySubtask> getTypeClass() {
				return TestraySubtask.class;
			}
		};
}