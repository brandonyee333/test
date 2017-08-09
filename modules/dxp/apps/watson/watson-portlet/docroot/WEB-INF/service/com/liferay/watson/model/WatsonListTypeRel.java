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
 * The extended model interface for the WatsonListTypeRel service. Represents a row in the &quot;WatsonListTypeRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eddie Olson
 * @see WatsonListTypeRelModel
 * @see com.liferay.watson.model.impl.WatsonListTypeRelImpl
 * @see com.liferay.watson.model.impl.WatsonListTypeRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonListTypeRelImpl")
@ProviderType
public interface WatsonListTypeRel extends WatsonListTypeRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonListTypeRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonListTypeRel, Long> WATSON_LIST_TYPE_REL_ID_ACCESSOR =
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