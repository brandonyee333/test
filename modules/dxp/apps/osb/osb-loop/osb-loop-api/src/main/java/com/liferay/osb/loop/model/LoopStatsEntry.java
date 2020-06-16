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
 * @see com.liferay.osb.loop.model.impl.LoopStatsEntryImpl
 * @see com.liferay.osb.loop.model.impl.LoopStatsEntryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopStatsEntryImpl")
@ProviderType
public interface LoopStatsEntry extends LoopStatsEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.model.impl.LoopStatsEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopStatsEntry, Long> LOOP_STATS_ENTRY_ID_ACCESSOR =
		new Accessor<LoopStatsEntry, Long>() {
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