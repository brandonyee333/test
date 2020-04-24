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
 * The extended model interface for the LoopUserNotificationSubscription service. Represents a row in the &quot;LoopUserNotificationSubscription&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationSubscriptionModel
 * @see com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionImpl
 * @see com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionImpl")
@ProviderType
public interface LoopUserNotificationSubscription
	extends LoopUserNotificationSubscriptionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<LoopUserNotificationSubscription, Long> LOOP_USER_NOTIFICATION_SUBSCRIPTION_ID_ACCESSOR =
		new Accessor<LoopUserNotificationSubscription, Long>() {
			@Override
			public Long get(
				LoopUserNotificationSubscription loopUserNotificationSubscription) {
				return loopUserNotificationSubscription.getLoopUserNotificationSubscriptionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<LoopUserNotificationSubscription> getTypeClass() {
				return LoopUserNotificationSubscription.class;
			}
		};
}