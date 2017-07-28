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
 * The extended model interface for the TicketFeedback service. Represents a row in the &quot;OSB_TicketFeedback&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TicketFeedbackModel
 * @see com.liferay.osb.model.impl.TicketFeedbackImpl
 * @see com.liferay.osb.model.impl.TicketFeedbackModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.model.impl.TicketFeedbackImpl")
@ProviderType
public interface TicketFeedback extends TicketFeedbackModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.model.impl.TicketFeedbackImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TicketFeedback, Long> TICKET_FEEDBACK_ID_ACCESSOR =
		new Accessor<TicketFeedback, Long>() {
			@Override
			public Long get(TicketFeedback ticketFeedback) {
				return ticketFeedback.getTicketFeedbackId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TicketFeedback> getTypeClass() {
				return TicketFeedback.class;
			}
		};

	public java.lang.String getAnswer1Label();

	public java.lang.String getAnswer2Label();

	public java.lang.String getAnswer3Label();

	public double getAverageRating();

	public java.lang.String getAverageRatingDisplay();

	public java.lang.String getRating1Label();

	public java.lang.String getRating2Label();

	public java.lang.String getRating3Label();

	public java.lang.String getRating4Label();

	public java.lang.String getSatisfiedLabel();

	public TicketEntry getTicketEntry()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isClosed();
}