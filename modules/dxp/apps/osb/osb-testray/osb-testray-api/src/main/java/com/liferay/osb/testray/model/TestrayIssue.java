/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.testray.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the TestrayIssue service. Represents a row in the &quot;OSB_TestrayIssue&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestrayIssueModel
 * @generated
 */
@ImplementationClassName("com.liferay.osb.testray.model.impl.TestrayIssueImpl")
@ProviderType
public interface TestrayIssue extends PersistedModel, TestrayIssueModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.testray.model.impl.TestrayIssueImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayIssue, Long> TESTRAY_ISSUE_ID_ACCESSOR =
		new Accessor<TestrayIssue, Long>() {

			@Override
			public Long get(TestrayIssue testrayIssue) {
				return testrayIssue.getTestrayIssueId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TestrayIssue> getTypeClass() {
				return TestrayIssue.class;
			}

		};

}