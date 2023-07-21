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
 * The extended model interface for the LoopUserNotificationEvent service. Represents a row in the &quot;LoopUserNotificationEvent&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationEventModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.loop.model.impl.LoopUserNotificationEventImpl"
)
@ProviderType
public interface LoopUserNotificationEvent
	extends LoopUserNotificationEventModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.loop.model.impl.LoopUserNotificationEventImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopUserNotificationEvent, Long>
		LOOP_USER_NOTIFICATION_EVENT_ID_ACCESSOR =
			new Accessor<LoopUserNotificationEvent, Long>() {

				@Override
				public Long get(
					LoopUserNotificationEvent loopUserNotificationEvent) {

					return loopUserNotificationEvent.
						getLoopUserNotificationEventId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<LoopUserNotificationEvent> getTypeClass() {
					return LoopUserNotificationEvent.class;
				}

			};

}