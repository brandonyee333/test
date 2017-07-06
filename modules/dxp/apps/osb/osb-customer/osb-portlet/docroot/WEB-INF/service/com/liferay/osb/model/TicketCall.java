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

package com.liferay.osb.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the TicketCall service. Represents a row in the &quot;OSB_TicketCall&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TicketCallModel
 * @see com.liferay.osb.model.impl.TicketCallImpl
 * @see com.liferay.osb.model.impl.TicketCallModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.TicketCallImpl")
@ProviderType
public interface TicketCall extends TicketCallModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.TicketCallImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TicketCall, Long> TICKET_CALL_ID_ACCESSOR = new Accessor<TicketCall, Long>() {
			@Override
			public Long get(TicketCall ticketCall) {
				return ticketCall.getTicketCallId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TicketCall> getTypeClass() {
				return TicketCall.class;
			}
		};

	public java.lang.String getCallLengthLabel();

	public java.lang.String getTypeLabel();
}