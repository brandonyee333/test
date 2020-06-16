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
 * The extended model interface for the LoopDivision service. Represents a row in the &quot;LoopDivision&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopDivisionModel
 * @see com.liferay.osb.loop.model.impl.LoopDivisionImpl
 * @see com.liferay.osb.loop.model.impl.LoopDivisionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopDivisionImpl")
@ProviderType
public interface LoopDivision extends LoopDivisionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.model.impl.LoopDivisionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopDivision, Long> LOOP_DIVISION_ID_ACCESSOR = new Accessor<LoopDivision, Long>() {
			@Override
			public Long get(LoopDivision loopDivision) {
				return loopDivision.getLoopDivisionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LoopDivision> getTypeClass() {
				return LoopDivision.class;
			}
		};
}