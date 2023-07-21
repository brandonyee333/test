/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the TicketAttachment service. Represents a row in the &quot;OSBCustomer_TicketAttachment&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.ticket.model.impl.TicketAttachmentImpl"
)
@ProviderType
public interface TicketAttachment
	extends PersistedModel, TicketAttachmentModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.ticket.model.impl.TicketAttachmentImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<TicketAttachment, Long>
		TICKET_ATTACHMENT_ID_ACCESSOR = new Accessor<TicketAttachment, Long>() {

			@Override
			public Long get(TicketAttachment ticketAttachment) {
				return ticketAttachment.getTicketAttachmentId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<TicketAttachment> getTypeClass() {
				return TicketAttachment.class;
			}

		};

	public String getFilePath();

}