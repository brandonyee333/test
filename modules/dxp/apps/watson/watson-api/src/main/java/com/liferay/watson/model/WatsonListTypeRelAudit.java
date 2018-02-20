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
 * The extended model interface for the WatsonListTypeRelAudit service. Represents a row in the &quot;WatsonListTypeRelAudit&quot; database table, with each column mapped to a property of this class.
 *
 * @author Steven Smith
 * @see WatsonListTypeRelAuditModel
 * @see com.liferay.watson.model.impl.WatsonListTypeRelAuditImpl
 * @see com.liferay.watson.model.impl.WatsonListTypeRelAuditModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.watson.model.impl.WatsonListTypeRelAuditImpl")
@ProviderType
public interface WatsonListTypeRelAudit extends WatsonListTypeRelAuditModel,
	PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.watson.model.impl.WatsonListTypeRelAuditImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<WatsonListTypeRelAudit, Long> WATSON_LIST_TYPE_REL_AUDIT_ID_ACCESSOR =
		new Accessor<WatsonListTypeRelAudit, Long>() {
			@Override
			public Long get(WatsonListTypeRelAudit watsonListTypeRelAudit) {
				return watsonListTypeRelAudit.getWatsonListTypeRelAuditId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<WatsonListTypeRelAudit> getTypeClass() {
				return WatsonListTypeRelAudit.class;
			}
		};
}