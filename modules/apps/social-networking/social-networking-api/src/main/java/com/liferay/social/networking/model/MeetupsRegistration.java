/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the MeetupsRegistration service. Represents a row in the &quot;SN_MeetupsRegistration&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsRegistrationModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.social.networking.model.impl.MeetupsRegistrationImpl"
)
@ProviderType
public interface MeetupsRegistration
	extends MeetupsRegistrationModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.social.networking.model.impl.MeetupsRegistrationImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MeetupsRegistration, Long>
		MEETUPS_REGISTRATION_ID_ACCESSOR =
			new Accessor<MeetupsRegistration, Long>() {

				@Override
				public Long get(MeetupsRegistration meetupsRegistration) {
					return meetupsRegistration.getMeetupsRegistrationId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<MeetupsRegistration> getTypeClass() {
					return MeetupsRegistration.class;
				}

			};

}