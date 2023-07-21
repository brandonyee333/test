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
 * The extended model interface for the TestrayTeam service. Represents a row in the &quot;OSB_TestrayTeam&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestrayTeamModel
 * @generated
 */
@ImplementationClassName("com.liferay.osb.testray.model.impl.TestrayTeamImpl")
@ProviderType
public interface TestrayTeam extends PersistedModel, TestrayTeamModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.testray.model.impl.TestrayTeamImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayTeam, Long> TESTRAY_TEAM_ID_ACCESSOR =
		new Accessor<TestrayTeam, Long>() {

			@Override
			public Long get(TestrayTeam testrayTeam) {
				return testrayTeam.getTestrayTeamId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TestrayTeam> getTypeClass() {
				return TestrayTeam.class;
			}

		};

}