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
 * The extended model interface for the LoopPersonRel service. Represents a row in the &quot;LoopPersonRel&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopPersonRelModel
 * @see com.liferay.osb.loop.model.impl.LoopPersonRelImpl
 * @see com.liferay.osb.loop.model.impl.LoopPersonRelModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopPersonRelImpl")
@ProviderType
public interface LoopPersonRel extends LoopPersonRelModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.model.impl.LoopPersonRelImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopPersonRel, Long> LOOP_PERSON_REL_ID_ACCESSOR =
		new Accessor<LoopPersonRel, Long>() {
			@Override
			public Long get(LoopPersonRel loopPersonRel) {
				return loopPersonRel.getLoopPersonRelId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LoopPersonRel> getTypeClass() {
				return LoopPersonRel.class;
			}
		};
}