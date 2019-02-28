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
 * @generated
 */
@ImplementationClassName(
	"com.liferay.watson.model.impl.WatsonIncidentRelAuditImpl"
)
@ProviderType
public interface WatsonIncidentRelAudit
	extends WatsonIncidentRelAuditModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.watson.model.impl.WatsonIncidentRelAuditImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonIncidentRelAudit, Long>
		WATSON_INCIDENT_REL_AUDIT_ID_ACCESSOR =
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