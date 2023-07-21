/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the UserThread service. Represents a row in the &quot;PM_UserThread&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.social.privatemessaging.model.impl.UserThreadImpl"
)
@ProviderType
public interface UserThread extends PersistedModel, UserThreadModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.social.privatemessaging.model.impl.UserThreadImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<UserThread, Long> USER_THREAD_ID_ACCESSOR =
		new Accessor<UserThread, Long>() {

			@Override
			public Long get(UserThread userThread) {
				return userThread.getUserThreadId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<UserThread> getTypeClass() {
				return UserThread.class;
			}

		};

}