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
 * The extended model interface for the WatsonDocument service. Represents a row in the &quot;WatsonDocument&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonDocumentModel
 * @see com.liferay.watson.model.impl.WatsonDocumentImpl
 * @see com.liferay.watson.model.impl.WatsonDocumentModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonDocumentImpl")
@ProviderType
public interface WatsonDocument extends WatsonDocumentModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonDocumentImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonDocument, Long> WATSON_DOCUMENT_ID_ACCESSOR =
		new Accessor<WatsonDocument, Long>() {
			@Override
			public Long get(WatsonDocument watsonDocument) {
				return watsonDocument.getWatsonDocumentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonDocument> getTypeClass() {
				return WatsonDocument.class;
			}
		};
}