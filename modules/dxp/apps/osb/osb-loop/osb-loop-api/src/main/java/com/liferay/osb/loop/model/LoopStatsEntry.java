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
 * The extended model interface for the LoopStatsEntry service. Represents a row in the &quot;LoopStatsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopStatsEntryModel
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopStatsEntryImpl")
@ProviderType
public interface LoopStatsEntry extends LoopStatsEntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.model.impl.LoopStatsEntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopStatsEntry, Long>
		LOOP_STATS_ENTRY_ID_ACCESSOR = new Accessor<LoopStatsEntry, Long>() {

			@Override
			public Long get(LoopStatsEntry loopStatsEntry) {
				return loopStatsEntry.getLoopStatsEntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LoopStatsEntry> getTypeClass() {
				return LoopStatsEntry.class;
			}

		};

}