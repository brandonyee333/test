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
 * The extended model interface for the WatsonIncidentRel service. Represents a row in the &quot;WatsonIncidentRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eddie Olson
 * @see WatsonIncidentRelModel
 * @see com.liferay.watson.model.impl.WatsonIncidentRelImpl
 * @see com.liferay.watson.model.impl.WatsonIncidentRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonIncidentRelImpl")
@ProviderType
public interface WatsonIncidentRel extends WatsonIncidentRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonIncidentRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonIncidentRel, Long> WATSON_INCIDENT_REL_ID_ACCESSOR =
		new Accessor<WatsonIncidentRel, Long>() {
			@Override
			public Long get(WatsonIncidentRel watsonIncidentRel) {
				return watsonIncidentRel.getWatsonIncidentRelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonIncidentRel> getTypeClass() {
				return WatsonIncidentRel.class;
			}
		};
}