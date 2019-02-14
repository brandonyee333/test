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
 * The extended model interface for the WatsonDocumentAudit service. Represents a row in the &quot;WatsonDocumentAudit&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonDocumentAuditModel
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonDocumentAuditImpl")
@ProviderType
public interface WatsonDocumentAudit extends WatsonDocumentAuditModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.watson.model.impl.WatsonDocumentAuditImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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