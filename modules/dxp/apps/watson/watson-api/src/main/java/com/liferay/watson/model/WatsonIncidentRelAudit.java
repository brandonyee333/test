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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the WatsonIncidentRelAudit service. Represents a row in the &quot;WatsonIncidentRelAudit&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonIncidentRelAuditModel
 * @see com.liferay.watson.model.impl.WatsonIncidentRelAuditImpl
 * @see com.liferay.watson.model.impl.WatsonIncidentRelAuditModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonIncidentRelAuditImpl")
@ProviderType
public interface WatsonIncidentRelAudit extends WatsonIncidentRelAuditModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonIncidentRelAuditImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonIncidentRelAudit, Long> WATSON_INCIDENT_REL_AUDIT_ID_ACCESSOR =
		new Accessor<WatsonIncidentRelAudit, Long>() {
			@Override
			public Long get(WatsonIncidentRelAudit watsonIncidentRelAudit) {
				return watsonIncidentRelAudit.getWatsonIncidentRelAuditId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonIncidentRelAudit> getTypeClass() {
				return WatsonIncidentRelAudit.class;
			}
		};
}