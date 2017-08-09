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
 * The extended model interface for the WatsonIncident service. Represents a row in the &quot;WatsonIncident&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eddie Olson
 * @see WatsonIncidentModel
 * @see com.liferay.watson.model.impl.WatsonIncidentImpl
 * @see com.liferay.watson.model.impl.WatsonIncidentModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonIncidentImpl")
@ProviderType
public interface WatsonIncident extends WatsonIncidentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonIncidentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonIncident, Long> WATSON_INCIDENT_ID_ACCESSOR =
		new Accessor<WatsonIncident, Long>() {
			@Override
			public Long get(WatsonIncident watsonIncident) {
				return watsonIncident.getWatsonIncidentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonIncident> getTypeClass() {
				return WatsonIncident.class;
			}
		};
}