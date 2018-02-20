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
 * The extended model interface for the WatsonDocumentAudit service. Represents a row in the &quot;WatsonDocumentAudit&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonDocumentAuditModel
 * @see com.liferay.watson.model.impl.WatsonDocumentAuditImpl
 * @see com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonDocumentAuditImpl")
@ProviderType
public interface WatsonDocumentAudit extends WatsonDocumentAuditModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonDocumentAuditImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonDocumentAudit, Long> WATSON_DOCUMENT_AUDIT_ID_ACCESSOR =
		new Accessor<WatsonDocumentAudit, Long>() {
			@Override
			public Long get(WatsonDocumentAudit watsonDocumentAudit) {
				return watsonDocumentAudit.getWatsonDocumentAuditId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonDocumentAudit> getTypeClass() {
				return WatsonDocumentAudit.class;
			}
		};
}