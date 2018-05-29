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
 * The extended model interface for the WatsonVehicleAudit service. Represents a row in the &quot;WatsonVehicleAudit&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonVehicleAuditModel
 * @see com.liferay.watson.model.impl.WatsonVehicleAuditImpl
 * @see com.liferay.watson.model.impl.WatsonVehicleAuditModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonVehicleAuditImpl")
@ProviderType
public interface WatsonVehicleAudit extends WatsonVehicleAuditModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonVehicleAuditImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonVehicleAudit, Long> WATSON_VEHICLE_AUDIT_ID_ACCESSOR =
		new Accessor<WatsonVehicleAudit, Long>() {
			@Override
			public Long get(WatsonVehicleAudit watsonVehicleAudit) {
				return watsonVehicleAudit.getWatsonVehicleAuditId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonVehicleAudit> getTypeClass() {
				return WatsonVehicleAudit.class;
			}
		};
}