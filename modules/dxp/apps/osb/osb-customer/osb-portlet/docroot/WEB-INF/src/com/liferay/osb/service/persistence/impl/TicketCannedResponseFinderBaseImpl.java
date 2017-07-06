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

import com.liferay.osb.model.TicketCannedResponse;
import com.liferay.osb.service.persistence.TicketCannedResponsePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TicketCannedResponseFinderBaseImpl extends BasePersistenceImpl<TicketCannedResponse> {
	public TicketCannedResponseFinderBaseImpl() {
		setModelClass(TicketCannedResponse.class);
	}

	/**
	 * Returns the ticket canned response persistence.
	 *
	 * @return the ticket canned response persistence
	 */
	public TicketCannedResponsePersistence getTicketCannedResponsePersistence() {
		return ticketCannedResponsePersistence;
	}

	/**
	 * Sets the ticket canned response persistence.
	 *
	 * @param ticketCannedResponsePersistence the ticket canned response persistence
	 */
	public void setTicketCannedResponsePersistence(
		TicketCannedResponsePersistence ticketCannedResponsePersistence) {
		this.ticketCannedResponsePersistence = ticketCannedResponsePersistence;
	}

	@BeanReference(type = TicketCannedResponsePersistence.class)
	protected TicketCannedResponsePersistence ticketCannedResponsePersistence;
}