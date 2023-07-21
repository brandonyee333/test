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
 * The extended model interface for the LoopJobTitle service. Represents a row in the &quot;LoopJobTitle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopJobTitleModel
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopJobTitleImpl")
@ProviderType
public interface LoopJobTitle extends LoopJobTitleModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.model.impl.LoopJobTitleImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopJobTitle, Long>
		LOOP_JOB_TITLE_ID_ACCESSOR = new Accessor<LoopJobTitle, Long>() {

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