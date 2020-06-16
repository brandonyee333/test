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
 * The extended model interface for the LoopUserNotificationEvent service. Represents a row in the &quot;LoopUserNotificationEvent&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationEventModel
 * @see com.liferay.osb.loop.model.impl.LoopUserNotificationEventImpl
 * @see com.liferay.osb.loop.model.impl.LoopUserNotificationEventModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopUserNotificationEventImpl")
@ProviderType
public interface LoopUserNotificationEvent
	extends LoopUserNotificationEventModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.model.impl.LoopUserNotificationEventImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopUserNotificationEvent, Long> LOOP_USER_NOTIFICATION_EVENT_ID_ACCESSOR =
		new Accessor<LoopUserNotificationEvent, Long>() {
			@Override
			public Long get(LoopUserNotificationEvent loopUserNotificationEvent) {
				return loopUserNotificationEvent.getLoopUserNotificationEventId();
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