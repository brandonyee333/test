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
 * The extended model interface for the LoopJobTitle service. Represents a row in the &quot;LoopJobTitle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopJobTitleModel
 * @see com.liferay.osb.loop.model.impl.LoopJobTitleImpl
 * @see com.liferay.osb.loop.model.impl.LoopJobTitleModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopJobTitleImpl")
@ProviderType
public interface LoopJobTitle extends LoopJobTitleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.model.impl.LoopJobTitleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopJobTitle, Long> LOOP_JOB_TITLE_ID_ACCESSOR = new Accessor<LoopJobTitle, Long>() {
			@Override
			public Long get(LoopJobTitle loopJobTitle) {
				return loopJobTitle.getLoopJobTitleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LoopJobTitle> getTypeClass() {
				return LoopJobTitle.class;
			}
		};
}