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
 * The extended model interface for the WatsonRelationship service. Represents a row in the &quot;WatsonRelationship&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eddie Olson
 * @see WatsonRelationshipModel
 * @see com.liferay.watson.model.impl.WatsonRelationshipImpl
 * @see com.liferay.watson.model.impl.WatsonRelationshipModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonRelationshipImpl")
@ProviderType
public interface WatsonRelationship extends WatsonRelationshipModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonRelationshipImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonRelationship, Long> WATSON_RELATIONSHIP_ID_ACCESSOR =
		new Accessor<WatsonRelationship, Long>() {
			@Override
			public Long get(WatsonRelationship watsonRelationship) {
				return watsonRelationship.getWatsonRelationshipId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonRelationship> getTypeClass() {
				return WatsonRelationship.class;
			}
		};
}