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
 * The extended model interface for the TicketWorker service. Represents a row in the &quot;OSB_TicketWorker&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TicketWorkerModel
 * @see com.liferay.osb.model.impl.TicketWorkerImpl
 * @see com.liferay.osb.model.impl.TicketWorkerModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.TicketWorkerImpl")
@ProviderType
public interface TicketWorker extends TicketWorkerModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.TicketWorkerImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TicketWorker, Long> TICKET_WORKER_ID_ACCESSOR = new Accessor<TicketWorker, Long>() {
			@Override
			public Long get(TicketWorker ticketWorker) {
				return ticketWorker.getTicketWorkerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TicketWorker> getTypeClass() {
				return TicketWorker.class;
			}
		};
}