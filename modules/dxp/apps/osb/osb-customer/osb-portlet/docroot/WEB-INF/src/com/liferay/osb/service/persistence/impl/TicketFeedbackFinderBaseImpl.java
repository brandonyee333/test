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

import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.service.persistence.TicketFeedbackPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TicketFeedbackFinderBaseImpl extends BasePersistenceImpl<TicketFeedback> {
	/**
	 * Returns the ticket feedback persistence.
	 *
	 * @return the ticket feedback persistence
	 */
	public TicketFeedbackPersistence getTicketFeedbackPersistence() {
		return ticketFeedbackPersistence;
	}

	/**
	 * Sets the ticket feedback persistence.
	 *
	 * @param ticketFeedbackPersistence the ticket feedback persistence
	 */
	public void setTicketFeedbackPersistence(
		TicketFeedbackPersistence ticketFeedbackPersistence) {
		this.ticketFeedbackPersistence = ticketFeedbackPersistence;
	}

	@BeanReference(type = TicketFeedbackPersistence.class)
	protected TicketFeedbackPersistence ticketFeedbackPersistence;
}