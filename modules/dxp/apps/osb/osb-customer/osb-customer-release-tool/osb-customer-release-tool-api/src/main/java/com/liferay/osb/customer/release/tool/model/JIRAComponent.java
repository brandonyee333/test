/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the JIRAComponent service. Represents a row in the &quot;OSBCustomer_JIRAComponent&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponentModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.release.tool.model.impl.JIRAComponentImpl"
)
@ProviderType
public interface JIRAComponent extends JIRAComponentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.release.tool.model.impl.JIRAComponentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JIRAComponent, Long>
		JIRA_COMPONENT_ID_ACCESSOR = new Accessor<JIRAComponent, Long>() {

			@Override
			public Long get(JIRAComponent jiraComponent) {
				return jiraComponent.getJiraComponentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<JIRAComponent> getTypeClass() {
				return JIRAComponent.class;
			}

		};

}