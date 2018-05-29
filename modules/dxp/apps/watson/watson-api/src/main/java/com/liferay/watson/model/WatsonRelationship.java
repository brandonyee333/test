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
 * The extended model interface for the WatsonRelationship service. Represents a row in the &quot;WatsonRelationship&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
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