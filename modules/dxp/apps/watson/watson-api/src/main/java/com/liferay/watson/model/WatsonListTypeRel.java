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
 * The extended model interface for the WatsonListTypeRel service. Represents a row in the &quot;WatsonListTypeRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonListTypeRelModel
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonListTypeRelImpl")
@ProviderType
public interface WatsonListTypeRel
	extends WatsonListTypeRelModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.watson.model.impl.WatsonListTypeRelImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonListTypeRel, Long>
		WATSON_LIST_TYPE_REL_ID_ACCESSOR =
			new Accessor<WatsonListTypeRel, Long>() {

				@Override
				public Long get(WatsonListTypeRel watsonListTypeRel) {
					return watsonListTypeRel.getWatsonListTypeRelId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<WatsonListTypeRel> getTypeClass() {
					return WatsonListTypeRel.class;
				}

			};

}