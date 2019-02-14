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
 * The extended model interface for the WatsonChildAudit service. Represents a row in the &quot;WatsonChildAudit&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonChildAuditModel
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonChildAuditImpl")
@ProviderType
public interface WatsonChildAudit extends WatsonChildAuditModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.watson.model.impl.WatsonChildAuditImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonChildAudit, Long> WATSON_CHILD_AUDIT_ID_ACCESSOR =
		new Accessor<WatsonChildAudit, Long>() {
			@Override
			public Long get(WatsonChildAudit watsonChildAudit) {
				return watsonChildAudit.getWatsonChildAuditId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonChildAudit> getTypeClass() {
				return WatsonChildAudit.class;
			}
		};
}