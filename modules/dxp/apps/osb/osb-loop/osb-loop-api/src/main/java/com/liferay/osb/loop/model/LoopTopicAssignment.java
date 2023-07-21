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
 * The extended model interface for the LoopTopicAssignment service. Represents a row in the &quot;LoopTopicAssignment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopTopicAssignmentModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.loop.model.impl.LoopTopicAssignmentImpl"
)
@ProviderType
public interface LoopTopicAssignment
	extends LoopTopicAssignmentModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.model.impl.LoopTopicAssignmentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopTopicAssignment, Long>
		LOOP_TOPIC_ASSIGNMENT_ID_ACCESSOR =
			new Accessor<LoopTopicAssignment, Long>() {

				@Override
				public Long get(LoopTopicAssignment loopTopicAssignment) {
					return loopTopicAssignment.getLoopTopicAssignmentId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<LoopTopicAssignment> getTypeClass() {
					return LoopTopicAssignment.class;
				}

			};

}