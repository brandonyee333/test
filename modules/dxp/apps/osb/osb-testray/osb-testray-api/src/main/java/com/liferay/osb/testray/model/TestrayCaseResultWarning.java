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
 * The extended model interface for the TestrayCaseResultWarning service. Represents a row in the &quot;OSB_TestrayCaseResultWarning&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultWarningModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.testray.model.impl.TestrayCaseResultWarningImpl"
)
@ProviderType
public interface TestrayCaseResultWarning
	extends PersistedModel, TestrayCaseResultWarningModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.testray.model.impl.TestrayCaseResultWarningImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TestrayCaseResultWarning, Long>
		TESTRAY_CASE_RESULT_WARNING_ID_ACCESSOR =
			new Accessor<TestrayCaseResultWarning, Long>() {

				@Override
				public Long get(
					TestrayCaseResultWarning testrayCaseResultWarning) {

					return testrayCaseResultWarning.
						getTestrayCaseResultWarningId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<TestrayCaseResultWarning> getTypeClass() {
					return TestrayCaseResultWarning.class;
				}

			};

}