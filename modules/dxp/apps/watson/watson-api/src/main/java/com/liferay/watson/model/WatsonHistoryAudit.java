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
 * The extended model interface for the WatsonHistoryAudit service. Represents a row in the &quot;WatsonHistoryAudit&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonHistoryAuditModel
 * @see com.liferay.watson.model.impl.WatsonHistoryAuditImpl
 * @see com.liferay.watson.model.impl.WatsonHistoryAuditModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonHistoryAuditImpl")
@ProviderType
public interface WatsonHistoryAudit extends WatsonHistoryAuditModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonHistoryAuditImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonHistoryAudit, Long> WATSON_HISTORY_AUDIT_ID_ACCESSOR =
		new Accessor<WatsonHistoryAudit, Long>() {
			@Override
			public Long get(WatsonHistoryAudit watsonHistoryAudit) {
				return watsonHistoryAudit.getWatsonHistoryAuditId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonHistoryAudit> getTypeClass() {
				return WatsonHistoryAudit.class;
			}
		};
}