/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the LoopAuditEntry service. Represents a row in the &quot;LoopAuditEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopAuditEntryModel
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopAuditEntryImpl")
@ProviderType
public interface LoopAuditEntry extends LoopAuditEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.model.impl.LoopAuditEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopAuditEntry, Long>
		LOOP_AUDIT_ENTRY_ID_ACCESSOR = new Accessor<LoopAuditEntry, Long>() {

			@Override
			public Long get(LoopAuditEntry loopAuditEntry) {
				return loopAuditEntry.getLoopAuditEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LoopAuditEntry> getTypeClass() {
				return LoopAuditEntry.class;
			}

		};

}