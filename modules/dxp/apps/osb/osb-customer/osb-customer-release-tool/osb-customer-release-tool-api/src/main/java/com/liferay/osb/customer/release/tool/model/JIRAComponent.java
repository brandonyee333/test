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
 * @see com.liferay.osb.customer.release.tool.model.impl.JIRAComponentImpl
 * @see com.liferay.osb.customer.release.tool.model.impl.JIRAComponentModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.customer.release.tool.model.impl.JIRAComponentImpl")
@ProviderType
public interface JIRAComponent extends JIRAComponentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.customer.release.tool.model.impl.JIRAComponentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<JIRAComponent, Long> JIRA_COMPONENT_ID_ACCESSOR =
		new Accessor<JIRAComponent, Long>() {
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