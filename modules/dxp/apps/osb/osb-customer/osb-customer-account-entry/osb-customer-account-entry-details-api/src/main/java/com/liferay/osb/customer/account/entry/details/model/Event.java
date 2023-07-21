/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Event service. Represents a row in the &quot;OSBCustomer_Event&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see EventModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.account.entry.details.model.impl.EventImpl"
)
@ProviderType
public interface Event extends EventModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.account.entry.details.model.impl.EventImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Event, Long> EVENT_ID_ACCESSOR =
		new Accessor<Event, Long>() {

			@Override
			public Long get(Event event) {
				return event.getEventId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Event> getTypeClass() {
				return Event.class;
			}

		};

}