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

package com.liferay.osb.service.persistence.impl;

import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.persistence.TicketEntryPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TicketEntryFinderBaseImpl extends BasePersistenceImpl<TicketEntry> {
	/**
	 * Returns the ticket entry persistence.
	 *
	 * @return the ticket entry persistence
	 */
	public TicketEntryPersistence getTicketEntryPersistence() {
		return ticketEntryPersistence;
	}

	/**
	 * Sets the ticket entry persistence.
	 *
	 * @param ticketEntryPersistence the ticket entry persistence
	 */
	public void setTicketEntryPersistence(
		TicketEntryPersistence ticketEntryPersistence) {
		this.ticketEntryPersistence = ticketEntryPersistence;
	}

	@BeanReference(type = TicketEntryPersistence.class)
	protected TicketEntryPersistence ticketEntryPersistence;
}